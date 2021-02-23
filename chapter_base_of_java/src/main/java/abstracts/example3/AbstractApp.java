package abstracts.example3;

/**
 * Абстрактный класс - это супер класс без реализации методов, он может
 * содержать абст.методы, они не имеют реализации, пусть их реализуют потомки.
 * Если в классе есть абстр.методы, то класс должен быть абстрактным.
 * Абст.класс может содержать неабстрактные методы и они будут иметь реализацию
 * У абстрактного класса нельзя создать объект чере new, но можно
 * создавать его потомки через extends и они могут иметь уже объекты
 * <p>
 * Австрактный класс Shape показывается для иерархии, так с ним никто не работает
 * обычно создают наследники и работают от них: new Square(4); new Rectangle(2,3);
 */


public class AbstractApp {
    public static void main(String[] args) {

        /*используем св-ва полиморфизма: можем вместо Square square
         * написать Shape square, т.е. через ссылку на абстрактный класс*/

        Shape square = new Square(4);
        square.area();
        square.perimetr();

        Shape rectangle = new Rectangle(2, 3);
        rectangle.area();
        rectangle.perimetr();

        Shape circle = new Circle();
        /*методы в Circle не определены и он будет использовать методы в
        * супер классе, т.е. abstract class Shape
         */
        circle.area();
        circle.perimetr();

    }
}

abstract class Shape {
    abstract void area();//если это будет не абстракт то нужно будет писать реализацию

    //неабстрактный метод
    void perimetr() {
        System.out.println("Периметр для данной фигуры не определен");
    }
}

class Square extends Shape {
    int n;//сторона квадрата

    public Square(int n) {
        this.n = n;
    }

    @Override
    void area() {
        System.out.println("Площадь квадрата = " + (n * n));
    }

    @Override
    void perimetr() {
        System.out.println("Периметр квадрата = " + (2 * n));
    }
}

class Rectangle extends Square {
    /*т.к. Rectangle наследует и Shape и Square, то он должен реализовать
     * метод area() и он получит прерменную int n; от квадрата*/

    int m; //добавляем еще одну сторону для прямоугольника


    public Rectangle(int n, int m) {
        super(n);//вызываем конструктор верхнего уровня и передадим ему n.
        this.m = m;
    }

    @Override
    void area() {
        System.out.println("Площадь прямоугольника = " + (n * m));
    }

    @Override
    void perimetr() {
        System.out.println("Периметр прямоугольника = " + (2 * n + 2*m));

    }
}

/*в этом классе мы не сделали переопределение методов,т.к. пока не решили,
* как это сделаем и в этом случае у нас используются методы супер-класса*/
class Circle extends Shape{

    @Override
    void area() {

    }
}