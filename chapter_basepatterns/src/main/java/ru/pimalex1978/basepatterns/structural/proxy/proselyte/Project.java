package ru.pimalex1978.basepatterns.structural.proxy.proselyte;

/**
 * Интерфейс сервиса определяет общий интерфейс для сервиса и заместителя.
 * Благодаря этому, объект заместителя можно использовать там, где
 * ожидается объект сервиса.
 */
public interface Project {
    public void run();
}
