package ru.pimalex1978.function_interface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Supplier (поставщик) - это встроенный функциональный интерфейс, добавленный
 * в Java SE 8 в пакет java.util.function.
 * Возвращает значение, одно и тоже или разные.
 * <p>
 * Supplier (с англ. — поставщик) — функциональный интерфейс, который не
 * принимает никаких аргументов, но возвращает некоторый объект типа T.
 * <p>
 * Интерфейс Supplier используется тогда, когда на вход не передаются значения,
 * но необходимо вернуть результат.
 * Его задача-буквально предоставить экземпляр ожидаемого класса.
 * Например, каждая ссылка на метод "getter" является Supplier
 * Он используется для генерации новых значений.
 * Функциональный дескриптор интерфейса:
 * () -> T
 */
public class SupplierDemo1 {

    public static void main(String[] args) {
        //
        String t = "One day";
        //Сапплаер обрабатывает Стринг, выполняя действие по переводу символов в верхний регистр.
        Supplier<String> supplierStr = () -> t.toUpperCase();

        System.out.println(supplierStr.get()); //ONE DAY

        //Оператор new сам является поставщиком ссылки на вновь созданный объект
        Supplier<List<String>> listSupplier = ArrayList::new;

        //Еще пример: рассмотрим Supplier, который будет выдавать
        // рандомные имена из списка
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("Elena");
        nameList.add("John");
        nameList.add("Alex");
        nameList.add("Jim");
        nameList.add("Sara");
        nameList.add("York");
        nameList.add("Woolf");
        nameList.add("Nick");
        System.out.println(nameList);

        Supplier<String> randomName = () -> {
            int value = (int) (Math.random() * nameList.size());
            return nameList.get(value);
        };

        System.out.println("Выводим случайное имя: " + randomName.get()); //вызываем метод get()

        /*Примером метода в Stream, использующего функциональный интерфейс
         * Supplier, является generate, который генерирует бесконечную
         * последовательность на основе переданного ему функционального
         * интерфейса.
         * Воспользуемся нашим примером Supplier для вывода в консоль
         * пяти случайных имен
         * Здесь мы использовали метод limit(5), чтобы задать ограничение
         * методу generate, иначе программа выводила бы рандомные имена
         * в консоль бесконечно.*/
        Stream.generate(() -> { //производим что то по этому алгоритму
            int value = (int) (Math.random() * nameList.size());
            return nameList.get(value);
        }).limit(5).forEach(System.out::println);


        //Еще пример:
        getValue(() -> "Output1");
        getValue(() -> "OutPut2");

    }

    public static void getValue(Supplier<?> supplier) {
        System.out.println(supplier.get());
    }
}

//Описание интерфейса Supplier:
//@FunctionalInterface
//public interface Supplier<T> {
//    T get();
//}

//Еще небольшое объяснение поставщика:
//public Integer getInteger() {
//    return new Random().nextInt();
//}
