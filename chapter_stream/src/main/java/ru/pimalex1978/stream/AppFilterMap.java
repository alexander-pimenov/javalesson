package ru.pimalex1978.stream;

import java.util.stream.Stream;

/**
 * Не забываем, что созданный стрим можно использовать оин раз с терминальной операцией.
 * После такой операции нужно создавать стрим заново.
 */
public class AppFilterMap {
    public static void main(String[] args) {
        /*Фильтрация элементов*/
        Stream<String> password = Stream.of("3as123de", "94f5gtr", "h67utf3d4");
        password.filter(p -> p.length() == 7).forEach(System.out::println);

        Stream<String> stream1 = Stream.of("Value 1", "Value 2", "Value 3", "Value 4", "Value 5");
        Stream<String> stream2 = Stream.of("Value 1", "Value 2", "Value 3", "Value 4", "Value 5");
        //ограничить
        stream1.limit(2).forEach(System.out::println);
        //пропустить (перепрыгнуть)
        stream2.skip(3).forEach(System.out::println);

        /*Отображение или мапинг является промежуточной операцией.
         * map() - преобразует один тип к другому.*/
        Stream<Film> filmStream = Stream.of(new Film("Deadpool 2", 100.0), new Film("Avengers: Infinity War", 75.0));
        //Выполним преобразование от типа Film к типу String или double
        filmStream.map(Film::getTitle).forEach(System.out::println);
//        filmStream.map(Film::getPrice).forEach(System.out::println);

        /*Также для мапинга используют flatMap но он используется в тех случаях когда мы хотим из одного объекта
         * потока данных мы хотим получить несколько объектов*/
        //Допустим поток данных состоит из одного объекта
        Stream<Pizza> pizzaStream = Stream.of(new Pizza("Pepperoni", 90));
        //Допустим у пиццы есть цена в обычное время и время со скидкой
        pizzaStream.flatMap(pizza -> Stream.of(
                String.format("Pizza: %s, price: $%.2f", pizza.getTitle(), pizza.getPrice()),
                String.format("(HAPPY HOURS DISCOUNT -50%%)%nPizza: %s, price: $%.2f", pizza.getTitle(), pizza.getPrice()/2)
        )).forEach(System.out::println);


    }
}

class Film {
    private String title;
    private double price;

    Film(String title, double price) {
        this.title = title;
        this.price = price;
    }

    String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}

class Pizza {
    private String title;
    private double price;

    Pizza(String title, double price) {
        this.title = title;
        this.price = price;
    }

    String getTitle() {
        return title;
    }

    double getPrice() {
        return price;
    }
}
