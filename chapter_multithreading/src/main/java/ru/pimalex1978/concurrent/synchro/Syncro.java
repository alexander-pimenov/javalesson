package ru.pimalex1978.concurrent.synchro;

/*
 * При использовании слова synchronized потоки будут выполняться один за другим,
 * т.е. последовательно. Слово synchronized говорит, что код будет выполняться
 * в один момент времени только одним потоком! На время пока первый поток
 * выполняет код метода, второй остается заблоктрованым, и ожидает до тех пор
 * первый поток не закончит работу в методе.
 * МОНИТОР СИНХРОНИЗАЦИИ (lock) - это может быть любой объект.
 * Поток может войти внутрь synchronized блока только если осуществит захват
 * монитора синхронизации. До тех пор пока поток держит монитор синхронизации
 * никакой другой поток не может попасть внутрь этого блока. Поток дойдет
 * до слова synchronized и если монитор уже захвачен другим потоком, то
 * он будет ждать освобождения этого монитора. Когда монитор освобождается,
 * то один из ожидающих потоком может захватить монитор и выйти в блок
 * synchronized и начать выполнять код внутри его.
 * Если у нас есть несколько методов с указанием одного и того же lock объекта,
 * в нашем примере это this, то если первый поток захватил монитор и начал
 * выполнять код этого метода, то другие потоки не смогут войти даже в другие
 * методы синхронизованные на этом же локе. Конечно, если объекты локов разные,
 * то с каждым локом может работать одновременно разные потоки. По одному на
 * каждый лок.
 * Но тем не менее, нельзя всё подряд оборачивать в synchronized блоки, т.к.
 * тогда пропадет смысл многопоточного программирования.
 *
 */
public class Syncro {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    /*статическая переменная counter счетчик*/
    private static int counter;

    /*
     * В методе видим, что потоки выполняются один за другим, т.е. последовательно,
     * это благодаря synchronized. Один поток увеличит счетчик от 0 до 5.
     * Потом вторым потоком он сброситься в 0 и тоже увеличит счетчик от 0 до 5.
     * Без этого слова у потоков было бы состояние гонки. И счетчик будет увеличен
     * на непонятное число. Можно закомментировать synchronized и посмотреть на результат.
     *
     */
    public static void test1() {

        /*создается Runnable*/
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                /*для синхронизации будем работать в блоке synchronized*/
                synchronized (this) {
                    counter = 0; //обнуляется счетчик
                    /*5 раз счетчик увеличиваем на 1*/
                    for (int i = 0; i < 5; i++) {
                        counter = counter + 1;
                        /*печатаем имя потока, в котором выполняется код и значение счетчика*/
                        System.out.println(Thread.currentThread().getName() + ": " + counter);
                    }
                }
            }
        };

        /*Создаем два потока с объектом runnable*/
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * В методе видим, как работает потокобезопасный StringBuffer. Если хотим
     * получить не корректную работу с потоками, то можем заменить StringBuffer
     * на StringBuilder (не потокобезопасный). Если посмотреть на то как устроен
     * StringBuffer, то увидим что все етоды этого класса synchronized.
     */
    public static void test2() {
        /* Создаем объект StringBuffer. Он потоко безопасный. Все его методы имеют слово
         * synchronized. Только один поток в один момент времени может работать с
         * этим классом.
         * Если будем использовать StringBuilder, то есть возможность получить
         * не корректную работу метода или даже иксепшен, т.к. он не потоко безопасный. */
        StringBuffer stringBuffer = new StringBuffer();

        /*создается Runnable. В stringBuilder добавляем строку и имя потока.*/
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                stringBuffer
                        .append("long hello world long hello world long hello world long hello world long hello world long hello world long hello world from thread : ")
                        .append(Thread.currentThread().getName())
                        .append("\n");
            }
        };

        /*Создаем четыре потока с объектом runnable*/
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(stringBuffer);
    }
}
