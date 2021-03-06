package ru.pimalex1978.basepatterns.creational.builder;

/**
 * Реализация абстрактного класса WebsiteBuilder.
 * Для создания корпоративных сайтов.
 */
public class EnterpriseWebsiteBuilder extends WebsiteBuilder {
    @Override
    void buildName() {
        website.setName("Enterprise web site");
    }

    @Override
    void buildCms() {
        website.setCms(Cms.ALIFRESCO);
    }

    @Override
    void buildPrice() {
        website.setPrice(10000);
    }
}
