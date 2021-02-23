package ru.pimalex1978.concurrent;

import java.io.InputStream;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TestingClass implements Runnable {
    @Override
    public void run() {
        System.out.println("12");
    }

    public static void main(String[] args) {
//        new Thread(new TestingClass()).start();
        System.out.println(String.class.getName());
        System.out.println(Class.class.getName());
        System.out.println(int[].class.getName());
        System.out.println(void.class.getName());
        System.out.println(char.class.getName());
        System.out.println(double.class.getName());
        System.out.println(ArrayList.class.getName());
        System.out.println(InputStream.class.getName());
        System.out.println(Stream.class.getName());

    }
}
//Output
// Exception in thread "main" java.lang.ClassCastException
