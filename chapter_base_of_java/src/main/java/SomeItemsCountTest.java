import java.util.concurrent.atomic.AtomicInteger;

/**
 * В примере написано как создать в конструкторе объекта счётчик, который будет вписывать
 * в поле объекта номер его создания? То есть первый объект имеет номер 1, следующий-- 2...
 */
public class SomeItemsCountTest {

    public static void main(String[] args) {
        //-------------------------------------------
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();

        System.out.println(person3.getNumberCreation()); //3
        System.out.println(person2.getNumberCreation()); //2
        System.out.println(person1.getNumberCreation()); //1

        //-------------------------------------------
        System.out.println(new User().getId()); //1
        System.out.println(new User().getId()); //2
        System.out.println(new User().getId()); //3
    }
}

/**
 * Класс со счетчиком созданных объектов.
 */
class Person {
    private static int grow;
    private final int numberCreation;

    public Person() {
        grow++;
        this.numberCreation = getGrow();
    }

    private int getGrow() {
        return grow;
    }

    public int getNumberCreation() {
        return numberCreation;
    }
}

/**
 * Класс со счетчиком созданных объектов.
 * Использование AtomicInteger гарантирует атомарность инкремента
 * и предотвращает различные ошибки при многопоточном использовании.
 */
class User {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private final int id;

    public User() {
        id = COUNTER.getAndIncrement();
    }

    public int getId() {
        return id;
    }
}



