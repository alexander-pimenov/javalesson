package annotation.exampleSimpleService_by_U_Tkach;

@Service(name = "VeryLazyNetworkingService")
public class LazyService {
    @Init
    public void lazyInit() throws Exception {
        System.out.println("Выполняется метод lazyInit");
    }
}
