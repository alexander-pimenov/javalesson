package ru.pimalex1978.concurrent.singleton;

/*
 *
 * При реализации шаблона Singleton необходимо учитывать проблему с visibility (Видимость).
 * Эту проблему можно решать двумя способами используя volatile или сразу
 * публикуя объект через final.
 * Многопоточные реализации аналогичны не многопоточным.
 * Их можно разделить на две группы:
 * 1. энергичные
 * 2. ленивые
 *
 * Энергичные с помощью enum:
 *
 * Объект enum создается при загрузке класса и безопасно
 * публикуется всех клиентам.
 *
 * Вывод.
 * Если у вас нет необходимости в ленивой загрузке, используются шаблоны из
 * первой группы. Например, инициализация кеша или базы данных.
 * Если в приложении есть затратные ресурсы нужно использовать шаблоны с
 * ленивой загрузкой.
 * Здесь можно использовать только один шаблон - это Holder. Другие шаблоны
 * будут отрицательно влиять на производительность системы.
 *
 * */
public enum TrackerSingle1 {
    INSTANCE;

    public static void main(String[] args) {
        TrackerSingle1 trackerSingle1 = TrackerSingle1.INSTANCE;
    }
}
