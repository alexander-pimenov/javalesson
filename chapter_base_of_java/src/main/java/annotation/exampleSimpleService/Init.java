package annotation.exampleSimpleService;

import java.lang.annotation.*;
/*
* Когда будем пробегаться по методам и те которые будут помечены
* @Init то мы их вызовем.
*/
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Init {
    boolean suppressException() default false;
}
