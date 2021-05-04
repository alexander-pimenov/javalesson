package nestedclasses.anonymosclasses.anonymousandenum;

import java.util.Arrays;

public class AnonimousEnumMain {
    public static void main(String[] args) {
        //обработаем наши Shape с помощью стрима
        Arrays.stream(Shape.values())
                .forEach(e -> System.out.println(e.computeSquare()));
    }
}
