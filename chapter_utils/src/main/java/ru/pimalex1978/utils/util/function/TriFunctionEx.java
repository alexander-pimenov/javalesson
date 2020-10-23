package ru.pimalex1978.utils.util.function;

@FunctionalInterface
public interface TriFunctionEx<T, U, V, R> {
    R apply(T t, U u, V v);
}