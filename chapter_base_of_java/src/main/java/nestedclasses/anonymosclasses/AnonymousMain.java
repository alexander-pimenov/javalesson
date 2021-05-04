package nestedclasses.anonymosclasses;

public class AnonymousMain {
    public static void main(String[] args) {
        //при создании класса переопределим метод
        Service service = new Service() {
            @Override
            public void service1() {
                System.out.println("anon serv 1");
            }

//            @Override
//            public void service2() {
//                System.out.println("anon serv 2");
//            }
        };

        // при вызове требуемого метода мы вызовем
        // переопределенный метод
        service.service1();

        //будет вызван метод суперкласса
        service.service2();
    }
}
