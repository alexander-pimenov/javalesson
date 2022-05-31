package logical_operators;

/* Java использует короткую схему (short-circuiting)
 * вычисления логических выражений. Это означает, что
 * выражение будет вычисляться только до тех пор, пока
 * истинность или ложность всего выражения не будет
 * однозначно определена. В результате последние части
 * логического выражения могут не вычисляться. Например,
 * при оценке «A && B», если A ложно, тогда всё выражение
 * всегда ложно, вне зависимости от значения B.
 * Вывод такой: foo1() foo1()foo2() foo2()foo1() foo2(),
 * т.е. на 26 строке отрабатывает только метод foo1,
 * на 29 и 31 строках отрабатывают оба метода и foo1 и foo2,
 * на 34 опять только один метод, но теперь foo2*/
public class LogicalOperatorsTest {
    static boolean foo1() {
        System.out.print("foo1()");
        return true;
    }

    static boolean foo2() {
        System.out.print("foo2()");
        return false;
    }

    public static void main(String[] args) {
        boolean bool;
        bool = foo1() || foo2();
        System.out.print(" ");
        bool = foo1() && foo2();
        System.out.print(" ");
        bool = foo2() || foo1();
        System.out.print(" ");
        bool = foo2() && foo1();
    }
}
