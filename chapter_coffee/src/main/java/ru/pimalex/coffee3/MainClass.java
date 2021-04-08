package ru.pimalex.coffee3;
/*Рассмотрим Исключения и Многпоточность.
* А также подключение к Dropbox.
* Полный пример кода для подключения к Dropbox показан по ссылке
* https://github.com/dropbox/dropbox-sdk-java  (Full Example Snippet)*/

public class MainClass {

    public static void main(String[] args) {


        // Об Exceptions
        int[] drinkPrices = {80, 120, 150};

        System.out.println("Before");
        try {
            System.out.println(drinkPrices[2]);
        } catch (Exception ex) {
            System.out.println("Exception :(");
            ex.printStackTrace();
        }
        System.out.println("AFTER");

        //О Многопоточности
        //Поток засыпает на 5 секунд
        //Бесконечный цикл for (;;)
        //Бесконечный цикл для цифр for(int i;;i++)
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " YES");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
