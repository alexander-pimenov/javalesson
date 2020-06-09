package ru.pimalex1978.list.singly_linked_list;

public class Runner {
    public static void main(String[] args) {

        List<Integer> list = new List<>();

        //добавление в начало
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);
        list.print();
        System.out.println("================");

        List<Integer> list2 = new List<>();
        //добавление в конец
        list2.addLast(10);
        list2.addLast(20);
        list2.addLast(30);
        list2.addLast(40);
        list2.addLast(50);
        list2.addLast(60);
        list2.print();

//        System.out.println();
//        list.remove();
//        list.print();

        System.out.println();
//        list.removeAt(30);
//        list.print();
//        list.removeAt(50);
//        list.print();
        System.out.println("Индекс в списке list: " + list.findId(20));
        System.out.println("Индекс в списке list2: " + list2.findId(20));

    }
}
