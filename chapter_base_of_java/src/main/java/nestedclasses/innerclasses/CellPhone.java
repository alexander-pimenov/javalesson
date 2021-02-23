package nestedclasses.innerclasses;

public class CellPhone {

    private String make;
    private String model;
    private Display display;
    private RadioModule gsm; //радиомодуль с помощью которого мы должны звонить
    private AbstractPhoneButton button;

    //Внутри классов можно объявлять не только внутренние классы, но интерфейсы
    //абстрактная кнопка, у которой будет метод click
    public interface AbstractPhoneButton {
        void click();
    }

    public CellPhone(String make, String model) {
        this.make = make;
        this.model = model;
    }

    //Инициализировать Display можно прямо в конструкторе,но
    //мы сделаем по другому, в методах:
    //метод - включить, в котором вызывается метод в котором инициализируется Дисплей
    public void turnOn() {
        initDisplay();
        //запускаем RadioModule
        gsm = new RadioModule();
        initButton();//инициализация конпки Button
    }

    //метод для инициализации кнопки Button, используется интерфейс, но т.к. из
    //интерфейса нельзя создать объект, то добавим {} и получим класс без имени,
    //т.е. АНОНИМНЫЙ класс.
    //Такие классы часто используются. Они очень важны для Лямбда выражений
    //Если бы не было АНОНИМНЫХ классов, то не было бы Лямбда выражений!!!
    //и здесь мы тоже можем заменить анонимный класс на лямбда выражения
    public void initButton(){
        button = new AbstractPhoneButton() { //имплементируем интерфейс
            @Override
            public void click() {
                System.out.println("Button clicked");
            }
        };

    }

    //создадим метод call, в котором обращаемся к RadioModule для совершения звонка
    public void call(String number) {
        //нажимаем кнопку
        button.click();
        //совершается звонок
        gsm.call(number);
    }

    //Здесь и создаем инстанс нашего Display
    private void initDisplay() {
        display = new Display();

    }


    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Display getDisplay() {
        return display;
    }
}
