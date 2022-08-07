package ru.pimalex1978.stream.reduce;

import java.util.Arrays;
import java.util.List;

public class AppReduce {
    public static void main(String[] args) {
        User john = new User("John", 30);
        System.out.println(john);
        john.getRating().add(new Review(5, ""));
        john.getRating().add(new Review(3, "not bad"));
        System.out.println(john);

        User julie = new User("Julie", 35);
        julie.getRating().add(new Review(4, "great!"));
        julie.getRating().add(new Review(2, "terrible experience"));
        julie.getRating().add(new Review(4, ""));
        System.out.println(julie);

        List<User> users = Arrays.asList(john, julie);

        Review r11 = new Review(5, "класс");
        Review r12 = new Review(4, "хорошо");
        Review r13 = new Review(3, "удовлетворительно");
        Rating rating1 = new Rating();
        rating1.add(r11);
        rating1.add(r12);
        rating1.add(r13);
        Review r21 = new Review(4, "просто хорошо");
        Review r22 = new Review(4, "хорошо ж");
        Review r23 = new Review(4, "ну, хорошо");
        Rating rating2 = new Rating();
        rating2.add(r21);
        rating2.add(r22);
        rating2.add(r23);


        System.out.println(rating1);
        System.out.println(rating2);

        System.out.println(Rating.average(rating1, rating2));
    }
}
