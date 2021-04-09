package generics.example2;

/**
 * Контейнер для хранения продуктов.
 * Чтобы хранились только Product и его наследники, то указываем bounded type parameters
 * (Можно посмотреть здесь https://docs.oracle.com/javase/tutorial/java/generics/bounded.html)
 *
 */
public class Container<T extends Product> {
    T item;
}
