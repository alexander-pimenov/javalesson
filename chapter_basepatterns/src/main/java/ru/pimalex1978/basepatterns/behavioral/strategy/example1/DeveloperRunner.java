package ru.pimalex1978.basepatterns.behavioral.strategy.example1;

/**
 * Класс Клиент.
 * Клиент должен создать объект конкретной стратегии и передать
 * его в конструктор контекста. Кроме этого, клиент должен
 * иметь возможность заменить стратегию на лету, используя сеттер.
 * Благодаря этому, контекст не будет знать о том, какая именно
 * стратегия сейчас выбрана.
 */
public class DeveloperRunner {
    public static void main(String[] args) {
        Developer developer = new Developer();

        /*Устанавливаем активность (стратегию)*/
        developer.setActivity(new Sleeping());
        /*Выполняем стратегию.*/
        developer.executeActivity();

        developer.setActivity(new Training());
        developer.executeActivity();

        developer.setActivity(new Coding());
        developer.executeActivity();

        developer.setActivity(new Reading());
        developer.executeActivity();

        developer.setActivity(new Sleeping());
        developer.executeActivity();
    }
}