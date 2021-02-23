package nestedclasses.anonymosclasses.example1base;

public class Test {
    int i;
    final int iff = 444;

    public static void main(String[] args) {
        //оставим пустым
        Test test = new Test();
        test.testAnonym(5000);
    }

    private void testAnonym(Integer p) {
        int a;
        final int aff = 222;

        //копируем пришедшую переменную p, чтобы потом можно было с ней работать.
        //обрамляем через массив. Он финальный
        final Integer[] testables = new Integer[1];
        testables[0] = p;

        Testable testable = new Testable() {
            @Override
            public void test() {
                // к камим переменным можно обратиться отсюда из анонимного класса
                // a = 10; //должна быть final
                i = 5; //к переменной класса можно
                System.out.println(aff);
                System.out.println(iff);

                //т.к. прернная не финальная то с ней работать не сможем
//                p = new Testable() {
//                    @Override
//                    public void test() {
//                    }
//                };
                //нужно работать с её копией
                testables[0] = 100500;
                System.out.println(testables[0]);
            }
        };
    }
}

