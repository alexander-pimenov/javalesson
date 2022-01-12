package ru.pimalex1978.make_type_without_declaring_it;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeaturesOfRemovalFromArrayList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList(
                "Arni", "Chuck", "Slay"
        ));

        /*Мы могли бы получить CME,т.к. удаляем элемент в forEach, что нельзя делать,
        но получим NPE. Вот почему:
        stream().forEach() -> spliterator().forEachRemaining()
        но forEachRemaining проверяет modcount один раз в конце
        Удаление элемента приведет к сдвигу массива с добавлением null в конце:
        ["Arni","Chuck","Slay"]->["Arni","Slay",null]
        На последней итерации if(null.equals("Chuck")) упадет с NPE,
        поэтому до ConcurrentModificationException не дожили ))))*/

        /*list.stream().forEach(x->{
            if (x.equals("Chuck")){
                list.remove(x);
            }
        }); //получим NPE !!!
        */

        //а так получим CME:
        /*list.stream().forEach(x->{
            if ("Chuck".equals(x)){
                list.remove(x);
            }
        }); //Exception in thread "main" java.util.ConcurrentModificationException
        */

        //а так отработает:
        /*for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("Chuck")) {
                list.remove(i);
            }
        }*/
        System.out.println(list); //[Arni, Slay]

        //или вот так еще лучше сновыми методами:
        list.removeIf("Chuck"::equals);
//        list.removeIf(x -> x.equals("Chuck"));
        System.out.println(list); //[Arni, Slay]

    }
}
