package ru.pimalex1978.examplelikerunnable;

/*
 * Лямбда выражения можем использовать там где нужно использовать анонимный класс с одним методом.
 * !!!!! Обязательно должен быть метод из интерфейса , как здесь, например execute(), execStr()
 *          и мы его должны использовать!!!!
 *        Мы его должны вызвать, а его реализацию можно находу написать в main потоке!!!
 *
 * Если лямбда возвращает что то, то это мы можем использовать.
 * Также лямбда может иметь принимающие аргументы.
 *
 * Еще лямбды,  в отличие от анонимных классов, нет своей собственной области видимости (scope) !!!
 *
 * В качестве примера, как записывать и менять лямбду, можно вспоминать list.sort(Comparator),
 * ведь в зависимости от реализации метода, мы по разному сможем сортировать строки в списке.
 *
 * */
public class TestLambda3 {
    public static void main(String[] args) {

        Runner3 runner = new Runner3();

        int zzz = 55;
        int f = 5;

        //2
        runner.run(new Executable3() {
            @Override
            public int execute(int x) {

                int g = f + 5; //f нельзя изменять, как и в лямбде.

                //это локальная zzz
                int zzz = 100; //это уже не та zzz, которую видим вне этого метода !!!

                System.out.println("Hello from anonimous class");
                System.out.println("Goodbye from anonimous class");
                return x + 42 + zzz;
            }
        });

        //Любые переменные доступны для работы в лямбда-выражении.
        //переменую вне лямбды нужно делать final, либо она должна быть effectively final
        //т.е. после инициализации не должна изменяться.
        //Еще лямбды,  в отличие от анонимных классов, нет своей собственной области видимости (scope) !!!
        //У неё она та, где была создана.
        //Если говорим про ссылочные типы, то ссылка не должно менять своего target.
        final int i = 1;
        //3
        runner.run(x -> {
            System.out.println("Hello from lambda");
            System.out.println("Goodbye from lambda");
            return x * 47 + i + zzz; //здесь zzz это из main потока
        });

        //4
        runner.runStr(new ExecStr() {
            @Override
            public String execStr(String a, String b) {
                return a.toLowerCase() + " " + b.toLowerCase() + " !!!";
            }
        });

        //Любые переменные доступны для работы в лямбда-выражении.
        //переменую вне лямбды нужно делать final, либо она должна быть effectively final
        //т.е. после инициализации не должна изменяться.
        //Если говорим про ссылочные типы, то ссылка не должно менять своего target.
        final StringBuilder stringBuilder = new StringBuilder();

        //5
        runner.runStr((a, b) -> {
                    String result = a.toUpperCase() + " - " + b.toUpperCase() + " " + stringBuilder.append(" !!!");
                    String str = " WAO ";
                    return result + str;
                }
        );
    }
}

/*Этот интерфейс, как аналог Runnable*/
interface Executable3 {
    int execute(int x);
}

interface ExecStr {
    String execStr(String a, String b);
}

/*Этот класс как аналог Thread*/
class Runner3 {
    public void run(Executable3 executable) {
        //!!!!! обязательно должен быть метод из интерфейса , как здесь, например execute()!!!!
        //Мы его должны вызвать, а его реализацию можно находу написать в main потоке!!!
        int execute = executable.execute(10);
        System.out.println(execute);
    }

    public void runStr(ExecStr execStr) {
        //!!!!! обязательно должен быть метод из интерфейса , как здесь, например execStr()!!!!
        String result = execStr.execStr("Hi", "Alex");
        System.out.println(result);
    }
}
