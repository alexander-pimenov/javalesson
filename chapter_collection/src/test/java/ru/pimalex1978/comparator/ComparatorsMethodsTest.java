package ru.pimalex1978.comparator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * Руководство по Java 8 Comparator.comparing ()
 * https://www.baeldung.com/java-8-comparator-comparing
 */
public class ComparatorsMethodsTest {

    Employee[] employees = new Employee[]{
            new Employee("John", 25, 3000.0, 9922001),
            new Employee("Ace", 22, 2000.0, 5924001),
            new Employee("Keith", 35, 4000.0, 3924401)
    };

    Employee[] employeesArrayWithNulls = new Employee[]{
            new Employee("John", 25, 3000.0, 9922001),
            null,
            new Employee("Ace", 22, 2000.0, 5924001),
            null,
            new Employee("Keith", 35, 4000.0, 3924401)
    };

    Employee[] someMoreEmployees = new Employee[]{
            new Employee("Jake", 25, 3000.0, 9922001),
            new Employee("Jake", 22, 2000.0, 5924001),
            new Employee("Ace", 22, 3000.0, 6423001),
            new Employee("Keith", 35, 4000.0, 3924401)
    };

    TreeMap<Integer, Employee> employeeTreeMap;
    Employee[] sortedEmployeesByName;
    Employee[] sortedEmployeesByNameDesc;
    Employee[] sortedEmployeesByAge;
    Employee[] sortedEmployeesByMobile;
    Employee[] sortedEmployeesBySalary;
    Employee[] sortedEmployeesArray_WithNullsFirst;
    Employee[] sortedEmployeesArray_WithNullsLast;
    Employee[] sortedEmployeesByAgeName;
    Employee[] sortedEmployeesByNameAge;

    @Before
    public void initData() {
        sortedEmployeesByName = new Employee[]{
                new Employee("Ace", 22, 2000.0, 5924001),
                new Employee("John", 25, 3000.0, 9922001),
                new Employee("Keith", 35, 4000.0, 3924401)
        };
        sortedEmployeesByNameDesc = new Employee[]{
                new Employee("Keith", 35, 4000.0, 3924401),
                new Employee("John", 25, 3000.0, 9922001),
                new Employee("Ace", 22, 2000.0, 5924001),
        };
        sortedEmployeesByAge = new Employee[]{
                new Employee("Ace", 22, 2000.0, 5924001),
                new Employee("John", 25, 3000.0, 9922001),
                new Employee("Keith", 35, 4000.0, 3924401)
        };

        sortedEmployeesByMobile = new Employee[]{
                new Employee("Keith", 35, 4000.0, 3924401),
                new Employee("Ace", 22, 2000.0, 5924001),
                new Employee("John", 25, 3000.0, 9922001)
        };
        sortedEmployeesBySalary = new Employee[]{
                new Employee("Ace", 22, 2000.0, 5924001),
                new Employee("John", 25, 3000.0, 9922001),
                new Employee("Keith", 35, 4000.0, 3924401)
        };
        sortedEmployeesArray_WithNullsFirst = new Employee[]{
                null,
                null,
                new Employee("Ace", 22, 2000.0, 5924001),
                new Employee("John", 25, 3000.0, 9922001),
                new Employee("Keith", 35, 4000.0, 3924401)
        };
        sortedEmployeesArray_WithNullsLast = new Employee[]{
                new Employee("Ace", 22, 2000.0, 5924001),
                new Employee("John", 25, 3000.0, 9922001),
                new Employee("Keith", 35, 4000.0, 3924401),
                null,
                null
        };
        sortedEmployeesByAgeName = new Employee[]{
                new Employee("Ace", 22, 3000.0, 6423001),
                new Employee("Jake", 22, 2000.0, 5924001),
                new Employee("Jake", 25, 3000.0, 9922001),
                new Employee("Keith", 35, 4000.0, 3924401)
        };
        sortedEmployeesByNameAge = new Employee[]{
                new Employee("Ace", 22, 3000.0, 6423001),
                new Employee("Jake", 22, 2000.0, 5924001),
                new Employee("Jake", 25, 3000.0, 9922001),
                new Employee("Keith", 35, 4000.0, 3924401)
        };

        //TreeMap имеет сортированную структуру по КЛЮЧУ
        employeeTreeMap = new TreeMap<>() {{
            put(2, new Employee("Keith", 35, 4000.0, 3924401));
            put(3, new Employee("Jake", 32, 2000.0, 5924001));
            put(6, new Employee("Jake", 32, 5000.0, 5924001));
            put(4, new Employee("John", 25, 3000.0, 9922001));
            put(5, new Employee("Ace", 22, 3000.0, 6423001));
            put(1, new Employee("Jake", 26, 3000.0, 9922001));
        }};
    }

