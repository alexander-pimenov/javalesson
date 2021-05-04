package constructor;

/**
 * Explanation
 * <p>
 * В иерархии классов конструкторы вызываются в порядке наследования,
 * начиная с суперкласса и заканчивая подклассом. Ключевое слово this
 * представляет текущий экземпляр класса, а ключевое слово super -
 * текущий экземпляр родительского класса. В данном примере сначала
 * вызовется конструктор без параметров родительского класса A, затем
 * вызовется конструктор без параметров дочернего класса B, здесь
 * через ключевое слово this вызовется конструктор с параметрами класса В,
 * в этом конструкторе через ключевое слово super вызовется конструктор с
 * параметрами родительского класса A.
 * Если бы в конструкторе без аргументов B(){} не было строчки this("");,
 * то пошелбы вызов суперкоструктора A(){}.
 */
public class A {
    A() {
        System.out.println("A: no arguments");
    }

    A(String args) {
        System.out.println("A: one arguments");
    }
}

class B extends A {
    public B() {
        this("");
        System.out.println("B: no arguments");
    }

    public B(String args) {
        super("");
        System.out.println("B: one arguments");
    }
}

class Main {
    public static void main(String[] args) {
        B b = new B();
    }
}
//Output:
//A: one arguments
//B: one arguments
//B: no arguments