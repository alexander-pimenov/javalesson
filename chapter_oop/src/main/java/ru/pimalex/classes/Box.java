package ru.pimalex.classes;

/*Данные Классов могут содержать Экземпляры Классов
 * здесь в коробке находится Мяч*/
public class Box {
    double height;

    //Здесь видно, что внутри Класса Box мы поместили Экземпляр другого Класса Ball
    Ball ball;

    /*Конструктор без параметров*/
    Box() {
        /*здесь указываем, что при создании ящика, размер его по умолчанию будет равняться 3*/
        height = 3;
    }

    /*Конструктор с параметрами в которые мы можем передать данные*/
    Box(double h) {
        height = h; //передаем в Конструктор параметр h, который выставляет значение height, т.е. высоту нашего ящика
    }


    /*метод возвращает нам радиус мяча лежащего в Коробке*/
    double radius() {

        return ball.radius;//возвращаем радиус мяча
    }

    /*метод который возвращает объем коробки/ящика*/
    double volume() {

        return height * height * height;//предположим, что ящик кубический и его объем равен произведению высоты-ширины-длины, а они одинаковы
    }


}

