package ru.pimalex1978.error;

/*При вызове Error в методе trytry() мы видим, что мы не дойдем до блока finally,
* программа закроется, т.к. System.exit(42); завершает работу программы.
* А при tryreturn() напечатается finally, т.е. main метод дойдет до конца.*/
public class ErrorApp1 {
    public static void main(String[] args) {
//        trytry();
        tryreturn();
    }

    private static void tryreturn() {
        try {
            return;
        } finally {
            System.out.println("finally");
        }
    }

    private static void trytry() {
        try {
            System.exit(42);
        } finally {
            System.out.println("finally");
        }
    }
}
