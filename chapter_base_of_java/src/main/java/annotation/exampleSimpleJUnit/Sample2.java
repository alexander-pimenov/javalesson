package annotation.exampleSimpleJUnit;
import java.util.ArrayList;
import java.util.List;
public class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    public static void m1() { // Test should pass
        int i = 0;
        i = i / i; //=1
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m2() { // Should fail (wrong exception)
        int[] a = new int[0];
        int i = a[1]; //Index 1 out of bounds for length 0
    }

    //этот тест не пройдет, т.к. ставим маркер об ArithmeticException,
    //но его не будет (т.к. тело метода пустое)
    @ExceptionTest(ArithmeticException.class)
    public static void m3() {
    } // Should fail (no exception)

    /*
    * Метод doublyBad() аннотирован аннотацией @ExceptionTest дважды.
    * Это означает, что метод выбрасывает любое
    * из этих исключений и их надо протестировать,
     *
    * */
    // Code containing an annotation with an array parameter
    // Код, содержащий аннотацию с параметром массива
    @ExceptionTest(IndexOutOfBoundsException.class)
    @ExceptionTest(NullPointerException.class)
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
// The spec permits this method to throw either
// IndexOutOfBoundsException or NullPointerException
        // Спецификация разрешает этому методу бросать либо
        // IndexOutOfBoundsException либо NullPointerException
        list.addAll(5, null);
    }
}
