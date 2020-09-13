package ru.pimalex1978.basepatterns.structural.proxy.proselyte;

/**
 * Прокси ProxyProject также будет реализацией интерфейса Project
 * и будет иметь метод run().
 * Будем загружать наш проет не при создании проекта, а
 * только при запуске проекта. Т.е. така ленивая загрузка.
 * в отличие от RealProject, где при создании проекта (при вызове
 * конструктора) будет сразу загружаться проект.
 */

public class ProxyProject implements Project {
    /**
     * Ссылка на проект, по которой можно скачать проект.
     */
    private String url;

    /**
     * Ссылка на реальный проект.
     */
    private RealProject realProject;

    /**
     * При создании объекта ProxyProject будет
     * присвоена только ссылка на проект,
     * но никакого скачивания с Гитхаба происходить не
     * будет.
     *
     * @param url ссылка на проект.
     */
    public ProxyProject(String url) {
        this.url = url;
    }

    /**
     * При вызове метода будет происходить скачивание
     * проекта с Гитхаба и запуск проекта.
     */
    @Override
    public void run() {
        if (realProject == null) {
            realProject = new RealProject(url);
        }
        realProject.run();
    }
}
