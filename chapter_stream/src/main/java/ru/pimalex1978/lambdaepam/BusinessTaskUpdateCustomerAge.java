package ru.pimalex1978.lambdaepam;

/**
 * Класс реализующий интерфейс и выполняющий задание.
 * Если нужно менять логику, то это делается тут.
 */
public class BusinessTaskUpdateCustomerAge implements BusinessTask {

    @Override
    public void updateStateInDB() {
        System.out.println("We are doing that [" + Thread.currentThread().getName() + "]");
    }
}
