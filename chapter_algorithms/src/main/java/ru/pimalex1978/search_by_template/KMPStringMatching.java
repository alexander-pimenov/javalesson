package ru.pimalex1978.search_by_template;

// JAVA-программа для реализации шаблона KMP (Кнут Моррис Пратт)
// алгоритм поиска
public class KMPStringMatching {
    private void KMPSearch(String txt, String pat) {
        int M = pat.length(); // длина паттерна
        int N = txt.length(); // длина текста

        // создаем lps [], который будет содержать самый длинные
        // префиксные суффиксные значения для шаблона
        int[] lps = new int[M];
        int j = 0; // индекс для pat[]

        // Предварительная обработка шаблона (вычисление lps[]  массив)
        computeLPSArray(pat, M, lps);

        int i = 0; //индекс для txt[]

        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern at index " + (i - j));
                j = lps[j - 1];
            }

            //несоответствие после j совпадений
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Не совпадают символы lps [0..lps [j-1]],
                // они все равно будут совпадать
                if (j != 0) j = lps[j - 1];
                else i = i + 1;
            }
        }
    }

    private void computeLPSArray(String pat, int M, int lps[]) {
        // длина предыдущего длинного суффикса префикса
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps [0] всегда 0

        // цикл вычисляет lps [i] для i = 1 до M-1
        while (i < M) {

            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else { //(pat [i]! = pat [len])
                // Это сложно. Рассмотрим пример.

                // AAACAAAA и i = 7. Идея похожа

                // искать шаг.

                if (len != 0) {

                    len = lps[len - 1];


                    // Также обратите внимание, что мы не увеличиваем

                    // я здесь

                } else // if (len == 0)

                {

                    lps[i] = len;

                    i++;

                }

            }

        }

    }


    // Программа драйвера для проверки вышеуказанной функции

    public static void main(String args[]) {

        String txt = "ABABDABACDABABCABAB";

        String pat = "ABABCABAB";

        new KMPStringMatching().KMPSearch(pat, txt);

    }

}
// Этот код предоставлен Амитом Хандельвалом.

