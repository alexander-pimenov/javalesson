package ru.pimalex1978.split;

import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
//        String pattern = "[a-z]+";
//        String pattern = "(\\b[a-z]*?\\b)";
        String pattern = "(\\b[a-zA-Z]*?\\b)";
        String text = "Code 2 learn java tutorial abbyy. Use SBESET.EXE to manually configure SB resources or \n" +
                "    disable SB Emulation. By default, SB Emulation in DOS follows \n" +
                "    the Windows settings.";
//        Pattern p = Pattern.compile(pattern);
//        Matcher m = p.matcher(text);
//        while (m.find()) {
//            System.out.print(text.substring(m.start(), m.end()) + " ");
//        }

        String[] res = text.split(pattern);
        System.out.println(Arrays.toString(res));
        System.out.println();

        for (int i = 0; i < res.length; i++) {
            res[i] = firstUpperCase(res[i]);
        }

        System.out.println(Arrays.toString(res));
    }

    public static String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";//или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
