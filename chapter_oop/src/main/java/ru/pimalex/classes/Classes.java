package ru.pimalex.classes;

/*
 * В java без Классов вообще ничто не может обойтись!!!
 * и поэтому даже самая минимальная программа заключается внутри Класса
 *
 *
 */

public class Classes {
    public static void main(String[] args) {
        Ball ball = new Ball();//создаем объект Ball
        ball.radius = 1; // ВАЖНО ПОНИМАТЬ, что указанные переменные принадлежат не Классу, а они принадлежат экземпляру Класса (Объекту), который мы создали
        ball.color = "Red";//оператор (.) используется для того чтобы получить доступ к переменным Класса Ball

        Ball blueBall = new Ball();//создаем объект Ball
        blueBall.radius = 2; // ВАЖНО ПОНИМАТЬ, что указанные переменные принадлежат не Классу,
        blueBall.color = "Blue"; //а они принадлежат экземпляру Класса (Объекту), который мы создали. т.е. конкретным нами созданным Сущностям

        System.out.println("Параметры первого мяча:");
        System.out.println(ball.radius);
        System.out.println(ball.color);
        System.out.println("Параметры второго мяча:");
        System.out.println(blueBall.radius);
        System.out.println(blueBall.color);

        Box box = new Box();// создаем Коробку
        //задаем высоту коробки/ящика
        box.height = 2.5;
        /*внутри этой Коробки у нас может быть Мяч*/
        box.ball = blueBall;//помещаем внутрь этой Коробки синий мяч blueBall, ранее нами созданный

        System.out.println("Мяч из Коробки Box:");
        System.out.println(box.ball.color);
        System.out.println("Вывод метода возвращающего радиус мяча:");
        System.out.println(box.radius());
        System.out.println("Вывод метода возвращающего объем ящика:");//важно заранее указать высоту ящика, а то получим 0
        System.out.println(box.volume());

        Box bigBox = new Box();//создадим новый ящик
        System.out.println("Вывод объем нового ящика bigBox:");
        System.out.println(bigBox.volume());//27.0 т.к. в переменную height по умолчанию подставилось 3. Это то что прописанов Конструкторе дефолтном

        Box someBox= new Box(5);//создаем новый ящик
        System.out.println("Вывод объем нового ящика someBox:");
        System.out.println(someBox.volume());//125.0 т.к. в переменную height передалось 5. Это то что прописанов Конструкторе втором

    }
}
