package nestedclasses.staticExample1;

public class NestedClasses {

    public static void main(String[] args) {
        //Bank bank = new Bank(); - доступа нет, т.к. Bank private

        /*чтобы создать Bank нам нужно создать Банкира Banker
        * но т.к. в области видимости Bank есть Banker, то это выглядит так:*/
        Bank.Banker banker = new Bank.Banker();
        //теперь banker может создать Банк
        Bank bank = banker.createBank();

    }
}
