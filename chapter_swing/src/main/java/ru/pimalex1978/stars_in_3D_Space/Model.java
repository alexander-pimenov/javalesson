package ru.pimalex1978.stars_in_3D_Space;

import java.util.ArrayList;

/**
 * Класс управление движением точек. (логика)
 * "Игровой движок".
 */
public class Model {
    //координата z. с этой позиции старту.т все точки
    public static float INITIAL_Z_COORD = -3;

    //скорость точки
    public static float MOTION_SPEED = 0.001f;

    //коллекция для хранения множества точек
    ArrayList<Point> points = new ArrayList<>();

    /**
     * Главная функция всей модели.
     *
     * @param elapsedTime принимает прошедшее время,
     *                    время которое прошло с предыдущего
     *                    обновления.
     */
    public void update(long elapsedTime) {
        //переменная для появления новых точек - "шанс рождения новой точки"
        //вероятность создания новой точки
        float birthChance = 0.02f;

        //логика создания новой точки
        if (random(0, 1) < birthChance) {
            //создаем новую точку
            points.add(
                    new Point(random(-1, 1), random(-1, 1), INITIAL_Z_COORD)
            ); //координа х и у задаются в пределах от -1 до 1, z константа
        }

        //создаем движение для тех точек, которые уже существуют
        for (Point point : points) {
            //передвигаем по координате z вперед
            point.z += elapsedTime * MOTION_SPEED;
        }

        //та точка, которая уже дошла до экрана (до наблюдателя)
        //уничтожается. Описываем условие
        points.removeIf(point -> point.z >= 0);
    }

    /**
     * Главная функция всей модели.
     * Метод взят из пакета file_render для Model.
     *
     * @param elapsedTime принимает прошедшее время,
     *                    время которое прошло с предыдущего
     *                    обновления.
     */
    public void update2(long elapsedTime) {
        for (int i = 0; i < 3; i++) {
            points.add(new Point(random(-1, 1), random(-1, 1), INITIAL_Z_COORD,
                    0xff000000 | (int) random(180, 255) << 16 | (int) random(180, 255) << 8 | (int) random(180, 255)));
        }

        for (Point point : points) {
            point.z += elapsedTime * MOTION_SPEED;
        }

        points.removeIf(point -> point.z >= 0);
    }

    /**
     * Вспомагательный метод для создания случайных
     * чисел в нужном диапазоне. Т.е. растягиваем число
     * в указаном диапазоне.
     *
     * @param from начало диапазона
     * @param to   конец диапазона
     * @return возвращет случайное число, входящее в
     * диапазон.
     */
    private float random(float from, float to) {
        return (float) (from + (to - from) * Math.random());
    }

    /**
     * Метод получения точек.
     *
     * @return список точек.
     */
    public ArrayList<Point> getPoints() {
        return points;
    }
}
