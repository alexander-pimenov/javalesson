package ru.pimalex1978.reverse;

public class ReverseDemo {
    public static void main(String[] args) {
        String s = "My Java";
        reverse1(s);
        reverse2(s);
        System.out.println(reverse3(s));
    }

    //1. Using StringBuffer Class
    private static void reverse1(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        final String reverseString = sb.toString();
        System.out.println(reverseString);
    }

    //2. Using iterative method
    private static void reverse2(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        String reverseString = sb.toString();
        System.out.println(reverseString);
    }

    //3. Recursive method to reverse string
    //1st Call —>   recursiveMethod(“My Java”)
    //2nd Call —> recursiveMethod(“y Java”) + “M”
    //3rd Call —>  (recursiveMethod(“ Java”) + “y”) + “M”
    //4rd Call —>  ((recursiveMethod(“Java”) + “ ”) + “y”) + “M”
    //5th call —>  (((recursiveMethod(“ava”) + “J”) + “ ”) +”y”) + “M”
    //6th Call —>  ((((recursiveMethod(“va”) + “a”) + “J”) + “ ”) +”y”) + “M”
    //7th Call —>  (((((recursiveMethod(“a”) + “v”) + “a”) + “J”) + “ ”) +”y”) + “M”
    private static String reverse3(String s) {
        //System.out.println(s); //здесь можно посмотреть ход
        if (s == null || s.length() <= 1) {
            return s;
        }
        return reverse3(s.substring(1)) + s.charAt(0);
    }
}
