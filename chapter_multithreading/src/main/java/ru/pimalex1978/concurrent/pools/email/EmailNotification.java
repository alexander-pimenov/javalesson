package ru.pimalex1978.concurrent.pools.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * В этой программе реализовано задание.
 *
 * 1. Реализовать сервис для рассылки почты. Создайте класс EmailNotification (Уведомление по электронной почте).
 * 2. В классе будет метод emailTo(User user) - он должен через ExecutorService отправлять почту.
 *    Так же добавьте метод close() - он должен закрыть pool.
 *    То есть в классе EmailNotification должно быть поле pool, которые используется в emailTo() и close().
 * 3. Модель User описывают поля username, email.
 * 4. Метод emailTo() должен брать данные пользователя и подставлять в шаблон
 *      subject = Notification {username} to email {email}.
 *      body = Add a new event to {username}
 * 5. создайте метод public void send(String subject, String body, String email) - он должен быть пустой.
 * 6. Через ExecutorService создайте задачу, которая будет создавать данные для пользователя и передавать
 *    их в метод send().
 *
 * */
public class EmailNotification {
    private ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        executorService.submit(() ->
        {
            System.out.println("Я в объекте Runnable"); //лог
            final String subject = String.format("Notification {%s} to email {%S}", user.getUsername(), user.getEmail());
            final String body = String.format("Add a new event to {%s}", user.getUsername());

            send(subject, body, user.getEmail());
        });
    }

    public void close() {
        System.out.println("Я в close()"); //как лог
        this.executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
        System.out.println("Я в методе send(), как бы посылаю email"); //как лог
        System.out.println("С такой темой: " + subject);
        System.out.println("С таким телом: " + body);
        System.out.println("Такому адресату: " + email);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("Alex");
        user.setEmail("6862613@gmail.com");

        EmailNotification notification = new EmailNotification();
        notification.emailTo(user);
        notification.close();
    }
}
