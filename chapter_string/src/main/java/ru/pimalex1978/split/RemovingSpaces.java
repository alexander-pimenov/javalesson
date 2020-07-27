package ru.pimalex1978.split;

import java.util.Arrays;
import java.util.List;

public class RemovingSpaces {
    public static void main(String[] args) {
        String[] str = new String[]{"java", "  -jar", "pack.jar  ",
                "   -d", "c:/projects/job4j/chapter_001", "   -n",
                ".xml   ", "-m   ", "-o", "log_found_files_chapter_001.txt"};
        System.out.println("Элементы массива в списке до удаления пробелов:");
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
        System.out.println("===========================");
        System.out.println("Элементы массива в списке после удаления пробелов:");
        List<String> strings = Arrays.asList(str);
        for (String s : strings) {
            String s1 = s.replaceAll("\\s+", "");
            System.out.println(s1);
        }
    }
}
