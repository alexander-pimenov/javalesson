package ru.pimalex1978.split;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Пример из статьи:
 * <a href="http://www.quizful.net/post/Java-RegExp">ссылка на источник</a>
 * <p>
 * В нашей программе есть два метода для проверки имени пользователя на
 * валидность. Первый метод checkWithRegExp(String userNameString) использует
 * регулярное выражение для проверки валидности, а второй
 * dumbCheck(String userNameString) делает проверку "Вручную".
 * <p>
 * И многие другие примеры.
 */

public class UserNameCheck {
    public static void main(String[] args) {
        System.out.println("Cool check:");

        System.out.println(checkWithRegExp("_@BEST"));
        System.out.println(checkWithRegExp("vovan"));
        System.out.println(checkWithRegExp("vo"));
        System.out.println(checkWithRegExp("Z@OZA"));

        System.out.println("\nDumb check:");

        System.out.println(dumbCheck("_@BEST"));
        System.out.println(dumbCheck("vovan"));
        System.out.println(dumbCheck("vo"));
        System.out.println(dumbCheck("Z@OZA"));

        System.out.println("\nBACON check:");

        System.out.println(testWORD("BACON"));      //true
        System.out.println(testWORD("  BACON"));    //false
        System.out.println(testWORD("BACON  "));    //false
        System.out.println(testWORD("^BACON$"));    //false
        System.out.println(testWORD("bacon"));      //false

        System.out.println("\nCheck end of link:");

        System.out.println(testLink("trololo.com"));     //true
        System.out.println(testLink("trololo.ua "));     //false
        System.out.println(testLink("trololo.ua"));      //true
        System.out.println(testLink("trololo/ua"));      //false
        System.out.println(testLink("i love java because it is cool.ru"));      //true
        System.out.println(testLink("BACON.ru"));        //true
        System.out.println(testLink("BACON.de"));        //false

        System.out.println("\nCheck word pizza:");

        System.out.println(testWord("pizza"));   //true
        System.out.println(testWord("@pizza"));  //false
        System.out.println(testWord("pizza3"));  //false


    }

    /*"^[a-z0-9_-]{3,15}$"
     * Разберем её по кусочкам:
     * ^ - начало строки
     * [a-z0-9_-] - символ который может быть маленькой латинской
     * буквой или цифрой или символом подчеркивания.
     * {3,15} - предыдущий объект(смотри выше) может повторяться от 3-х до 15 раз.*/
    public static boolean checkWithRegExp(String userNameString) {
        Pattern p = Pattern.compile("^[a-z0-9_-]{3,15}$");
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }

    public static boolean dumbCheck(String userNameString) {

        char[] symbols = userNameString.toCharArray();
        if (symbols.length < 3 || symbols.length > 15) return false;

        String validationString = "abcdefghijklmnopqrstuvwxyz0123456789_";

        for (char c : symbols) {
            if (validationString.indexOf(c) == -1) return false;
        }

        return true;
    }

    /*Метод проверит строку на содержание в ней слова BACON*/
    public static boolean testWORD(String testString) {
        Pattern p = Pattern.compile("^BACON$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    /*Метод проверки того, что строка заканчивается на .com или .ru или .ua.
     * Этакий очень примитивный валидатор ссылки.
     * + - означает, что сначала может идти любое количество любых символов (от одного)
     * \\. - экранирование точки. Таким образом мы указываем, что идет именно точка,
     *  а не любой символ. (com|ua|ru) - тут все просто: либо com, либо ua, либо ru.*/
    public static boolean testLink(String testString) {
        Pattern p = Pattern.compile(".+\\.(com|ua|ru)");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    /*Внутри символьных классов есть свои собственные метасимволы:
     * ^ - логическое НЕ. Например [^ABC] - не A или B или C, но
     * все остальные символы подходят.
     * - -(дефис) интервал символов; так, выражение <H[1-6]> эквивалентно <H[123456]>
     * "^[a-z]+" = начало строки + любой символ в пределах a-z (abcdef...z)
     * любое количество раз (от одного).*/
    public static boolean testWord(String testString) {
        Pattern p = Pattern.compile("^[a-z]+");
        Matcher m = p.matcher(testString);
        return m.matches();
    }
}
