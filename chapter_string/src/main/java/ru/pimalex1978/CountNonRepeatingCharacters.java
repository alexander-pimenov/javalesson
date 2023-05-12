package ru.pimalex1978;

//Определить максимальную подстроку в строке не повторяющихся символов
//Пример 1:
//Input: s = "abcabcbb"
//Output: 3 - "abc"
//Пример 2:
//Input: s = "bbbbbbbb"
//Output: 1 - "b"
//Пример 3:
//Input: s = "pwwkew"
//Output: 3 - "wke"
//Constraints:
//0 <= s.length() <= 5*10^4
//s consists of English letters , digits, symbols and spaces.

//Здесь есть идея двух указателей, чтобы понимать, какая подстрока может быть самой длинной.
//Берем первый указатель, идем по строке и ищем, где он начинает повторяться. И нужно сохранить значение о том, что он начал повторяться.
//Чтобы хранить символы, которые были встречены используем мапу (ключ-значение: ключ - символ, значение - его индекс).
//И если символ не встречался ранее, то мы его индекс можем в мапу. Если символ встречался, то указатель можно сдвинуть до первого
//вхождения этого символа.
//

import java.util.HashMap;
import java.util.Map;

public class CountNonRepeatingCharacters {
    public static void main(String[] args) {
        String input1 = "abcabcbb";
        String input2 = "bbbbbbbb";
        String input3 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(input1));
        System.out.println(lengthOfLongestSubstring(input2));
        System.out.println(lengthOfLongestSubstring(input3));
    }

    public static int lengthOfLongestSubstring(String s) {
        int start = 0;
        int maxLength = 0;
        Map<Character, Integer> processedChars = new HashMap<>();
        //идем по всем символам строки в цикле
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //узнаем, был или нет данный символ
            if (processedChars.containsKey(c) && start <= processedChars.get(c)) {
                //если был, то передвигаем указатель вправо
                start = processedChars.get(c) + 1;
            } else {
                //если нет, то обновим максимум,
                //если он больше, то
                //сравниваем с текущим значением и там где находимся (i - start) плюс 1
                maxLength = Math.max(maxLength, i - start + 1);
                System.out.println("maxLength => " + maxLength);
            }
            processedChars.put(c, i);
            System.out.println(processedChars);
        }
        return maxLength;
    }
}
