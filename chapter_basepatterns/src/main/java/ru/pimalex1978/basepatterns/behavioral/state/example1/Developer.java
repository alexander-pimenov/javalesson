package ru.pimalex1978.basepatterns.behavioral.state.example1;

public class Developer {
    /**
     * Ссылка на интерфейс (на состояние).
     */
    private Activity activity;

    /**
     * Установить состояние.
     *
     * @param activity объект интерфейса.
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * В цепочке условий устанавливаем разные активности:
     * после сна тренеруемся, после тренеровки пишем код,
     * после написания кода читаем, после чтения идем спать.
     */
    public void changeActivity() {
        if (activity instanceof Sleeping) {
            setActivity(new Training());
        } else if (activity instanceof Training) {
            setActivity(new Coding());
        } else if (activity instanceof Coding) {
            setActivity(new Reading());
        } else if (activity instanceof Reading) {
            setActivity(new Sleeping());
        }
    }

    /**
     * Выполнить активность.
     */
    public void justDoIt() {
        activity.justDoIt();
    }
}
