package annotation.exampleEasyJava;

import java.lang.reflect.Field;

/*https://github.com/EasyJavaRu/java-annotations-hello*/
public class Main {

    @GreeterTarget
    private static Greeter world = new Greeter();

    @GreeterTarget(value = "Annotations")
    private static Greeter annotations = new Greeter();

    @GreeterTarget("Java")
    private static Greeter java = new Greeter();

    public static void main(String[] args) throws NoSuchFieldException {

        Field worldField = Main.class.getDeclaredField("world");
        GreeterTarget worldTarget = worldField.getAnnotation(GreeterTarget.class);
        System.out.println(world.greet(worldTarget.value())); //Hello, world

        Field annotationsField = Main.class.getDeclaredField("annotations");
        GreeterTarget annotationsTarget = annotationsField.getAnnotation(GreeterTarget.class);
        System.out.println(annotations.greet(annotationsTarget.value())); //Hello, Annotations

        Field javaField = Main.class.getDeclaredField("java");
        GreeterTarget javaTarget = javaField.getAnnotation(GreeterTarget.class);
        System.out.println(java.greet(javaTarget.value())); //Hello, Java
    }
}