    /*
     *   static <T,U extends Comparable<? super U>> Comparator<T> comparing(
     *                      Function<? super T,? extends U> keyExtractor)
     */
    @Test
    public void whenComparing_thenSortedByName() {
        Comparator<Employee> employeeNameComparator
                = Comparator.comparing(Employee::getName);

        Arrays.sort(employees, employeeNameComparator);

        assertTrue(Arrays.equals(employees, sortedEmployeesByName));
    }

    @Test
    public void whenComparingWithComparator_thenSortedByNameDesc() {
        Comparator<Employee> employeeNameComparator
                = Comparator.comparing(
                Employee::getName, Comparator.reverseOrder());
        //или так:
//        Comparator<Employee> employeeNameComparator
//                = Comparator.comparing(
//                Employee::getName, (s1, s2) -> {
//                    return s2.compareTo(s1);
//                });

        Arrays.sort(employees, employeeNameComparator);

        assertTrue(Arrays.equals(employees, sortedEmployeesByNameDesc));
    }

    @Test
    public void whenReversed_thenSortedByNameDesc() {
        Comparator<Employee> employeeNameComparator
                = Comparator.comparing(Employee::getName);
        Comparator<Employee> employeeNameComparatorReversed
                = employeeNameComparator.reversed();
        Arrays.sort(employees, employeeNameComparatorReversed);
        assertTrue(Arrays.equals(employees, sortedEmployeesByNameDesc));
    }

    @Test
    public void whenComparingInt_thenSortedByAge() {
        Comparator<Employee> employeeAgeComparator
                = Comparator.comparingInt(Employee::getAge);

        Arrays.sort(employees, employeeAgeComparator);

        assertTrue(Arrays.equals(employees, sortedEmployeesByAge));
    }

    @Test
    public void whenComparingLong_thenSortedByMobile() {
        Comparator<Employee> employeeMobileComparator
                = Comparator.comparingLong(Employee::getMobile);

        Arrays.sort(employees, employeeMobileComparator);

        assertTrue(Arrays.equals(employees, sortedEmployeesByMobile));
    }

    @Test
    public void whenComparingDouble_thenSortedBySalary() {
        Comparator<Employee> employeeSalaryComparator
                = Comparator.comparingDouble(Employee::getSalary);

        Arrays.sort(employees, employeeSalaryComparator);

        assertTrue(Arrays.equals(employees, sortedEmployeesBySalary));
    }

    //этот тест проходит благодаря реализации интерфеса Comparable<Employee>
    @Test
    public void whenNaturalOrder_thenSortedByName() {
        Comparator<Employee> employeeNameComparator
                = Comparator.<Employee>naturalOrder();

        Arrays.sort(employees, employeeNameComparator);

        assertTrue(Arrays.equals(employees, sortedEmployeesByName));
    }

    //этот тест проходит благодаря реализации интерфеса Comparable<Employee>
    @Test
    public void whenReverseOrder_thenSortedByNameDesc() {
        Comparator<Employee> employeeNameComparator
                = Comparator.<Employee>reverseOrder();

        Arrays.sort(employees, employeeNameComparator);

        assertTrue(Arrays.equals(employees, sortedEmployeesByNameDesc));
    }

