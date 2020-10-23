package ru.pimalex1978.shamRailOld.io.console;

import ru.pimalex1978.shamRailOld.io.Input;

import java.util.Scanner;

/**
 * Ввод с консоли.
 */
public class ConsoleInput implements Input {

    /**
     * Способ ввода.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод запращивает у пользователя и данные и ожидает от него ответа.
     * @param question вопрос пользователю.
     * @return ответ пользователя.
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.next();
    }

}
