package nestedclasses.nonStaticExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/*рассмотрим работу примитивного банка
 * используем публичные вложенные классы*/

public class Bank2 {
    /*для того чтобы связывать человека с его счетом, хранить информацию о человеке
     * создадим класс, обозначающий счет. Определим этот класс внутри класса Bank*/
    /* Поскольку класс Account является нестатическим, соответственно он был создан со ссылкой
     * на экземпляр класса Bank2 (класс оболочка) Поэтому внутри методов класса Account
     * мы имеем полный доступ к ПОЛЯМ класса оболочки*/
    public class Account {
        /*чтобы хранить инфо о пользователе, заводим ПОЛЯ*/
        private String id;
        private String name;
        private int money;//колич.денег на его счету
        private ArrayList<AccountOperation> operation = new ArrayList<AccountOperation>();//список операций

        //чтобы пользователь самостоятельно открывать счет создаем конструктор
        public Account(String id, String name, int amount) {
            this.id = id;
            this.name = name;
            this.money = amount;
        }

        //регистрация счета в банке
        public void open() {
            //обращаемся к accounts определенным в классе оболочки
            //в эти accounts можем положить в себя
            accounts.put(id, this);

        }

        //закрытие счета
        //удаляем ссылку на самого себя
        public void close() {
            accounts.remove(id);
        }
    }

    /*т.к. Банк нельзя создавать на ровном месте, мы конструктор Bank объявим private.
     * И это будет говорить, что нельзя будет создать Bank в любом месте программы
     * через new, кроме самого этого класса*/
    private Bank2() {

    }

    /*не смотря на private Bank изнутри статического вложенного класса Banker
     * мы  вполне можем вызвать этот конструктор  private Bank() не нарушив видимости*/
    public static class Banker2 {
        //главная функция банкира Banker будет создание банка Bank
        public Bank2 createBank() {
            return new Bank2();
        }
    }

    /*чтобы иметь возможность по идентификатору id доставать тот или иной счет
     * мы заводим такое ПОЛЕ (список счетов):*/
    //это HashMap ключем у которой будет строчная переменная id, а значением будет счет Account
    //при регистрации (open) счета он должен быть помещен в список счетов Банка
    //при закрытии (close) счета, он должен быть удален из списка счетов
    private HashMap<String, Account> accounts = new HashMap<String, Account>();

    /*заводим методы нужные для банка:*/
    //открывает счет Account самостоятельно

    //метод положить деньги: с входными данными id & amount колич. денег
    public void putMoney(String id, int amount) {
        //чтобы положить деньги на счет, мы из списка счетов достаем счет Account соответствующий данному id
        Account account = accounts.get(id);
        //делаем проверку на наличие счета: если нет такого счета, то ничего не делаем
        if (account == null) {
            return;
        }
//        //если счет в списке есть, то колич.денег увеличиваем на amount
//        account.money += amount;

        /*ниже мы создаем новый Анонимный Класс, к-рый на самом деле является наследником Интерфейса. И вэтом классе определены 2 метода*/
        /*создадим экземпляр операции operation. Т.к. просто через new создать экземпляр у интерфеса
        мы не можем то будем использовать реализацию в { } скобках*/
        AccountOperation operation = new AccountOperation() {
            /*даем описание тех методов, которые есть у AccountOperation*/
            @Override
            public Date getDate() {
                return new Date();
            }

            @Override
            public int amount() {
                return amount;
            }
        }; //ВНИМАНИЕ { } заканчиваются ';'

        //добавляем эту Операцию в список операций счета
        account.operation.add(operation);
    }

    //метод снять деньги со счета: с входными данными id & amount
    public void getMoney(String id, int amount) {
        //чтобы снять деньги со счета, мы из списка счетов достаем счет Account соответствующий данному id
        Account account = accounts.get(id);
        //делаем проверку на наличие счета: если нет такого счета, то ничего не делаем return
        if (account == null) {
            return;
        }
//        //если счет в списке есть, то колич.денег уменьшаем на amount
//        account.money -= amount;
        AccountOperation operation = new AccountOperation() {
            /*даем описание тех методов, которые есть у AccountOperation*/
            @Override
            public Date getDate() {
                return new Date();
            }

            @Override
            public int amount() {
                return -amount;//минус т.к. идет снятие денег
            }
        }; //ВНИМАНИЕ { } заканчиваются ';'

        //добавляем эту Операцию в список операций счета
        account.operation.add(operation);
    }
}
