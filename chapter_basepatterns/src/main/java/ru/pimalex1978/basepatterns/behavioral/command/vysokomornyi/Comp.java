package ru.pimalex1978.basepatterns.behavioral.command.vysokomornyi;

/**
 * Работа с компьютером.
 * Receiver - получатель команд.
 * О классе User не знает ничего.
 */
public class Comp {

    //имеет команды работы с компьтером
    void start() {
        System.out.println("Start...");
    }

    void stop() {
        System.out.println("Stop...");
    }

    void reset() {
        System.out.println("Reset...");
    }
}
