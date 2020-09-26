package ru.pimalex1978.srp.example2;

/**
 * Класс клиента.
 * В жтом приложении обязанности разделенны и это
 * приложение написано с соблюдением SRP.
 */
public class PhoneClient {
    public static void main(String[] args) {
        //Создаем объект Phone
        Phone phone = new Phone(new ConnectionManagerImpl(),
                new DataManagerImpl());
        phone.dial("0123456789");
        phone.send("message text");
        phone.receive();
        phone.disconnect();

    }
}
