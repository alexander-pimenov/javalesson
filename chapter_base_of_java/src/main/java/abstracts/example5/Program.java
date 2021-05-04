package abstracts.example5;

public class Program {
    public static void main(String[] args) {
        A c = new C() { //2
            public void b() {
                System.out.print("B");
            }

            public void e() {
                System.out.print("e");
            }
        };
        c.a();
        c.b();
        c.c();
        c.d();
        c.e();
    }
}
//Output
//aBcDe

