package generics.example2;

public class Phone extends Product<Phone> {
    String model;

    @Override
    boolean subCompare(Phone p) {
        //Проверка что передали сюда Phone отпадает
//        if (p instanceof Phone){
//        }
        final int i = model.compareTo(p.model);
        return i == 0;
    }
}
