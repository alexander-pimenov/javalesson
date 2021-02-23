package nestedclasses.nonStaticExample;


public class NestedClasses2 {

    public static void main(String[] args) {
        //Bank bank = new Bank(); - доступа нет, т.к. Bank private

        /*чтобы создать Bank нам нужно создать Банкира Banker
         * но т.к. в области видимости Bank есть Banker, то это выглядит так:*/
        Bank2.Banker2 banker = new Bank2.Banker2();
        //теперь banker может создать Банк
        Bank2 bank = banker.createBank();

        /*создаем новый объект Account
         * такая конструкция обозначает, что создаем новый объект Account
         * и этот объект будет ассоциирован именно с этим экземпляром банка
         * имеем такую запись Bank2.Account - т.к. Account лежит внутри Bank2*/
        Bank2.Account account = bank.new Account("qwer", "Filip Loo", 10000);
        //открываем этот Account
        account.open();
        //кладем деньги на счет
        bank.putMoney("qwer", 1500);
        //берем деньги
        bank.getMoney("qwer", 500);
        //закрываем этот Account
        account.close();


    }
}
