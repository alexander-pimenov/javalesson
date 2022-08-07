package ru.pimalex1978.examplelikerunnable;

/*
 * Лямбда выражения можем использовать там где нужно использовать анонимный класс с одним методом.
 * !!!!! Обязательно должен быть метод из интерфейса, как здесь, например execute()
 *          и мы его должны использовать!!!!
 * Мы его должны вызвать, а его реализацию можно находу написать в main потоке!!!
 *
 * Если лямбда возвращает что-то, то это мы можем использовать.
 *
 * В качестве примера, как записывать и менять лямбду, можно вспоминать list.sort(Comparator),
 * ведь в зависимости от реализации метода, мы по-разному сможем сортировать строки в списке.
 *
 * */
public class TestLambda2 {
    public static void main(String[] args) {

        Runner2 runner = new Runner2();


        //2
        runner.run(new Executable2() {
            @Override
            public int execute() {
                System.out.println("Hello from anonimous class");
                System.out.println("Goodbye from anonimous class");
                return 42;
            }
        });

        //3
        runner.run(() -> {
            System.out.println("Hello from lambda");
            System.out.println("Goodbye from lambda");
            return 47;
        });
    }

}

/*Этот интерфейс, как аналог Runnable*/
interface Executable2 {
    int execute();
}

/*Этот класс как аналог Thread*/
class Runner2 {
    public void run(Executable2 executable) {
        //!!!!! обязательно должен быть метод из интерфейса, как здесь, например execute()!!!!
        //Мы его должны вызвать, а его реализацию можно находу написать в main потоке!!!
        int execute = executable.execute();
        System.out.println(execute);
    }
}
