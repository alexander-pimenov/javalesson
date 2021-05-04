package abstracts.example6;

/**
 * Explanation
 *
 * При вызове getClass().getSimpleName() в виде строки вернется
 * имя класса, от которого был создан объект.
 */
public class MegaClass {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public static void main(String[] args) {
        BasicClass basicClass = new BasicClass();
        System.out.println(basicClass.toString());
    }
}

class SuperClass extends MegaClass {
}

class BasicClass extends SuperClass {
}

//Output
//BasicClass