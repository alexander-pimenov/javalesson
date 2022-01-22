package ru.pimalex1978.stream.drucoder;

import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.*;

/**
 * Класс Test используем, чтобы запускать в отдельных методах стримы,
 * а не в одном методе main.
 * Стримы это ленивые выражения.
 * Промежуточные опреации (операции транчформации) не вызываются в тот
 * момент когда мы их описали. Что позволяет иметь много модификаций
 * в разных местах, но отрабатывать только в одном месте.
 * А Терминальные методы при вызове выполняют все операции.
 * <p>
 * Например Промежуточные опреации можно производить на уровне сервиса,
 * а Терминальные опреции на уровне контроллера.
 * <p>
 * Java Stream API: функционально, модно, молодёжно!
 * https://www.youtube.com/watch?v=RzEiCguFZiY
 */
public class StreamsTest {
    private List<Employee> emps = List.of(
            new Employee("Michael", "Smith", 243, 43, Position.CHEF),
            new Employee("Jane", "Smith", 523, 40, Position.MANAGER),
            new Employee("Jury", "Gagarin", 6423, 26, Position.MANAGER),
            new Employee("Jack", "London", 5543, 53, Position.WORKER),
            new Employee("Eric", "Jackson", 2534, 22, Position.WORKER),
            new Employee("Andrew", "Bosh", 3456, 44, Position.WORKER),
            new Employee("Joe", "Smith", 723, 30, Position.MANAGER),
            new Employee("Jack", "Gagarin", 7423, 35, Position.MANAGER),
            new Employee("Jane", "London", 7543, 42, Position.WORKER),
            new Employee("Mike", "Jackson", 7534, 31, Position.WORKER),
            new Employee("Jack", "Bosh", 7456, 54, Position.WORKER),
            new Employee("Mark", "Smith", 123, 41, Position.MANAGER),
            new Employee("Jane", "Gagarin", 1423, 28, Position.MANAGER),
            new Employee("Sam", "London", 1543, 52, Position.WORKER),
            new Employee("Jack", "Jackson", 1534, 27, Position.WORKER),
            new Employee("Eric", "Bosh", 1456, 32, Position.WORKER)
    );

    private List<Department> deps = List.of(
            new Department(1, 0, "Head"),
            new Department(2, 1, "West"),
            new Department(3, 1, "East"),
            new Department(4, 2, "Germany"),
            new Department(5, 2, "France"),
            new Department(6, 3, "China"),
            new Department(7, 3, "Japan")
    );

    /**
     * Рассмотим методы по созданию стримов.
     *
     * @throws IOException исключение от Files.
     */
    @Test
    public void creation() throws IOException {
        //Попробуем создать стримы из обектов файловой системы

        //"source.info about lambda.txt" - путь к файлу, который мы хотели бы прочитать, указан относительный путь
        //Метод Files.lines(path) вернет стрим из строк содержащихся в файле
        Stream<String> lines = Files.lines(Paths.get("src/main/resources/source.info about lambda.txt"));
//        Stream<String> lines = Files.lines(Paths.get("C:\\projects\\javalessons\\source.info about lambda.txt"));
//        lines.forEach(System.out::println); //чтобы посмотреть вывод
        //список текущей директории
        Stream<Path> list = Files.list(Paths.get("./"));
//        list.forEach(System.out::println); //чтобы посмотреть вывод
        //обход текущей директории с погружением на 3 уровня вниз
        Stream<Path> walk = Files.walk(Paths.get("./"), 3);
//        walk.forEach(System.out::println); //чтобы посмотреть вывод

        //в стримах примитивные типы не используются (нельзя), но для них есть
        //фабричные методы. Там используются обертки.
        IntStream intStream = IntStream.of(1, 2, 3, 4);
        DoubleStream doubleStream = DoubleStream.of(1.2, 3.4);

        //создание стримы в диапазоне range
        IntStream range = IntStream.range(10, 100); // 10 11 .. 99
        IntStream intStream1 = IntStream.rangeClosed(10, 100); // 10 11 .. 100

        //данные примитивного типа (скаляры) мы можем получить из вне
        //и из них получить стрим можно через класс Arrays и его метод stream()
        int[] ints = {1, 2, 3, 4};
        IntStream stream = Arrays.stream(ints);

        //получим стрим объектов с помощью метода of(...), например стрим String
        Stream<String> stringStream = Stream.of("1", "2", "3");
        //так же можно указать тип от общего предка
        Stream<? extends Serializable> stream1 = Stream.of(1, "2", "3");

        //получние стрима через билдер. Тоже помним про тип данных.
        Stream<String> build = Stream.<String>builder()
                .add("Mike")
                .add("Joe")
                .add("Ben")
                .build();

        //Часто мы получаем коллекции например из БД или с HttpRequest
        //создадим стрим из наших коллекций (ввурху класса созданных)
        Stream<Employee> stream2 = emps.stream();
        //также можно делать параллелтный стрим, который будет обрабатываться в несколько
        //потоков, если есть такая возможность (но это тяжеловестно)
        Stream<Employee> employeeStream = emps.parallelStream();

        //создадим стрим из функции генератора условно бесконечных последовательностей
        //здесь объекты не содержаться, а генерируются при обращении к этому стриму
        Stream<Event> generate = Stream.generate(() ->
                new Event(UUID.randomUUID(), LocalDateTime.now(), "")
        );
//        generate.limit(10).forEach(System.out::println); //чтобы посмотреть вывод

        //генерируем стрим лет с шагом 3
        Stream<Integer> iterate = Stream.iterate(1950, val -> val + 3);
//        iterate.limit(20).forEach(System.out::println); //чтобы посмотреть вывод

        //получим стрим складывая два стрима и у него на выходе будет стрим содержащий элементы
        //первого и второго стримов
        Stream<String> concat = Stream.concat(stringStream, build);
//        concat.forEach(System.out::println); //чтобы посмотреть вывод
    }

