package ru.pimalex1978.stars_in_3D_Space;

/**
 * Класс представляющий точку в 3-х мерном
 * пространстве.
 */
public class Point {
    float x;
    float y;
    float z;
    int color;

    public Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(float x, float y, float z, int color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }
}
