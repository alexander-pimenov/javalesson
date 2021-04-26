package annotation.exampleSimpleService;

@Service(name = "VeryLazyNetworkingService")
public class LazyService {
    @Init
    public void lazyInit() throws Exception {
        System.out.println("Выполняется метод lazyInit");
    }
}
