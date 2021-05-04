package ru.pimalex1978.musictype;

/**
 * При первом обращении к какому либо перечислению инициализируются
 * все имеющиеся в классе enum перечисления. Это видно при
 * запуске программы.
 */
public class Runner {
    public static void main(String[] args) {
        //варианты создания enum
        //1-й вариант
        MusicType type = MusicType.JAZZ;

        //2-й вариант
        String value = " Rock "; //название так пришло например
//        MusicType type1 = MusicType.valueOf(value.toUpperCase().trim());

        type.info();

//        String name = type.getRealName();
        //используем в switch-case
        //не забываем про default
//        switch (type) {
//            case JAZZ:
//                System.out.println("this is " + name);
//                break;
//            case ROCK:
//                System.out.println("this is " + name);
//                break;
//            case CLASSIC:
//                System.out.println("this is classic");
//                break;
//            default:
//                //здесь можно выбрасывать исключения или что еще
//                System.out.println("style out");
//
//        }

        //сравниваются перечисления по порядку в котором  они стоят
        MusicType type1 = MusicType.valueOf("CLASSIC");
        System.out.println(type.compareTo(type1));

    }
}
