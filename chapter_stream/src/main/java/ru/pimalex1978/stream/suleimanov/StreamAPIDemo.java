package ru.pimalex1978.stream.suleimanov;

import ru.pimalex1978.stream.suleimanov.model.Specialist;
import ru.pimalex1978.stream.suleimanov.model.Specialty;
import ru.pimalex1978.stream.suleimanov.util.StreamAPIUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Основы работы со Stream API в Java
 * https://www.youtube.com/watch?v=aC0-KsuPG0I
 */
public class StreamAPIDemo {
    public static void main(String[] args) {
        //List<Specialist> specialists = OldApproachUtil.getSpecialists();
        List<Specialist> specialists = StreamAPIUtil.getSpecialists();

        // Filter: Фильтрация коллекций
//        List<Specialist> engineers = OldApproachUtil.filterBySpecialty(specialists, Specialty.ENGINEER);
        List<Specialist> engineers = StreamAPIUtil.filterBySpecialty(specialists, Specialty.ENGINEER);
        StreamAPIUtil.printSpecialists(engineers);

        // Sorting: Сортировка коллекций
        List<Specialist> sortedSpecialistsAsc = StreamAPIUtil.sortSpecialistsByNameAsc(specialists);
        List<Specialist> sortedSpecialistsDesc = StreamAPIUtil.sortSpecialistsByNameDesc(specialists);
        StreamAPIUtil.printSpecialists(sortedSpecialistsAsc);
        StreamAPIUtil.printSpecialists(sortedSpecialistsDesc);

        // Max salary: Поиск максимального значения
        Specialist withMaxSalary = StreamAPIUtil.findWithMaxSalary(specialists);
        System.out.println(withMaxSalary);
        // Min salary: Поиск минимального значения
        Specialist withMinSalary = StreamAPIUtil.findWithMinSalary(specialists);
        System.out.println(withMinSalary);

        // GroupingЖ Группировка
        Map<Specialty, List<Specialist>> groupedBySpecialty = StreamAPIUtil.groupBySpecialty(specialists);
        System.out.println(groupedBySpecialty);

        //Совпадения по заданному условию
        // All engineers
//        boolean allEngineers = OldApproachUtil.matchAllEngineers(specialists);
        boolean allEngineers = StreamAPIUtil.matchAllEngineers(specialists);
        System.out.println(allEngineers);

        // Any engineers
//        boolean anyEngineer = OldApproachUtil.matchAnyEngineer(specialists);
        boolean anyEngineer = StreamAPIUtil.matchAnyEngineer(specialists);
        System.out.println(anyEngineer);

        // All salary more then
        //boolean allSalaryMoreThen1000 = OldApproachUtil.matchAllSalaryMoreThen(specialists, new BigDecimal(10000));
        boolean allSalaryMoreThen1000 = StreamAPIUtil.matchAllSalaryMoreThen(specialists, new BigDecimal(10000));
        System.out.println(allSalaryMoreThen1000);

        // No one with salary more then
//        boolean noOneWithSalaryMoreThen10000 = OldApproachUtil.matchNoneSalaryMoreThen(specialists, new BigDecimal(10000));
        boolean noOneWithSalaryMoreThen10000 = StreamAPIUtil.matchNoneSalaryMoreThen(specialists, new BigDecimal(10000));
        System.out.println(noOneWithSalaryMoreThen10000);
    }
}
