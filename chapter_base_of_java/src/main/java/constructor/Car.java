package constructor;

/**
 * Explanation
 * <p>
 * Переменная wheelCount класса Car объявлена с модификатором доступа private,
 * поэтому в классе-наследнике SuperCar к данной переменной нельзя получить
 * доступ, поэтому вариант 5 является верным. Также верным является 4 вариант,
 * так как в классе-родителе Car нет конструктора по умолчанию, поэтому нужно
 * явно вызвать через super другой конструктор с параметрами.
 */
public class Car {
    protected int wheelCount; //эта переменная была private
    private String model;

    public Car(String model) {
        this.model = model;
        this.wheelCount = 4;
    }

    public String getInfo() {
        return "Model: " + model + " wheels: " + wheelCount;
    }
}

class SuperCar extends Car {

    public SuperCar(String model) {
        super(model); //этого вызова не было
        this.wheelCount = 3;
    }
}
