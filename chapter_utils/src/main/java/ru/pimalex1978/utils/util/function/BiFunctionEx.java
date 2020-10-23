package ru.pimalex1978.utils.util.function;

@FunctionalInterface
public interface BiFunctionEx<T, U, R> {
    R apply(T t, U u) throws Exception;
}