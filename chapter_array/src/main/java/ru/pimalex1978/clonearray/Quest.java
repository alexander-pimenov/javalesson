package ru.pimalex1978.clonearray;

import java.util.Arrays;

/**
 * Explanation
 * <p>
 * Для массивов Java переопределен метод clone(),
 * который производит поэлементное копирование.
 * И после это метода имеем две ссылки на один массив,
 * поэтому и видим изменения.
 */
public class Quest {
    public static void main(String[] args) {
        Item[] ar1 = {new Item(1), new Item(2), new Item(3)};
        System.out.println(ar1[0].item + " " + ar1[1].item + " " + ar1[2].item);
        Item[] ar2 = ar1.clone(); //после это метода имеем две ссылки на один массив
        System.out.println(Arrays.hashCode(ar1));
        System.out.println(Arrays.hashCode(ar2));
        ar2[0].item = 4;
        System.out.println(ar1[0].item + " " + ar1[1].item + " " + ar1[2].item);
        System.out.println(ar2[0].item + " " + ar2[1].item + " " + ar2[2].item);
        System.out.println(Arrays.toString(ar1));
        System.out.println(Arrays.toString(ar2));
    }
}

class Item {
    public int item;

    Item(int item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Item{" +
                item +
                '}';
    }
}
