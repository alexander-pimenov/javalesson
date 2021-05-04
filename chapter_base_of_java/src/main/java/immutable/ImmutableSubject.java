package immutable;

import java.util.Objects;

/**
 * Создадим свой неизменяемый класс.
 * Неизменяемые объекты – классы, объекты которых невозможно изменить.
 * Если объект однажды создан, то никаким изменениям внутреннего
 * состояния он подвергнуться больше не может.
 * Однажды проинициализированный объект изменить нельзя.
 * Внутреннее состояние этого объекта изменить нельзя.
 * Манипуляции с изменениями приводят к созданию нового объекта,
 * т.е. любое изменение – это создание нового.
 *
 * У таких классов должен быть конструктор, с помощью которого мы
 * проинициализируем поля объекта один раз, и не должно быть сеттеров
 * и других методов изменяющих первоначальное состояние объекта, т.е.
 * не должно быть возможности изменить эти поля повторно.
 */
public class ImmutableSubject {
    private int id;
    private String name;

    /**
     * Конструктор для инициализации полей класса.
     * @param id id
     * @param name name
     */
    public ImmutableSubject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableSubject that = (ImmutableSubject) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
