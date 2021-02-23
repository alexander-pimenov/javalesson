package nestedclasses.innerclasses;

/*здесь рассматриваем такое понятие, как вложенный или внутренние классы*/

public class Main {
    public static void main(String[] args) {
        CellPhone phone = new CellPhone("Motorola", "XT1575");

        //вызовом turnOn инициализируем Display
        phone.turnOn();

        phone.call("1234567890");

        //с помощью геттера вытащим наш инстанс
        Display display = phone.getDisplay();

        /**/

/*таким будет код если внутренний класс Pixel не будет статическим , но публичным:
        //создадим класс Pixel, обрати внимание как он записывается и инициализируем его
        Display.Pixel pixel = display.new Pixel(10000, 100, Display.Color.RED);

        //или так:
        Display.Pixel pixel = phone.getDisplay().new Pixel(123, 123, Display.Color.GREEN);
*/

/*
но внутр.класс должен быть также приватным, нельзя чтобы был общий доступ, как здесь
нужно чтобы опция была доступна только для нашего Display
        Display.Pixel pixel = new Display.Pixel(123, 111, Display.Color.CYAN);

*/


    }
}
