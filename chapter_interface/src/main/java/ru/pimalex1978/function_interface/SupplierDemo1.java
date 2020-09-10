package ru.pimalex1978.function_interface;

import org.apache.logging.log4j.core.util.JsonUtils;

import java.util.function.Supplier;

/**
 * Supplier (поставщик) - это встроенный функциональный интерфейс, добавленный в Java SE 8 в пакет java.util.function.
 * Возвращает значение, одно и тоже или разные.
 * Интерфейс Supplier используется тогда, когда на вход не передаются значения,
 * но необходимо вернуть результат.
 * Функциональный дескриптор интерфейса:
 * () -> T
 */
public class SupplierDemo1 {

    public static void main(String[] args) {
        //
        String t = "One day";
        //Сапплаер обрабатывает Стринг, выполняя действие по переводу символов в верхний регистр.
        Supplier<String> supplierStr = () -> t.toUpperCase();

        System.out.println(supplierStr.get()); //ONE DAY
    }
}

//Описание интерфейса Supplier:
//@FunctionalInterface
//public interface Supplier<T> {
//    T get();
//}
