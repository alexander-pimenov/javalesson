package ru.pimalex1978.basepatterns.creational.builder;

/**
 * Реализация абстрактного класса WebsiteBuilder.
 * Для создания простых сайтов.
 */
public class VisitCardWebsiteBuilder extends WebsiteBuilder{
    @Override
    void buildName() {
        website.setName("Visit card");
    }

    @Override
    void buildCms() {
        website.setCms(Cms.WORDPRESS);
    }

    @Override
    void buildPrice() {
        website.setPrice(500);
    }
}
