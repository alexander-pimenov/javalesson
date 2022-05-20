package immutable.immutableobjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/* Неизменяемый объект — это объект, внутреннее состояние которого остается постоянным после того,
 * как он был полностью создан.
 * Это означает, что публичный API неизменяемого объекта гарантирует нам, что он будет вести себя
 * одинаково в течение всей своей жизни.
 * В Java переменные по умолчанию изменяемы, то есть мы можем изменить значение, которое они содержат.
 * Используя ключевое слово final при объявлении переменной, компилятор Java не позволит нам изменить
 * значение этой переменной. Вместо этого он сообщит об ошибке времени компиляции.
 * Создание API неизменяемого объекта требует, чтобы мы гарантировали, что его внутреннее состояние
 * не изменится независимо от того, как мы используем его API.
 *
 * Поскольку внутреннее состояние неизменяемого объекта остается постоянным во времени, мы можем безопасно делиться им между несколькими потоками .

 * Преимущества:
 * Мы также можем использовать его свободно, и ни один из объектов, ссылающихся на него, не заметит
 * никакой разницы, мы можем сказать, что неизменяемые объекты не имеют побочных эффектов.
 * Неизменяемые объекты не меняют свое внутреннее состояние во времени, они потокобезопасны и не
 * имеют побочных эффектов. Благодаря этим свойствам неизменяемые объекты также особенно полезны
 * при работе с многопоточными средами.
 * */
public class ImmutableObjectsUnitTest {

    /*Пример не изменяемости объектов String*/
    @Test
    public void whenCallingStringReplace_thenStringDoesNotMutate() {
        // 2. What's an Immutable Object?
        final String name = "baeldung";
        final String newName = name.replace("dung", "----");

        assertEquals("baeldung", name);
        assertEquals("bael----", newName);
    }

    public void whenReassignFinalValue_thenCompilerError() {
        // 3. The final Keyword in Java (1)
        final String name = "baeldung";
        // name = "bael...";
    }

    /*Если поле класса будет содержать список, то используя final мы
     * не поменяем ссылку, но можем изменить содержимое списка.*/
    @Test
    public void whenAddingElementToList_thenSizeChange() {
        // 3. The final Keyword in Java (2)
        final List<String> strings = new ArrayList<>();
        assertEquals(0, strings.size());
        strings.add("baeldung");
        assertEquals(1, strings.size());
    }

    /* Как видим из примера мы здесь ни как не можем изменить,
     * что-то в объектах после того,как они были созданы.
     * Только создавать опять новые объекты.*/
    @Test
    public void exampleWithCurrencyAndMoneyTest() {
        Currency usd = Currency.of("USD");
        Money moneyUsd = new Money(100, usd);

        Currency rus = Currency.of("RUS");
        Money moneyRus = new Money(100, rus);

        System.out.println(moneyRus == moneyUsd); //false
    }

}