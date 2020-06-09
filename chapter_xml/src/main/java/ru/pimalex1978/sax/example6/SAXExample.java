package ru.pimalex1978.sax.example6;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * SAX — это набор классов и интерфейсов, задача которых дать механизм разбора
 * XML в строковом представлении. Основными классами я бы назвал два:
 * 1. SAXParser
 * 2. DefaultHandler
 * Я бы так описал их совместную работу: класс SAXParser начинает читать
 * содержимое XML и когда наступает момент «Х» — начинается новый тэг,
 * заканчивается тэг, начинается текст внутри тэга — т.е. происходит
 * некоторое событие — он может вызывать определенную функцию класса DefaultHandler.
 * Для того, чтобы «наполнить» эти вызовы со стороны SAXParser хоть каким-то смыслом,
 * надо расширить класс DefaultHandler.
 * Давайте посмотрим пример печати содержимого тэга NAME из XML такого вида.
 * Phonebook.xml
 * <p>
 * Почему SAX используется ? Самое главное это то, что он позволяет читать
 * просто гигантские файлы — если вам надо при обработке только что-то найти
 * и изредка что-то запомнить. Ведь созданием каких-либо объектов для получения
 * результатов парсинга управляете вы сами. С одной стороны — все в ваших руках.
 * С другой стороны — ответственность тоже на вас. Что «насобираете», то и ваше.
 * <p>
 * В моей практике наиболее часто встречалась задача импорта больших объемов данных.
 * В одной системе подготавливается огромный XML (сотни мегабайт) и он передается
 * в другую систему, где данные из него можно загрузить в базу. Например список
 * фирм или транзакций по каким-то операциям. Информация об одной фирме занимает
 * мало места и ее легко загрузить в память и потом записать в базу, но количество
 * таких записей может выражаться сотнями миллионов. В такой ситуации SAX решает
 * задачу очень эффективно и удобно.
 */

public class SAXExample {
    public static void main(String[] args) {

        // Имя файла
        final String fileName = "C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\Phonebook.xml";

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Здесь мы определили анонимный класс, расширяющий класс DefaultHandler
            DefaultHandler handler = new DefaultHandler() {
                // Поле для указания, что тэг NAME начался
                boolean name = false;

                // Метод вызывается когда SAXParser "натыкается" на начало тэга
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    // Если тэг имеет имя NAME, то мы этот момент отмечаем - начался тэг NAME
                    if (qName.equalsIgnoreCase("NAME")) {
                        name = true;
                    }
                }

                // Метод вызывается когда SAXParser считывает текст между тэгами
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    // Если перед этим мы отметили, что имя тэга NAME - значит нам надо текст использовать.
                    if (name) {
                        System.out.println("Name: " + new String(ch, start, length));
                        name = false;
                    }
                }
            };

            // Стартуем разбор методом parse, которому передаем наследника от DefaultHandler, который будет вызываться в нужные моменты
            saxParser.parse(fileName, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
