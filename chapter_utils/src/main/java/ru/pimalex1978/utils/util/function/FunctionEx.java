package ru.pimalex1978.utils.util.function;

/**
 * Function interface. It takes param and return data.
 *
 * @param <T> input arg.
 * @param <R> return data.
 */
@FunctionalInterface
public interface FunctionEx<T, R> {

    /**
     * Take param and return value.
     *
     * @param param input param
     * @return value.
     * @throws Exception possible exception.
     */
    R apply(T param) throws Exception;
}