package ru.pimalex1978.srp.example1;

import java.util.Arrays;

/**
 * Класс, который распечатывает текст.
 * Единственная задача этого класса - печать текста .
 */
public class TextPrinter {
    TextManipulator textManipulator;

    public TextPrinter(TextManipulator textManipulator) {
        this.textManipulator = textManipulator;
    }

    public void printText() {
        System.out.println(textManipulator.getText());
    }

    public void printOutEachWorldOfText() {
        System.out.println(Arrays.toString(textManipulator.getText().split("")));
    }

    public void printRangeOfCharacters(int startingIndex, int endIndex) {
        System.out.println(textManipulator.getText().substring(startingIndex, endIndex));
    }

}
