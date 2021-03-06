package ru.pimalex1978.util.function;

/**
 * Unary functional interface with exception.
 */
@FunctionalInterface
public interface ProcedureEx {
    /**
     * Action with exception.
     *
     * @throws Exception possible exception.
     */
    void action() throws Exception;
}