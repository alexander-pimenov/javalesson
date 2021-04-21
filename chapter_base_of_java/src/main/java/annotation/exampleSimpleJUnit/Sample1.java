package annotation.exampleSimpleJUnit;

/**
 * Тестировать мы будем класс Sample1, а конкретно, его аннотированные
 * с помощью @Test методы
 */
public class Sample1 {
    @Test
    public static void m1() {
    } // Тест должен пройти

    public static void m2() {
    }

    @Test
    public static void m3() {
        // Тест должен завершиться неудачно
        throw new RuntimeException("Boom");
    }

    public static void m4() {
    }

    public static void m6() {
    }

    @Test
    public static void m7() {
        // Тест должен завершиться неудачно
        throw new RuntimeException("Crash");
    }

    public static void m8() {
    }

//    @Test
    public void m5() {

    }
    // НЕПРАВИЛЬНОЕ ИСПОЛЬЗОВАНИЕ: нестатический метод
    //Если раскомментировать @Test то падаем с ошибкой NullPointerException
    //т.к. это не статический метод и для работы с ним нужно создавать объект
    // класса Sample1 и работать через него.
}