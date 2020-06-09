package ru.pimalex1978.iterator.example1;

/**
 * В этом классе проверяем работу созданного Итератора.
 */

public class IteratorApp {
    public static void main(String[] args) {
        ConcreteContainer cAggr = new ConcreteContainer();

        Iterator it = cAggr.getIterator();

        //Работаем с итератором в цикле.
        while (it.hasNext()) {
            //пока есть элемент, мы считываем его и выводим на экран.
            //обязательное преобразование, т.к. метод next() универсальный
            //и он возвращает Object
            System.out.println((String) it.next());
        }
    }
}
