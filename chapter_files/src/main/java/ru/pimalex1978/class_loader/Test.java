package ru.pimalex1978.class_loader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Создаем тест Test.java, который будет загружать класс TestModule
 * <p>
 * В каждой итерации бесконечного цикла создается экземпляр нашего загрузчика loader,
 * с его помощью загружается класс TestModule, создается его экземпляр и
 * распечатывается, при этом, как обычно, неявно используется метод toString(),
 * реализованный в TestModule. Затем ожидается нажатие на клавиатуре клавиши ENTER
 * (либо Ctrl-C для выхода из программы).
 * <p>
 * Пока наш тест ждет нажатия ENTER, перейдем в другое окно (ОС Windows) или консоль
 * (ОС Unix), чуть-чуть исправим класс TestModule: изменим результат toString()
 * на «TestModule, version 2!» и перекомпилируем его. После чего вернемся в наш
 * тест и нажмем ENTER.
 * <p>
 * Сработало! В следующей итерации цикла мы видим результат работы свежей версии
 * класса TestModule.class – будет напечатана новая строка «TestModule, version 2!».
 * <p>
 * Мы добились успеха, не выходя из программы, модифицировали class-файл,
 * и новая версия класса была успешно загружена.
 */

public class Test {
    public static void main(String[] args) throws Exception {
        ClassLoader loader;
        for (; ; ) {
            loader = new DynamicClassOverloader(
                    new String[]{"."}); //C:\projects\lessons-job4j\chapter_tutorial\src\main\java\ru\job4j\tutorial\class_loader
            // текущий каталог "." будет единственным
            // каталогом поиска
            //Если так укажем путь, то почему то файл TestModule не находится - Class.forName("TestModule", true, loader)
            //Поэтому укзываю полное название файла TestModule: "ru.job4j.tutorial.class_loader.TestModule"
            Class clazz = Class.forName("ru.job4j.tutorial.class_loader.TestModule", true, loader);
            /*
            В тесте мы получили вызовом newInstance() переменную типа Object.
            Но если мы знаем, что ее тип – TestModule, мы можем спокойно привести
            ее к этому типу и работать дальше обычным образом:
            TestModule testModule = (TestModule)clazz.newInstance();
            работаем с полями testModule, вызываем методы и т.д.

            Если бы здесь был обычный вызов Class.forName(«TestModule»), все было бы
            нормально. Это был бы простейший вариант классической схемы построения
            расширяемых систем. В качестве аргумента forName мог бы выступать любой
            наследник TestModule (или класс, реализующий интерфейс TestModule),
            реализация которого неизвестна и не обязательно доступна в момент компиляции
            системы.

            Но с нашим необычным загрузчиком классов нас ждет неприятная неожиданность.
            При попытке приведения типа будет возбуждено исключение ClassCastException
            – ошибка приведения типа!
            */
            Object object = clazz.newInstance();
            System.out.println(object);
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
    }
}
