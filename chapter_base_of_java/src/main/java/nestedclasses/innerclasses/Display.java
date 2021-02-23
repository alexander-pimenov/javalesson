package nestedclasses.innerclasses;

public class Display {

    //высота и ширина дисплея, они константы и изменяться не будут
    private static final int DISPLAY_HEIGHT = 1920;
    private static final int DISPLAY_WIDTH = 1280;

    //конструктор
    public Display() {
        //сгенерируем Pixel в Display, с какими то начальными параметрами
        Pixel pixel = new Pixel(10, 10, Color.BLUE);
    }

    /*Рассматриаем первый способ использования вложенных классов*/
    /*Вложеный класс существует в пределах определенного класса, т.к. он имеет СУТЬ только внутри этого класса*/
    //создадим вложенный класс Pixel. Здесь класс статический, и стат.класс имеет доступ к стат.ПОЛЯМ.
    //если поля не статик то и класс может быть не статик
    //и следуя инкапсуляции, класс должен быть приватным, чтоб пользователь не смог попасть в него из вне
    //только Display программно имеет возможность в него попадать
    private static class Pixel {

        //координаты и цвет
        //Т.К. эти поля используются внутри только этого класса,
        //объявим их как private
        //это локальные поля класса Pixel
        private int x;
        private int y;
        private Color color;

        //конструктор
        private Pixel(int x, int y, Color color) {

            //также ставим проверку на соответствие высоте и ширине 1920*1280

            if (0 <= x && x <= DISPLAY_WIDTH && 0 <= y && y <= DISPLAY_HEIGHT){
                this.x = x;
                this.y = y;
                this.color = color;
                //чтобы видеть что инициализируется инстанс Pixel
                System.out.println("Creating " + color + " pixel at (" + x + ", " + y + ")");
            } else{
                //сообщаем об ошибке. Более целесообразно сообщать об ошибке с помощью exceptionsExample
                //самомтоятельно бросаем exception поэтому используем throw
                throw new  IllegalArgumentException("Coordinates x and y should be between 0-"+DISPLAY_WIDTH+" and 0-"+DISPLAY_HEIGHT);//Аргумент был Не Верным
            }
        }
    }

    public enum Color {
        RED, GREEN, BLUE, CYAN, MAGENTA, YELLOW, BLACK
    }
}
