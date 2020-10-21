package ru.pimalex1978.shamRail.io;

public class ConsoleOutput implements Output {
    @Override
    public void write(String text) {
        System.out.print(text);
    }
}
