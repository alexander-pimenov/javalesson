package ru.pimalex1978.basepatterns.creational.builder;

/**
 * Класс отвечающий за сайт:
 * имеет имя name, систему управления контентом cms,
 * цену создания price.
 * <p>
 * Понадобятся только сеттеры.
 * И toString() для вывода информации.
 */
public class Website {
    private String name;
    private Cms cms;
    private int price;

    public void setName(String name) {
        this.name = name;
    }

    public void setCms(Cms cms) {
        this.cms = cms;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Website{" +
                "price=" + price +
                ", cms=" + cms +
                ", name='" + name + '\'' +
                '}';
    }
}
