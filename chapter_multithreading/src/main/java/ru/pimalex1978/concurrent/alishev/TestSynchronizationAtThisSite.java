package ru.pimalex1978.concurrent.alishev;

/**
 * Монитор, как сущность объекта.
 * Используя слово synchronized в методе, мы указывает что синхронизируемся
 * на объекте this. Т.е. создали объект, вызвали метод этого объекта, а
 * на этом методе стоит ключевое слово synchronized, это и обозначает
 * что мы синхронизируемся на нашем созданном объекте, вернее на его мониторе.
 * Но можно также ключевому слову synchronized явно указывать объект на
 * котором хотим синхронизироваться. Синхронизируясь на отдельном объекте
 * можно увеличить производительность.
 * <p>
 * Ключевым словом synchronise мы убираем условие гонки (rise condition).
 */
public class TestSynchronizationAtThisSite {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        TestSynchronizationAtThisSite test = new TestSynchronizationAtThisSite();
        test.doWork();
    }

    /**
     * Синхронизированный метод, инкрементирующий переменную counter.
     * Синхронизируется на объекте this, т.е. на созданном объекте test.
     */
    public synchronized void increment1() {
        counter = counter + 1;
    }

    /**
     * Метод с синхронизированным блоком внутри него.
     * Синтаксис метода inrement1 полностью эквивалентен
     * методу inrement2.
     */
    public void increment2() {
        //в параметре указываем на каком объекте хотим синхронизировать
        //в данном случае синхронизирумся на объекте this, т.е. на объекте
        //test
        synchronized (this) {
            counter = counter + 1;
        }
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) increment1();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) increment1();
            }
        });
        thread1.start(); //старт 1-го потока
        thread2.start(); //старт 2-го потока

        /*.join() - метод который делает так, чтобы мы ждали пока
         * thread1 И thread2 отработает, т.е. верний метод main будет их ждать */
        thread1.join();
        thread2.join();
        System.out.println(counter);
    }
}
