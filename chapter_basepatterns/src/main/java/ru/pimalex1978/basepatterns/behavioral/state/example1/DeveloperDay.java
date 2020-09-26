package ru.pimalex1978.basepatterns.behavioral.state.example1;

/**
 * Это клиентский класс.
 */
public class DeveloperDay {
    public static void main(String[] args) {
        //Создаем активность.
        Activity activity = new Sleeping();
        //Создаем разработчика.
        Developer developer = new Developer();

        //Установим первую активность разработчику - спать.
        developer.setActivity(activity);

        //В цикле будем перебирать активности разработчика
        //с помощью метода .changeActivity()
        for (int i = 0; i < 10; i++) {
            developer.justDoIt();
            developer.changeActivity();
        }
    }
}
