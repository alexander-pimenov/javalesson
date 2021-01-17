package ru.pimalex1978.search_by_template;

// Java-программа для поиска наивного шаблона, т.е. по паттерну
//http://espressocode.top/java-program-for-naive-algorithm-for-pattern-searching/
/*Поиск по шаблону является важной проблемой в информатике. Когда мы
 * ищем строку в файле блокнота/слова, в браузере или в базе данных,
 * для отображения результатов поиска используются алгоритмы поиска
 * по шаблону.*/
public class NaiveSearch {
    private static void search(String txt, String pat) {
        int M = pat.length();
        int N = txt.length();

        /*Цикл для скольжения по очереди*/
        for (int i = 0; i <= (N - M); i++) {
            int j;

            /* Для текущего индекса i, проверьте шаблон совпадение */
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == M) { //если pat [0 ... M-1] = txt [i, i + 1, ... i + M-1]
                System.out.println("Pattern found at index " + i);
            }
        }
    }

    public static void main(String[] args) {
        String txt = "AABAACAADAABAAABAA";
        String pat = "AABA";
        search(txt, pat);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        String txt2 = "THIS IS A TEST TEXT FOR TEST";
        String pat2 = "TEST";
        search(txt2, pat2);
    }
}

//Result:
//Pattern found at index 0
//Pattern found at index 9
//Pattern found at index 13
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//Pattern found at index 10
//Pattern found at index 24
