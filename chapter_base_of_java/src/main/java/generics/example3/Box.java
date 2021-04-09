package generics.example3;

/**
 * Могут быть случаи, когда вы хотите ограничить типы, которые могут
 * использоваться в качестве аргументов типа в параметризованном типе.
 * Например, метод, который работает с числами, может хотеть принимать
 * только экземпляры Number или его подклассы. Для этого нужны параметры
 * ограниченного типа Bounded Type Parameters.
 * <p>
 * Чтобы объявить параметр ограниченного типа, укажите имя параметра типа,
 * за которым следует extends ключевое слово, а затем его верхняя граница,
 * которой в этом примере является Number. Обратите внимание, что в этом
 * контексте extends он используется в общем смысле для обозначения либо
 * «расширяет» (как в классах), либо «реализует» (как в интерфейсах).
 * https://docs.oracle.com/javase/tutorial/java/generics/bounded.html
 *
 * @param <T> generic
 */
public class Box<T> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u) {
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
        integerBox.set(new Integer(10));
        //
//        integerBox.inspect("some text"); // error: this is still String!
    }
}
