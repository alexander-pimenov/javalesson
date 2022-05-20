package deepcopy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/*Чтобы сравнить различные методы копирования объектов Java, нам понадобятся два класса для работы: Address, User
 * https://javascopes.com/java-deep-copy-0f169bfc/
 *
 * Неглубокая копия - это та, в которой мы копируем только значения полей из одного объекта в другой
 * В данном случае pm, что означает, что это разные объекты; однако проблема в том, что при
 * изменении любого из свойств исходного адреса это также повлияет на адрес ShallowCopy */
public class ShallowCopyUnitTest {

    /*при поверхностном копировании объекты не должны быть одинаковыми*/
    @Test
    public void whenShallowCopying_thenObjectsShouldNotBeSame() {

        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);

        //делаем не глубокую копию
        User shallowCopy = new User(pm.getFirstName(), pm.getLastName(), pm.getAddress());

        //видим что объекты разные
        assertThat(shallowCopy)
                .isNotSameAs(pm);

        System.out.println(pm.hashCode());
        System.out.println(shallowCopy.hashCode());
    }

    /*при изменении исходного объекта копия должна измениться*/
    @Test
    public void whenModifyingOriginalObject_thenCopyShouldChange() {
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);

        //делаем не глубокую копию
        User shallowCopy = new User(pm.getFirstName(), pm.getLastName(), pm.getAddress());

        //изменяем исходный объект
        address.setCountry("Great Britain");

        //видим что изменился и скопированный объект !!!
        assertThat(shallowCopy.getAddress().getCountry())
                .isEqualTo(pm.getAddress().getCountry());

        System.out.println(address.getCountry());
        System.out.println(shallowCopy.getAddress().getCountry());
    }


}