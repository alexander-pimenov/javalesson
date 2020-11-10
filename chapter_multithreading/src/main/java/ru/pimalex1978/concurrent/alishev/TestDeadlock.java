package ru.pimalex1978.concurrent.alishev;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * В этом классе TestDeadlock рассмотрим пример явления Deadlock.
 * В примере переводим деньги с одного аккаунта на другой.
 * Начальная сумма на счету каждого аккаунта 10_000.
 * Баланс аккаунта 1 равен 10_000 и 2 аккаунта тоже 10_000.
 * Т.е. общий баланс двух аккаунтов 20_000.
 * Если не будем делать синхронизацию потоков, то получим состояние гонки.
 * И сумма общего баланса не будет равна 20_000.
 * Чтобы этого не происходило, нужно сделать так чтоб аккаунт 1 и аккаунт 2
 * в один момент времени мог изменяться только одним потоком, т.е. один
 * поток должен завладеть и аккаунтом 1 и аккаунтом 2.
 * Это  чтоб два потока не изменили одни и теже данные.
 * Это можно сделать таким блоком кода:
 * ###################################################
 * ....synchronized (account1){ //монитор объекта account1
 * ........synchronized (account2){ //монитор объекта account1
 * ............//здесь поток безопасно будет изменять
 * ............//данные двух аккаунтов.
 * ........}
 * ....}
 * ###################################################
 * Т.е. поток синхронизируется на аккаунте 1 и далее на аккаунте 2,
 * и только завладев двумя аккунтами выполняет перевод денег, т.е.
 * метод transfer(....)
 * Но эта вложенность не приветствуется в программировании, т.к.
 * увеличивает сложность восприятия кода.
 * Поэтому будем использовать ReentrantLock. Он поможет избежать вложенности.
 * lock1 будет представлять лок для аккаунта 1, и lock2 это будет
 * лок для аккаунта 2.
 * Имеем следующий код:
 * ###################################################
 * //Поля локов
 * Lock lock1 = new ReentrantLock();
 * Lock lock2 = new ReentrantLock();
 * method(){
 * ....lock1.lock();
 * ....lock2.lock();
 * ....try{
 * ........//здесь поток безопасно будет изменять
 * ........//данные двух аккаунтов.
 * ....}finally {
 * ........lock1.unlock(); //отпускаем лок 1-го счета
 * ........lock2.unlock(); //отпускаем лок 2-го счета
 * ....}
 * }
 * ###################################################
 * Метод unlock() нужно всегда вызывать в блоке finally, чтобы
 * гарантировано освобождать наши локи.
 * ----------------------------------------------------
 * Если очередность вызова метода lock() в методе firstThread
 * и secondThread будет не одинакова:
 * firstThread(){
 * lock1.lock()
 * lock2.lock()
 * }
 * а для
 * secondThread(){
 * lock2.lock()
 * lock1.lock()
 * }
 * то мы попадем в явление Deadlock. Программа зависнет. Т.к. первый поток
 * завладеет lock1, а второй lock2, потом первый поток захочет взять
 * lock2 и не сможет, т.к. он уже у второго потока, аналогично второй
 * поток захочет взять lock1 и тоже не сможет, т.к. он уже у первого
 * потока. Так они и будут ждать друг друга. Здесь мы
 * взяли локи в разных порядках.
 * ----------------------------------------------------
 * Попасть в Deadlock можно так же используя блоки synchronized(...)
 * аналогично с использованием класса ReentrantLock.
 * Для ReentrantLock можно написать метод следящий за взятием локов
 * (как в коде ниже), а для блоков synchronized(...) ничего сделать
 * не получиться, кроме как следить за правильной очередностью
 * взятия мониторов (те объекты, на которых синхронизируемся должны
 * идти в одном порядке).
 * ----------------------------------------------------
 * Способы избежать Deadlock:
 * 1. не забирать локи в разных порядках.
 * 2. использовать метод tryLock() и конструкцию показанную в коде ниже
 */
public class TestDeadlock {
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
            }
        });

        //стартуем потоки
        thread1.start();
        thread2.start();

        //чтобы в методе main дождаться выполнения потоков
        thread1.join();
        thread2.join();

        //вызываем финальный метод после выполнения работы в потоках
        runner.finished();

    }
}

