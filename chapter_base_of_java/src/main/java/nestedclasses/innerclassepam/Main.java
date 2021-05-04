package nestedclasses.innerclassepam;

/**
 * Пример видимости внутреннего класса.
 * public, protected, default разрешают создание объекта
 * внутреннего класса, а private запрещает доступ к нему.
 */
public class Main {
    public static void main(String[] args) {
        Student.Address address = new Student().new Address();

        Student student = new Student();
        Student.Address address1 = student.getAddress();

    }
}
