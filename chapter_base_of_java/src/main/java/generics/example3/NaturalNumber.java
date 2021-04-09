package generics.example3;

/**
 * В дополнение к ограничению типов, которые вы можете использовать
 * для создания экземпляра универсального типа, параметры
 * ограниченного типа позволяют вам вызывать методы, определенные
 * в границах.
 * https://docs.oracle.com/javase/tutorial/java/generics/bounded.html
 *
 * @param <T> generic
 */
public class NaturalNumber<T extends Integer> {
    private T n;

    public NaturalNumber(T n) {
        this.n = n;
    }

    public boolean isEven() {
        return n.intValue() % 2 == 0;
    }

    // ...
}
