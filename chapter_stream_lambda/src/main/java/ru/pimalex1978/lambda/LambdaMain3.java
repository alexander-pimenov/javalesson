package ru.pimalex1978.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaMain3 {
    public static void main(String[] args) {

        //создадим список целых чисел
        List<Integer> list = Arrays.asList(1, 3, 5, 6, 7, 9, 11, 23, 45, 68);

        //ОБРАТИ ВНИМАНИЕ, КАК С ПОМОЩЬЮ ЛЯМБДЫ МЫ МОЖЕМ МЕНЯТЬ ЛОГИКУ РАБОТЫ
        //МЕТОДА НА ХОДУ, НЕ МЕНЯЯ КОД НАПИСЫННЫЙ В САМОМ МЕТОДЕ !!!
        System.out.println("Выводит все числа: ");
        evaluate(list, n -> true);

        System.out.println("Не выводит ни одного числа: ");
        evaluate(list, n -> false);

        System.out.println("Вывод четных чисел: ");
        evaluate(list, n -> n % 2 == 0);

        System.out.println("Вывод нечетных чисел: ");
        evaluate(list, n -> n % 2 == 1);

        System.out.println("Вывод чисел больше 5: ");
        evaluate(list, n -> n > 5);

        System.out.println("Вывод чисел кратных 3: ");
        evaluate(list, n -> n % 3 == 0);
    }

    /*В этом методе мы используем функциональный интерфейс Predicate
    для создания теста и печати элементов, прошедших этот тест.
    Для этого используется метод интерфейса 'boolean test(T t);',
    реализацию, которого мы будем писать в main методе.
    Таким способом мы можем в main методе (или в другом потоке) помещать
    логику в lambda-выражения и делать что-либо на ее основе.*/
    public static void evaluate(List<Integer> listInt, Predicate<Integer> predicate) { //оценивать
        for (Integer n : listInt) {
            if (predicate.test(n)) {
                System.out.print(n + " ");
            }
        }
        System.out.println();
    }
}
