package annotation;

/**
 * Annotation имеет элемент с названием value, который имеет особенные характеристики.
 */
public @interface AnnWithValue {
    // Элемент с названием 'value', Аннотации.
    // Имеет некоторую особенность при использовании данного элемента.
    public int value();

    // Элемент 'name'
    public String name() default "";
}
