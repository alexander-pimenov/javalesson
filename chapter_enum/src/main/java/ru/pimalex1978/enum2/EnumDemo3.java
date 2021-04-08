package ru.pimalex1978.enum2;

//использовать конструктор, переменную экземпляра и метод в перечислении
enum Apple3 {
    Jonatan(10){
        @Override
        public String toString() {
            return "Это название Jonatan";
        }
    }, GoldenDel(9), RedDel, Winesap(15), Cortland(8);

    private int price;//ПОЛЕ - цена яблока каждого сорта

    //конструктор
    Apple3(int price) {
        this.price = price;
    }

    //перегружаемый конструктор
    //инициализирует цену значением -1, которое означает, что цена не указана
    Apple3() {
        price = -1;
    }

    //геттер возвращающий цену яблока
    int getPrice() {
        return price;
    }
}

public class EnumDemo3 {
    public static void main(String[] args) {

        //Когда объявляется переменная ap, конструктор  Apple3()
        // вызывается один раз
        //для каждой объявленной константы
        Apple3 ap;

        //вывести цену на яблоко сорта Winesap
        System.out.println("Яблоко сорта Winesap стоит " +
                Apple3.Winesap.getPrice() + " центов.\n");

        //вывести цены на все яблоки
        System.out.println("Цены на все сорта яблок:");
        for (Apple3 a : Apple3.values()) {
            if (a.getPrice() == -1) {
                System.out.println(a + " цена на яблоко не указана.");
            }
            System.out.println(a + " стоит " + a.getPrice() + " центов.");
        }

        //найти значение константы (или переопределенный метод toString())
        final Apple3 jonatan = Apple3.valueOf("Jonatan");
        System.out.println(jonatan);
        final Apple3 red = Apple3.valueOf("RedDel");
        System.out.println(red);
    }
}
