package abstracts.example2;

public class Rectangle2 extends Shape2{
    private double height;
    private double width;

    /*параметры ПОЛЯ определяем в конструкторе */

    public Rectangle2(String color, double height, double width) {
        super(color);
        this.height = height;
        this.width = width;
    }

//    public Rectangle2(double height, double width) {
//        super("Red"); //ссылка на метод базового класса
//        this.height = height;
//        this.width = width;
//    }


    public String getName2() {
        return "Rectangle";
    }

    /*формула уже другая нежели у круга*/

    public double getSquare2() {
        return height * width;
    }

}
