package ru.pimalex1978.example_like_runnable;

/*
 * Лямбда выражения можем использовать там где нужно использовать анонимный класс с одним методом.
 * !!!!! Обязательно должен быть метод из интерфейса , как здесь, например execute()
 *          и мы его должны использовать!!!!
 *        Мы его должны вызвать, а его реализацию можно находу написать в main потоке!!!
 *
 * Если лямбда возвращает что то, то это мы можем использовать.
 *
 * В качестве примера, как записывать и менять лямбду, можно вспоминать list.sort(Comparator),
 * ведь в зависимости от реализации метода, мы по разному сможем сортировать строки в списке.
 *
 * */
public class TestLambda1 {
    public static void main(String[] args) {

        Runner1 runner = new Runner1();

        //1
        runner.run(new ExecutableImpl1());

        //2
        runner.run(new Executable1() {
            @Override
            public void execute() {
                System.out.println("Hello from anonimous class");
                System.out.println("Goodbye from anonimous class");
            }
        });

        //3
        runner.run(() -> {
            System.out.println("Hello from lambda");
            System.out.println("Goodbye from lambda");
        });
    }

}

/*Этот интерфейс, как аналог Runnable*/
interface Executable1 {
    void execute();
}

/*Этот класс как аналог Thread*/
class Runner1 {
    public void run(Executable1 executable) {
        //!!!!! обязательно должен быть метод из интерфейса , как здесь, например execute()!!!!
        //Мы его должны вызвать, а его реализацию можно находу написать в main потоке!!!
        executable.execute();

    }
}

/*Реализуем отдельно интерфейс Executable*/
class ExecutableImpl1 implements Executable1 {
    @Override
    public void execute() {
        System.out.println("Hello from ExecutableImpl");
        System.out.println("Goodbye from ExecutableImpl");
    }
}
