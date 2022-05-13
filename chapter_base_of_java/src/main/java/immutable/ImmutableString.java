package immutable;

/*Пример, как получить неизменяемый класс.
 * Использовать в полях неизменяемый String.
 * !!! Так можно делать с примитивами и иммутабельными объектами !!!
 * (есть геттеры, но нет сеттеров)
 */
public class ImmutableString {
    private String builder;

    public ImmutableString(String builder) {
        this.builder = builder;
    }

    public String getBuilder() {
        return builder;
    }
}
