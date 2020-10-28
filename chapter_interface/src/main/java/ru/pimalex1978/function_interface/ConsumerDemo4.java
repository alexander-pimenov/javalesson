package ru.pimalex1978.function_interface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * https://www.javabrahman.com/java-8/java-8-java-util-function-consumer-tutorial-with-examples/
 * Во всех сценариях, где объект должен быть принят в качестве входных
 * данных и над ним выполняется операция, можно использовать встроенный
 * функциональный интерфейс Consumer <T> без необходимости каждый
 * раз определять новый функциональный интерфейс.
 * <p>
 * Все лямбда-определения для Consumer должны быть написаны в соответствии
 * с сигнатурой метода accept, и, наоборот, все лямбда-выражения с той
 * же сигнатурой, что и accept(), являются кандидатами для назначения
 * экземпляру интерфейса Consumer.
 * <p>
 * Создаем разные объекты Consumer, в которых и описываем, что хотим выполнить.
 * А вызывая, consumer.get(T t) мы запускаем эти реализации Консумера.
 */
public class ConsumerDemo4 {
    public static void main(String[] args) {
        /*
         * Чтобы понять метод accept(), давайте взглянем на
         * приведенный ниже пример, где я беру список целых чисел
         * и распечатываю их с помощью метода printList().
         * */
        //создаем объект Consumer1
        Consumer<Integer> consumer1 = i -> System.out.print(" " + i);

        //создаем объект Consumer2
        Consumer<Integer> consumer2 = i -> {
            i = i + 10;
            System.out.print(" " + i);
            File file = new File("c:/test/UseConsumerAndBufferedWriter.txt"); //указываем куда записывать данные
            FileWriter fw = null;
            BufferedWriter bw = null;
            String dataWithNewLine = i + System.getProperty("line.separator"); //("line.separator") - добавили новую строку
            try {
                fw = new FileWriter(file, true); //true чтобы дозаписывать в файл
                bw = new BufferedWriter(fw);
                bw.write(dataWithNewLine);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bw.close(); //закрываем BufferedWriter
                    fw.close(); //закрываем FileWriter
                } catch (IOException e) {
                    System.out.println("Ошибка: " + e);
//                    e.printStackTrace();
                }
            }
        };

        //создаем объект Consumer3
        Consumer<String> consumer3 = str -> {
            System.out.println("Печать строкового значения: " + str);
            String filePath = "c:/test/UseConsumerAndFiles.txt";
            str = str + System.getProperty("line.separator");
            try {
                Files.write(Paths.get(filePath), str.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                System.out.println("Ошибка: " + ex);
//                ex.printStackTrace();
            }
        };


        //создаем список Интежеров
        List<Integer> integerList = Arrays.asList(
                1, 10, 200, 101, -10, 0);
        //создаем список Стрингов
        List<String> stringIntegerList = Arrays.asList(
                "1 дом", "10 машин", "200 яблок", "101 долматинец", "-10 пиков", "0 провалов");

        //вызываем метод печать списка, где используется Consumer
        printList(integerList, consumer1);
        printList(integerList, consumer2);
        printListT(stringIntegerList, consumer3);


        //Пример с использованием andThen()
        Consumer<Integer> consumerWithAndThen = consumer1.andThen(i -> System.out.print("(printed " + i + ")"));
        printList(integerList, consumerWithAndThen);
    }

    /**
     * Метод пачатающий список. Но не напрямую, а
     * с использованием Consumer
     * В Consumer описано, сто нужно делать.
     *
     * @param listOfIntegers список целых чисел
     * @param consumer       объект Consumer
     */
    public static void printList(List<Integer> listOfIntegers, Consumer<Integer> consumer) {
        for (Integer integer : listOfIntegers) {
            consumer.accept(integer);
        }
        System.out.println();
    }

    /**
     * Метод пачатающий список. Но не напрямую, а
     * с использованием Consumer
     * В Consumer описано, сто нужно делать.
     *
     * @param listOfT  список целых чисел
     * @param consumer объект Consumer
     */
    public static void printListT(List<String> listOfT, Consumer<String> consumer) {
        for (String t : listOfT) {
            consumer.accept(t);
        }
        System.out.println();
    }
}

//@FunctionalInterface
//public interface Consumer<T> {
//    void accept(T t);
//    default Consumer<T> andThen(Consumer<? super T> after) {
//        Objects.requireNonNull(after);
//        return (T t) -> { accept(t); after.accept(t); };
//    }
//}
