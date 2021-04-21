package annotation;

@MyFirstAnnotation(name = "Some name", description = "Some description")
public class UsingMyFirstAnnotation {

    // Annotation (Аннотация) прикреплена к Constructor (Конструктору).
    // Со значением элемента name "John"
    // Значение элемента description является "Write by John".
    @MyFirstAnnotation(name = "John", description = "Write by John")
    public UsingMyFirstAnnotation() {
    }

    // Annotation (Аннотация) прикреплена к методу.
    // Со значением элемента 'name' "Tom"
    // Элемент 'description' не объявлен, он будет взят по умолчанию.
    @MyFirstAnnotation(name = "Tom")
    public void someMethod() {
    }

    // Annotation прикреплена к параметру одного метода.
    public void todo(@MyFirstAnnotation(name = "none") String job) {

        // Annotation прикреплена к локальной переменной.
        @MyFirstAnnotation(name = "Some name")
        int localVariable = 0;
    }
}
