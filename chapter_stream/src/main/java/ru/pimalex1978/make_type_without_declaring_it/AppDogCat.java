package ru.pimalex1978.make_type_without_declaring_it;

import java.util.Optional;

/* Барух Садогурский, Евгений Борисов, Тагир Валеев — Java 8 Puzzlers
https://www.youtube.com/watch?v=6bN1HcRhse4&t=1581s
* Java 8 дали больше прав кастингу типов.
И теперь можно катовать к пересечению двух типов !!!
 Но нельзя объявить и создать переменную двух типов !!! Но с var так можно сделать!!!
 Можно сделать через лямбды !!!  Т.к. аргументу лямбды тип явно можно не указывать,
  и */
public class AppDogCat {
    public static void main(String[] args) {
        //локальный класс
        class КотПес implements Кот, Пес {
        }
        test2(new КотПес());
        test3(new КотПес());

    }

    //А. Это сработает? - Нет, это не верный вариант
    //Тут нельзя объявить и создать переменную двух типов !!!
    /*private static void test1(Object obj) {
        Кот & Пес x = (Кот & Пес) obj;
        x.мяукать();
        x.лаять();
    }*/

    //Б. Это сработает? - да
    //С var так можно делать !!!
    private static void test2(Object obj) {
        var x = (Кот & Пес) obj;
        x.мяукать();
        x.лаять();
    }

    //В. Это сработает?
    //не верный вариант
    /*
    private static void test2(Object obj) {
        ((Consumer < ? extends Кот & Пес >)(x -> {
            x.мяукать();
            x.лаять();
        })).accept((Кот & Пес) obj);
    }*/

    //Г. Это сработает? -да
    //Можно сделать через лямбды !!!
    // Т.к. аргументу лямбды тип явно можно не указывать то так можно записать.
    //Обарачиваем наш некий объект (Кот & Пес) в Optional. И далее, если он существует, то
    //запустим его методы.
    private static void test3(Object obj) {
        Optional.of((Кот & Пес) obj)
                .ifPresent(x -> {
                    x.мяукать();
                    x.лаять();
                });
    }
}
