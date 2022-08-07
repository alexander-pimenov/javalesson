package ru.pimalex1978.stream.reduce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*Затем нам нужно определить наш  рейтинг, который будет содержать наши обзоры вместе с полем баллов.
 * По мере добавления отзывов это поле будет соответственно увеличиваться или уменьшаться*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rating {
    double points;
    List<Review> reviews = new ArrayList<>();

    public void add(Review review) {
        reviews.add(review);
        computeRating();
    }

    private double computeRating() {
        double totalPoints =
                reviews.stream().map(Review::getPoints).reduce(0, Integer::sum);
        this.points = totalPoints / reviews.size();
        return this.points;
    }

    /*Мы также добавили функцию среднего значения для вычисления среднего значения на основе двух входных Rating s.
     * Это будет хорошо работать для наших компонентов объединителя и аккумулятора.*/
    public static Rating average(Rating r1, Rating r2) {
        Rating combined = new Rating();
        combined.reviews = new ArrayList<>(r1.reviews);
        combined.reviews.addAll(r2.reviews);
        combined.computeRating();
        return combined;
    }

}
