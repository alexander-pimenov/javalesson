package ru.pimalex1978.function_interface;

public class ConvertAnimal {
    public static void main(String[] args) {
        Dog dog = new Dog("Bobbie", 5, 3);

        //Создаем объект интерфейса
        Converter<Animal, Animal> converter = x -> new Raccoon(x.name, x.age, x.weight);

        //Вызываем у интерфейса абстрактный метод convert
        Raccoon raccoon = (Raccoon) converter.convert(dog);

        //Вызываем у интерфейса дефолтный метод writeToConsole
        converter.writeToConsole(dog);
        System.out.println("Raccoon has parameters: name - " + raccoon.name + ", age - " + raccoon.age + ", weight - " + raccoon.weight);
    }
}

abstract class Animal {
    String name;
    int age;
    int weight;

    public Animal(String name, int age, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", weight=" + weight
                + '}';
    }
}

class Raccoon extends Animal {

    Raccoon(String name, int age, int weight) {
        super(name, age, weight);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }
}

class Dog extends Animal {

    Dog(String name, int age, int weight) {
        super(name, age, weight);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }
}

/**
 * Наш функциональный интерфейс.
 * Задача которого — преобразовывать объекты одного
 * типа в объекты другого (такой себе адаптер).
 *
 * @param <T> тип входного параметра
 * @param <N> тип выходного параметра
 */
@FunctionalInterface
interface Converter<T, N> {

    N convert(T t);

    default void writeToConsole(T t) {
        System.out.println("Текущий объект - " + t.toString());
    }

    //метод переопределен из класса Object, поэтому компилятор не
    //ругается.
    boolean equals(Object obj);
}
