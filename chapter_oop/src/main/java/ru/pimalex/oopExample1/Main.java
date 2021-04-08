package ru.pimalex.oopExample1;

/*Объекты окружают нас в реальном мире:
 * компьютер - объект
 * авто - объект
 * стул - объект
 * стол - объект
 * собака - объект
 * Мы из Класса, как из шаблона (как по чертежу) можем сделать большое количество объектов(столов, стульев, машин)
 * Объект - это что то реальное, что нас окружает
 * у каждого объекта есть какие то св-ва (размер, вес, возможности: ехать, сигналить, поворачивать)
 * У объектов есть Сосотояние и Поведение
 * Состояние - это то свойство, к-рое есть у объекта, независимо от того выполняет он какую то функцию или нет
 * Поведение - описывается методами
 * Состояние определяется ПОЛЯМИ
 * Поля - это переменные,к-рые мы описываем в Классе,в частности в Классе Dog, СРАЗУ после Класса, не в методе
 * чтобы передать параметры в ПОЛЯ, используются методы геттеры и сеттеры,
 * которые создаем в Классе Dog, конструируем их, а данные записываем (генереруем) в методе main через сеттеры setXxxx()*/
public class Main {

    /*выводим значения обоих объектов:*/

    public static void main(String[] args) {

        /*вызываем метод getDogsCount еще до того, как создадим наш объект
        обращаемся к нашему Классу Dog. и вызываем метод.
        выведем его га консоль*/

        System.out.println("Dog's count " + Dog.getDogsCount());

        /*потому что статическое поле или статический метод являются АТРИБУТАМИ Класса, а не объекта данного Класса
        К статическому методу или ПОЛЮ можно обращаться используя Класс Dog, а не объект Класса Dog,
        к-рый создается в main методе класса Main
        как видно мы к Классу Dog обращаемся Dog.getDogsCount еще до Dog lab = new Dog()*/

        /*к примеру у нас есть чертеж нашей собаки, это класс Dog.java.
         * Создаем объект Собака (например робот), по чертежу Класс Dog
         * мы можем это сделать с помощью оператора new
         * вводим переменую с типом Dog, он определен в Dog.java, имя даем ей lab */

        Dog lab = new Dog();//создаем объект lab с помощью оператора new из класса Dog. объект из чертежа-класса
        //по сути записывая "new Dog()", мы обращаеся к Конструктору Dog
        //но пока Конструктор не создан, он был у нас в нашем Классе Dog не явно и пустой Конструктор объявлять не обязательно

        //мы инициализировали переменную lab из класса Dog с помощью метода new
        //т.обр. мы будто бы создаем новую собаку, или если бы была машина, то новую машину из какого то чертежа
        /*дальше можем получить доступ к нашим полям
         * это возможно, если ПОЛЯ public
         * но чаще всего ПОЛЯ private*/
        /*ниже код закомментирован, т.к. они были public, а мы их переделали в private. приведен вариант доступа к ПОЛЯМ private*/

        /*lab.paws = 4;  //оператор '.' служит также к получению доступа к полям
          lab.tail = 1;
          lab.name = "Charley";
          lab.breed = "Lab";
        //мы не сможем получить доступ к переменных,к-рые находятся внутри метода main Dog,
        //но можем получить доступ к переменным (к полям) внутри самого класса Dog
        //здесь переменные paws, tail, name, breed могут быть переопределенны
        */
        //таким вот способом мы получаем доступ к нашему состоянию объекта Dog
        //в том числе к полям,к-рые ранее не были инициализированны


        /*доступ к ****private*** в Классе Dod полям можно сделать ТОЛЬКО с помощью методов,
        к-рые называются геттеры и сестеры
        *
          поэтому уже будет не
          lab.paws = 4;
          lab.tail = 1;
          lab.name = "Charley";
          lab.breed = "Lab";
          а
          lab.setPaws(4);
          lab.setTail(1);
          lab.setName("Charley");
          lab.setBreed("Lab");
           - в() обязательно передаем параметры, в данном случае (4),(1),("Charley"),("Lab").
           если парметры не запишем, то будет ошибка компиляции
          */
        //lab.setPaws(4); //здесь специально поставли не корректное количество ног. смотри,что будет далее
        //lab.setTail(1); //аналогично с хвостом
        lab.setName("Charley");
        lab.setBreed("Lab");
        lab.setSize(Size.AVERAGE);//как только завели "enum Size" то String больше
        // не подходит в качестве переданного параметра
        //нам нужно здесь указывать Size. И обращаемся к ним как к static final
        //наример Size.AVERAGE
        lab.bark(); // - так мы "заставляем" лаять нашу собаку, т.к. вызываем МЕТОД bark()

        /*Создадим еще один объект данного класса
         * и присваиваем ряд значений для наших полей
         * ТАКИХ ОБЪЕКТОВ, созданных из одного КЛАССА может быть МНОЖЕСТВО
         * есть один ШАБЛОН и множество ОБЪЕКТОВ*/
        Dog sheppard = new Dog();
        //sheppard.setPaws(4);
        //sheppard.setTail(1);
        sheppard.setName("Mike");
        sheppard.setBreed("Sheppard");
        sheppard.setSize(Size.BIG);
        sheppard.bite();

        Dog doberman = new Dog();
        //doberman.setPaws(4);
        //doberman.setTail(1);
        doberman.setName("Jack");
        doberman.setBreed("Doberman");
        doberman.setSize(Size.BIG);
        doberman.bite();

        //System.out.println("Lab has " + lab.getPaws() + " paws");
        System.out.println("Lab's name is " + lab.getName());
        System.out.println("Sheppard's name is " + sheppard.getName());
        //System.out.println("Sheppard has " + sheppard.getPaws() + " paws");


         /*Для того чтобы продемонстрировать методы для -Enum,
    которые видим при Ctrl-F12, создадим новый Size*/

        Size s = Size.SMALL; //здесь мы не можем использовать оператор new, Size не public.
        //т.е. используем как вызов метода. Такмы инициализировали новый Size s
        //System.out.println(s); // результат - SMALL
        Size s1 = Size.valueOf("BIG"); //берем переменную, и хотим создать из строки Size.valueOf("BIG") Size s1
        System.out.println(s1);
        /*
         * можем получить весь массив значений нашего Size
         * */
        Size[] values = Size.values(); //Ctrl+Q:Returns an array containing the constants of this enum type, in the order they're declared. This method may be used to iterate over the constants as follows:
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
    }
    /*ENUM тип - разновидность Классов, который используется если у нас есть некоторые ОГРАНИЧЕННЫЕ разновидности чего либо
     * например, дни недели: они разные, но их только семь. Эти значения КОНСТАНТНЫ и они не меняются
     *
     * В данном случае ENUM тип поможет решить проблему с нашим размером. Помогает защитится от некоторых проблем, ошибок
     *
     * ENUM тип может быть определен внутри уже существующего класса:
     * НО это не всегда удобно, особенно елси этот ENUM будет использоваться из разных Классов
     * ПОЭТОМУ ENUM тип ЦЕЛЕСООБРАЗНО определить в отдельном Классе
     * */

    //public enum Size {BIG, AVERAGE, SMALL} //возможные параметры здесь являются КОНСТАНТАМИ
    //поэтому пишутся с большой буквы

}
