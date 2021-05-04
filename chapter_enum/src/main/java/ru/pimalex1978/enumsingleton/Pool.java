package ru.pimalex1978.enumsingleton;

import java.util.concurrent.BlockingQueue;

public enum Pool {
    INSTANCE;

    //ПОЛЯ И МЕТОДЫ
    private BlockingQueue<Plane> planes;

    Pool() {
        //more code
        //инициализация полей
    }
    //more method
}
