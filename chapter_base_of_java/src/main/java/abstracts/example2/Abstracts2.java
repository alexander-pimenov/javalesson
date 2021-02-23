package abstracts.example2;

import java.util.ArrayList;

/*т.к. в классах Circle & Rectangle есть повторяющийся код
* мы поправим во 2-м примере код с использованием extends*/
public class Abstracts2 {
    /*создать метод, к-рый будет принимать на вход список фигур и печатать
     * в консоли их параметры*/
    public static void main(String[] args) {
        /*чтобы вызвать метод printShape нужно сформировать список фигур*/
        ArrayList<Shape2> shapes2 = new ArrayList<>();
        /*нужно определить не абстрактные,а конкретные фигуры
         * полиморфизм Shape shape = new Circle*/
        Circle2 circle2 = new Circle2(1);

        /*добавим созданную фигуру в список фигур*/
        shapes2.add(circle2);
        shapes2.add(new Rectangle2("Blue",1,1));//а прямоугольник создали и добавили по-другому

        /*вызываем метод который печатает фигуры*/
        printShape2(shapes2);
    }

    /*пишем метод принимающий на вход список фигур
     * этот метод будет неизменным для всех фигур.
     * Это плюс от проектирования сверху*/
    public static void printShape2(ArrayList<Shape2> shapes2){
        //с помощью цикла выводим в консоль данные фигуры
        for(Shape2 shape:shapes2){
            System.out.println("Name: "+shape.getName2());
            System.out.println("Square: "+shape.getSquare2());
            System.out.println("Color: "+shape.getColor2());
        }
    }
}
