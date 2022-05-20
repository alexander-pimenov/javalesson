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
public class User implements Serializable, Cloneable {

    private static final long serialVersionUID = -3427002229954777557L;
    private String firstName;
    private String lastName;
    private Address address;

    /*Для неглубокой копии подойдет конструктор с заданием полей*/
    public User(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    /*Для глубокой копии нужен конструктор с аргументом такого объекта,
     * и внутри которого уже будет вызван другой конструктор.
     * Конструктор копирования.
     * В приведенной выше реализации глубокой копии мы не создали
     * новые Строки в нашем конструкторе копирования, потому что
     * Строка является неизменяемым классом.*/
    public User(User that) {
        this(that.getFirstName(), that.getLastName(), new Address(that.getAddress()));
    }

    public User() {
    }

    /* Обратите внимание, что super.clone() вызов возвращает неглубокую копию объекта,
     * но мы вручную устанавливаем глубокие копии изменяемых полей,
     * поэтому результат правильный*/
    @Override
    public Object clone() {
        User user;
        try {
            user = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            user = new User(this.getFirstName(), this.getLastName(), this.getAddress());
        }
        user.address = (Address) this.address.clone();
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }
}