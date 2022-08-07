package ru.pimalex1978.streamepam;

import ru.pimalex1978.streamepam.beans.Department;
import ru.pimalex1978.streamepam.beans.Developer;
import ru.pimalex1978.streamepam.beans.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class StreamCollectorMain {
    public static void main(String[] args) {

        System.out.println("===коллекторы в стримах===");

        //мапа скилов бекэнд разработчика
        Map<String, Integer> backendSkillMatrix = new HashMap<>();
        backendSkillMatrix.put("Java", 4);
        backendSkillMatrix.put("Scala", 3);
        backendSkillMatrix.put("Kotlin", 1);

        //создаем бекэнд разработчика
        Developer backender = new Developer("Petja", backendSkillMatrix);

        //мапа скилов фронтенд разработчика
        Map<String, Integer> frontendSkillMatrix = new HashMap<>();
        frontendSkillMatrix.put("React", 90);
        frontendSkillMatrix.put("Angular", 80);
        frontendSkillMatrix.put("Kotlin", 4);

        //создаем фронтенд разработчика
        Developer front = new Developer("Vasja", frontendSkillMatrix);

        //собираем список из девелоперов
        List<Developer> projectTeam = new ArrayList<>();
        projectTeam.add(backender);
        projectTeam.add(front);

        /*Хотим распечатать все скилы у которых уровень больше 1*/

        //прийдется обходить коллекцию внутри коллекции
        List<String> result = projectTeam.stream()
                .map(e -> e.getSkillMatrix().entrySet()) //получим энтрисет
                .flatMap(set -> set.stream()) //из каждого сета делаем стрим
                .peek(System.out::println) //возьмем посмотрим что проходит
                .filter(item -> item.getValue() > 2) //идем по всем value и проверяем их на условие
                .map(item -> item.getKey()) //теперь у каждого item достанем имя
                .distinct() //чтоб не было повторений
                .collect(Collectors.toList()); //данные соберем в список

        System.out.println(result);

        /*несколько различных способов суммирования данных*/
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(ints.stream().mapToInt(i -> i).sum());
        System.out.println(ints.stream().reduce(0, Integer::sum));
        System.out.println(ints.stream().collect(summingInt(Integer::intValue)));


        /*создадим список работников и поработаем с ними*/
        List<Employee> employees = StreamCollectorMain.hireEmployees();
        //хотим получить мапу
        Map<String, Department> resultMap = employees.stream()
                .filter(e -> e.getAge() > 30)
                .sorted(comparing(e -> e.getName()))
                .collect(Collectors.toMap(Employee::getName, Employee::getDepartment)); //ставим ключ-имя, значение-департамент
        System.out.println(resultMap);

        /*collector joining - сборка в строковое представление*/
        String delimitedNames = employees.stream()
                .map(Employee::getName) //достаем из каждого сотрудника имя
                .collect(Collectors.joining(" # "));

        System.out.println(delimitedNames);

        /*с помощью collect можно даже найти максимум или минимум,
         * указав конечно компаратор*/
        Department maxByDepartment = employees.stream()
                .map(Employee::getDepartment)
                .collect(maxBy(comparing(Department::getName)))//сравниваем департаменты по имени и находим max
                .get();

        System.out.println(maxByDepartment);

        /*сгруппируем все данные по департаментам и потом получим статистики*/
        Map<Department, Long> groupByDepartment = employees.stream()
                .collect(groupingBy(Employee::getDepartment, counting())); // группируем по департаменту и считаем сколько в этом департаменте

        System.out.println(groupByDepartment);

        // группируем по департаменту, достаем все скилы и выводим скилы через &
        Map<Department, String> stringMap = employees.stream()
                .collect(groupingBy(Employee::getDepartment, mapping(Employee::getSkill, joining(" & "))));

        System.out.println(stringMap);
    }

    /**
     * Метод создающий список сотрудников.
     *
     * @return список сотрудников.
     */
    private static List<Employee> hireEmployees() {
        Department financialDepartment = new Department("Finance department");
        Department backendDepartment = new Department("Backend department");

        return Arrays.asList(new Employee(19, "Vasja", "Java", 1, financialDepartment),
                new Employee(20, "Petja", ".NET", 3, financialDepartment),
                new Employee(38, "Johann", "Delfi", 5, backendDepartment),
                new Employee(45, "Sergey", "Java", 2, backendDepartment));
    }
}
