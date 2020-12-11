package ru.pimalex1978.concurrent.threadsafety.callables;

import ru.pimalex1978.concurrent.threadsafety.services.MessageService;

import java.util.concurrent.Callable;

/*https://www.baeldung.com/java-thread-safety*/

public class MessageServiceCallable implements Callable<String> {

    private final MessageService messageService;

    public MessageServiceCallable(MessageService messageService) {
        this.messageService = messageService;

    }

    @Override
    public String call() {
        return messageService.getMessage();
    }
}