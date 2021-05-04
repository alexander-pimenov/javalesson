package ru.pimalex1978.musictype;

/**
 * Все перечисления это наследники класса java.lang.Enum;
 * От перечислений нельзя унаследоваться.
 * Не может быть суперклассом.
 * Перечисление не может быть абстрактным. Но может содержать абстрактные
 * методы.
 * Т.к. перечисления это классы, то у них могут быть разные конструкторы.
 * Но для них нужны параметры.
 * Мы помним, что конструкторы нужны чтобы инициализировать поля класса.
 * В теле перечисления можно объявлять методы, поля и конструкторы.
 * Конструктор перечисления определяется без модификатора доступа
 * или с модификатором private.
 * Элемент перечисления может хранить не только именованную
 * константу, но и набор каких то значений, которые связаны с
 * этой константой. Этих полей может быть много и они как то
 * характеризуют константу.
 */
public enum MusicType {
    ROCK("Rock"), JAZZ("Jazz"), CLASSIC;

    //дефолтный конструктор
    //при создании объекта enum будем выводить сообщения
    //его имя и номер
    MusicType() {
        System.out.println(this.name() + " " + this.ordinal());
    }

    private String realName;

    //конструктор с параметром
    MusicType(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    //используем в switch-case
    //не забываем про default
    public void info(){
        //this - это объект перечисления
        switch (this) {
            case JAZZ:
                System.out.println("this is " + realName);
                break;
            case ROCK:
                System.out.println("this is " + realName);
                break;
            case CLASSIC:
                System.out.println("this is classic");
                break;
            default:
                //здесь можно выбрасывать исключения или что еще
                System.out.println("style out");
        }
    }
}
