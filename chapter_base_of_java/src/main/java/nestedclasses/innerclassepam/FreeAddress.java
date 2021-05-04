package nestedclasses.innerclassepam;

/**
 * Пример наследования у внутренних классов.
 * Практического толка в таком наследовании нет, но
 * тем не менее это возможно.
 * Видим пример самого необычного применения
 * слова super()
 * Но тем не менее может быть использовано.
 */
public class FreeAddress extends Student.Address {
    public FreeAddress() {
        new Student().super();
    }

    public FreeAddress(Student student) {
        student.super();
    }
}
