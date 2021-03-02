package ru.pimalex1978.removesomeelements;

import java.io.*;
import java.util.ArrayList;

/**
 * Предложите эффективный алгоритм удаления нескольких рядом стоящих элементов из середины списка,
 * реализуемого ArrayList.
 * Допустим нужно удалить n элементов с позиции m в списке. Вместо выполнения удаления одного
 * элемента n раз (каждый раз смещая на 1 позицию элементы, стоящие «правее» в списке), нужно
 * выполнить смещение всех элементов, стоящих «правее» n + m позиции на n элементов «левее» к
 * началу списка. Таким образом, вместо выполнения n итераций перемещения элементов списка, все
 * выполняется за 1 проход. Но если говорить об общей эффективности - то самый быстрый способ
 * будет с использованием System.arraycopy(), и получить к нему доступ можно через
 * метод - subList(int fromIndex, int toIndex)
 */
public class Main {
    //позиция, с которой удаляем
    private static int m = 0;
    //количество удаляемых элементов
    private static int n = 0;
    //количество элементов в списке
    private static final int size = 1000000;
    //основной список (для удаления вызовом remove() и его копия для удаления путём перезаписи)
    private static ArrayList<Integer> initList, copyList;

    public static void main(String[] args) {

        initList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            initList.add(i);
        }
        System.out.println("Список из 1.000.000 элементов заполнен");

        copyList = new ArrayList<>(initList);
        System.out.println("Создана копия списка\n");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("С какой позиции удаляем? > ");
            m = Integer.parseInt(br.readLine());
            System.out.print("Сколько удаляем? > ");
            n = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        System.out.println("\nВыполняем удаление вызовом remove()...");
        long start = System.currentTimeMillis();

        for (int i = m - 1; i < m + n - 1; i++) {
            initList.remove(i);
        }

        long finish = System.currentTimeMillis() - start;
        System.out.println("Время удаления с помощью вызова remove(): " + finish);
        System.out.println("Размер исходного списка после удаления: " + initList.size());

        System.out.println("\nВыполняем удаление путем перезаписи...\n");
        start = System.currentTimeMillis();

        removeEfficiently();

        finish = System.currentTimeMillis() - start;
        System.out.println("Время удаления путём смещения: " + finish);
        System.out.println("Размер копии списка:" + copyList.size());

        System.out.println("\nВыполняем удаление через SubList...\n");
        start = System.currentTimeMillis();

        initList.subList(m - 1, m + n).clear();

        finish = System.currentTimeMillis() - start;
        System.out.println("Время удаления через саблист: " + finish);
        System.out.println("Размер копии списка:" + copyList.size());
    }

    private static void removeEfficiently() {
        /* если необходимо удалить все элементы, начиная с указанного,
         * то удаляем элементы с конца до m
         */
        if (m + n >= size) {
            int i = size - 1;
            while (i != m - 1) {
                copyList.remove(i);
                i--;
            }
        } else {
            //переменная k необходима для отсчёта сдвига начиная от места вставка m
            for (int i = m + n, k = 0; i < size; i++, k++) {
                copyList.set(m + k, copyList.get(i));
            }

            /* удаляем ненужные элементы в конце списка
             * удаляется всегда последний элемент, так как время этого действия
             * фиксировано и не зависит от размера списка
             */
            int i = size - 1;
            while (i != size - n - 1) {
                copyList.remove(i);
                i--;
            }
            //сокращаем длину списка путём удаления пустых ячеек
            copyList.trimToSize();
        }
    }
}