    /**
     * Рассмотрим методы по переводу стримов в объекты и не только.
     * Т.е. расмотрим терминальные операции, которые закрывают
     * использование стрима. Терминальные операции нельзя ставить в цепочки.
     * После закрытия стрима, если к нему обратиться, то получим exception.
     */
    @Test
    public void terminate() {
        Stream<Employee> stream = emps.stream();
        stream.count();

        //выведем для каждого employee его возраст
        emps.stream().forEach(employee -> System.out.println(employee.getAge()));
        emps.forEach(employee -> System.out.println(employee.getAge()));

        //если работатем с параллельным стримом то этот метод гарантирует
        //порядок обхода коллекции.
        emps.stream().forEachOrdered(employee -> System.out.println(employee.getAge()));

        //преобразуем стрим в коллекцию List
        //Можно писать свой Коллектор, который будет как то по вашему
        //собирать данные вместе.
        emps.stream().collect(Collectors.toList());

        //преобразуем стрим в массив
        emps.stream().toArray();

        //преобразуем список в мапу. В качестве ключа - id сотрудника,
        //а в качестве значения - фамилия+имя
        //toMap(..) имеет две функции, которые преобразовывают данные в ключ,
        //а вторая в значение, для значения сгенерируем фамилия+имя
        Map<Integer, String> collect
                = emps.stream().collect(Collectors.toMap(
                Employee::getId,
                emp -> String.format("%s %s", emp.getLastName(), emp.getFirstName())
        ));

        //схлопнем стрим интов в какое то значение с помощью метода reduce
        //в данном случае сложим числа
        //Число в конце преобразования получим через orElse() или getAsInt()
        IntStream intStream = IntStream.of(100, 200, 300, 400);
        intStream.reduce((left, right) -> left + right).orElse(0);

        //с помощью дополнительно созданного метода и стрима получим
        //дерево департаментов
        System.out.println(deps.stream().reduce(this::reducer));

        //дополнительные методы хорошие для чисел в статистике
        IntStream.of(100, 200, 300, 400).average();
        IntStream.of(100, 200, 300, 400).max();
        IntStream.of(100, 200, 300, 400).min();
        IntStream.of(100, 200, 300, 400).sum();
        IntStream.of(100, 200, 300, 400).summaryStatistics();

        //также можно получить максимум для сотрудников из списка
        emps.stream().max(Comparator.comparingInt(Employee::getAge));

        //можно найти что то первое в стриме
        emps.stream().findAny();
        emps.stream().findFirst(); //для || стримов

        //преобразование стрима в булевое значение
        // noneMatch - ни один из объектов не соответствует условию
        emps.stream().noneMatch(employee -> employee.getAge() > 60); // true
        // anyMatch - хотя бы один из объектов соответствует условию
        emps.stream().anyMatch(employee -> employee.getPosition() == Position.CHEF); // true
        // allMatch - все объекты соответствуют условию
        emps.stream().allMatch(employee -> employee.getAge() > 18); // true
    }

