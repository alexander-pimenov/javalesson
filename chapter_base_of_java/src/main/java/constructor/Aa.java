package constructor;

/**
 * Explanation
 *
 * В иерархии классов конструкторы вызываются в порядке наследования,
 * начиная с суперкласса и заканчивая подклассом.
 */
public class Aa {
    public Aa() {
        System.out.print("Aa");
    }
}

class Bb extends Aa{
    public Bb() {
        System.out.print("Bb");
    }
}

class Cc extends Bb{
    public Cc() {
        System.out.print("Cc");
    }

    public static void main(String[] args) {
        Cc cc = new Cc();
    }
}
//Output
//AaBbCc
