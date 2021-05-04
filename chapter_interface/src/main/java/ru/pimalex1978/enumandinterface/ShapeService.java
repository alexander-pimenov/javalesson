package ru.pimalex1978.enumandinterface;

/**
 * Создаем интерфейс. Т.к. у него один метод, то
 * его смело называем функциональным интерфейсом.
 */
@FunctionalInterface
public interface ShapeService {
    /**
     * Метод для подсчета какой либо
     * величины разных фигур.
     *
     * @param param параметры сторон.
     * @return число периметра.
     */
    double service(double... param);
}
