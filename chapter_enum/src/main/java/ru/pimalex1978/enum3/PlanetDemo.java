package ru.pimalex1978.enum3;

public class PlanetDemo {
    public static void main(String[] args) {
        //перебираем весь массив констант енама
        Planet[] planets = Planet.values();
        for (Planet p : planets) {
            System.out.println(p);
            System.out.println("mass: " +p.getMass() + ", radius: " + p.getRadius());
        }

        //найдем константу по строковому предствавлению
        final Planet mercury = Planet.valueOf("MERCURY");
        System.out.println(mercury);

        int ordinal = Planet.MARS.ordinal();
        System.out.println(ordinal);

        int i = Planet.VENUS.compareTo(Planet.EARTH);
        System.out.println(i);
    }
}

enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.9736e+24, 6.371e6),
    MARS(6.4171e+23, 3.389e6);

    //масса планеты в кг
    private final double mass;
    //ралиус планеты в метрах
    private final double radius;
    //ускорения свободного падения
    private static final double G = 9.80665;

    private Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public double getSurfaceGravity() {
        return G * mass / (radius * radius);
    }
}