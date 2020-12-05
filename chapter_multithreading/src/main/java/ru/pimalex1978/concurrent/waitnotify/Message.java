package ru.pimalex1978.concurrent.waitnotify;

/**
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
