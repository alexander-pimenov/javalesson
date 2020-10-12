package ru.pimalex1978.basepatterns.creational.builder;

/**
 * Реализация абстрактного класса WebsiteBuilder.
 * Для создания сайтов-блогов.
 */
public class BlogWebsiteBuilder extends WebsiteBuilder {
    @Override
    void buildName() {
        website.setName("Blog web site");
    }

    @Override
    void buildCms() {
        website.setCms(Cms.MAGENTO);
    }

    @Override
    void buildPrice() {
        website.setPrice(400);
    }
}
