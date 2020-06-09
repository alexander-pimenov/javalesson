package ru.pimalex1978.list.dinamicListParametrezied;

public class Main {
    public static void main(String[] args) {
        List ml = new List();
        ml.addBack(1.0);
        ml.addBack(2);
        ml.addBack("brrr");
        ml.addFront(6);

        ml.printList();
        System.out.println();

        List<String> listString = new List<>();
        listString.addBack("qq");
        listString.addBack("aa");
        listString.addBack("zz");
        listString.addFront("zz");

        listString.printList();
        System.out.println();
    }
}
