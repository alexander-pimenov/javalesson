package statics;

/**
 * Что такое static метод интерфейса?
 * Статические методы интерфейса похожи на методы по умолчанию,
 * за исключением того, что для них отсутствует возможность
 * переопределения в классах, реализующих интерфейс.
 * <p>
 * Статические методы в интерфейсе являются частью интерфейса
 * без возможности использовать их для объектов класса реализации;
 * Методы класса java.lang.Object нельзя переопределить как статические;
 * Статические методы в интерфейсе используются для обеспечения
 * вспомогательных методов, например, проверки на null,
 * сортировки коллекций и т.д.
 * <p>
 * Как вызывать static метод интерфейса?
 * Используя имя интерфейса
 */
public class StaticMethonInterface {
    public static void main(String[] args) {
        Licence1 licence1 = new Licence1();
        licence1.showPaper();
    }
}

interface Paper1 {
    static void show1() {
        System.out.println("static method show() from interface");
    }
}

class Licence1 {
    public void showPaper() {
        Paper1.show1();
    }
}

