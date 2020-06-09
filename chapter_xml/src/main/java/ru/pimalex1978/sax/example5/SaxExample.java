package ru.pimalex1978.sax.example5;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/*
 * SAX парсер не хранит информацию о том, где какой элемент находится по отношению к другим,
 * не запоминает структуру.
 * Поэтому те теги, которые он проходит их нужно запоминать иначе они будут потеряны,
 * т.к. парсер назад к прошедшему месту не возвращается.
 *
 * */

public class SaxExample {

    public static void main(String[] args) {


        //SAX - parser

        //Подгружаем наш xml документ. Для этого делаем несколько шагов:
        //1. Создаем фабрику парсеров. Класс фабрики SAXParserFactory.
        //в нем вызываем статический метод newInstance(),
        //который возвращает объект этого класса
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        //2. Создаем объект класса слушателя событий
        Handler handler = new Handler();

        try {
            //3. Создаем сам парсер, вызывая его из фабрики с помощью
            // метода newSAXParser(), который возвращает объект парсера
            SAXParser saxParser = parserFactory.newSAXParser();
            //вызовем метод parser() у объекта saxParser, который принимает аргумет класса File,
            //т.е. для подгрузки xml файла путь к файлу и handler, это т.называемый
            //слушатель событий. Событий при наступлении какого то тега в xml файле.
            saxParser.parse(new File("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\xml_file10.xml"), handler);
        } catch (ParserConfigurationException |
                SAXException | IOException e) {
            e.printStackTrace();
        }

        /*
         * Получим наши данные из xml файла
         */
        Map<Integer, String> data = handler.getData();
        for (Map.Entry<Integer, String> dMap : data.entrySet()) {
            System.out.println(dMap.getKey() + " - " + dMap.getValue());
        }
    }
}
