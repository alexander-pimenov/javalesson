package nestedclasses.innerclassepam;

public class InterfaceMain {
    public static void main(String[] args) {
        // 1
        Action.StaticService.service1();

        // 2
        Action.newService();

        // 3
        new Action.StaticService().service2();

        // 4
        new Action() {
        }.newService2();
    }
}
