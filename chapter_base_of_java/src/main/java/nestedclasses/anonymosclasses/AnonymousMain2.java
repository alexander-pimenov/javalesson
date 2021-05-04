package nestedclasses.anonymosclasses;

public class AnonymousMain2 {
    public static void main(String[] args) {
        Action actionAnon = new Action() {
            @Override
            public void logic1() {
                some();
                System.out.println("interface method");
            }

            public void some() {
                System.out.println("some method");
            }
        };

        actionAnon.logic1();

        Action actionLambda = () -> System.out.println("from lambda");

        actionLambda.logic1();


    }
}
