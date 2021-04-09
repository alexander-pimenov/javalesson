package generics.example2;

public class Camera extends Product<Camera> {
    int pixel;

    @Override
    boolean subCompare(Camera p) {
        //Проверка что передали сюда Camera отпадает
//        if (p instanceof Camera){
//        }
        return pixel == p.pixel;
    }
}
