package ru.pimalex1978.enum2;

/**
 * Версия программы принятия решений
 * из главы 9. книги Г.Шилдта - Java. Полное руководство. 9 издание. 2015
 * В этой версии для предоставления возможных ответов
 * используется перечисление, а не переменные экземплярв.
 */

import java.util.Random;

/**
 * Перечисление возможных ответов
 */
enum Answers {
    NO, YES, MAYBE, LATER, SOON, NEVER
}

/**
 * Класс генерирующий случайные ответы.
 */
class Question {

    //для генерации случайного выбора
    Random rand = new Random();

    //метод ask возвращает ответы Answers
    Answers ask() {
        int prob = (int) (100 * rand.nextDouble());
        if (prob < 15)
            return Answers.MAYBE; //15%
        else if (prob < 30)
            return Answers.NO; //15%
        else if (prob < 60)
            return Answers.YES; //30%
        else if (prob < 75)
            return Answers.LATER; //15%
        else if (prob < 98)
            return Answers.SOON; //13%
        else
            return Answers.NEVER; //2%
    }

}

public class AskMe {
    static void answer(Answers result) {
        switch (result) {
            case NO:
                System.out.println("Нет");
                break;
            case MAYBE:
                System.out.println("Возможно");
                break;
            case LATER:
                System.out.println("Позднее");
                break;
            case SOON:
                System.out.println("Вскоре");
                break;
            case YES:
                System.out.println("Да");
                break;
            case NEVER:
                System.out.println("Никогда");
                break;
        }
    }

    public static void main(String[] args) {
        Answers ans;

        Question q = new Question();
        answer(q.ask());
        answer(q.ask());
        answer(q.ask());
        answer(q.ask());
        answer(q.ask());

        System.out.println("Все варианты ответов:");
        for (Answers a : Answers.values()) {
            System.out.print(a + "\t");
        }
    }
}
