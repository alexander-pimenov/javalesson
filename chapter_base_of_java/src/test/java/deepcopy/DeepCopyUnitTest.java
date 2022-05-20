package deepcopy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/* Глубокая копия-это альтернатива, которая решает эту проблему.
 * Его преимущество заключается в том, что каждый изменяемый объект
 * в графе объектов рекурсивно копируется.
 * Поскольку копия не зависит от какого-либо изменяемого объекта,
 * созданного ранее, она не будет изменена случайно, как мы видели с неглубокой копией.
 *
 * Приведенные выше примеры выглядят просто, но иногда они не работают
 * как решение, когда мы не можем добавить дополнительный конструктор
 * или переопределить метод клонирования.
 * Это может произойти, когда мы не владеем кодом или когда граф объектов
 * настолько сложен, что мы не закончили бы наш проект вовремя, если бы
 * сосредоточились на написании дополнительных конструкторов или реализации
 * метода clone для всех классов в графе объектов.
 *
 * Так что же мы тогда можем сделать? В этом случае мы можем использовать
 * внешнюю библиотеку. Чтобы добиться глубокого копирования, мы можем
 * сериализовать объект, а затем десериализовать его в новый объект.
 * (Gson, Jackson)
 *
 * Какую реализацию мы должны использовать при создании глубокой копии?
 * Окончательное решение часто будет зависеть от классов, которые мы будем
 * копировать, и от того, владеем ли мы классами в графе объектов.*/
public class DeepCopyUnitTest {

    @Test
    public void whenCreatingDeepCopyWithCopyConstructor_thenObjectsShouldNotBeSame() {

        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);

        //создаем глубокую копию с помощью конструктора копирования
        User deepCopy = new User(pm);

        assertThat(deepCopy).isNotSameAs(pm);
    }

    @Test
    public void whenModifyingOriginalObject_thenCopyShouldNotChange() {
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);

        //создаем глубокую копию с помощью конструктора копирования
        User deepCopy = new User(pm);

        address.setCountry("Great Britain");

        //видим, что изменение в родительском объекте не привели к изменению в
        // скопированном объекте, как это было в легком копировании
        assertThat(deepCopy.getAddress().getCountry())
                .isNotEqualTo(pm.getAddress().getCountry());

        System.out.println(pm.getAddress().getCountry()); //Great Britain
        System.out.println(deepCopy.getAddress().getCountry()); //England
    }

    @Test
    public void whenModifyingOriginalObject_thenCloneCopyShouldNotChange() {
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);

        //создаем глубокую копию с помощью метода clone()
        User deepCopy = (User) pm.clone();

        address.setCountry("Great Britain");

        assertThat(deepCopy.getAddress().getCountry())
                .isNotEqualTo(pm.getAddress().getCountry());

        System.out.println(pm.getAddress().getCountry()); //Great Britain
        System.out.println(deepCopy.getAddress().getCountry()); //England
    }

    @Test
    public void whenModifyingOriginalObject_thenCloneCopyShouldNotChange2() {
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);

        //создаем глубокую копию с помощью метода clone()
        User deepCopy = (User) pm.clone();
        Address deepCopyAddress = (Address) address.clone();

        address.setCountry("Great Britain");

        assertThat(deepCopy.getAddress().getCountry())
                .isNotEqualTo(pm.getAddress().getCountry());

        System.out.println(deepCopy.getAddress().getCountry()); //England
        System.out.println(address == deepCopyAddress); //false
        System.out.println(deepCopyAddress.getCountry()); //England
    }


    /* В Apache Commons Lang есть SerializationUtils#clone, который выполняет глубокое копирование,
     * когда все классы в графе объектов реализуют Сериализуемый интерфейс.
     * Если метод обнаруживает класс, который не сериализуем, он завершится ошибкой и выдаст
     * непроверенное Исключение сериализации.
     * Следовательно, нам нужно добавить Сериализуемый Serializable интерфейс в наши классы*/
    @Test
    public void whenModifyingOriginalObject_thenCommonsCloneShouldNotChange() {
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);
        User deepCopy = (User) SerializationUtils.clone(pm);

        address.setCountry("Great Britain");

        assertThat(deepCopy.getAddress().getCountry()).isNotEqualTo(pm.getAddress().getCountry());

        System.out.println(pm.getAddress().getCountry()); //Great Britain
        System.out.println(deepCopy.getAddress().getCountry()); //England
    }

    /* Другой способ сериализации-использовать сериализацию JSON. Gson-это библиотека,
     * которая используется для преобразования объектов в JSON и наоборот.
     * В отличие от языка Apache Commons Lang, GSON не нуждается в Сериализуемом интерфейсе
     * или Cloneable для выполнения преобразований.*/
    @Test
    public void whenModifyingOriginalObject_thenGsonCloneShouldNotChange() {
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);

        //создали объект Gson
        Gson gson = new Gson();

        //Сначала преобразовали в json
        String jsonUser = gson.toJson(pm);
        System.out.println(jsonUser);

        //Затем снова преобразовали в объект
        User deepCopy = gson.fromJson(jsonUser, User.class);

        address.setCountry("Great Britain");

        assertThat(deepCopy.getAddress().getCountry()).isNotEqualTo(pm.getAddress().getCountry());

        System.out.println(pm.getAddress().getCountry()); //Great Britain
        System.out.println(deepCopy.getAddress().getCountry()); //England
    }

    /* Джексон-это еще одна библиотека, поддерживающая сериализацию JSON.
     * Эта реализация будет очень похожа на реализацию с использованием Gson,
     * но нам нужно добавить конструктор по умолчанию в наши классы.*/
    @Test
    public void whenModifyingOriginalObject_thenJacksonCopyShouldNotChange() throws IOException {
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);

        //Создали объект ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        //Сначала преобразовали в json
        String jsonUser = objectMapper.writeValueAsString(pm);
        System.out.println(jsonUser);

        //Затем снова преобразовали в объект
        User deepCopy = objectMapper.readValue(jsonUser, User.class);

        address.setCountry("Great Britain");

        assertThat(deepCopy.getAddress().getCountry()).isNotEqualTo(pm.getAddress().getCountry());

        System.out.println(pm.getAddress().getCountry()); //Great Britain
        System.out.println(deepCopy.getAddress().getCountry()); //England
    }


}