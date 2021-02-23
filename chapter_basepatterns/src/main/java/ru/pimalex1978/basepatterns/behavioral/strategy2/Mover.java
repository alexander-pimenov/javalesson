package ru.pimalex1978.basepatterns.behavioral.strategy2;

/*Тот кто двигает фигуры*/
public class Mover {
    /*Сначала фигуру рисуем*/
    private Drawable drawable;

    /*Создаем то что нужно двигать*/
    public Mover(Drawable drawable) {
        this.drawable = drawable;
    }

    /*Метод вибирающий стратегии передвижения фигур*/
    public void move(MoveStrategy moveStrategy){
        moveStrategy.move(drawable);
    }
}
