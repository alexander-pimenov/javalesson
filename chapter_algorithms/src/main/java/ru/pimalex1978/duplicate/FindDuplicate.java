package ru.pimalex1978.duplicate;

import java.util.*;

import static java.util.Arrays.asList;

/**
 * В классе реализован метод по поиску дубликатов в списске.
 * Вторым параметром указано количество повторений этих дупликатов, например в списке
 * (1, 2, 1, 3, 1, 2, 4, 4) цифра 1 встречается 3 раза, цифра 2 встречается 2 раза,
 * цифра 3 встречается 1 раз, цифра 4 встречается 2 раза.
 * Если вторым параметром укажем 2, то на выходе получим список [2, 4],
 * т.е. только те элементы, которые встречаются 2 раза. Если укажем 3, то
 * получим [1], т.е 1 встречается 3 раза.
 * Если в списке повторяются null значения, то возвращаем пустой список,
 * а если количество null совпадет со значением второго парметра, то количество null не будет показано.
 */
public class FindDuplicate {
    public static void main(String[] args) {
        List<Integer> duplicates1 = FindDuplicate.findDuplicates(asList(-1, 1, -1, 1, 3, 2, 5, 6, -1, 3, 6), 3);
        System.out.println(duplicates1); //[-1]

        List<Integer> duplicates2 = FindDuplicate.findDuplicates(asList(1, 1, null, 2, 5, 6, 1, 3, 6, null), 2);
        System.out.println(duplicates2); //[6]

        List<Integer> duplicates3 = FindDuplicate.findDuplicates(asList(null, null, 2, 5, 6, 1, 3), 2);
        System.out.println(duplicates3); //[]
    }

    public static List<Integer> findDuplicates(List<Integer> integers, int numberOfDuplicates) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> uniqueSet = new HashSet<>(integers);
        for (Integer temp : uniqueSet) {
            if (temp == null) {
                continue;
            }
            int i = Collections.frequency(integers, temp);
            if (i == numberOfDuplicates) {
                result.add(temp);
            }
        }
        return result;
    }
}
