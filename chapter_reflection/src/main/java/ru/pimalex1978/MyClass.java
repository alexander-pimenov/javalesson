package ru.pimalex1978;

/**
 * Как мы видим, это самый обычный класс.
 * Конструктор с параметрами закомментирован не просто так, мы к этому еще вернемся.
 * Если вы внимательно просмотрели содержимое класса, то наверняка увидели отсутствие
 * getter’a для поля name. Само поле name помечено модификатором доступа private,
 * обратиться к нему вне самого класса у нас не выйдет => мы не можем получить
 * его значение.
 */

public class MyClass {
    private int number;
    private String name = "default";

    public MyClass(int number, String name) {
        this.number = number;
        this.name = name;
    }

    /*
     * Конструктор без параметров необходим, чтобы с помощью него
     * через метод рефлексии newInstance() создавать объекты. */
    public MyClass() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void printData() {
        System.out.println(number + name);
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
