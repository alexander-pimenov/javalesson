package nestedclasses.innerclassepam;

public interface Action {

    public static class StaticService { //public static можно не писать
        static void service1() {
        }

        void service2() {
        }
    }

    static void newService() {
    }

    default void newService2() {
    }
}
