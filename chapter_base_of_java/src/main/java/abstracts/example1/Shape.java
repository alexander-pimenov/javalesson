package abstracts.example1;
/*создать метод, к-рый будет принимать на вход список фигур и печатать
 * в консоли их параметры
 * создаем интерфейс и определяем в нем, какие методы нам понадобится*/
public interface Shape {
    String getName();//получение имени
    double getSquare();//получение площади
    String getColor();//метод получения цвета
}
