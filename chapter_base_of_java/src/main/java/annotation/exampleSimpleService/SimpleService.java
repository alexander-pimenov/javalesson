package annotation.exampleSimpleService;

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
