package generics.example1;

/*смотрим как это CustomArrayList можно использовать в программе*/
public class Generics {
    public static void main(String[] args) {
        /*создаем новый CustomArrayList и указываем в качестве конкретного типа String
         * это означает, что когда будет создан CustomArrayList, то вместо <T> подставится String */
        CustomArrayList<String> strings = new CustomArrayList<>();

        //добавляем в массиве 100 строк Hello world с индексом
        for (int i = 0; i < 100; i++) {
            strings.add("Hello world " + i);
        }

        //выводим эти строки Hello world на экран
        for (int i = 0; i < strings.getLength(); i++) {
            System.out.println(strings.get(i));
        }

        /*пример вызова обощенного метода с различными типами параметра:
         * int & String ...*/
        function(42); //<int> - U: java.lang.Integer
        function("Hello Nice Life");//<String> - Hello Nice Life
        function(8.4); //<double> - U: java.lang.Double
        function('A'); //<char> - A

        CustomArrayList<Integer> list = new CustomArrayList<>();

    }

    /*ОБОБЩЕННЫЕ МЕТОДЫ*/

    /**
     * Метод function может быть использована для разных типов.
     * Т.к. он имеет указание на GENERIC_TYPES.
     * Обобщенный метод нужен когда алгоритм примерно один и тот же для разных типов данных.
     * А перегруженный метод может реализовать алгоритм, который вообще не похож на алгоритм
     * в родительском классе.
     *
     * @param parametr входной параметр
     * @param <GENERIC_TYPES> обобщенный тип
     */
    static <GENERIC_TYPES> void function(GENERIC_TYPES parametr) {
        System.out.println(parametr.toString());//возвращаем строковое представление параметра
    }

    /**
     * Этот метод будет вызыван только для Числовых Параметров,
     * т.к. это прописано в Bounded Type Parameters
     * @param u входная переменная
     * @param <U> Bounded Type Parameters
     */
    public static <U extends Number> void function(U u){
        System.out.println("U: " + u.getClass().getName());
    }
}


