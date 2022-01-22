package ru.pimalex1978.exceptionsExample;

public class Exceptions {
    public static void main(String[] args) {
        System.out.println("Начало main метода");

        Example example = new Example();

        /*обработаем исключение с помощью блока try-catch,
        * т.е. поймаем его*/
        try {

            example.method3();
            example.method1();
            example.method2();

        } catch (ExapleException ee) {
            //System.out.println(e);//выведем на экран это исключение
            System.out.println(ee.getMyText());//преобразовав в строку
        }catch (NewException ne){
            System.out.println(ne.toString());
        }catch (Exception e){
            System.out.println(e.toString());
        }

        System.out.println("Конец main метода");
    }
}
