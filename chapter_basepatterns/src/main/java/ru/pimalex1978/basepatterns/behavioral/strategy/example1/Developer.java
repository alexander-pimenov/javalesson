package ru.pimalex1978.basepatterns.behavioral.strategy.example1;

/**
 * Класс Контекст.
 * Контекст хранит ссылку на объект конкретной стратегии,
 * работая с ним через общий интерфейс стратегий.
 * Т.е. используем интерфейс для вызова конкретно стратегии.
 */
public class Developer {
    /**
     * Ссылка на интерфейс (на стратегию).
     */
    private Activity activity;

    /**
     * Установить активность (стратегию).
     * Т.е. это метод для установки стратегии.
     *
     * @param activity объект интерфейса.
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Выполнить активность (стратегию).
     */
    public void executeActivity() {
        /*Выполнение активности через интерфейс.*/
        activity.justDoIt();
    }
}
