package ru.pimalex.oopExample1;

public enum Size {

    /*
    *
    * если в названии есть два слова, то используем '_'
    * также как и любой другой Класс, enum может содержать Конструктор
    * у Конструктора нет return type (int, String..),в отличие от МЕТОДА
    * */

   VERY_SMALL ("XS"), SMALL("S"),
    AVERAGE("M"), BIG("L"), VERY_BIG("XL"),
    UNDEFINED (" ");


    /*
    * метод доступа (public, private...) можно не указывать
    * как и МЕТОД Конструктор в () передает какой то параметр
    * в данном случае выбрали передачу абревиатуры*/
    Size (String abbreviation){
        //this. ключевое слово, к-рое показывает, что мы используем ПОЛЕ "private String abbreviation;", а не параметр "Size (String abbreviation)"
        //которое будет указывать на текущий объект
        //здесь мы у текущего объекта берем ПОЛЕ abbreviation и ему присваиваем значение нашего параметра abbreviation
        this.abbreviation = abbreviation;
    }

    /*
    * для того чтобы присвоить куда то значение параметра из () (Size())
    * нам нужно создать какое то ПОЛЕ
    * это ПОЛЕ пусть называется String abbreviation
    * */
    private String abbreviation;

    /*
    * создадим геттер метод, который может возвращать значение, к-рое хранится в ПОЛЕ abbreviation
    *
    * сгенерируем его
    * */

    public String getAbbreviation() {
        return abbreviation;
    }
    /*кроме МЕТОДА getAbbreviation были не явно унаследованы МЕТОДЫ от нашего enum типа
    *а какие, можем узнать нажав Ctrl-F12, поставить галочку "Show inherited members", то мы увидим целый ряд методов
    *интересуют записи с -Enum
    * */
}
