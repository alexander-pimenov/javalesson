package ru.pimalex1978.srp.example2;

/**
 * Класс нашего телефона.
 */
public class Phone implements IConnectionManager, IDataManager {
    //Вводим поля работы со звонками и сообщениями.
    private IConnectionManager connection;
    private IDataManager dataChannel;

    public Phone(IConnectionManager connection, IDataManager dataChannel) {
        this.connection = connection;
        this.dataChannel = dataChannel;
    }

    @Override
    public void dial(String phoneNumber) {
        connection.dial(phoneNumber);
    }

    @Override
    public void disconnect() {
        connection.disconnect();
    }

    @Override
    public void send(String message) {
        dataChannel.send(message);
    }

    @Override
    public int receive() {
        return dataChannel.receive();
    }
}
