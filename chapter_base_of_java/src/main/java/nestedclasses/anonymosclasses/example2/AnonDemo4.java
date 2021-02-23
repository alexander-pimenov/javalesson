package nestedclasses.anonymosclasses.example2;


/**
 * пример, в котором демонстрируется несколько
 * приемов использования анонимных классов
 * В этом примере метод createAnon объявляет анонимный класс
 * и возвращает ссылку типа суперкласс (AA) на экземпляр анонимного
 * класса. Это означает, что экземпляр анонимного класса может быть
 * использован вне объявляющего его контекста (createAnon). Далее вызывается
 * метод getValue объектной ссылки на анонимный класс.
 */
class AA {
    int afield;

    // установить значение afield

    AA(int afield) {
        this.afield = afield;
    }

    // получить значение afield

    int getValue() {
        return afield;
    }
}

public class AnonDemo4 {
    static AA createAnon() {
        final int dlocal = 40;

        //возвратить из метода f() экземпляр
        //анонимного класса, порожденного из А

        //вызвать конструктор суперкласса
        return new AA(10) {
            int bfield = 20;
            int cfield;

            {
                cfield = 30;
            }

            int getValue() {
                return afield + bfield + cfield + dlocal;
            }
        };
    }

    public static void main(String[] args) {
        AA anonfef = createAnon();

        System.out.println(anonfef.getValue());
    }
}

