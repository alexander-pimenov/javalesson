package ru.pimalex1978.concurrent.locks.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static ru.pimalex1978.concurrent.ColorScheme.BLUE;
import static ru.pimalex1978.concurrent.ColorScheme.RED;

/**
 * reentrant lock - возвратная блокировка.
 * Этот класс еще одна реализация Producer-Consumer паттерна.
 * Не всегда удобно и полезно применять synchronized блок, т.к.
 * есть ряд ограничений:
 * - synchronized блок ограничен одним методом, нельзя расширить за пределы блока.
 * - в случае synchronized не известно какой поток станет следующим в получении лока,
 * и в этом случае некоторые потоки могут "голодать".
 * - можно попасть в дедлок.
 * <p>
 * В конструкторе класса new ReentrantLock(true) есть параметр fair. Он позволяет
 * нам гарантировать, что поток который ожидает дольше всего получение лока, будет
 * допущен к локу, т.е. не будет "голодания" потоков.
 *
 * <p>
 * Метод unlock() для монитора monitor лучше всегда ставить в блоке finally. Для того чтобы
 * даже если выбросит Exception мы попали в блок finally и вызвали unlock(). Т.обр.
 * гарантируем, что не будет дедлока!!!
 * <p>
 * В этом классе применяется класс Condition.
 * Класс Condition - Переменные условия - это механизм, который позволяет
 * вам проверить выполнение определенного условия, прежде чем разрешить
 * выполнение вашего метода. Condition заменяет использование методов монитора объектов.
 * Conditions (Условия) (также известные, как очереди условий "condition queues" или переменные условий
 * "condition variables") предоставляют возможность одному потоку приостанавливать выполнение
 * (to «wait») до тех пор, пока другой поток не получит уведомление о том, что какое-то условие состояния
 * теперь может быть истинным. Поскольку доступ к этой общей информации о состоянии происходит в разных
 * потоках, он должен быть защищен, поэтому с условием связана блокировка той или иной формы.
 * Ключевое свойство, обеспечивающее ожидание условия, заключается в том, что оно атомарно освобождает
 * связанную блокировку и приостанавливает текущий поток, как и Object.wait.
 * Метод signal() - аналог notify().
 * <p>
 * Объект класса Condition неразрывно связан с замком lock. Чтобы получить экземпляр Condition
 * для конкретного Lock экземпляра, используйте его newCondition() метод.
 * <p>
 * Источник:
 * https://www.udemy.com/course/java-simple2advanced/learn/lecture/11252416#overview
 */
public class SynchronizedBuffer {

    //Объявим переменную лока, помогает установить взаимодействие
    //между двумя потоками. Используется как wait() и notify().
    private static final Lock monitor = new ReentrantLock(true);

    /* Класс Condition - Переменные условия - это механизм, который позволяет
     * вам проверить выполнение определенного условия, прежде чем разрешить
     * выполнение вашего метода. Condition заменяет использование методов монитора объектов.
     * Здесь создаем наши Conditions*/
    private static final Condition canRead = monitor.newCondition();
    private static final Condition canWrite = monitor.newCondition();

    // Поле в котором будут пересохраняться переменные значения для наших потоков.
    private static int buffer = 0;

    // Поле для проверки пустоты буфера. Изначально он пуст, т.е. true.
    private static boolean isEmpty = true;

    public static void main(String[] args) {

        new Thread(SynchronizedBuffer::blockingWrite).start();
        new Thread(SynchronizedBuffer::blockRead).start();
    }

    /**
     * Метод для записи значения в буфер.
     */
    private static void blockingWrite() {
        //Записываем поочереди в буфер числа от 1 до 10
        for (int i = 0; i < 10; i++) {
            monitor.lock();
            try {
                //Когда буфер полный, т.е. в нем есть запись, то надо подождать.
                while (!isEmpty) {
                    System.out.println(RED + "Writer is trying to access a resource.");
                    System.out.println(RED + "Buffer is full.");
                    //Скажем Writer-у чтобы он подождал.
                    canWrite.await();
                }
                //увеличим буфер на 1, т.е. как бы записываем в буфер +1
                buffer++;
                isEmpty = false;
                System.out.println(RED + "Writer produced: " + buffer);
                //Просигналим Reader-у, что можно уже читать из буфера.
                canRead.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                monitor.unlock();
            }
        }
    }

    private static void blockRead() {
        //Прочитываем поочереди из буфера числа от 1 до 10
        for (int i = 0; i < 10; i++) {
            monitor.lock();
            try {
                //Пока буфер пустой, то вычитывать нечего. Ждем.
                while (isEmpty) {
                    System.out.println(BLUE + "Reader is trying to access a resource.");
                    System.out.println(BLUE + "Buffer is empty.");
                    //Скажем Reader-у подождать.
                    canRead.await();
                }
                //вычитываем значение из буфера
                int readValue = buffer;
                isEmpty = true;
                System.out.println(BLUE + "Reader reads from buffer: " + readValue);
                //Просигналим Writer-у что можно записывать в буфер.
                canWrite.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                monitor.unlock();
            }
        }
    }
}
