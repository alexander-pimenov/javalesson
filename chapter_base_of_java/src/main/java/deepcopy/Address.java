package deepcopy;

import java.io.Serializable;

/*Чтобы сравнить различные методы копирования объектов Java, нам понадобятся два класса для работы: Address, User
 * https://javascopes.com/java-deep-copy-0f169bfc/*/
/* Глубокая копия-это альтернатива, которая решает эту проблему.
 * Его преимущество заключается в том, что каждый изменяемый объект
 * в графе объектов рекурсивно копируется.
 * Поскольку копия не зависит от какого-либо изменяемого объекта,
 * созданного ранее, она не будет изменена случайно, как мы видели с неглубокой копией.
 * Первая реализация глубокого копирования - это конструктор копирования.
 * Вторая реализация основана на методе клонирования clone(), унаследованном от Объекта.
 * Он защищен, но нам нужно переопределить его как общедоступный.
 * Мы также добавим интерфейс маркера, Клонируемый Cloneable, к классам, чтобы
 * указать, что классы действительно клонируются.
 *
 * Примеры работы с созданием копии смотри в тестах.
 * */
public class Address implements Serializable, Cloneable {

    @Override
    public Object clone() {
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Address(this.street, this.getCity(), this.getCountry());
        }
    }

    private static final long serialVersionUID = 1740913841244949416L;
    private String street;
    private String city;

    private String country;

    /* Конструктор копирования.
     * В приведенной выше реализации глубокой копии мы не создали
     * новые Строки в нашем конструкторе копирования, потому что
     * Строка является неизменяемым классом.*/
    public Address(Address that) {
        this(that.getStreet(), that.getCity(), that.getCountry());
    }


    public Address(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}