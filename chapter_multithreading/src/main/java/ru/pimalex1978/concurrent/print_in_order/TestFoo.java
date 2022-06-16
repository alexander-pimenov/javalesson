package ru.pimalex1978.concurrent.print_in_order;

import static ru.pimalex1978.concurrent.alishev.ColorScheme.BLUE;
import static ru.pimalex1978.concurrent.alishev.ColorScheme.CYAN;
import static ru.pimalex1978.concurrent.alishev.ColorScheme.PURPLE;
import static ru.pimalex1978.concurrent.alishev.ColorScheme.RED;

/* Асинхронно запускаются три потока. Ввод [1, 2, 3] означает, что поток А вызывает first(),
 * поток B вызывает second(), C - third().
 * Должно правильно вывестись "firstsecondthird"*/
public class TestFoo {
    public static void main(String[] args) {
//        new Thread(new SimpleRunner()).start();

        new Thread(() -> {
            try {
                FooFirstSolution foo = new FooFirstSolution();
                foo.first(() -> System.out.println(CYAN + "Hello from first Runnable!"));
                foo.second(() -> System.out.println(BLUE + "Hello from second Runnable!"));
                foo.third(() -> System.out.println(RED + "Hello from third Runnable!"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

class SimpleRunner implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            System.out.println(RED + "WARN - Runnable - " + Thread.currentThread().getName() + " was interrupted!");
        }
        System.out.println(PURPLE + "first " + Thread.currentThread().getName());
    }
}
