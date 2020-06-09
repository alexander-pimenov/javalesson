package ru.pimalex1978.algorithms_in_java;

public class TypicalStaticMethods {

    /**
     * Абсолютная величина значения int
     *
     * @param x x
     * @return int
     */
    public static int abs(int x) {
        if (x < 0) return -x;
        else return x;
    }

    /**
     * Абсолютная величина значения double
     *
     * @param x x
     * @return double
     */
    public static double abs(double x) {
        if (x < 0.0) return -x;
        else return x;
    }

    /**
     * Проверка, простое ли число
     *
     * @param n число
     * @return true/false
     */
    public static boolean isPresent(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * Квадратный корень (метод Ньютона)
     *
     * @param c c
     * @return double
     */
    public static double sqrt(double c) {
        System.out.println("стартует метод sqrt()....");
        if (c < 0) return Double.NaN;
        double err = 1e-15;
        System.out.println(err);
        double t = c;
        while (Math.abs(t - c / t) > err * t) {
            t = (c / t + t) / 2.0;
            System.out.println(t);
        }
        System.out.println("... метод sqrt заканчивает работу.");
        return t;
    }

    /**
     * Гипотенуза прямоугольного треугольника
     *
     * @param a a
     * @param b b
     * @return double
     */
    public static double hypotenuse(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    /**
     * Гармоническое число
     *
     * @param n n
     * @return double
     */
    public static double H(int n) {
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }

    public static void main(String[] args) {

        System.out.println("Абсолютная величина числа -3 = " + TypicalStaticMethods.abs(-3));
        System.out.println("Абсолютная величина числа 5,5 = " + TypicalStaticMethods.abs(5.5));
        System.out.println("Простое ли число 13 = " + TypicalStaticMethods.isPresent(13));
        System.out.println("Простое ли число 12 = " + TypicalStaticMethods.isPresent(12));
        System.out.println("Квадр. корень числа 16 = " + TypicalStaticMethods.sqrt(16));
        System.out.println("Гипотенуза треугольника со стороной 10 и 12 = " + TypicalStaticMethods.hypotenuse(10, 12));
        System.out.println("Гармоническое число 15 = " + TypicalStaticMethods.H(15));


    }
}
