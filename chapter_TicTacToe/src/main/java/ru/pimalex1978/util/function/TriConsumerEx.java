package ru.pimalex1978.util.function;

/**
 * It takes three args.
 *
 * @param <F> first.
 * @param <S> second.
 * @param <T> third.
 */
@FunctionalInterface
public interface TriConsumerEx<F, S, T> {
    /**
     * It takes three args.
     *
     * @param first  first.
     * @param second second.
     * @param third  third.
     * @throws Exception possible exception.
     */
    void accept(F first, S second, T third) throws Exception;
}
