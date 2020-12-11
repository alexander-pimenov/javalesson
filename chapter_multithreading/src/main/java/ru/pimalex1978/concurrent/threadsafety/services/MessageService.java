package ru.pimalex1978.concurrent.threadsafety.services;

/*https://www.baeldung.com/java-thread-safety*/
public class MessageService {

    private final String message;

    public MessageService(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}