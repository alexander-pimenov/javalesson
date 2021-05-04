package equalshashcode;

import java.util.Objects;

/**
 * Вывод программы будет разным, если не переопределить equals()
 * и hashCode() или переопределить их.
 */
public class ProgramWithOverriddenEqualsAndHashCode {
    public static void main(String[] args) {
        A a1 = new A(1);
        A a2 = new A(1);
        System.out.print(a1 == a2);
        System.out.print(", " + a1.equals(a2));
        System.out.print(", " + Objects.equals(a1, a2));
        System.out.print(", " + Objects.deepEquals(a1, a2));
    }
}

//Output
//false, true, true, true

class A {
    int a;

    public A(int a) {
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a1 = (A) o;
        return a == a1.a;
    }

    //
    @Override
    public int hashCode() {
        return Objects.hash(a);
    }
}