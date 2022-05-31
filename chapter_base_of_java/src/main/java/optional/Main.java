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
        Optional<String> optional = Optional.of("hello");
        Optional<String> optionalEmpty = Optional.empty();
        Optional<String> optionalOfNullable = Optional.ofNullable("Hi");

        //Если значение присутствует, выполняет данное действие со значением,
        // т.е. s.length(), в противном случае ничего не делает.
        System.out.println(optional.isPresent()); // true
        optional.ifPresent(s -> System.out.println(s.length())); // 5
        System.out.println(optional.get()); // "hello"
        optional.orElse("ops...");

        System.out.println(optionalEmpty.isPresent()); // false
        optionalEmpty.ifPresent(s -> System.out.println(s.length())); // 0
//        System.out.println(optionalEmpty.get()); // "hello"
        String orElse = optionalEmpty.orElse("ops...");
        System.out.println(orElse);//ops...

        /* Метод orElseGet() - если значение присутствует, возвращает значение, в противном
         * случае возвращает результат, полученный функцией-поставщиком.
         * Обрати внимание, что возвращается Стринг, т.е. тот тип которым параметризован Optional!
         * Броски исключений:
         *         NullPointerException — если значение отсутствует, а функция-поставщик равна нулю.*/
        String orElseGet = optionalEmpty.orElseGet(() -> "ops from Supplier");//
        System.out.println(orElseGet);

        //Можно и так возвращать, что нибудь))))
        String userString = optionalEmpty.orElseGet(() -> {
            return new User("Пустой юзер").toString();
        });
        System.out.println(userString);

        String orElseGet2 = optional.orElseGet(() -> "ops from Supplier");//
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
