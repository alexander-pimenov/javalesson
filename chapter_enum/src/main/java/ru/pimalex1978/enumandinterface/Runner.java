package ru.pimalex1978.enumandinterface;

public class Runner {
    public static void main(String[] args) {
        RectangleParam rectangleParam = RectangleParam.PERIMETER;
        System.out.println(rectangleParam.service(2,3));

        RectangleAction rectangleAction = new RectangleAction();
        double result = rectangleAction.action(RectangleParamFunction.PERIMETER, 1.5, 4.0);
        System.out.println(result);
    }
}
