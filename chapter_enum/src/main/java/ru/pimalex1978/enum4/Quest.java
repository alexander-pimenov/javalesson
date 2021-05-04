package ru.pimalex1978.enum4;

/**
 * Explanation
 * <p>
 * Объекты перечисления создаются в единственном экземпляре!!!
 * Поэтому сравнение оператором «==» элементов перечисления
 * приводит к сравнению их объектных ссылок.
 * hashCode() будут одинаковыми.
 * Нумерация позиций элементов перечисления начинается с нуля.
 *
 * В теле перечисления можно объявлять методы, поля и конструкторы.
 * Конструктор перечисления определяется без модификатора доступа
 * или с модификатором private.
 * Перечисления неявно наследуются от класса java.lang.Enum.
 */
public class Quest {
    public static void main(String[] args) {
        Numbers n1 = Numbers.ONE;
        Numbers n2 = Numbers.ONE;

        System.out.println(n1.hashCode());
        System.out.println(n2.hashCode());

        if (n1 == n2) {
            System.out.print("true");
        } else {
            System.out.print("false");
        }
        System.out.println(Numbers.FIVE.ordinal());

    }
}

enum Numbers {
    ONE, TWO, THREE, FOUR, FIVE
}