    /**
     * Рассмотрим какая операции и трансформации нам доступны над стримами.
     * Методы трансформации можно объелинять в цепочки.
     */
    @Test
    public void transform() {
        //берем стрим примитивов и создаем из него стрим состоящий из объектов
        //int => Long
        LongStream longStream = IntStream.of(100, 200, 300, 400).mapToLong(Long::valueOf);

        //берем стрим примитивов и создаем из него стрим состоящий из объектов
        //создаем стрим объектов Event.
        //в поле объекта timeTag будет передаваться int число как год
        Stream<Event> eventStream = IntStream.of(100, 200, 300, 400).mapToObj(value ->
                new Event(UUID.randomUUID(), LocalDateTime.of(value, 12, 1, 12, 0), "")
        );

        //исключение дубликатов
        IntStream.of(100, 200, 300, 400, 100, 200).distinct(); // 100, 200, 300, 400

        //использование метода filter()
        //например мы отфильтровываем, т.е. отсеиваем тех кто есть Position.CHEF
        Stream<Employee> employeeStream = emps.stream().filter(employee -> employee.getPosition() != Position.CHEF);

        //skip - пропускает количество элементов
        //limit - ограничение пропущенных элементов
        emps.stream()
                .skip(3)
                .limit(5);

        //модифицируем наш стрим с методом sorted(),
        //с помощью peek установим ывсем сотрудникам 18,
        //далее с помощью map преобразуем один элемент в другой, т.е. на вход нам пришел
        //стрим сотруднуков, а на выходе мы аолучили String фамилия+имя
        emps.stream()
                .sorted(Comparator.comparingInt(Employee::getAge))
                .peek(emp -> emp.setAge(18))
                .map(emp -> String.format("%s %s", emp.getLastName(), emp.getFirstName()));

        //takeWhile() [как тригер] - будет отрабатывать стрим до тех пор пока не выполнится условие переданное
        //в скобках. Один раз совпало условие и всё стрим дальше не идет. Т.е. этот
        //метод ни как filter
        emps.stream().takeWhile(employee -> employee.getAge() > 30).forEach(System.out::println);
        System.out.println();
        //dropWhile() [как тригер] - запускает проход стрима как только условие не выполнится, т.е.
        //до >30 никто не проходит, а как только <30, в нашем случаем 26, то это
        //условие снимается и пропускаются все.
        //метод ни как filter
        emps.stream().dropWhile(employee -> employee.getAge() > 30).forEach(System.out::println);
        System.out.println();

        //flatMap - преобразовывает стрим в большее количество элементов, чем есть
        //в начальном стриме, так же можно один стрим преобразовать в другой.
        IntStream.of(100, 200, 300, 400)
                .flatMap(value -> IntStream.of(value - 50, value))
                .forEach(System.out::println);
    }

    /**
     * Здесь показано как работа со стримами происходит в жизни.
     */
    @Test
    public void real() {
        Stream<Employee> empl = emps.stream()
                .filter(employee ->
                        employee.getAge() <= 30 && employee.getPosition() != Position.WORKER
                )
                .sorted(Comparator.comparing(Employee::getLastName));

        //в этом методе будет вызвана терминальная операция.
        print(empl);

        Stream<Employee> sorted = emps.stream()
                .filter(employee -> employee.getAge() > 40)
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .limit(4);

        //в этом методе будет вызвана терминальная операция.
        print(sorted);

        //преобразуем список сотрудников в список чисел
        IntSummaryStatistics statistics = emps.stream()
                .mapToInt(Employee::getAge)
                .summaryStatistics();

        System.out.println(statistics);
    }

    /**
     * Дополнительный метод для вывода информации в виде таблицы.
     *
     * @param stream стрим сотрудников
     */
    private void print(Stream<Employee> stream) {
        stream
                .map(emp -> String.format(
                        "%4d | %-15s %-10s age %s %s",
                        emp.getId(),
                        emp.getLastName(),
                        emp.getFirstName(),
                        emp.getAge(),
                        emp.getPosition()
                ))
                .forEach(System.out::println);

        System.out.println();
    }

    /**
     * Метод помагающий выводить объекты в виде дерева.
     * Рекурсивная проверка.
     * Проверяем что каждый parent либо яляется родителем
     * переденному child либо дает не проверку своих детей
     * на проверку, что они являются родителями для этого child.
     * Этот метод генерирует дерево из плоского списка.
     *
     * @param parent родительский департамент
     * @param child  дочерний департамент
     * @return родительский департамент
     */
    public Department reducer(Department parent, Department child) {
        if (child.getParent() == parent.getId()) {
            parent.getChild().add(child);
        } else {
            parent.getChild().forEach(subParent -> reducer(subParent, child));
        }
        return parent;
    }
}