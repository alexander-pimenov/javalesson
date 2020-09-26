package ru.pimalex1978.srp.example2;

public class DataManagerImpl implements IDataManager {
    @Override
    public void send(String message) {
        System.out.println("data sent successfully");

    }

    @Override
    public int receive() {
        System.out.println("data received successfully");
        return 0;
    }
}
