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
        //3 способа создания Optional
        Optional<String> optionalNotEmpty = Optional.of("hello");
        Optional<String> optionalEmpty = Optional.empty();
        Optional<String> optionalOfNullable = Optional.ofNullable("Hi");

        //Если значение присутствует, выполняет данное действие со значением,
        // т.е. s.length(), в противном случае ничего не делает.
        System.out.println(optionalNotEmpty.isPresent()); // true
        optionalNotEmpty.ifPresent(s -> System.out.println("длина слова = " + s.length())); // 5
        System.out.println(optionalNotEmpty.get()); // "hello"
        System.out.println(optionalNotEmpty.orElse("ops...")); // "hello" - выполнится get(), т.к. есть значение
        System.out.println(optionalOfNullable.orElse("ops...")); // "Hi"

        System.out.println(optionalEmpty.isPresent()); // false
        optionalEmpty.ifPresent(s -> System.out.println("длина слова = " + s.length())); // это действие не выполнится, т.к. optionalEmpty
        //System.out.println(optionalEmpty.get()); // будет ошибка 'java.util.NoSuchElementException: No value present', т.к. optionalEmpty
        System.out.println(optionalEmpty.orElse("ops..."));//ops...

        /* Метод orElseGet() - если значение присутствует, возвращает значение, в противном
         * случае возвращает результат, полученный функцией-поставщиком.
         * Обрати внимание, что возвращается Стринг, т.е. тот тип которым параметризован Optional!
         * Броски исключений:
         *         NullPointerException — если значение отсутствует, а функция-поставщик равна нулю.*/
        System.out.println(optionalEmpty.orElseGet(() -> "ops from Supplier")); //"ops from Supplier"
        System.out.println(optionalOfNullable.orElseGet(() -> "ops from Supplier")); //Hi

        //Можно и так возвращать, что нибудь, но совместимым с параметризацией optional, в нашем случае это String))))
        String userString = optionalEmpty.orElseGet(() -> {
            return new User("Пустой юзер").toString();
        });
        System.out.println(userString);

        String orElseGet2 = optionalNotEmpty.orElseGet(() -> "ops from Supplier");//
        System.out.println(orElseGet2);
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
