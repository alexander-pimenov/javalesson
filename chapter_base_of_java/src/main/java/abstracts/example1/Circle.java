package abstracts.example1;
/*создаем класс Circle и реализуем методы Shape*/
public class Circle implements Shape {
    private double radius;
    private String color;

    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public String getName() {
        return "Circle";
    }

    /*чтобы была площадь нужен радиус, его прописали в ПОЛЕ, дальше
    * вычисляем по фомуле*/
    @Override
    public double getSquare() {
        return 3.1415926*radius*radius;
    }

    /*цвет тоже в ПОЛЕ, и передается в конструкторе*/
    @Override
    public String getColor() {
        return color;
    }
}
