package abstracts.example1;

import java.util.ArrayList;

/**
 * Абстрактный класс - это супер класс без реализации методов, он может
 * содержать абст.методы, они не имеют реализации, пусть их реализуют потомки
 * У абстрактного класса нельзя создать объект чере new, но можно
 * создавать его потомки через extends
 *
 */

public class Abstracts {
    /*создать метод, к-рый будет принимать на вход список фигур и печатать
    * в консоли их параметры*/
    public static void main(String[] args) {
        /*чтобы вызвать метод printShape нужно сформировать список фигур*/
        ArrayList<Shape> shapes = new ArrayList<>();
        /*нужно определить не абстрактные,а конкретные фигуры
        * полиморфизм Shape shape = new Circle*/
        Shape shape = new Circle(1, "Red");

        /*добавим созданную фигуру в список фигур*/
        shapes.add(shape);
        shapes.add(new Rectangle(1,1,"Blue"));//а прямоугольник создали и добавили по-другому

        /*вызываем метод который печатает фигуры*/
        printShape(shapes);



    }

    /*пишем метод принимающий на вход список фигур
    * этот метод будет неизменным для всех фигур.
    * Это плюс от проектирования сверху*/
    public static void printShape(ArrayList<Shape> shapes){
        //с помощью цикла выводим в консоль данные фигуры
        for(Shape shape:shapes){
            System.out.println("Name: "+shape.getName());
            System.out.println("Square: "+shape.getSquare());
            System.out.println("Color: "+shape.getColor());
        }
    }
}
