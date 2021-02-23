package abstracts.example1;

public class Rectangle implements Shape {
    private double height;
    private double width;
    private String color;

    /*параметры ПОЛЯ определяем в конструкторе */
    public Rectangle(double height, double width, String color) {
        this.height = height;
        this.width = width;
        this.color = color;
    }

    @Override
    public String getName() {
        return "Rectangle";
    }

    /*формула уже другая нежели у круга*/
    @Override
    public double getSquare() {
        return height * width;
    }

    @Override
    public String getColor() {
        return color;
    }
}
