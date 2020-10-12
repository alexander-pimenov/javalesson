package ru.pimalex1978.basepatterns.creational.builder;

/**
 * Класс-клиент.
 */
public class BuildWebsiteRunner {
    public static void main(String[] args) {
        Director director = new Director();

        /*Устанавливаем нужную реализацию билдера.*/
        director.setBuilder(new EnterpriseWebsiteBuilder());
//        director.setBuilder(new VisitCardWebsiteBuilder());
//        director.setBuilder(new BlogWebsiteBuilder());

        /*Создаем экземпляр веб-сайта.*/
        Website website = director.buildWebsite();

        /*Выводим информацию о веб-сайте.*/
        System.out.println(website);


    }
}
