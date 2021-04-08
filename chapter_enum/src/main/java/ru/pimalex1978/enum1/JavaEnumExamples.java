package ru.pimalex1978.enum1;

/*Продвинутые возможности Enum на примере*/

import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Set;

public class JavaEnumExamples {
    public static void main(String[] args) throws IOException {

        usingEnumMethods();

//        usingEnumValueOf();

//        usingEnumValues();

//        usingEnumInSwitch(ThreadStatesEnum.START);

//        usingEnumInSwitch(ThreadStatesEnum.DEAD);

//        usingEnumMap();

//        usingEnumSet();

    }

    //Метод usingEnumMethods() показывает как создать объект перечисления
    //и как мы можем использовать его методы.
    private static void usingEnumSet() {
        EnumSet<ThreadStatesEnum> enumSet = EnumSet.allOf(ThreadStatesEnum.class);
        for (ThreadStatesEnum tsenum : enumSet) {
            System.out.println("Используем EnumSet, приоритет = " + tsenum.getPriority());
            System.out.println(tsenum.name());
        }
    }

    private static void usingEnumMap() {
        EnumMap<ThreadStatesEnum, String> enumMap = new EnumMap<>(ThreadStatesEnum.class);
        enumMap.put(ThreadStatesEnum.START, "Поток начал работу");
        enumMap.put(ThreadStatesEnum.RUNNING, "Поток ждет");
        enumMap.put(ThreadStatesEnum.WAITING, "Поток работает");
        enumMap.put(ThreadStatesEnum.DEAD, "Поток умер");

        Set<ThreadStatesEnum> keySet = enumMap.keySet();
        for (ThreadStatesEnum key : keySet) {
            System.out.println("ключ = " + key.toString() + " :: значение = " + enumMap.get(key));
        }

    }

    //Метод usingEnumInSwitch() показывает, как использовать константы Enum в switch.
    private static void usingEnumInSwitch(ThreadStatesEnum th) {
        switch (th) {
            case START:
                System.out.println("Состояние потока: START");
                break;
            case WAITING:
                System.out.println("Состояние потока: WAITING");
                break;
            case RUNNING:
                System.out.println("Состояние потока: RUNNING");
                break;
            case DEAD:
                System.out.println("Состояние потока: DEAD");
        }
    }

    //Метод usingEnumValues() показывает использование метода values(),
    //который возвращает массив, содержащий все значения перечисления в
    //том порядке, в котором они были объявлены.
    private static void usingEnumValues() {
        ThreadStatesEnum[] thArray = ThreadStatesEnum.values();

        for (ThreadStatesEnum th : thArray) {
            System.out.println(th.toString() + "::приоритет = " + th.getPriority());
        }
    }

    //Метод usingEnumValueOf() показывает использование метода valueOf(enumType,name)
    //класса java.util.Enum, с помощью которого мы можем создать объект
    //перечисления из строки.
    private static void usingEnumValueOf() {
        ThreadStatesEnum th = Enum.valueOf(ThreadStatesEnum.class, "START");
        System.out.println("th приоритет = " + th.getPriority());
    }

    //Метод usingEnumMap() показывает использование java.util.EnumMap,
    //который ввелся в Java 1.5 Collections Framework. EnumMap является
    //реализацией Map. Все ключи в EnumMap должны быть одного типа,
    //указанного явно или неявно, когда карта будет создана. Мы не можем
    //использовать null в качестве ключа для EnumMap.
    private static void usingEnumMethods() throws IOException {
        ThreadStatesEnum thc = ThreadStatesEnum.DEAD;
        System.out.println("приоритет : " + thc.getPriority());

        thc = ThreadStatesEnum.DEAD;
        System.out.println("Используем переопределенный метод. " + thc.toString());

        thc = ThreadStatesEnum.START;
        System.out.println("Используем переопределенный метод. " + thc.toString());
        thc.setPriority(10);
        System.out.println("Константа Enum изменила значение = " + thc.getPriority());
        thc = ThreadStatesEnum.WAITING;
        System.out.println(thc.toString()+ ", название: "+thc.getDetail());

        final ThreadStatesEnum[] values = ThreadStatesEnum.values();
        for (ThreadStatesEnum value : values) {
            System.out.println(value.toString());
        }

        System.out.println(ThreadStatesEnum.valueOf("RUNNING"));

        thc.close();
    }
}
