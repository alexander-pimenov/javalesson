package overridingoverloading;

public class Test {

    //к private int i запрещен доступ из других классов,
    // но не из других экземпляров данного класса.
    private int i;

    public Test(int i) {
        this.i = i;
    }

    public void testing(Test test) {
        System.out.println(i + test.i);
    }

    public static void main(String[] args) {
        Test test1 = new Test(1);
        Test test2 = new Test(2);
        test1.testing(test2);
    }
}
//Output
//3
