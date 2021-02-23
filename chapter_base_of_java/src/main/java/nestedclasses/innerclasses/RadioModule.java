package nestedclasses.innerclasses;

public class RadioModule {
    public RadioModule() {
        System.out.println("RadioModule initialized");
    }

    //создаем метод "звонить call" чтобы совершать звонки
    //в качестве параметра принимает номер телефона, стринговый правда не логично,
    // но для примера нужно, чтобы использовать поля и переменные
    //в нашем локальном классе
    public void call(String number) {
        int lengh = 10; //обозначает длину номера


        //создаем класс внутри метода.
        //Локальный класс, который обладает всеми стандартными атрибутами
        //локальные классы мало используются
        //НО он не может иметь public и private доступ, только default access

        class GSMModule {
            private String phoneNumber;
            private int validNumber; //проверка номера, можно ли его использовать для звонков

            public GSMModule(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public boolean isValid() {
                //проверка длины нашего номера, что он не  меньше 10
                if (phoneNumber.length() != lengh) {
                    return false; //т.е. номер не прошел валидацию
                } else {
                    //еще одна валидация с использование try-catch, в который заключаем код
                    //который потенциально может вызвать ошибку
                    try {
                        validNumber = Integer.parseInt(phoneNumber);//берем нашу стринговую строку.
                        // распарсиваем ее и получаем числовое значение
                        //а если phoneNumber будет иметь и числа и буквы, то мы получим исключение
                        return true;
                    } catch (NumberFormatException e) {
                        return false;//если возникнет эксепшн, то просто возращаем false, без вывода сообщений
                    }
                }
            }

            //метод отвечающий за вызов метода isValid и за выполнение звонка, если валидация прошла
            public void dialIn() {
                if (isValid()) {
                    System.out.println("Calling phone number " + validNumber);
                } else {
                    System.out.println("Phone number is invalid. Please correct phone number.");
                }
            }
        }

        //инстансиируем (создаем объект) наш локальный класс и вызовем метод
        GSMModule module = new GSMModule(number);//здесь тот number, который мы передали нашему методу call
        //помним что используем конструктор, который в качестве параметра принимает телефонный номер

        module.dialIn();
    }
}
