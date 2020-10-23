package ru.pimalex1978.slevkelebr;

import java.util.Scanner;

/**
 * Класс создан, для работы с пользователем через консоль.
 * Так же для разрыва строгой зависимости от класса Scanner через интерфейс Input.
 *
 * @author Frolov Sergey (Slevkelebr@yandex.ru)
 * @version 0.1
 * @since 22.05.2020
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(askStr(question));
    }

}