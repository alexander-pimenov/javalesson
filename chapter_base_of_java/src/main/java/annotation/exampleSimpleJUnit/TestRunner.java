package annotation.exampleSimpleJUnit;

// Программа для обработки аннотаций маркеров
//https://sysout.ru/annotatsii-v-java-i-ih-obrabotka-v-runtime-s-pomoshhyu-refleksii/
//https://github.com/myluckagain/sysout/tree/master/annotations-runtime/src/main/java/ru/sysout/annotationsruntime

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) throws Exception {

        Class<?> testClass = Sample1.class;
        run(testClass);

        testClass = Sample2.class;
        run(testClass);

    }

    /**
     * Здесь обработка аннотации @Test.
     * <p>
     * В цикле мы идем по полученным методам класса и выбираем аннотированные.
     * <p>
     * Затем мы их запускаем с помощью invoke(). В метод m.invoke(null) мы
     * передаем null, поскольку метод статический. (!!!!!)
     * Именно для этого по условию задачи мы и сделали  методы статическими.
     * Иначе пришлось бы создавать и передавать экземпляр класса, на котором
     * вызывается метод, вместо null.
     * <p>
     * Далее, в InvocationTargetException оборачивается исключение, выброшенное
     * изнутри тестового метода. То есть попадание в этот блок catch() считается
     * провалом теста.
     * <p>
     * В блок IllegalAccessException мы попадаем, если некорректно вызвали
     * invoke(): например, передали ему null, когда m не статический. Эта
     * ситуация считается невалидным тестом.
     * <p>
     * Если же ни в какой блок catch() мы не попали, что тест считается валидным
     * и пройденным.
     *
     * @param testClass объект типа Class.
     */
    public static void run(Class<?> testClass) {
        int tests = 0;
        int passed = 0;
        //Здесь обработка аннотации @Test
        for (Method m : testClass.getDeclaredMethods()) {
            System.out.println("Метод: " + m.getName());

            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(null);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m + " failed: " + exc);
                } catch (IllegalAccessException exc) {
                    System.out.println("Invalid @Test: " + m);
                }
            }

            // Processing repeatable annotations
            // Обработка повторяющихся аннотаций
            /*
             * Обрабатывать повторяемые аннотации надо осторожно.
             * Особенность в том, что
             * m.isAnnotationPresent(ExceptionTest.class) возвращает false,
             * если метод аннотирован ею как минимум дважды. В этом случае
             * m.isAnnotationPresent(ExceptionTestContainer.class)== true.
             * Зато если метод аннотирован ею единожды, то
             * m.isAnnotationPresent(ExceptionTest.class)==true,
             * а m.isAnnotationPresent(ExceptionTestContainer.class)==false
             * */

            if (m.isAnnotationPresent(ExceptionTest.class)
                    || m.isAnnotationPresent(ExceptionTestContainer.class)) {
                tests++;
                try {
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", m);
                } catch (Throwable wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    int oldPassed = passed;
                    ExceptionTest[] excTests =
                            m.getAnnotationsByType(ExceptionTest.class);
                    for (ExceptionTest excTest : excTests) {
                        if (excTest.value().isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }
                    if (passed == oldPassed)
                        System.out.printf("Test %s failed: %s %n", m, exc);
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n",
                passed, tests - passed);
    }
}
