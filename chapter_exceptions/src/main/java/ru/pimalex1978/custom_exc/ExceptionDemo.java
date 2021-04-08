package ru.pimalex1978.custom_exc;


public class ExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Start of main");

        makeArithmeticException();
        System.out.println("End of main");
    }

    private static void makeArithmeticException() {

        try {
            int a = 5;
            int b = 0;
            //System.out.println(a / b); //здесь выбросится ArithmeticException
            throw new MyOwnException();
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } catch (MyOwnException e) {
            e.logSomething();
        } finally {
            System.out.println("Hello from finally");
        }
        //throw new ArithmeticException("It was a ZERO!@");
    }
}
