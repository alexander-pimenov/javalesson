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
        Optional<String> optionalEmpty = Optional.empty();

        System.out.println(optional.isPresent()); // true
        optional.ifPresent(s -> System.out.println(s.length())); // 5
        System.out.println(optional.get()); // "hello"
        optional.orElse("ops..."); // "hello"

        System.out.println(optionalEmpty.isPresent()); // false
        optionalEmpty.ifPresent(s -> System.out.println(s.length())); // 0
//        System.out.println(optionalEmpty.get()); // "hello"
        String orElse = optionalEmpty.orElse("ops...");//
        System.out.println(orElse);

        /*Если значение присутствует, возвращает значение, в противном случае возвращает результат, полученный функцией-поставщиком.
        Броски:
        NullPointerException — если значение отсутствует, а функция-поставщик равна нулю.*/
        String orElseGet = optionalEmpty.orElseGet(() -> "ops from Supplier");//
        System.out.println(orElseGet);

        String orElseGet2 = optional.orElseGet(() -> "ops from Supplier");//
        System.out.println(orElseGet2);

    }
}
