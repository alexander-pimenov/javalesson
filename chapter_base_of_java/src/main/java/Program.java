/**
 * Explanation
 * <p>
 * Так как класс Object находится на вершине иерархии классов в Java,
 * то суперкласса класса Object не существует, а суперклассом любого
 * класса Class является класс Object.
 */
public class Program {
    public static void main(String[] args) {
        Object obj = new Object();
        Class<?> clazz = obj.getClass();
        System.out.print(clazz.getSuperclass() + ", ");
        Class<?> clazzClass = clazz.getClass(); //или Class.class;
        System.out.print(clazzClass.getSuperclass());
        System.out.println();

        String str = "AAA";
        final Class<? extends String> strClass = str.getClass();
        System.out.println(strClass.getSuperclass() + ", " + strClass);
    }
}