class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    /*Создаем поля Lock для синхронизации потоков*/
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    /**
     * Метод по забиранию локов (перенесли в отдельный метод).
     * В нём реализована проверка забран лок или нет, в этом
     * помогает метод .tryLock(), возвращающий true or false в
     * зависимости от результата.
     * Выйти из метода можно только при успешном забирании
     * двух локов.
     */
    private void takeLocks(Lock lock1, Lock lock2) {

        //заведем булевы переменные
        boolean firstLockTaken = false; //первый лок забрался
        boolean secondLockTaken = false; //второй лок забрался

        //бесконечный цикл, выход из него только при успешном взятии двух локов
        while (true) {
            try {
                firstLockTaken = lock1.tryLock(); //tryLock() вернет true or false
                secondLockTaken = lock2.tryLock(); //tryLock() вернет true or false
            } finally {
                /*если локи взяты, т.е. оба значения true, то выходим
                 * из метода*/
                if (firstLockTaken && secondLockTaken) {
                    return;
                }
                /*если прошли дальше, то значит какой то лок не смогли
                 * забрать, значит отпустим тот лок который забрали, чтоб
                 * другой поток его мог использовать */
                if (firstLockTaken) {
                    lock1.unlock();
                }
                /*если прошли дальше, то значит какой то лок не смогли
                 * забрать, значит отпустим тот лок который забрали, чтоб
                 * другой поток его мог использовать */
                if (secondLockTaken) {
                    lock2.unlock();
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Вариант 3

    /**
     * Будем переводить случайное количество денег
     * (от 0 до 100) с аккаунта 1 на аккаунт 2.
     * Синхронизация потоков с помощью класса ReentrantLock.
     * Забираем локи с помощью метода takeLocks(), в нем следить за
     * очередностью локов не обязательно, это будет сделано в методе.
     */
    public void firstThread() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            takeLocks(lock1, lock2);
            try {
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock1.unlock(); //отпускаем лок 1-го счета
                lock2.unlock(); //отпускаем лок 2-го счета
            }
        }
    }

//    Вариант 2
//    /**
//     * Будем переводить случайное количество денег
//     * (от 0 до 100) с аккаунта 1 на аккаунт 2.
//     * Синхронизация потоков с помощью класса ReentrantLock.
//     */
//    public void firstThread() {
//        Random random = new Random();
//        for (int i = 0; i < 10000; i++) {
//            lock1.lock(); //получаем лок 1-го счета
//            первый поток здесь
//            lock2.lock(); //получаем лок 2-го счета
//            try {
//                Account.transfer(account1, account2, random.nextInt(100));
//            } finally {
//                lock1.unlock(); //отпускаем лок 1-го счета
//                lock2.unlock(); //отпускаем лок 2-го счета
//            }
//        }
//    }

//    Вариант 1
//    /**
//     * Будем переводить случайное количество денег
//     * (от 0 до 100) с аккаунта 1 на аккаунт 2.
//     * Синхронизация потоков с помощью двух вложенных
//     * блоков synchronized(...).
//     */
//    public void firstThread() {
//        Random random = new Random();
//        for (int i = 0; i < 10000; i++) {
//            synchronized (account1) { //монитор объекта account1
//                synchronized (account2) { //монитор объекта account2
//                    Account.transfer(account1, account2, random.nextInt(100));
//                }
//            }
//        }
//    }

    //Вариант 3

    /**
     * Будем переводить случайное количество денег
     * (от 0 до 100) с аккаунта 2 на аккаунт 1.
     * Синхронизация потоков с помощью класса ReentrantLock.
     * Забираем локи с помощью метода takeLocks(), в нем следить за
     * очередностью локов не обязательно, это будет сделано в методе.
     */
    public void secondThread() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            takeLocks(lock2, lock1);
            try {
                Account.transfer(account2, account1, random.nextInt(100));
            } finally {
                lock1.unlock(); //отпускаем лок 1-го счета
                lock2.unlock(); //отпускаем лок 2-го счета
            }
        }
    }

//    Вариант 2
//    /**
//     * Будем переводить случайное количество денег
//     * (от 0 до 100) с аккаунта 2 на аккаунт 1.
//     * Синхронизация потоков с помощью класса ReentrantLock.
//     */
//    public void secondThread() {
//        Random random = new Random();
//        for (int i = 0; i < 10000; i++) {
//            lock1.lock(); //получаем лок 1-го счета
//            второй поток здесь
//            lock2.lock(); //получаем лок 2-го счета
//            try {
//                Account.transfer(account2, account1, random.nextInt(100));
//            } finally {
//                lock1.unlock(); //отпускаем лок 1-го счета
//                lock2.unlock(); //отпускаем лок 2-го счета
//            }
//        }
//    }

//    Вариант 1
//    /**
//     * Будем переводить случайное количество денег
//     * (от 0 до 100) с аккаунта 2 на аккаунт 1.
//     * Синхронизация потоков с помощью двух вложенных
//     * блоков synchronized(...).
//     */
//    public void secondThread() {
//        Random random = new Random();
//        for (int i = 0; i < 10000; i++) {
//            synchronized (account1) { //монитор объекта account1
//                synchronized (account2) { //монитор объекта account2
//                    Account.transfer(account2, account1, random.nextInt(100));
//                }
//            }
//        }
//    }

    /**
     * Будем выводить баланс аккаунтов.
     */
    public void finished() {
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
        System.out.println("Total balance " + (account1.getBalance() + account2.getBalance()));
    }
}

/**
 * Класс для эмуляции реальный случай.
 * Эмулирование работы с банковскими счетами.
 */
class Account {
    //начальный баланс при сздании
    private int balance = 10000;

    /**
     * Метод для пополнения счета на какое то
     * количество денег.
     *
     * @param amount сумма пополнения.
     */
    public void deposit(int amount) {
        balance = balance + amount;
    }

    /**
     * Метод для списания денег с баланса.
     *
     * @param amount сумма для списания.
     */
    public void withdraw(int amount) {
        balance = balance - amount;
    }

    /**
     * Метод для возвращения суммы баланса.
     *
     * @return сумма баланса.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Метод для перевода денег с одного аккаунта
     * на другой.
     *
     * @param acc1   первый аккаунт
     * @param acc2   второй аккаунт
     * @param amount сумма перевода
     */
    public static void transfer(Account acc1, Account acc2, int amount) {
        acc1.withdraw(amount); //деньги снимаем
        acc2.deposit(amount); //деньги помещаем
    }
}