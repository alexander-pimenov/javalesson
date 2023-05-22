package ru.pimalex1978.lambda;

/**
 * Ссылка на метод передается в виде
 * имя_класса::имя_статического_метода (если метод статический) или
 * объект_класса::имя_метода (если метод нестатический).
 * Рассмотрим еще на нескольких примерах.
 * 1) На место второго параметра в метод private static int sum(int[] numbers, Expression func)
 * передается ExpressionHelper::isEven, то есть ссылка на статический метод isEven() класса ExpressionHelper.
 * <p>
 * 2) На место второго параметра в метод private static int sum(int[] numbers, Expression func)
 * передается ExpressionHelper::isPositive, то есть ссылка на статический метод isPositive() класса ExpressionHelper.
 * !!! При этом методы, на которые идет ссылка, должны совпадать по параметрам и результату с методом функционального интерфейса(!!!).
 */
public class LambdaAndReferenceApp {
    public static void main(String[] args) {

        int[] nums = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        //проверка на четность + sum
        Expression isEven = ExpressionHelper::isEven;
        System.out.println("Результат: " + sum(nums, isEven));

        System.out.println("============");
        //проверка на положительное число + sum
        Expression isPositive = ExpressionHelper::isPositive;
        System.out.println("Результат: " + sum(nums, isPositive));
        System.out.println("============");


//pool.execute(new MyRunnable());

//-> public void myMethod() {
//        do {
//            new Thread(new MyRunnable()).start();
//        } while(true);
//    }
//
//    public void myMethod() {
//        pool.execute(new MyRunnable());
//    }




    }


    private static int sum(int[] numbers, Expression func) {
        int result = 0;
        for (int i : numbers) {
            if (func.isEqual(i)) {
                System.out.println(i);
                result += i;
            }
        }
        return result;
    }

}

//Класс, в котором определены статические методы. Далее будем ссылаться на эти методы.
class ExpressionHelper {
    static boolean isEven(int n) { //входящий параметр и возвращаемый тип сопоставимы с
        // методом boolean isEqual(int n) функционального интерфейса Expression
        return n % 2 == 0;
    }

    static boolean isPositive(int n) { //также сопоставим с isEqual()

        return n > 0;
    }
}

//Функциональный интерфейс
interface Expression {
    boolean isEqual(int n);
}

