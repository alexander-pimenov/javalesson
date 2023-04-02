package pimalex1978.console_menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Пример консольного меню, с применением ветвления
 * операторов if-else
 */
public class ConsoleMenu1 {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        startPO();
    }

    private static void startPO() {

        boolean exit = false;
        while (!exit) {
            programMenu();
            int menuInput = inInt();
//            int menuInput = sc.nextInt();
            if (menuInput == 1) {
                //действие1
                System.out.println("Мы выбрали пункт 1");
            } else if (menuInput == 2) {
                //действие2
                System.out.println("Мы выбрали пункт 2");
            } else if (menuInput == 3) {
                //действие3
                System.out.println("Мы выбрали пункт 3");
            } else if (menuInput == 4) {
                //действие4
                System.out.println("Мы выбрали пункт 4");
            } else if (menuInput == 5) {
                //действие5
                System.out.println("Мы выбрали пункт 5 - Выход.");
                exit = true;
            } else {
                //действие
                System.out.println("Вы не выбрали нужный пункт.");
            }
        }
    }

    /**
     * Метод читает из консоли данные и возвращает число,
     * если только это есть целое число.
     * Иначе крутимся в бесконечном цикле.
     *
     * @return цлое число.
     */
    private static int inInt() {
        //Крутимся в бесконечном цикле пока не будет введено
        //целое число.
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Not an integer. Try again");
            }
        }
    }

    /**
     * Метод рисует шапку нашего консольного меню.
     */
    private static void programMenu() {
        System.out.println(
                "Выберете пункт меню:" + "\n"
                        + "1. блаблабла" + "\n"
                        + "2. бла бла" + "\n"
                        + "3. блаблаблабла" + "\n"
                        + "4. блабла" + "\n"
                        + "5. exit" + "\n"
        );
    }
}
