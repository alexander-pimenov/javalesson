package ru.pimalex1978.stream.reduce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Каждый отзыв должен содержать простой комментарий и оценку:*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Review {
    private int points;
    private String review;
}
