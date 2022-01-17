package ru.pimalex1978.recursion;

public class PowExample {
    /*Рассмотрим пример рекурсии на функции возведения в степень.
    Действие, которое выполняется в цикле этой функции довольно однотипное,
    и следовательно функцию можно сделать рекурсивной.
    Рекурсивная функция - это когда функция вызывает саму себя.
    То есть на каждый новый вызов будет происходить умножение.*/

    //function pow(x, n) { // функция с параметрами
    //let result = 1;
    //for (let i = 0; i < n; i++) { //цикл с условиями
    //result = result * x;
    //}
    //return result;
    //}

    public static long pow(int x, int n) { // функция с параметрами
        long result = 1;
        for (int i = 0; i < n; i++) { //цикл с условиями
            result = result * x;
        }
        return result;
    }

    public static long powR(int x, int n) {
        if (n == 1) {
            return x;
        } else {
            return x * powR(x, n - 1);
        }
    }

    /*Здесь мы избавились от цикла. И каждый новый запуск происходит с уменьшением степени до тех пор,
    пока степень не станет 1. Эта 1 указывает нам на то, что пора рекурсию завершить.
    Если это условие не указать, то она будет выполняться бесконечно.
    То есть, рекурсия - это вызов функции самой себя.
    Говорят, что рекурсия хороша там, где заранее не известна глубина и количество ответвлений.
    Например, рекурсия используется для листинга иерархической файловой структуры.*/

    //function pow(x, n) {
    //if (n == 1) {
    //return x;
    //} else {
    //return x * pow(x, n - 1);
    //}
    //}

    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        System.out.println(pow(2, 32));
        System.out.println("check time: " + (System.currentTimeMillis() - start));

        var start2 = System.currentTimeMillis();
        System.out.println(powR(2, 32));
        System.out.println("check time: " + (System.currentTimeMillis() - start2));

    }
}
