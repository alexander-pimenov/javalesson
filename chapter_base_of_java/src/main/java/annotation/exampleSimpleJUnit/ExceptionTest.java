package annotation.exampleSimpleJUnit;

import java.lang.annotation.*;

/*
* Аннотации @ExceptionTest – используется, чтобы показать,
* что тест должен выбрасывать определенное исключение.
* Т.е. это маркер выбрасывания исключения.
*
* Repeatable аннотации
* Аннотации, которыми можно аннотировать элемент несколько раз,
* называются repeatable. @ExceptionTest является repeatable-аннотацией.
* Синтаксис их не прост, а особенно обработка.
* Чтобы  определить аннотацию как repeatable, мы должны аннотировать ее
* дополнительной метааннотацией @Repeatable(ExceptionTestContainer.class)
* Здесь внутри @Repeatable задана вторая аннотация ExceptionTestContainer.class
* и она служит контейнером для повторяемых значений.
*
* */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class)
public @interface ExceptionTest {
    //далее при применении аннотации указывается элемент,
    //который наследует Exception
    Class<? extends Exception> value();
}