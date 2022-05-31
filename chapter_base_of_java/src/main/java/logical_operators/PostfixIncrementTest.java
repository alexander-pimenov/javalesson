package logical_operators;

/*Постфиксный инкремент*/
public class PostfixIncrementTest {
    public static void main(String[] args) {

        //1 пример
        postfixIncrementOperator();

        //2 пример
        int a = 5;
        System.out.println(a++); //5
        System.out.println(a); //6
    }

    /* Программа выведет 0. В утверждении «num = num++» используется
     * постфиксный оператор инкремента.
     * Поэтому порядок действий следующий:
     *  - сохранить значение числа во временной переменной,
     *  - увеличить ее значение на единицу
     *  - и присвоить переменной num.
     * Т.е. сначала присваиваем num 0, а потом увеличиваем на 1,
     * но это увеличение уже не присваивается и теряется.*/
    private static void postfixIncrementOperator() {
        int num = 0;
        for (int i = 0; i < 100; i++) {
//            num = num + 1;
            num = num++;
        }
        System.out.println(num);
    }
}
