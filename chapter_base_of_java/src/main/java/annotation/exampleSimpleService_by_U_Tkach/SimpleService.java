package annotation.exampleSimpleService_by_U_Tkach;

@Service(name = "SuperSimpleService")
public class SimpleService {
    @Init
    public void initService() {
        System.out.println("Выполняется метод initService");
    }

    public void printSomething(){
        System.out.println("Печатаем из метода printSomething");
    }
}
