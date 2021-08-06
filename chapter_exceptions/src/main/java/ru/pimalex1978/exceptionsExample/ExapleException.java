package ru.pimalex1978.exceptionsExample;

/*покажем, как создавать исключения
 * наследуем этот класс от класса Exception*/
public class ExapleException extends Exception {
    private String myText;

    //создаем myText в конструкторе
    public ExapleException(String myText) {
        this.myText = myText;
    }

    //берем этот myText с помощью геттера
    public String getMyText() {
        return myText;
    }
}
