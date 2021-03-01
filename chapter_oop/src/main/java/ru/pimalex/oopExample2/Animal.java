package ru.pimalex.oopExample2;

public abstract class Animal {
    /*переменную name напишем здесь, но т.к. Cat & Dog наследники
    * Animal, то она будет и уних */
    private String name;

    /*публичный метод для установки имени*/
    public void setName(String name) {
        this.name = name;
    }

    /*публичный метод для чтения переменной name*/
    public String getName() {
        return name;
    }

    /*метод voice говорит, что животное имеет голос, но он будет укаждого свой*/
    public abstract String voice ();

    /*метод выдающий звуки сна*/
    public void sleep(){
        System.out.println("Zzz-zz-z...");
    }
}
