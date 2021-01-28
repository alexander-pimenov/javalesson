package ru.pimalex1978.concurrent.pools.mythreadpool2;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * Класс SimpleThreadpoolThread, который представляет потоки в пуле.
 * Он действует как оболочка для всех запускаемых файлов в очереди.
 *
 * Класс, который отвечает за фактический опрос очереди и выполнение объектов Runnables.
 * SimpleThreadpoolThreadбазовый - это поток в нашем пуле потоков, который выполняет
 * опрос (изъятие объекта из очереди) и выполнение этого объекта Runnable.
 * Группа этих потоков запускается, и они образуют пул потоков.
 *
 * https://caffinc.github.io/2016/03/simple-threadpool/
 */

public class SimpleThreadpoolThread extends Thread {
    // Этот флаг используется для управления выполнением while цикла внутри run() метода.
    // Если execute флаг установлен false, а очередь пуста, поток останавливается.
    private AtomicBoolean execute;

    // Это очередь, в которой хранятся исполняемые файлы.
    private ConcurrentLinkedQueue<Runnable> runnables;

    public SimpleThreadpoolThread(String name, AtomicBoolean execute, ConcurrentLinkedQueue<Runnable> runnables) {
        super(name);
        this.execute = execute;
        this.runnables = runnables;
    }

    @Override
    public void run() {
        try {
            // Continue to execute when the execute flag is true, or when there are runnables in the queue
            // Продолжить выполнение, когда установлен флаг выполнения или когда в очереди есть исполняемые файлы
            while (execute.get() || !runnables.isEmpty()) {
                Runnable runnable;
                // Poll a runnable from the queue and execute it
                // Взять запускаемый объект из очереди и выполнить его
                while ((runnable = runnables.poll()) != null) {
                    runnable.run();
                }
                // Сон на случай, если в очереди не было запускаемых файлов. Это помогает избежать перегрузки процессора.
                // Thread.sleep(1) во внешнем цикле. Это сделано для того, чтобы поток не
                // вел себя как бесконечный цикл и не перегружал весь доступный ему ЦП.
                // Цикл входит в эту строку только тогда, когда очередь пуста, что не
                // должно происходить очень часто в хорошо оптимизированной системе.
                Thread.sleep(1);
            }
        } catch (RuntimeException | InterruptedException e) {
            throw new ThreadpoolException(e);
        }
    }
}
