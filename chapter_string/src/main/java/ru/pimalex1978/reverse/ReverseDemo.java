package ru.pimalex1978.reverse;

public class ReverseDemo {
    public static void main(String[] args) {
        String s = "I am a best Tester in EPAM";
        reverse1(s);
        reverse2(s);
    }

    private static void reverse1(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        final String reverseString = sb.toString();
        System.out.println(reverseString);
    }

    private static void reverse2(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length()-1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        String reverseString = sb.toString();
        System.out.println(reverseString);
    }
}