    //Учет нулевых значений в компараторе
    //Функция nullsFirst вернет Comparator, который хранит все нули в начале последовательности упорядочивания:
    @Test
    public void whenNullsFirst_thenSortedByNameWithNullsFirst() {
        Comparator<Employee> employeeNameComparator
                = Comparator.comparing(Employee::getName);
        Comparator<Employee> employeeNameComparator_nullFirst
                = Comparator.nullsFirst(employeeNameComparator);

        Arrays.sort(employeesArrayWithNulls,
                employeeNameComparator_nullFirst);

        assertTrue(Arrays.equals(
                employeesArrayWithNulls,
                sortedEmployeesArray_WithNullsFirst));
    }

    //Учет нулевых значений в компараторе
    //Функция nullsLast вернет Comparator, который сохраняет все нули в конце упорядочивающей последовательности:
    @Test
    public void whenNullsLast_thenSortedByNameWithNullsLast() {
        Comparator<Employee> employeeNameComparator
                = Comparator.comparing(Employee::getName);
        Comparator<Employee> employeeNameComparator_nullLast
                = Comparator.nullsLast(employeeNameComparator);

        Arrays.sort(employeesArrayWithNulls, employeeNameComparator_nullLast);

        assertTrue(Arrays.equals(
                employeesArrayWithNulls, sortedEmployeesArray_WithNullsLast));
    }

    //Использование Comparator.thenComparing
    //Функция thenComparing позволяет настроить лексикографический порядок значений,
    // предоставив несколько ключей сортировки в определенной последовательности.
    //последовательность сравнений в виде возраста, за которым следует имя
    @Test
    public void whenThenComparing_thenSortedByAgeName() {
        Comparator<Employee> employee_Age_Name_Comparator
                = Comparator.comparing(Employee::getAge)
                .thenComparing(Employee::getName);

        Arrays.sort(someMoreEmployees, employee_Age_Name_Comparator);

        assertTrue(Arrays.equals(someMoreEmployees, sortedEmployeesByAgeName));
    }

    //Давайте воспользуемся другой версией thenComparing, то есть thenComparingInt ,
    // изменив лексикографическую последовательность на имя, за которым следует возраст :
    @Test
    public void whenThenComparing_thenSortedByNameAge() {
        Comparator<Employee> employee_Name_Age_Comparator
                = Comparator.comparing(Employee::getName)
                .thenComparingInt(Employee::getAge);

        Arrays.sort(someMoreEmployees, employee_Name_Age_Comparator);

        assertTrue(Arrays.equals(someMoreEmployees,
                sortedEmployeesByNameAge));
    }

    //Точно так же есть функции thenComparingLong и thenComparingDouble для
    // использования длинных и двойных ключей сортировки.

    @Test
    public void compareTest() {
        Comparator<Employee> comparatorEmployee = new Comparator<Employee>() {
            @Override
            public int compare(Employee obj1, Employee obj2) {
                if (obj1 == null) {
                    return -1;
                }
                if (obj2 == null) {
                    return 1;
                }
                int compareName = obj1.getName().compareTo(obj2.getName());
                if (compareName == 0) {
                    if (obj1.getAge() == obj2.getAge()) {
                        int compareSalary = Double.compare(obj1.getSalary(), obj2.getSalary());
                        if (compareSalary == 0) {
                            return Long.compare(obj1.getMobile(), obj2.getMobile());
                        } else {
                            return compareSalary;
                        }
                    } else {
                        return obj1.getAge() - obj2.getAge();
                    }
                } else {
                    return compareName;
                }
            }
        };

        for (Map.Entry<Integer, Employee> entry : employeeTreeMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("-------------------");

        List<Employee> employeeList = new ArrayList<>(employeeTreeMap.values());
        employeeList.forEach(System.out::println);
        System.out.println("-------------------");
        employeeList.sort(comparatorEmployee);
        employeeList.forEach(System.out::println);

        //or - так можно передавать компаратор в TreeMap
        TreeMap<Integer, String> treeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2); //!!!!!! Хороший вариант
            }
        });
    }


}
