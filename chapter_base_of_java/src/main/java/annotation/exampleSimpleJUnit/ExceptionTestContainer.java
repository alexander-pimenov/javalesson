package annotation.exampleSimpleJUnit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* Эта аннотация будет задействована дальше внутри
* @Repeatable в @ExceptionTest.
* Она служит контейнером для повторяемых значений.
* В ExceptionTestContainer есть массив типа ExceptionTest.
* */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface ExceptionTestContainer {
    ExceptionTest[] value();
}