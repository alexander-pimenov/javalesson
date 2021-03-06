package optional;

import java.util.Optional;

/**
 * Что такое Optional?
 * Опциональное значение Optional — это контейнер для объекта, который может содержать
 * или не содержать значение null. Такая обёртка является удобным средством
 * предотвращения NullPointerException, т.к. имеет некоторые функции высшего порядка,
 * избавляющие от добавления повторяющихся if null/notNull проверок
 */
public class Main {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("hello");

        System.out.println(optional.isPresent()); // true
        optional.ifPresent(s -> System.out.println(s.length())); // 5
        System.out.println(optional.get()); // "hello"
        optional.orElse("ops..."); // "hello"
    }
}
