package abstracts.example2;

public abstract class Shape2 {

    private String color;

    public Shape2(String color) {
        this.color = color;
    }

    /*если метод в абстрактном классе не определяется, то ставим слово abstract
     */
    public abstract String getName2();
    public abstract double getSquare2();

    /*getColor() общий и мы его определяем*/
    public String getColor2(){
        return color;
    }
}
