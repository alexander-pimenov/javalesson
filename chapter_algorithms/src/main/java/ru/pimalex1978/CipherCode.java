package ru.pimalex1978;

import java.util.Scanner;

/**
 * Здесь рассмотрены некоторые варианты кодировки или просто сдвига посимвольно в строке
 */

public class CipherCode {

    static String alfa = "абвгдежзиклмнопрстуфхцчшщъыьэюяАБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЪЫьЭЮЯ !?.,";

    public static void main(String[] args) {

        String ret;
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите строку:");
        //String str = "просто пример строки"; //sc.nextLine();
        String str = "я ваша тетя"; //sc.nextLine();
        System.out.print("Введите строку:");
        String key = "ключ"; //sc.nextLine();
        System.out.println("");
        System.out.println(str);
        System.out.println(key);

        ret = cryptStr(str, key);

        System.out.println("туда");
        System.out.println(ret);
        ret = cryptStr(ret, key);
        System.out.println("обратно");
        System.out.println(ret);


        String row = "yandex";
        String row2 = "a";
        String row3 = "y";
        String row4 = "z";
        String row5 = "bcc";
        String row6 = "def";

//        String s1 = "abcdef";
//        char[] str = s1.toCharArray();
//        StringBuilder s2 = new StringBuilder();
//        s2.append(str[str.length-1]);
//        for (int i=0; i<s1.length()-1; i++) {
//            s2.append(str[i]);
//        }
//        System.out.println(s2);


        System.out.println(cycle(row));

        System.out.println(substring(row));

        System.out.println(encode(row, 2));
        System.out.println(encode(row2, 2));
        System.out.println(encode(row3, 2));
        System.out.println(encode(row4, 2));
        System.out.println(row5 + " -> " + encode(row5, 2));
        System.out.println(row5 + " -> " + code(row5, 2));
        System.out.println(row6 + " -> " + encode(row6, 2));
        System.out.println(row6 + " -> " + code(row6, 2));

    }

    private static String cryptStr(String str, String key) {
        StringBuilder res = new StringBuilder();
        char[] codeKey = key.toCharArray(); // разложили ключ в байты
        for (int i = 0; i < codeKey.length; i++)
            codeKey[i] = (char) alfa.indexOf(codeKey[i]); // привели символы к номерам символов в alfa
        char[] codeStr = str.toCharArray(); // разложили строку в байты
        for (int i = 0; i < codeStr.length; i++) {
            codeStr[i] = (char) alfa.indexOf(codeStr[i]); // привели символ к номеру из alfa
            res.append(alfa.charAt(codeStr[i] ^ codeKey[i % codeKey.length])); // сохранили символ alfa с номером XOR кодов
        }
        return res.toString();
    }

    public static String cycle(String row) { // сдвиг вправо на 1 элемент
        char[] m = row.toCharArray();
        int n = 1;
        char[] result = new char[m.length];
        for (int i = 0; i < m.length - n; i++) {
            result[i + n] = m[i];
        }

        for (int i = 0; i < n; i++) {
            result[i] = m[m.length - n + i];
        }

        return new String(result);
    }

    public static String substring(String row) { // сдвиг вправо на 2 элемента
        int move = 2;
        int cursor = row.length() - move;
        return row.substring(cursor) + row.substring(0, cursor);
    }

    public static String encode(String text, int move) {
        char[] chars = text.toCharArray();
        char[] result = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int i1 = chars[i] + move;
            System.out.print(i1);
            System.out.println((char) i1);
            result[i] = (char) (chars[i] + move);
        }
        return new String(result) + " - такой ответ";
    }

    public static String code(String text, int move) {
        char[] chars = text.toCharArray();
        char[] result = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int i1 = chars[i] - move;
            System.out.print(i1);
            System.out.println((char) i1);
            result[i] = (char) (chars[i] - move);
        }
        return new String(result) + " - такой ответ";
    }
}
