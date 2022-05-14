package ru.pimalex1978.concurrent;

import java.io.InputStream;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TestingClass implements Runnable {
    @Override
    public void run() {
        System.out.println("Запущен поток - " + Thread.currentThread().getName());
        System.out.println("12");
    }

    public static void main(String[] args) {
//        System.out.println("Запущен поток - " + Thread.currentThread().getName());
//        new Thread(new TestingClass()).start();

        //Посмотри как правильно называются классы:
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
//output:
//java.lang.String
//java.lang.Class
//[I
//void
//char
//double
//java.util.ArrayList
//java.io.InputStream
//java.util.stream.Stream
