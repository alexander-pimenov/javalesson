package ru.pimalex1978.enumandinterface;

public class RectangleAction {
    public Double action(RectangleParamFunction param, Double a, Double b) {
        return param.get().apply(a, b);
    }
}
