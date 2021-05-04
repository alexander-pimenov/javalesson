package finalkeyword;

import java.util.Random;

/**
 * Ключевое слово final можно применять к полям класса, к методам
 * и к самим классам.
 * Назначение полю final говорит что это становится константой, и
 * в дальнейшем его изменить нельзя.
 * Можно изменять только изменяемые типы (StringBuilder), и они
 * не становятся константами, и вот переприсвоить ссылку на них
 * нельзя будет. Ссылка не изменяема.
 * Если final стоит на классе, то класс и его методы финальные
 * и переопределены быть не могут.
 * Final для методов показывает, что в подклассе переопределить
 * метод нельзя!!!
 * Писать и final class и final method бессмысленно.
 * Если поле final инициализируем в конструкторе, то это делаем во
 * всех конструкторах.
 * Значение поля final не изменяемо в пределах одного объекта!!!
 * Так же инициализировать поле можно в логическом блоке.
 * Логический блок вызывается перед каждым вызовом конструктора.
 * Если есть инициализация в лог.блоке, то в конструкторах
 * инициализировать не надо.
 */
public final class Point {
    //инициализируем сразу
    private final int MAX_RANGE = 10000;
    //инициализируем в конструкторах
    private final int MIN_RANGE;
    //инициализируем в логическом блоке
    private final int AVR_RANGE;
    final StringBuilder builder = new StringBuilder();
    private int x;
    private String name;

    {
        AVR_RANGE = new Random().nextInt(700);
    }

    public Point(int x) {
        this.x = x;
        builder.append("hello");
        MIN_RANGE = 500;
    }

    public Point(int x, String name) {
        //случайное число в пределах 1000
        this.MIN_RANGE = new Random().nextInt(1000);
        this.x = x;
        this.name = name;
    }

    /**
     * Финальный метод по вычислению абсолютного значения
     * числа.
     *
     * @return абс. число
     */
    final int length() {
        return Math.abs(x);
    }

}
