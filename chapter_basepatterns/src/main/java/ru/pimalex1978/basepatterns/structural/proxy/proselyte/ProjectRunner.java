package ru.pimalex1978.basepatterns.structural.proxy.proselyte;

/**
 * Класс Клиент.
 */

public class ProjectRunner {
    public static void main(String[] args) {
//        Project project = new RealProject("https://www.github.com/proselytear/realProject");
        Project project = new ProxyProject("https://www.github.com/proselytear/realProject");

        /*Запускаем проет.*/
        project.run();
    }
}

//Результат работы:
//
//Loading project from https://www.github.com/proselytear/realProject...
//Running project https://www.github.com/proselytear/realProject...
//
//Только при использовании RealProject сразу идет скачивание проекта
//Loading project from https://www.github.com/proselytear/realProject... а
//потом при вызове run() происходит запуск проекта
//Running project https://www.github.com/proselytear/realProject...
//
//А если мы используем ProxyProject, то при сначала идет просто присваивание
//ссылки при вызове new ProxyProject, а только лишь при вызове run()
//произойдет и скачивание проекта
//Loading project from https://www.github.com/proselytear/realProject...
//и запуск проекта
//Running project https://www.github.com/proselytear/realProject...
