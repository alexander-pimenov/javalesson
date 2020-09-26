package ru.pimalex1978.basepatterns.behavioral.state.example1;

public class Sleeping implements Activity {
    @Override
    public void justDoIt() {
        System.out.println("Sleeping...");
    }
}
