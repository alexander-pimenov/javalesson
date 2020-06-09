package ru.pimalex1978.sax.example5;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;
/*
* Класс слушателя Событий при наступлении какого то тега в xml файле.
* Он является наследником класса DefaultHandler.
* В этом классе мы опишем, каким образом нам нужно просмотреть данные в xml
* файле.
*
* Принцип работы SAX парсера:
* Он находит самый первый тег. В нашем случае это market. Элемент <market>.
* Затем он находит второй элемент <product>, затем третий элемнт <title>, затем
* закрывающий элемент </title>, потом находит <price>, потом </price> и т.д.
* Т.е. сканирует весь xml документ по порядку.
* Т.е. если он уже просканировал, например участок кода:
* <product id="1">
            <title>Продукт 1</title>
            <price>30</price>
            <amount type="шт">10</amount>
   </product>
* то он уже не вернется назад.
* Т.е. этот парсер нужно использовать тогда, когда нам стоит один раз просканировать
* файл и потом этими данными оперировать в приложении.
* */

public class Handler extends DefaultHandler {

    //здесь будем хранить весь наш результат
    private Map<Integer, String> data = new HashMap<>();

    //чтобы хранить данные об id продуктов
    //это также ключ в Map date
    private int id;

    //чтобы хранить название продуктов
    //Это значение в Map date
    private String element;

    /*
     * startDocument
     * Этот метод начинает работать тогда и только тогда, когда мы начинаем парсить
     * xml документ, т.е. как только подгрузили документ и начинаем его парсить,
     * этот метод срабатывает.
     */
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parsing...");
    }

    /*
     * endDocument()
     * Это полная противоположность метода startDocument()
     * Он срабатывает тогда, когда мы заканчиваем парсить весь документ.
     */
    @Override
    public void endDocument() throws SAXException {
        System.out.println("End parsing...");
    }

    /*
     * Для того чтобы получить некие сведения, которые для нас актуальны,
     * необходимо использовать startElement(), который срабатывает тогда,
     * когда мы находим некий элемент, например <market>, <department> и т.д.
     * uri - значение пространства имен;
     * localName - локальное имя;
     * qName - составное имя, состоящее из пространства имен uri и localName,
     * которые разделены символом ":";
     * attributes - объект класса Attributes, он возвращает все сведения о неких
     * атрибутах на тегах. В нашем случае атрибуты есть в таких тегах, как
     * <department id="1">, <product id="1">, <amount type="шт">.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        //Когда мы находим элемент, то мы записываем имя тега, который парсим.
        //Т.обр. мы можем получить все имена различных тегов, которые встретятся по пути
        //пока не дойдем до конца документа.
        element = qName;

        //Мы должны определить какой элемент сейчас происходит.
        // Мы ищем элемент продукта. Имя product
        //Когда мы его находим то записываем его id.
        if (element.equals("product")) {
            //Чтобы найти id, используем класс attributes.
            //определим название атрибута по индексу (есть и другие методы)
            //и преобразуем его из String к int
            id = Integer.parseInt(attributes.getValue(0));
        }
    }

    /*
     * Метод endElement() сработает как только мы закончим читать элемент.
     * В параметрах он не принимает атрибутов, т.е. мы можем получить только
     * имя того элемента, который закончили считывать.
     * Например,вычитываем <amount type="шт">10</amount> и когда мы дошли до закрывающего
     * тега </amount>, то сработает этот метод.
     * А после закрывающегося </amount>, нашли закрывающийся </product> и опять
     * сработает метод endElement()
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        //После каждого закрывающегося тега, мы указываем что элемента нет ""
        //и id=-1, т.е. как такового нет.
        element = "";
        id = -1; //тут можно ставить любое значение
    }

    /*
     * У нас есть метод который обрабатывает символьные данные (текст) находящиеся
     * между открывающимся < > и закрывающимся </ > тегами.
     * Получает уведомления о символьных данных внутри элемента.
     * <title>Продукт 1</title>, символьные данные - "Продукт 1"
     * <price>30</price>, символьные данные - "30"
     * Нужно самим определиться, как использовать эти данные:
     * добавить данные в узел или буфер или печать их в файл или еще как.
     *
     * Этот метод принимает 4 параметра:
     * char[] ch - набор символов
     * int start - начальная позиция в массиве символов
     * int length - количество символов, используемых из массива символов.
     * (как бы конечный элемент в массиве символов)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        //Создадим объект Стринг и в параметры его конструктора передадим наш массив,
        //начальный элемент массива, конечный элемент масства.
        //Т.е. так весь набор символов преобразуем в объект.

        //Далее говорим, что эсли наш элемент title, т.е. содержит название
        //то укажем его имя
        if (element.equals("title")) {
            String titleStr = new String(ch, start, length);

            //и запишем данные в Map
            data.put(id, titleStr);
        }

        //здесь просто выводим текст содержащийся между тегами
        String str = new String(ch, start, length);
        System.out.println(str);
    }

    /*
     * Метод, который возвращает нам нашу коллекцию.
     */
    public Map<Integer, String> getData() {
        return data;
    }
}
