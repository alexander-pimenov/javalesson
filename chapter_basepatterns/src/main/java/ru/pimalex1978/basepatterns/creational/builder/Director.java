package ru.pimalex1978.basepatterns.creational.builder;

/**
 * Класс для уменьшения работы в конечном классе клиенте.
 * Он будет определять какой же именно сайт мы будем создавать.
 * Здесь можно использовать любую реализацию абстрактного
 * класса WebsiteBuilder.
 */
public class Director {
    WebsiteBuilder builder;

    /**
     * Сеттер устанавливающий нужный нам WebsiteBuilder.
     * @param builder объект класса WebsiteBuilder.
     */
    public void setBuilder(WebsiteBuilder builder) {
        this.builder = builder;
    }

    /**
     * Метод возвращающий определенный веб-сайт.
     * В нем поочередно вызываются методы для создания сайта
     * и установки в нем нужной информации.
     *
     * @return website
     */
    Website buildWebsite() {
        builder.createWebsite();
        builder.buildName();
        builder.buildCms();
        builder.buildPrice();

        Website website = builder.getWebsite();

        return website;
    }
}
