package ru.pimalex1978.concurrent.locks.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Синхронизаторы – вспомогательные утилиты для синхронизации потоков,
 * которые дают возможность разработчику регулировать и/или ограничивать
 * работу потоков и предоставляют более высокий уровень абстракции, чем
 * основные примитивы языка (мониторы).
 * <p>
 * Рассмотрим следующий пример. Существует парковка, которая одновременно
 * может вмещать не более 5 автомобилей. Если парковка заполнена полностью,
 * то вновь прибывший автомобиль должен подождать пока не освободится хотя
 * бы одно место. После этого он сможет припарковаться.
 * Семафор отлично подходит для решения такой задачи:
 * он не дает автомобилю (потоку) припарковаться (зайти в заданный блок
 * кода и воспользоваться общим ресурсом) если мест на парковке нет
 * (счётчик равен 0).
 * Стоит отметить, что класс Semaphore поддерживает захват и освобождение
 * более чем одного разрешения за раз, но в данном задаче это не нужно.
 * Источник:
 * https://habr.com/ru/post/277669/
 */
public class Parking {
    //Парковочное место занято - true, свободно - false
    private static final boolean[] PARKING_PLACES = new boolean[5];

    //Устанавливаем флаг "fair - справедливый", в таком случае метод
    //aсquire() будет раздавать разрешения в порядке очереди
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 7; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }

    //класс автомобиля
    public static class Car implements Runnable {
        private int carNumber;

        Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            System.out.printf("Автомобиль №%d подъехал к парковке.\n", carNumber);
            try {
                //acquire() запрашивает доступ к следующему за вызовом этого метода блоку кода,
                //если доступ не разрешен, поток вызвавший этот метод блокируется до тех пор,
                //пока семафор не разрешит доступ
                SEMAPHORE.acquire();

                int parkingNumber = -1;

                //Ищем свободное место и паркуемся
                synchronized (PARKING_PLACES) {
                    for (int i = 0; i < 5; i++)
                        if (!PARKING_PLACES[i]) {      //Если место свободно
                            PARKING_PLACES[i] = true;  //занимаем его
                            parkingNumber = i;         //Наличие свободного места, гарантирует семафор
                            System.out.printf("Автомобиль №%d припарковался на месте %d.\n", carNumber, i);
                            break;
                        }
                }

                Thread.sleep(5000);       //Уходим за покупками, к примеру

                synchronized (PARKING_PLACES) {
                    PARKING_PLACES[parkingNumber] = false; //Освобождаем место
                }

                //release(), напротив, освобождает ресурс
                SEMAPHORE.release();
                System.out.printf("Автомобиль №%d покинул парковку.\n", carNumber);
            } catch (InterruptedException e) {
            }
        }
    }
}
