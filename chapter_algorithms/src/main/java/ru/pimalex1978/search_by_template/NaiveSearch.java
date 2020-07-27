package ru.pimalex1978.search_by_template;

// Java-программа для поиска наивного шаблона, т.е. по паттерну
public class NaiveSearch {
    private static void search(String txt, String pat) {
        int M = pat.length();
        int N = txt.length();

        /*Цикл для скольжения по очереди*/
        for (int i = 0; i < N - M; i++) {
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
    }
}

//Result:
//Pattern found at index 0
//Pattern found at index 9
//Pattern found at index 13
