package annotation;

public class UsingAnnWithValue {

    // Генерировать элементы Аннотации обычным способом.
    @AnnWithValue(name = "Name1", value = 100)
    public void someMethod1() {

    }

    // Генерировать элементы Аннотации обычным способом.
    // Элемент 'name' Аннотации будет иметь значение по умолчанию
    @AnnWithValue(value = 100)
    public void someMethod2() {

    }

    // Элемент с названием 'value' является особенным.
    // Вместо @AnnWithValue(value = 100)
    // Вам нужно написать только @AnnWithValue(100)
    @AnnWithValue(100)
    public void someMethod3() {

    }
}
