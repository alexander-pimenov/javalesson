package statics;

/* Сначала может показаться, что программа должна сгенерировать исключение
 * NullPointerException. Однако при запуске программы мы увидим надпись
 * "Hello world from static!", поскольку Java разрешает вызывать статические
 * методы на null.*/
public class HelloStaticNull {
    public static void main(String[] args) {
        Hello hello = null;
        hello.staticPrint(); //Hello world from static!
        hello.print(); //NPE !!!

        //Вызов в 10 строчке это то же самое что и в 14
//        Hello.print();
    }
}

class Hello {
    public static void staticPrint() {
        System.out.println("Hello world from static!");
    }

    public void print() {
        System.out.println("Hello world!");
    }
}
