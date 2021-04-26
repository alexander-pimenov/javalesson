package annotation.exampleSimpleService;


import java.lang.annotation.*;

//@Documented - чтобы эта аннотация попала в JavaDoc
//@Inherited - эта аннотация будет унаследоваться потомками класса
//@Target(ElementType.TYPE) - область применения: над классами и интерфейсами
//@Retention(RetentionPolicy.RUNTIME) - аннотация будет жить во время runtime приложения
//а не только во время компиляции или только в исходниках или во время генерации
//кода
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    String name();

    boolean lazyLoad() default false;
}
