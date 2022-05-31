package ru.pimalex1978.basepatterns.creational.singleton;

public class ProgramRunner {
    public static void main(String[] args) {
        ProgramLogger.getProgramLogger().addLogInfo("First log...");
        ProgramLogger.getProgramLogger().addLogInfo("Second log...");
        ProgramLogger.getProgramLogger().addLogInfo("Third log...");

        ProgramLogger.getProgramLogger().showLogFile();

        //так сможем увидеть, что ссылки ссылаются на один и тот же объект в памяти
        ProgramLogger programLogger1 = ProgramLogger.getProgramLogger();
        ProgramLogger programLogger2 = ProgramLogger.getProgramLogger();
        System.out.println(programLogger2==programLogger1);
    }
}
