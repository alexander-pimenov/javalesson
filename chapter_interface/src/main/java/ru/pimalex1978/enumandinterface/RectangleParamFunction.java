package ru.pimalex1978.enumandinterface;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Здесь реализуется метод под каждую формулу фигуры.
 * Используем стандартные функциональные интерфейсы Supplier, BiFunction
 * для реализаций вычисляемых формул.
 * Используем лямбды.
 */
public enum RectangleParamFunction implements Supplier<BiFunction> {
    PERIMETER((o1, o2) -> 2 * (o1 + o2)),
    SQUARE((o1, o2) -> (o1 * o2));

    private BiFunction<Double, Double, Double> biFunction;

    RectangleParamFunction(BiFunction<Double, Double, Double> biFunction) {
        this.biFunction = biFunction;
    }

    @Override
    public BiFunction<Double, Double, Double> get() {
        return biFunction;
    }
}
