package annotation;

/**
 * TYPE - Прикрепленный на объявлении Class, interface, enum, annotation.
 * FIELD - Прикрепленный на объявлении поля (field), включая констанции enum.
 * METHOD - Прикрепленный на объявлении method.
 * PARAMETER - Прикрепленный на объявлении parameter
 * CONSTRUCTOR - Прикрепленный на объявлении конструктора
 * LOCAL_VARIABLE - Прикрепленный на локальной переменной.
 * ANNOTATION_TYPE - Прикрепленный на объявлении Annotation
 * PACKAGE - Прикрепленный на объявлении package.
 */
public @interface MyFirstAnnotation {
    //Элемент "name"
    public String name();

    // Элемент 'description', со значением по умолчанию "".
    public String description() default "";
}
