package annotation.exampleSimpleJUnit;

//Объявление типа аннотации маркера

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* В этой статье мы сконцентрируемся на обработке аннотаций в Runtime.
* Мы напишем свой собственный обработчик – упрощенный фреймворк для
* тестирования, который запускает тесты. Обработчик будет искать методы,
* аннотированные аннотацией @Test, и запускать их.
* https://github.com/myluckagain/sysout/tree/master/annotations-runtime/src/main/java/ru/sysout/annotationsruntime
*
*/

/**
 * Указывает, что аннотированный метод является тестовым.
 * Используйте только для статических методов без параметров.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}
