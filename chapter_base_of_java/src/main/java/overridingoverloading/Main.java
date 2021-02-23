package overridingoverloading;

public class Main {
    public static class Parent {
        public void test() {
            System.out.println("parent.test()");
        }
    }

    public static class Child extends Parent {
        public void test() {
            System.out.println("child.test()");
        }
    }

    public static class Tester {
        //Раннее связывание. На этапе компиляции. Overloading. Перегрузка
        public void test(Parent parent) {
            System.out.println("test parent");
            //Позднее связывание. На этапе выполнения. Overriding. Переопределение. Полиморфизм.
            parent.test();
        }

        //Раннее связывание. На этапе компиляции. Overloading. Перегрузка
        public void test(Child child) {
            System.out.println("test child");
            //Позднее связывание. На этапе выполнения. Overriding. Переопределение. Полиморфизм.
            child.test();
        }
    }

    public static void main(String[] args) {
        Parent child = new Child();
        child.test();
        System.out.println();

        Tester tester = new Tester();
        tester.test(new Parent());
        tester.test(new Child());
        System.out.println();

        tester.test(child); //Раннее связывание. На этапе компиляции. Overloading. Перегрузка
    }
}
