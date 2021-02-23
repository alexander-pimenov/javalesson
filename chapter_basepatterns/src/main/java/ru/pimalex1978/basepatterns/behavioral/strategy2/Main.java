package ru.pimalex1978.basepatterns.behavioral.strategy2;

/*
 * Паттерн Стратегия нужен, чтобы организовать разные способы
 * работы и отвязать классы и сделать их максимально не зависимыми
 * друг от друга.
 * */
public class Main {
    public static void main(String[] args) {
        /*Создаем мувер и он будет двигать круг*/
        Mover mover = new Mover(new Circle());

        /*выбираем как будет двигать*/
        mover.move(new MoveStrategy() {
            @Override
            public void move(Drawable drawable) {
                drawable.draw();
                System.out.println("Плавно");
            }
        });
        mover.move(new MoveStrategy() {
            @Override
            public void move(Drawable drawable) {
                drawable.draw();
                System.out.println("Скачками");
            }
        });
    }
}
