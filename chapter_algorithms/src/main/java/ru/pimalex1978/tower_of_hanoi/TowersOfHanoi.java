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
 * Move disk #1 from pin 1 to 3.
 * Move disk #2 from pin 1 to 2.
 * Move disk #1 from pin 3 to 2.
 * Move disk #3 from pin 1 to 3.
 * Move disk #1 from pin 2 to 1.
 * Move disk #2 from pin 2 to 3.
 * Move disk #1 from pin 1 to 3.
 *
 *  % java TowersOfHanoi 4
 * Move disk #1 from pin 1 to 2.
 * Move disk #2 from pin 1 to 3.
 * Move disk #1 from pin 2 to 3.
 * Move disk #3 from pin 1 to 2.
 * Move disk #1 from pin 3 to 1.
 * Move disk #2 from pin 3 to 2.
 * Move disk #1 from pin 1 to 2.
 * Move disk #4 from pin 1 to 3.
 * Move disk #1 from pin 2 to 3.
 * Move disk #2 from pin 2 to 1.
 * Move disk #1 from pin 3 to 1.
 * Move disk #3 from pin 2 to 3.
 * Move disk #1 from pin 1 to 2.
 * Move disk #2 from pin 1 to 3.
 * Move disk #1 from pin 2 to 3.
 *
 ******************************************************************************/
public class TowersOfHanoi {
    public static void move(int n, int start, int finish) {
        if (n == 0) {
            System.out.println("The number of disks is 0");
            return;
        }
        if (n == 1) {
            System.out.printf("Move disk #1 from pin %d to %d.\n", start, finish);
        } else {
            int tmp = 6 - start - finish;
            move(n - 1, start, tmp);
            System.out.printf("Move disk #%d from pin %d to %d.\n", n, start, finish);
            move(n - 1, tmp, finish);
        }
    }

    public static void main(String[] args) {
//        int i = Integer.parseInt(args[0]); //Если хотим запускать программу из консоли
        //нужно закоментировать нижнме 3 строчки
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of disks you want to move: \r\n");
        int i = scanner.nextInt();

        move(i, 1, 3);
    }
}
