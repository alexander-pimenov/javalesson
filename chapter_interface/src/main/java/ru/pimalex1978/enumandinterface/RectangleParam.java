package ru.pimalex1978.enumandinterface;

/**
 * Здесь реализуется метод из нашего интерфейса ShapeService
 * под каждую формулу фигуры.
 */
public enum RectangleParam implements ShapeService {
    /*используем анонимные классы*/
    PERIMETER {
        @Override
        public double service(double... param) {
            return 2 * (param[0] + param[1]);
        }
    },
    SQUARE {
        @Override
        public double service(double... param) {
            return param[0] * param[1];
        }
    };

//    @Override
//    public double service(double... param) {
//        return 0;
//    }
}
