package ru.pimalex1978.concurrent.forkjoin;

import org.junit.Before;
import org.junit.Test;
import ru.pimalex1978.concurrent.forkjoin.util.PoolUtil;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.*;

public class Java8ForkJoinIntegrationTest {
    private String workLoad = "";
    private int[] arr;
    private CustomRecursiveTask customRecursiveTask;

    @Before
    public void init() {
        Random random = new Random();

        arr = new int[50];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(35);
        }
        customRecursiveTask = new CustomRecursiveTask(arr);

        String[] strings = generateRandomWords(1);
        workLoad = strings[0];
    }

    //генерируем массив рандомных наборов символов, как бы слов,
    //разной длины от 3 до 10 символов.
    public String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            // words of length 3 through 10. (1 and 2 letter words are boring.)
            char[] word = new char[random.nextInt(8) + 3];
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    @Test
    public void callPoolUtil_whenExistsAndExpectedType_thenCorrect() {
        ForkJoinPool forkJoinPool = PoolUtil.forkJoinPool;
        ForkJoinPool forkJoinPoolTwo = PoolUtil.forkJoinPool;

        assertNotNull(forkJoinPool);
        assertEquals(2, forkJoinPool.getParallelism());
        assertEquals(forkJoinPool, forkJoinPoolTwo);
    }

    @Test
    public void callCommonPool_whenExistsAndExpectedType_thenCorrect() {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        ForkJoinPool commonPoolTwo = ForkJoinPool.commonPool();

        assertNotNull(commonPool);
        assertEquals(commonPool, commonPoolTwo);
    }

    @Test
    public void executeRecursiveAction_whenExecuted_thenCorrect() {

        CustomRecursiveAction myRecursiveAction = new CustomRecursiveAction("ddddffffgggghhhh");
        ForkJoinPool.commonPool().invoke(myRecursiveAction);

        System.out.println("Result: " + myRecursiveAction.toString());
        assertTrue(myRecursiveAction.isDone());
    }

    @Test
    public void executeRecursiveAction_withSmallTask_whenExecuted_thenCorrect() {

        CustomRecursiveAction myRecursiveAction = new CustomRecursiveAction("ghh");
        ForkJoinPool.commonPool().invoke(myRecursiveAction);

        System.out.println("Result: " + myRecursiveAction.toString());
        assertTrue(myRecursiveAction.isDone());
    }

    @Test
    public void executeRecursiveAction_withRandomTask_whenExecuted_thenCorrect() {

        CustomRecursiveAction myRecursiveAction = new CustomRecursiveAction(workLoad);
        ForkJoinPool.commonPool().invoke(myRecursiveAction);
        System.out.println("Task: " + workLoad);
        System.out.println("Result: " + myRecursiveAction.toString());
        assertTrue(myRecursiveAction.isDone());
    }

    @Test
    public void executeRecursiveTask_whenExecuted_thenCorrect() {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        forkJoinPool.execute(customRecursiveTask);
        int result = customRecursiveTask.join();
        assertTrue(customRecursiveTask.isDone());

        forkJoinPool.submit(customRecursiveTask);
        int resultTwo = customRecursiveTask.join();
        assertTrue(customRecursiveTask.isDone());
    }

    @Test
    public void executeRecursiveTaskWithFJ_whenExecuted_thenCorrect() {
        CustomRecursiveTask customRecursiveTaskFirst = new CustomRecursiveTask(arr);
        CustomRecursiveTask customRecursiveTaskSecond = new CustomRecursiveTask(arr);
        CustomRecursiveTask customRecursiveTaskLast = new CustomRecursiveTask(arr);

        System.out.println(customRecursiveTaskFirst);
        System.out.println(customRecursiveTaskSecond);
        System.out.println(customRecursiveTaskLast);

        customRecursiveTaskFirst.fork();
        customRecursiveTaskSecond.fork();
        customRecursiveTaskLast.fork();
        int result = 0;
        result += customRecursiveTaskLast.join();
        result += customRecursiveTaskSecond.join();
        result += customRecursiveTaskFirst.join();

        System.out.println(result);

        assertTrue(customRecursiveTaskFirst.isDone());
        assertTrue(customRecursiveTaskSecond.isDone());
        assertTrue(customRecursiveTaskLast.isDone());
        assertTrue(result != 0);
    }


}