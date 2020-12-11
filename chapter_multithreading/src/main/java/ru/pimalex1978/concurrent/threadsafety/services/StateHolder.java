package ru.pimalex1978.concurrent.threadsafety.services;

/*https://www.baeldung.com/java-thread-safety*/
public class StateHolder {

    private final String state;

    public StateHolder(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}