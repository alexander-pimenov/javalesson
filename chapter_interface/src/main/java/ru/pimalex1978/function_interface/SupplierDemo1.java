package ru.pimalex1978.function_interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://www.javabrahman.com/java-8/java-8-java-util-function-supplier-tutorial-with-examples/
 * <p>
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
 * Например, каждая ссылка на метод "getter" является Supplier.
 * Он используется для генерации новых значений.
 * <p>
 * Поставщик может использоваться во всех контекстах, где нет ввода, но ожидается вывод.
 * <p>
 * Сначала СОЗДАЕМ объект Supplier: Supplier<T> randVal=()->{...}; ,
 * а затем РАЛИЗУЕМ его с использованием метода get(): randVal.get();
 * <p>
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


        // СОЗДАЕМ объект Supplier, указываем что он должен делать:
        // Эта функция возвращает случайное значение.

        Supplier<Double> randomValue = () -> Math.random();

        // РЕАЛИЗУЕМ используя get()
        // Распечатать случайное значение, используя get ()

        System.out.println("Выводим случайное число: " + randomValue.get());

        // Оператор NEW сам является поставщиком ссылки на вновь созданный объект
        Supplier<List<String>> listSupplier = ArrayList::new;

        // Еще пример: рассмотрим Supplier, который будет выдавать
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

        //СОЗДАЕМ объект Supplier, указываем что он должен делать:
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
        Stream.generate(() -> { //ПРОИЗВОДИМ что то по этому алгоритму
            int value = (int) (Math.random() * nameList.size());
            return nameList.get(value);
        }).limit(5).forEach(System.out::println);


        //Еще пример С ПРОИЗВОДСТВОМ чего то:
        getValue(() -> "Output1");
        getValue(() -> "OutPut2");

        // Пример использования Supplier в OptionalDouble
        /*
         * http://espressocode.top/optionaldouble-orelsethrowsupplier-method-in-java-with-examples/
         * Синтаксис:
         * public <X extends Throwable> double
         *     orElseThrow(Supplier<X> exceptionSupplier)
         *         throws X extends Throwable
         * */

        // создаем OptionalDouble1
        OptionalDouble opDouble1 = OptionalDouble.of(0.268924);
        // применить orElseThrow (поставщик)
        double value = opDouble1.orElseThrow(ArithmeticException::new);
        System.out.println("value " + value);

        // создаем OptionalDouble2
        OptionalDouble opDouble2 = OptionalDouble.empty();

        // применить orElseThrow (поставщик)
        double value2;
        try {
            value2 = opDouble2.orElseThrow(IOException::new);
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }

        //Создаем экземпляр Поставщика с лямбда-выражением
        Supplier<String> helloStrSupplier = () -> new String("Hello, geek!");

        //Реализуем с помощью get() получая возвращаемый тип String
        String helloStr = helloStrSupplier.get();
        System.out.println("String in helloStr is->" + helloStr + "<-");

        //Экземпляр поставщика, использующий ссылку на метод для конструктора по умолчанию
        Supplier<String> emptyStrSupplier = String::new;
        String emptyStr = emptyStrSupplier.get();
        System.out.println("String in emptyStr is->" + emptyStr + "<-");

        //Экземпляр поставщика, использующий ссылку на статический метод
        Supplier<Date> dateSupplier = SupplierDemo1::getSystemDate;
        Date systemDate = dateSupplier.get();
        System.out.println("systemDate->" + systemDate);

        //Использование класса Employee. Создаем Supplier
        Supplier<Employee> supplier = () -> {
            return new Employee(
                    1, "Robert", 35, "Manager");
        };

        //Релизуем Supplier с помощью get()
        final Employee employee = supplier.get();
        System.out.println(employee.getDesignation());

        // Мы сгенерируем новый поток, используя статический метод stream ().
        // Этот метод принимает в качестве аргумента одного поставщика.
        final Stream<Employee> s = Stream.generate(supplier).limit(1);
        List<Employee> empList = s.collect(Collectors.toList());
        empList.forEach(e -> System.out.println(e.getAge()));

    }


    public static void getValue(Supplier<?> supplier) {
        System.out.println(supplier.get());
    }

    public static Date getSystemDate() {
        return new Date();
    }
}

//Описание интерфейса Supplier:
//@FunctionalInterface
//public interface Supplier<T> {
//    T get();
//}

//Еще небольшое объяснение поставщика: - это getter
//public Integer getInteger() {
//    return new Random().nextInt();
//}

/**
 * Используется в нашем примере.
 */
class Employee {

    private long empId;
    private String name;
    private int age;
    private String designation;

    public Employee(long empId, String name, int age, String designation) {
        super();
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.designation = designation;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
