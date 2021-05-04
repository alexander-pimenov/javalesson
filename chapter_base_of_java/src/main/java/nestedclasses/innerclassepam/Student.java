package nestedclasses.innerclassepam;

public class Student {
    private int studentId;
    private String name;
    private int group;
    private String faculty;
    private Address address;

    /**
     * Внутренний класс, характеризует адрес студента.
     * Внутренние НЕ СТАТИЧЕСКИЕ классы обладают свойствами:
     * Внешний класс не видит поля своего внутреннего
     * класса напрямую совсем! Единственный способ добраться до
     * этих полей, это через объект этого внутреннего класса
     * и его методы. В нашем случае это Address address.
     * Внутренний же класс имеет прямой доступ к любому полю
     * класса владельца.
     * Внутренние НЕ СТАТИЧЕСКИЕ классы не могут объявлять статические поля,
     * т.к. НЕ СТАТИЧЕСКИЙ ВНУТРЕННИЙ класс предназначен полностью
     * для не статического использования. Это касается и методов.
     */
    class Address {
        private String city;
        private String street;
        private int houseId;
        private int flatId;
        private String email;
        private String skype;
        private long phoneNumber;

        public Address() {
        }

        public void action() {
            group = 101;
        }
    }

    public Student() {
    }

    public void operation() {
        address.city = "Kiev";
    }

    public Address getAddress() {
        return address;
    }
}
