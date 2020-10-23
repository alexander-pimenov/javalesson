package ru.pimalex1978.util.function;

/**
 * Function interface. It supplies a single param.
 *
 * @param <T> type.
 */
@FunctionalInterface
public interface SupplierEx<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws Exception;
}