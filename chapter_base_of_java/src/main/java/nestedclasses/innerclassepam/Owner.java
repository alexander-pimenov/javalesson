package nestedclasses.innerclassepam;

import java.util.Comparator;

/**
 * Пример с внутренними локальными классами, те которые внутри
 * метода. Эти классы во внешнем мире не видны в принципе. Или
 * только если сам метод не предоставит такой доступ.
 */
public class Owner {
    private int value = 1;
    private static int statValue = 2;

    void action() {
        int a = 2;
        class InnerAction {
            int inner = value;
            int innerA = a;
        }
//        new InnerAction();
    }

    static void staticAction() {
        int b = 3;
        class StaticInnerAction {
            //            int inner = value; // - так нельзя!!! т.к. value не static
            int innerB = b;
        }
    }

    /**
     * В статическом внутреннем классе есть напрямую
     * доступ только к статическим полям.
     */
    static class Nested {
        static int param = statValue;
    }

    /**
     * В статическом внутреннем классе через объект
     * внешнего класса есть доступ не только к статическим
     * полям, но и обычным.
     */
    static class ValueComparator implements Comparator<Owner> {

        @Override
        public int compare(Owner o1, Owner o2) {
            return o1.value - o2.value;
        }
    }
}
