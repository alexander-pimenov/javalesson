package ru.pimalex1978.concurrent.waitnotify;

/**
 * https://www.udemy.com/course/java-simple2advanced/learn/lecture/11252410#overview
 * Объект этого класса будет служить, как контейнер для сообщений,
 * а также будет локом.
 */
class Message {
    private String message;

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }
}
