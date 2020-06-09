package ru.pimalex1978.dom.example3;

//Сотрудник

public class Member extends Human {

    //Дожность
    private String position;

    public Member(String name, String position) {
        super(name);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Сотрудник обслуживающего персонала "
                + getName()
                + ", должность: "
                + position;
    }
}
