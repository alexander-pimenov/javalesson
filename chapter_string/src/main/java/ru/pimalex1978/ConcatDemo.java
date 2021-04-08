package ru.pimalex1978;

public class ConcatDemo {
    public static void main(String[] args) {
        String s = "Some text";
        System.out.println(s.length());
        System.out.println("Text".length());

        s = s.concat("Additional text");
        System.out.println(s);
        s = "Text".concat("Another text");
        System.out.println(s);
        s = "Text" + "Another text";
        System.out.println(s);
    }
}
