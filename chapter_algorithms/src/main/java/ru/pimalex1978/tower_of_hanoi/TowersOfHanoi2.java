package ru.pimalex1978.tower_of_hanoi;

import java.util.Scanner;

/******************************************************************************
 *  Compilation:  javac TowersOfHanoi.java
 *  Execution:    java TowersOfHanoi n
 *
 *  Solves the Towers of Hanoi problem on n discs. The discs are labeled
 *  in increasing order of size from 1 to n.
 *  Решает проблему Ханойских башен на n дисках. Диски помечены в порядке возрастания размера от 1 до n.
 *
 *  %  java TowersOfHanoi 3
 *  1 left
 *  2 right
 *  1 left
 *  3 left
 *  1 left
 *  2 right
 *  1 left
 *
 *  % java TowersOfHanoi 4
 *  1 right
 *  2 left
 *  1 right
 *  3 right
 *  1 right
 *  2 left
 *  1 right
 *  4 left
 *  1 right
 *  2 left
 *  1 right
 *  3 right
 *  1 right
 *  2 left
 *  1 right
 *
 ******************************************************************************/
public class TowersOfHanoi2 {

    /******************************************************************************
     *  Compilation:  javac TowersOfHanoi.java
     *  Execution:    java TowersOfHanoi n
     *
     *  Solves the Towers of Hanoi problem on n discs. The discs are labeled
     *  in increasing order of size from 1 to n.
     *  Решает проблему Ханойских башен на n дисках. Диски помечены в порядке возрастания размера от 1 до n.
     *
     *  %  java TowersOfHanoi2 3
     *  1 left
     *  2 right
     *  1 left
     *  3 left
     *  1 left
     *  2 right
     *  1 left
     *
     *  % java TowersOfHanoi2 4
     *  1 right
     *  2 left
     *  1 right
     *  3 right
     *  1 right
     *  2 left
     *  1 right
     *  4 left
     *  1 right
     *  2 left
     *  1 right
     *  3 right
     *  1 right
     *  2 left
     *  1 right
     *
     ******************************************************************************/

    //распечатайте инструкцию по перемещению n дисков влево
    // (если слева - true) или вправо (если left - false)
    public static void moves(int n, boolean left) {
        if (n == 0) return;
        moves(n - 1, !left);
        if (left) System.out.println(n + " left");
        else System.out.println(n + " right");
        moves(n - 1, !left);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); //Если хотим запускать программу из консоли
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the number of disks you want to move: \r\n");
//        int n = scanner.nextInt();
        moves(n, true);
    }
}
