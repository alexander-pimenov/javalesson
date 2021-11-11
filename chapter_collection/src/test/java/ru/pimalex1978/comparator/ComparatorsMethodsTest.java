package ru.pimalex1978.comparator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

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


}
