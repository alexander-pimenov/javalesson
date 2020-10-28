package ru.pimalex1978.function_interface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * http://espressocode.top/java-8-consumer-interface-in-java-with-examples/
 * <p>
 * Сначала нужно написать объект Consumer т.е. СОЗДАТЬ его:
 * Consumer<T> action = a -> {....};,
 * пишем, что мы хотим делать в нём: выводить в консоль,
 * умножать на 2, записывать в файл и конечно разные другие действия.
 * А затем РЕАЛИЗУЕМ его с помощью accept() или используя addThen()
 * <p>
 * -- метод принимает одно значение и выполняет операцию с данным аргументом
 * -- метод не возвращает никакого значения
 * <p>
 * void accept(T t) //принять
 * <p>
 * -- возвращает составного Потребителя,
 * -- в котором параметризованный Потребитель будет выполнен после первого.
 * Примечание.
 * Функция, передаваемая в качестве аргумента, должна иметь тип Consumer.
 * <p>
 * default Consumer <T>
 * andThen(Consumer<? super T> after
 * <p>
 * Исключение: этот метод генерирует исключение NullPointerException,
 * если операция после равна нулю.
 */
public class ConsumerDemo3 {
    public static void main(String[] args) {

        // СОЗДАЕМ Потребитель для отображения номера
        Consumer<Integer> display = a -> System.out.println(a);

        // РЕАЛИЗУЕМ отображение с помощью accept ()
        display.accept(10); // 10

        // СОЗДАЕМ Потребитель для умножения 2 на каждое целое число в списке
        Consumer<List<Integer>> modify = list -> {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, 2 * list.get(i));
            }
        };

        // СОЗДАЕМ Потребитель для отображения списка номеров
        Consumer<List<Integer>> dispList = list -> list.stream().forEach(
                a -> System.out.print(a + " ")
        );

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);

        // РЕАЛИЗУЕМ модификацию используя accept()
        modify.accept(list);

//        System.out.println(list);

        // РЕАЛИЗУЕМ dispList с помощью accept()
        dispList.accept(list); // 4 2 6

        System.out.println();

        // РЕАЛИЗУЕМ используя addThen()
        modify.andThen(dispList).accept(list); // 8 4 12

        System.out.println();

        // Чтобы продемонстрировать, когда возвращается NullPointerException.
        try {
            // РЕАЛИЗУЕМ используя addThen()
            modify.andThen(null).accept(list);
        } catch (Exception e) {
            System.out.println("Exception: " + e); //Exception: java.lang.NullPointerException
        }

        // Чтобы продемонстрировать, как исключение в функции after возвращается и обрабатывается.
        // РЕАЛИЗУЕМ используя addThen()

        try {
            dispList.andThen(modify).accept(list);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
