package ru.pimalex1978.basepatterns.creational.builder;

/**
 * Задача этого абстрактного класса -
 * создавать (конструировать) какие то сайты,
 * которые нам необходимы.
 */
public abstract class WebsiteBuilder {
    Website website;

    /**
     * Метод создающий сущность сайта.
     */
    void createWebsite() {
        website = new Website();
    }

    abstract void buildName();

    abstract void buildCms();

    abstract void buildPrice();

    /**
     * Метод возвращающий сайт.
     *
     * @return website
     */
    Website getWebsite() {
        return website;
    }
}
