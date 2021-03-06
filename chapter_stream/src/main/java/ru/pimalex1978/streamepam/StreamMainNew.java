package ru.pimalex1978.streamepam;

import ru.pimalex1978.streamepam.beans.Department;
import ru.pimalex1978.streamepam.beans.Employee;

import java.util.*;

public class StreamMainNew {
    public static void main(String[] args) {
        List<Employee> employees = hireEmployees();

        System.out.println("============Stream style============");
        employees.stream()
                .filter(e -> e.getLevel() > 2)
                .map(g -> g.getDepartment())
                .distinct()
                .sorted(Comparator.comparing(e -> e.getName()))
                .forEach(e -> System.out.println(e.getName()));

        System.out.println("Average is "
                + employees.stream()
                .mapToInt(Employee::getAge) //получаем стрим возрастов
                .summaryStatistics() //IntSummaryStatistics{count=4, sum=122, min=19, average=30,500000, max=45}
                .getAverage());

        System.out.println("============Old style============");
        List<Department> deps = new ArrayList<>();

        for (Employee e : employees) {
            if (e.getLevel() > 2) {
                deps.add(e.getDepartment());
            }
        }

        Set<Department> distinctDeps = new HashSet<>();
        for (Department dep : deps) {
            distinctDeps.add(dep);
        }

        List<Department> depDistinctList = new ArrayList<>(distinctDeps);
        Comparator<Department> comparator = new Comparator<Department>() {
            @Override
            public int compare(Department o1, Department o2) {
                if (o1 == o2) {
                    return 0;
                }
                if (o1 == null) {
                    return -1;
                }
                if (o2 == null) {
                    return 1;
                }
                return o1.getName().compareTo(o2.getName());
            }
        };

        Collections.sort(depDistinctList, comparator);

        for (Department dep : depDistinctList) {
            System.out.println(dep.getName());
        }

        int sum = 0;
        int counter = 0;
        for (Employee empl : employees) {
            sum += empl.getAge();
            counter++;
        }
        System.out.println("Average is " + (sum / counter));
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
