package abstracts.example4;

public class Program {
    public static void main(String[] args) {
        Figure triangle = new Triangle(3, 4);
        triangle.countArea();
        Figure rectangle = new Rectangle(1, 2);
        rectangle.countArea();
    }
}

//Output
//6.0, 6.0, 1, 2