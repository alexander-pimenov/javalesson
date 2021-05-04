package nestedclasses.anonymosclasses.anonymousandenum;

import java.util.StringJoiner;

/**
 * Унаследоваться от enum не возможно.
 * Enum не может быть супер классом.
 * Т.е. явных наследников он не может иметь.
 * Но решить эту проблему можно с помощью анонимного класса.
 * Имеем прямоугольник и прямоугольный треугольник.
 * Каждое перечисление получит свою собственную реализацию
 * абстрактного метода computeSquare.
 * Т.е. каждому перечислению можно давать свою версию
 * реализацию абстрактного метода.
 */
public enum Shape {
    RECTANGLE(2, 3) {
        @Override
        public double computeSquare() {
            return this.getA() * this.getB();
        }
    }, TRIANGLE(2, 5) {
        @Override
        public double computeSquare() {
            return this.getA() * this.getB() / 2;
        }
    };
    private double a; //сторона прямоугольника /катет треугольника
    private double b; //сторона прямоугольника /катет треугольника

    Shape(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void setShape(double a, double b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException();
        }
        this.a = a;
        this.b = b;
    }

    //этот абстрактный метод будет реализован анонимными классами
    //в наших константах RECTANGLE, TRIANGLE
    public abstract double computeSquare();

    /**
     * Для доступа к полю a
     *
     * @return значение поля a
     */
    public double getA() {
        return a;
    }

    /**
     * Для доступа к полю b
     *
     * @return значение поля b
     */
    public double getB() {
        return b;
    }

    @Override
    public String toString() {
        return new StringJoiner(
                ",", Shape.class.getSimpleName()
                + "[", "]")
                .add("a=" + a)
                .add("b=" + b)
                .toString();
    }
}
