package ru.pimalex1978.make_type_without_declaring_it;

import java.util.function.Supplier;

public class MethodReferenceAndLambdaTest {
    public static void main(String[] args) {
        MethodReferenceAndLambda test = new MethodReferenceAndLambda();
        test.run();
    }
}

/*Объяснение:
Здесь в s1 берется референс на метод конкретного объекта и
будет вызываться метод этого объекта, т.е. на момент записи s1
объект был str="ПриВет".
И если сделать запись
Supplier<String> s1 = str::toUpperCase; до
момента инициализации str="ПриВет", тогда получим NPE !!! (передвинь и проверь)
А лямбда захватывает ту переменную, которая есть сейчас на
момент вызова s2.get() и s3.get() !!!*/
class MethodReferenceAndLambda {
    String str;

    void run() {
        //Лямбда захватит ту переменную, которая будет на момент вызова get()
        //А здесь мы её просто объявили!!!
        Supplier<String> s2 = () -> str.toUpperCase();
        Supplier<String> s3 = () -> str.toLowerCase();

        //Референс на метод должен идти после инициализации объекта
        //и он сразу вызовет метод этого объекта, а результат сможем вызвать позже!!!
        str = "ПриВет";
        Supplier<String> s1 = str::toUpperCase;

        str = "HellO";

        System.out.println(s1.get()); //ПРИВЕТ
        System.out.println(s2.get()); //HELLO
        System.out.println(s3.get()); //hello
    }
}
