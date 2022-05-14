package ru.pimalex1978;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * https://javadevblog.com/polnoe-rukovodstvo-po-java-reflection-api-refleksiya-na-primerah.html
 * Объект java.lang.Class является точкой входа для всех операций рефлексии. Для каждого типа объекта,
 * JVM создает неизменяемый экземпляр java.lang.Class который предоставляет методы для получения свойств
 * объекта, создания новых объектов, вызова методов.
 *
 * Разберем что тут сейчас произошло. В java есть замечательный класс Class. Он представляет классы и интерфейсы
 * в исполняемом приложении Java. Связь между Class и ClassLoader мы затрагивать не будем, т.к. это не есть тема статьи.
 * <p>
 * Далее, чтобы получить поля этого класса нужно вызвать метод getFields(), этот метод вернет нам все доступные поля
 * класса. Нам это не подходит, так как наше поле private, поэтому используем метод getDeclaredFields(), этот метод
 * также возвращает массив полей класса, но теперь и private и protected. В нашей ситуации мы знаем имя поля,
 * которое нас интересует, и можем использовать метод getDeclaredField(String), где String — имя нужного поля.
 * <p>
 * Примечание: getFields() и getDeclaredFields() не возвращают поля класса-родителя!!!
 * <p>
 * Отлично, мы получили объект Field с ссылкой на наш name. Т.к. поле не было публичным (public) следует дать
 * доступ для работы с ним. Метод setAccessible(true) разрешает нам дальнейшую работу. Теперь поле name
 * полностью под нашим контролем! Получить его значение можно вызовом get(Object) у объекта Field, где Object —
 * экземпляр нашего класса MyClass. Приводим к типу String и присваиваем нашей переменной name.
 * 
 */
public class MyClassRunner {
    @SneakyThrows
    public static void main(String[] args) {
        MyClass myClass1 = new MyClass(10, "Bob");
        int number = myClass1.getNumber();
        String name = null; //no getter =(
        System.out.println(number + name);//output 10null
        try {
            Field field = myClass1.getClass().getDeclaredField("name");
            field.setAccessible(true);
            name = (String) field.get(myClass1);
            field.set(myClass1, (String) "new value");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(number + name);//output 10default

        try {
            Method printData = myClass1.getClass().getDeclaredMethod("printData");
            printData.setAccessible(true);
            printData.invoke(myClass1);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("hashCode() объекта myClass1 : " + myClass1.hashCode());
        System.out.println("==========");

        /*
         * На момент старта java приложения далеко не все классы оказываются загруженными
         * в JVM. Если в вашем коде нет обращения к классу MyClass, то тот, кто отвечает
         * за загрузку классов в JVM, а им является ClassLoader, никогда его туда и
         * не загрузит. Поэтому нужно заставить ClassLoader загрузить его и получить
         * описание нашего класса в виде переменной типа Class.
         * Для этой задачи существует метод forName(String), где String — имя класса,
         * описание которого нам требуется: Class.forName(MyClass.class.getName())
         *
         * Получив Сlass, вызов метода newInstance() вернет Object, который будет создан
         * по тому самому описанию. Остается привести этот объект к нашему классу MyClass.
         *
         * Теперь мы умеем создавать экземпляр класса буквально из одной строки!
         * К сожалению описанный способ будет работать только с конструктором по
         * умолчанию (без параметров).
         */
        //Создадим экземпляр класса с помощью рефлексии:
        //В моей иерархии package полным именем MyClass будет “ru.pimalex1978.MyClass”.
        //Также узнать имя класса можно простым способом (вернет имя класса в виде строки)
        MyClass myClass2 = null;

        try {
            Class<?> clazz2 = Class.forName(MyClass.class.getName());
            myClass2 = (MyClass) clazz2.newInstance(); //при конструкторе без параметров
            //clazz.getDeclaredConstructor().newInstance()

            System.out.println("clazz2 = " + clazz2); //output class ru.pimalex1978.MyClass
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("hashCode() объекта myClass2 : " + myClass2.hashCode());

        System.out.println(myClass2); //output created object MyClass{number=0, name='default'}
        System.out.println("==========");

        /*
         * Как же вызывать методы с аргументами и конструкторы с параметрами?
         * Для получения конструкторов класса следует у описания класса вызвать
         * метод getConstructors(), а для получения параметров конструктора -
         * getParameterTypes()
         */
        MyClass myClass3 = null;
        try {
            Class<?> clazz3 = Class.forName(MyClass.class.getName());
            //Эти параметры прописали самостоятельно, чтоб потом добавить их
            //в getConstructor(params)
            Class<?>[] params = {int.class, String.class};
            myClass3 = (MyClass) clazz3.getConstructor(params).newInstance(1, "default2");

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("hashCode() объекта myClass3 : " + myClass3.hashCode());

        System.out.println(myClass3); //output created object MyClass{number=1, name='default2'}
        System.out.println("==========");

        MyClass myClass4 = null;
        try {
            Class<?> clazz4 = Class.forName((MyClass.class.getName()));
            Constructor<?>[] constructors = clazz4.getConstructors();
            for (Constructor<?> constructor : constructors) {
                Class<?>[] paramTypes = constructor.getParameterTypes();
                for (Class<?> paramType : paramTypes) {
                    System.out.println(" - " + paramType.getName());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("==========");
        //Представим, что нам нужно спомощью рефлексии задать другое отличное при создании значение полю number
        MyClass myClassNick = new MyClass(10, "Nick");
        System.out.println(myClassNick); //MyClass{number=10, name='Nick'}

        Class<?> nickClass = Class.forName("ru.pimalex1978.MyClass");
        Field fieldNumber = nickClass.getDeclaredField("number");
        fieldNumber.setAccessible(true);
        //хотим посмотреть что находится в поле number
        //если мы знаем что это int то можно кастовать
        int o = (int) fieldNumber.get(myClassNick);
//        Object o = fieldNumber.get(myClassNick);
        System.out.println(o); //10
        /*Параметр myClassNick, который передается в методы для получения и установки значения поля,
        должен быть экземпляром класса, которому принадлежит само поле.
        В приведенном примере используется экземпляр класса myClassNick, потому что поле
        fieldName является членом экземпляра этого класса.*/
        fieldNumber.set(myClassNick,25);
        System.out.println(myClassNick); //MyClass{number=25, name='Nick'}

        System.out.println("==========");



    }
}
