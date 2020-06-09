package ru.pimalex1978;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Solution {

    public static Collection<BigDecimal> filter(Collection<BigDecimal> numbers) throws MyException {

        BigDecimal max = Collections.max(numbers);
        //System.out.println(max);
        BigDecimal min = Collections.min(numbers);
        //System.out.println(min);
        BigDecimal divide = max.divide(min, RoundingMode.UNNECESSARY);
        //System.out.println(divide);

        Collection<BigDecimal> result = new ArrayList<>();

        Iterator<BigDecimal> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            BigDecimal next = iterator.next();
            if ((next.compareTo(divide)) >= 0) {
                result.add(next);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        List<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal("10.0"));
        list.add(new BigDecimal("5.0"));
        list.add(new BigDecimal("3.0"));
        list.add(new BigDecimal("7.0"));
        list.add(BigDecimal.valueOf(2.0));
        list.add(BigDecimal.valueOf(6.0));
        list.add(BigDecimal.valueOf(8.0));
        list.add(BigDecimal.valueOf(9.0));
        System.out.println(list);

        Collection<BigDecimal> filtered = null;
        try {
            filtered = filter(list);
        } catch (MyException e) {
            e.printStackTrace();
        }

        System.out.println(filtered);
    }
}