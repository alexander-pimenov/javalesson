package ru.pimalex1978.palindrom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Palindromes {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("mom");
        list.add("rotator");
        list.add("Malayalam");
        list.add("car");
        list.add("pap");
        list.add("Fogof");
        list.add("Four");
        list.add("Adda");
        list.add("А роза упала на лапу Азора");
        System.out.println("List is: " + list);
        Palindromes.checkPalindrome(list);
    }

    public static void checkPalindrome(List<String> stringList) {
        List<String> stringsResult = new ArrayList<String>();
        for (String i : stringList) {
            String t = i.replaceAll("\\s+", "").toLowerCase();
            if (IntStream.range(0, t.length() / 2).noneMatch(j -> t.charAt(j) != t.charAt(t.length() - j - 1)))
                stringsResult.add(i);
        }
        System.out.println("Palindrome Strings are: " + stringsResult);
    }
}
