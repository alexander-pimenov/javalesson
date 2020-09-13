package ru.pimalex1978.basepatterns.structural.proxy.proselyte;

/**
 * Предположим, что у нас есть проект на GitHub.
 * Реализация интерфейса Project.
 * Создавая конструктор этого класса мы сразу будем загружать проет
 * с Гитхаба.
 */

public class RealProject implements Project {
    /**
     * Ссылка на проект, по которой можно скачать проект.
     */
    private String url;

    /**
     * В конструкторе мы присваиваем ссылку и
     * запускаем метод load().
     * Т.е. при создании объекта RealProject будет
     * происходить скачивание проекта.
     *
     * @param url ссылка на прект.
     */
    public RealProject(String url) {
        this.url = url;
        load();
    }

    /**
     * Возможность скачать наш проект по ссылке.
     */
    public void load() {
        System.out.println("Loading project from " + url + "...");
    }

    /**
     * При запуске метода будет происходить запуск проекта.
     */
    @Override
    public void run() {
        System.out.println("Running project " + url + "...");
    }
}
