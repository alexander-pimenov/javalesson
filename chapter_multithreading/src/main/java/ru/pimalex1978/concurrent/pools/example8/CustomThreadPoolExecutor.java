package ru.pimalex1978.concurrent.pools.example8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * https://howtodoinjava.com/java/multi-threading/how-to-use-blockingqueue-and-threadpoolexecutor-in-java/
 *
 * RunState обеспечивает основной контроль жизненного цикла, принимая значения:
 *
 * RUNNING (ВЫПОЛНЕНИЕ): принятие новых задач и обработка задач в очереди.
 * SHUTDOWN: не принимать новые задачи, а обрабатывать задачи из очереди.
 * STOP (СТОП): не принимать новые задачи, не обрабатывать задачи в очереди и прерывать выполняемые задачи.
 * TIDYING: все задачи завершены, workerCount равен нулю, поток, переходящий в состояние TIDYING, будет запускать метод ловушки terminated().
 * TERMINATED: terminated() завершено
 *
 * int corePoolSize - Размер основного пула - это минимальное количество рабочих workers,
 * которые должны оставаться в живых (и не допускать тайм-аута и т. Д.), Если не
 * установлен параметр allowCoreThreadTimeOut, и в этом случае минимум равен нулю.
 *
 * Поскольку счетчик рабочих worker фактически хранится в битах COUNT_BITS,
 * эффективное ограничение составляет {@code corePoolSize & COUNT_MASK}.
 *
 * */
public class CustomThreadPoolExecutor extends ThreadPoolExecutor {

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                    long keepAliveTime, TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println("Perform beforeExecute() logic");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t != null) {
            System.out.println("Perform exception handler logic");
        }
        System.out.println("Perform afterExecute() logic");
    }
}