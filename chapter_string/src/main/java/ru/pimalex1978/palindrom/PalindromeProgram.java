package ru.pimalex1978.palindrom;

import java.util.Scanner;

/**
 * <a href="https://javaconceptoftheday.com/palindrome-program-in-java/">ссылка на источник</a>
 */
public class PalindromeProgram {
    private static boolean isItPalindrome(String inputString) {
        //Clean inpuString by removing spaces and negating the case of the letters

        String cleanInputString = inputString.replaceAll("\\s+", "").toLowerCase();

        //Convert cleanInputString to char array

        char[] charArray = cleanInputString.toCharArray();

        //Let forward index point at first character

        int forward = 0;

        //And backward index point at last character
        //И обратная указательная точка на последний символ

        int backward = charArray.length - 1;

        //start iterating charArray from both ends simultaneously
        //начать итерацию charArray с обоих концов одновременно

        while (forward <= backward) {
            if (charArray[forward] != charArray[backward]) {
                //If chars at both ends are not same, return false

                return false;
            }

            //If chars at both ends are same, increment forward and decrement backward

            forward++;

            backward--;
        }

        return true;
    }

    public static void main(String[] args) {
        //Take the input string from the user

        System.out.println("Enter the input String...");

        Scanner sc = new Scanner(System.in);

        String inputString = sc.nextLine();

        if (isItPalindrome(inputString)) {
            System.out.println(inputString + " is a palindrome");
        } else {
            System.out.println(inputString + " is not a palindrome");
        }

        sc.close();
    }
}