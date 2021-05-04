package equalshashcode;

import java.util.Objects;

/**
 * Вывод программы будет разным, если не переопределить equals()
 * и hashCode() или переопределить их.
 */
public class ProgramNotOverriddenEqualsAndHashCode {
    public static void main(String[] args) {
        AA a1 = new AA(1);
        AA a2 = new AA(1);
        System.out.print(a1 == a2);
        System.out.print(", " + a1.equals(a2));
        System.out.print(", " + Objects.equals(a1, a2));
        System.out.print(", " + Objects.deepEquals(a1, a2));
    }
}

//Output
//false, false, false, false

class AA {
    int a;

    public AA(int a) {
        this.a = a;
    }

}