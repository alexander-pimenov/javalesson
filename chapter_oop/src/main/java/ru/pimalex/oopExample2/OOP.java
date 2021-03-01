package ru.pimalex.oopExample2;

/*рассмотрим в наглядном примере иерархию классов,
 * т.е. набор классов объединенных общим предком
 * с разными переменными мы можем общаться, как с переменной класса родителя */
public class OOP {

    public static void main(String[] args) {
        /*Animal dog = new Dog() - это пример полиморфизма*/
        Animal dog = new Dog();
        Animal cat = new Cat();

        /*если имя в Анимал публичное, то можно к нему обратится так: */
//        dog.name = "Sharik";
//        cat.name = "Murka";

        /*при использовании сеттеров имя можем установить так: */
        dog.setName("Sharik");
        cat.setName("Murka");

        System.out.println(dog.getName());
        System.out.println(cat.getName());

        /*вызываем метод voice*/
        System.out.println(dog.voice());
        System.out.println(cat.voice());

        /*вызываем метод sleep*/
        dog.sleep();
        cat.sleep();

        /*Просто хотел узнать, что обозначают коды символов
        * char это 2 байта (65536)*/
        char c = '\u0000';
        char c1 = '\u0001';
        char c2 = '\u0002';
        char c98 = '\u0098';
        char c258 = '\u0258';
        char c1155 = '\u1155';
        System.out.println("Символ '\\u0000':" + c);
        System.out.println("Символ '\\u0001':" + c1);
        System.out.println("Символ '\\u0002':" + c2);
        System.out.println("Символ '\\u0098':" + c98);
        System.out.println("Символ '\\u0258':" + c258);
        System.out.println("Символ '\\u1155':" + c1155);
    }
}
