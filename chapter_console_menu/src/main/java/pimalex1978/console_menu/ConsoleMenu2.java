package pimalex1978.console_menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Пример консольного меню с выбором опций.
 * В конструкции switch-case мы создаем некоторый класс,
 * а он уже в свою очередь что-то делает! Удобная конструкция.
 */
public class ConsoleMenu2 {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        startPO();
    }

    /**
     * Основной метод работы с консольным меню.
     * Выводится шапка меню.
     * Читаются выбранные пункты меню.
     * выполняются некоторые действия,
     * заложенные в вызываемых классах,
     * находящихся в кейсах конструкции switch-case.
     */
    private static void startPO() {
        boolean exit = false;
        // Local variable
        int scValue = 0;
        while (!exit) {
            //Вызываем метод рисования шапки меню
            printMenu();
            //Вызываем метод считывания из консоли только целого числа
            //если число не число или не целое, то будет ждать ввода
            //только целого.
            scValue = inInt();
//          scValue = sc.nextInt();
//          Switch construct
            switch (scValue) {
                case 1:
                    System.out.println("Option 1 selected");   // This is where I want to call the class
                    Class1 class1 = new Class1();
                    class1.doSomething();
                    break;
                case 2:
                    System.out.println("Option 2 selected");  // this is where I want to call the class
                    Class2 class2 = new Class2();
                    class2.doSomething();
                    break;
                case 3:
                    System.out.println("Exit selected");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid selection");
                    break; // This break is not really necessary
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
    private static void printMenu() {
// Display menu graphics
        System.out.println("============================");
        System.out.println("|   MENU SELECTION DEMO    |");
        System.out.println("============================");
        System.out.println("| Options:                 |");
        System.out.println("|        1. Option 1       |");
        System.out.println("|        2. Option 2       |");
        System.out.println("|        3. Exit           |");
        System.out.println("============================");
        System.out.println(" Select option: \r");
    }
}

class Class1 {
    private String name;

    Class1() {
        LocalDateTime date = LocalDateTime.now();
        this.name = "Class1 " + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"));
//        this.name = "Class1 " + date.toString();
    }

    void doSomething() {
        System.out.println("Что-то делаем в классе 1 " + name);
    }
}

class Class2 {

    private String name;

    Class2() {
        LocalDateTime date = LocalDateTime.now();
        this.name = "Class2 " + date.format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH:mm:ss"));
//        this.name = date.toString() + " Class2";
    }

    void doSomething() {
        System.out.println("Что-то делаем в классе 2 " + name);
    }
}

//        //Варианты форматирования даты и времени:
//        Date date = new Date();
//        //Pattern
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
//        //Вывод по формату
//        System.out.println("Time in 12 Hour format - " + sdf.format(date));
//        System.out.println("============================================");
//
//        LocalDateTime localDateTime = LocalDateTime.now();
//        //Pattern
//        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("hh:mm:ss a");
//        System.out.println("Time in 12 Hour format - " + localDateTime.format(pattern));
//        System.out.println("============================================");
//
//        LocalTime time = LocalTime.now();
//        // Pattern
//        DateTimeFormatter pattern2 = DateTimeFormatter.ofPattern("hh:mm:ss a");
//        System.out.println("Time in 12 Hour format - " + time.format(pattern2));
