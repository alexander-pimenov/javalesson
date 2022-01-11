package ru.pimalex1978.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*https://annimon.com/article/3462*/
public class CombiningTwoCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1. Комбинирование двух зависимых задач, с использованием thenCompose()
        // Правило таково: если функция-колбэк возвращает CompletableFuture, а вы хотите простой результат,
        // (а в большинстве случаев именно он вам и нужен), тогда используйте thenCompose().

        //2. Комбинирование двух независимых задач, с использованием thenCombine()
        // Если thenCompose() используется для объединения двух задач, когда одна зависит от другой,
        // то thenCombine() используется, когда вы хотите, чтобы две задачи работали независимо друг
        // от друга и по завершению обоих выполнялось какое-нибудь действие.
        System.out.println("Получение веса.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        System.out.println("Получение роста.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });

        // Колбэк, переданный методу thenCombine(), вызовется, когда обе задачи завершатся.
        System.out.println("Расчёт индекса массы тела.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm / 100;
                    return weightInKg / (heightInMeter * heightInMeter);
                });

        System.out.println("Ваш индекс массы тела - " + combinedFuture.get());

        //3. Объединение нескольких CompletableFuture
        //
        // Мы использовали thenCompose() и thenCombine(), чтобы объединить два CompletableFuture вместе.
        // Но что, если вы хотите объединить произвольное количество CompletableFuture? Можно воспользоваться следующими методами:
        //static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
        //static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
        //3.1. CompletableFuture.allOf()
        //
        // CompletableFuture.allOf() используется в тех случаях, когда есть список независимых задач,
        // которые вы хотите запустить параллельно, а после завершения всех задач выполнить какое-нибудь действие.

        //3.2. CompletableFuture.anyOf()
        //
        // CompletableFuture.anyOf(), как следует из названия, завершается сразу же, как только завершается
        // любой из заданных CompletableFuture. Конечным результатом будет результат этого первого завершившегося CompletableFuture.
        // В приведенном выше примере anyOfFuture завершается, когда завершается любой из трёх CompletableFuture.
        // Поскольку в future2 задержка меньше, он завершится первым, значит, конечным результатом будет:
        // Результат Future 2.
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Результат Future 1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Результат Future 2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Результат Future 3";
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);

        System.out.println(anyOfFuture.get()); // Результат Future 2

    }
}
