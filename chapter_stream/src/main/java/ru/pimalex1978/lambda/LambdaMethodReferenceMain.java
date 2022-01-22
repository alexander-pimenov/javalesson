package ru.pimalex1978.lambda;

import java.util.LinkedList;
import java.util.List;

/*Ссылки на нужный метод.
 * Использование ссылок на методы очень удобно, когда есть готовый метод ,
 * который вас полностью устраивает, и вы бы хотели использовать его в
 * качестве callback-а. В таком случае, вместо того чтобы писать
 * лямбда-выражение с кодом того метода, или же лямбда-выражение,
 * где мы просто вызываем этот метод, мы просто передаем ссылку на него.
 * И всё.*/
public class LambdaMethodReferenceMain {
    public static void main(String[] args) {

        //1
        List<String> strings = new LinkedList<>();
        strings.add("мама");
        strings.add("мыла");
        strings.add("раму");

        //такое лямбда-выражение (просто вызывающее другой метод) мы можем
        // заменить ссылкой на нужный нам метод.
        strings.forEach(x -> System.out.println(x));
        //Тогда наш код будет выглядеть так:
        strings.forEach(System.out::println);

        //2
        //Передаем ссылку на статический метод ИмяКласса:: имяСтатическогоМетода
        strings.forEach(LambdaMethodReferenceMain::staticMethod);
        //или например на существующий в Main классе медот
        strings.forEach(Main::staticMethodInMainClass);

        //3
        //Передаем ссылку на не статический метод используя существующий
        // объект имяПеременнойСОбъектом:: имяМетода
        LambdaMethodReferenceMain instance = new LambdaMethodReferenceMain();
        strings.forEach(instance::nonStaticMethod);

        //4
        //Передаем ссылку на не статический метод используя класс,
        // в котором реализован такой метод ИмяКласса:: имяМетода

        List<User> users = new LinkedList<>();
        users.add(new User("Вася"));
        users.add(new User("Коля"));
        users.add(new User("Петя"));

        users.forEach(User::print);

        //5
        //Передаем ссылку на конструктор ИмяКласса::new


    }

    private static void staticMethod(String s) {
        // do something
    }

    private void nonStaticMethod(String s) {
        // do something
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println(name);
    }
}