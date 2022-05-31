package infinity;

/* В отличие от целых чисел, деление значений с плавающей запятой
 * на ноль никаких исключений не вызывает. Арифметика чисел с плавающей
 * запятой реализована в соответствии со стандартом IEEE 754, который
 * требует возвращения специального значения «Infinity» (Бесконечность),
 * когда положительное число делится на ноль.
 *
 * В методе variableIsNotEqualToItself() показан пример, что переменная real не равна
 * самой себе!!! Как это возможно? Арифметика с плавающей запятой реализована в соответствии
 * со стандартом IEEE 754, который требует возвращения специального значения "NaN" (от
 * английского "Not a Number" - не число), когда ноль делится на ноль. В спецификации
 * также указано, что NaN не равно никакому значению с плавающей запятой, включая само себя.*/
public class Main {
    public static void main(String[] args) {

        divisionByZeroFlat(); //A is equal to Infinity
        divisionByZeroDouble(); //A is equal to Infinity
        variableIsNotEqualToItself(); //Not equal
    }

    private static void divisionByZeroFlat() {
        float num = 5.0f;
        System.out.println("A is equal to " + num / 0.0f); //A is equal to Infinity
    }

    private static void divisionByZeroDouble() {
        double num = 5.0;
        System.out.println("A is equal to " + num / 0.0); //A is equal to Infinity
    }

    private static void variableIsNotEqualToItself() {
        float real = 0.0f / 0.0f;
//        double real = 0.0 / 0.0;
        if (real == real) {
            System.out.println("Equal");
        } else {
            System.out.println("Not equal");
        }
    }
}
