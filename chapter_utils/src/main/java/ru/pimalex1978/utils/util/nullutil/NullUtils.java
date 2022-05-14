package ru.pimalex1978.utils.util.nullutil;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Источник - https://github.com/rcvaram/NullUtil/blob/master/src/main/java/utils/NullUtils.java
 */
public class NullUtils {
    private NullUtils() {
    }

    /**
     * This method will be used to trim the String without causing the nullPointer Exception. When the value is null,
     * It will return null.
     * Этот метод будет использоваться для обрезки строки без возникновения исключения nullPointer. Когда значение равно null,
     * Он вернет null.
     * <p>
     * "  test   " -> "test"
     * "    "      -> ""
     * ""          -> ""
     * null        -> null
     *
     * @param value -String value
     * @return - trimmed value
     */
    public static String trimValue(String value) {
        return NullUtils.executeViaNullSafer(value, String::trim);
    }

    /**
     * This method is used to mutate the input object without causing the null pointer exception.
     * when the value is null, It will not execute the mutator
     * Этот метод используется для изменения входного объекта, не вызывая исключения нулевого указателя.
     * когда значение равно null, он не будет выполнять мутатор.
     *
     * @param value   - The value which need to send in mutatorFunction
     * @param mutator - The function which mutates on the value
     * @param <I>     - Type of the value
     */
    public static <I> void executeMutator(I value, Consumer<I> mutator) {
        if (value != null) {
            mutator.accept(value);
        }
    }

    /**
     * This method will execute on Non-Null Values when the value is null It will return Null instead of executing the function
     * Этот метод будет выполняться для ненулевых значений, когда значение равно null.
     * Он вернет значение Null вместо выполнения функции.
     *
     * @param value            - The value which need to send in executorFunction
     * @param executorFunction - The function which applies on the value
     * @param <I>              - Input value type
     * @param <O>              - Executor Function Output Value
     * @return - executedValue or Null
     */
    public static <I, O> O executeViaNullSafer(I value, Function<I, O> executorFunction) {
        return value != null ? executorFunction.apply(value) : null;
    }

    /**
     * This method will execute the executorFunction on Non-Null Values. If the value is null, It will return the default value
     * Этот метод будет выполнять функцию executorFunction для ненулевых значений.
     * Если значение равно null, оно вернет значение по умолчанию.
     *
     * @param value            - The value which need to send in executorFunction
     * @param executorFunction - The function which applies on the value
     * @param defaultValue     - If the value is null, this value will be return instead of executing the function
     * @param <I>              - Input value type
     * @param <O>              - Output value type
     * @return - executed value or defaultValue
     */
    public static <I, O> O executeExecutorOrDefault(I value, Function<I, O> executorFunction, O defaultValue) {
        return value != null ? executorFunction.apply(value) : defaultValue;
    }

    /**
     * This method will replace the null value by provided defaultValue. when the value is not null, It will return the value
     * Этот метод заменит null значение предоставленным значением по умолчанию. Когда значение не равно null, оно вернет значение.
     *
     * @param value        - The value which needs to be replaced if it is null
     * @param defaultValue - This defaultValue will be used to replace the value when the value is null
     * @param <I>          - Type of the value
     * @return actualValue or defaultValue
     */
    public static <I> I replaceNull(I value, I defaultValue) {
        return value == null ? defaultValue : value;
    }

    /**
     * This method is used to check the String value has any kind of text
     * Этот метод используется для проверки того, что значение String имеет любой текст.
     * ""    - false
     * "   " - false
     * null  - false
     * "a"   - true
     * "  a" - true
     *
     * @param value - string value which needs to be checked
     * @return - true / false
     */
    public static boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
