package ru.pimalex1978.srp.example1;

/**
 * Класс, который каким-то образом изменяет текст.
 * Единственная задача этого класса - манипулировать текстом .
 */
public class TextManipulator {
    private String text;

    public TextManipulator(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void appendText(String newText) {
        text = text.concat(newText);
    }

    public String findWordAndReplace(String world, String replacementWorld) {
        if (text.contains(world)) {
            text = text.replace(world, replacementWorld);
        }
        return text;
    }

    public String findWorldAndDelete(String world) {
        if (text.contains(world)) {
            text = text.replace(world, "");
        }
        return text;
    }



}
