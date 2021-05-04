package ru.pimalex1978.lambdaepam;

/**
 * Клиентский код.
 * Предположим, что у нас некий конвеер, и по кнопке из
 * интерфейса поступают к нам таски.
 */
public class Main {
    public static void main(String[] args) {
        //создаем таски. они будет запускаться в отдельном потоке.
        BusinessTaskUpdateCustomerAge task1 = new BusinessTaskUpdateCustomerAge();
        BusinessTaskUpdateCustomerAge task2 = new BusinessTaskUpdateCustomerAge();

        //создадим объект Runnable для создания в нем выполнения BusinessTask
        //здесь происходит вызов бизнес логики
        Runnable task = () -> {
            //создадим таску BusinessTaskUpdateCustomerAge, вызовем ее метод
            new BusinessTaskUpdateCustomerAge().updateStateInDB();
            try {
                Thread.sleep(1000);
                System.out.println("Massive updates are gonna be [" + Thread.currentThread().getName() + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        //1-й вариант
        executeTask(() -> {
            //создадим таску BusinessTaskUpdateCustomerAge, вызовем ее метод
            new BusinessTaskUpdateCustomerAge().updateStateInDB();
            try {
                Thread.sleep(1000);
                System.out.println("Massive updates are gonna be [" + Thread.currentThread().getName() + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //2-й вариант
        executeTask(task);
    }

    /**
     * Метод выполняющий таски.
     * Отвечает за старт треда.
     * В параметр передаем объект типа Runnable с определенной логикой.
     */
    private static void executeTask(Runnable threadLogic) {
        //заводим новый тред, передаем туда логику
        new Thread(threadLogic).start();

    }
}
