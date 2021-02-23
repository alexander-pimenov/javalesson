package abstracts.example2;
/*все что связано с color у классов наследников удаляем*/
public class Circle2 extends Shape2{
    private double radius;

    public Circle2(double radius) {
        super("Blue");
        this.radius = radius;
    }

    @Override
    public String getName2() {
        return "Circle";
    }

    /*чтобы была площадь нужен радиус, его прописали в ПОЛЕ, дальше
     * вычисляем по фомуле*/
    @Override
    public double getSquare2() {
        return 3.1415926*radius*radius;
    }
}
