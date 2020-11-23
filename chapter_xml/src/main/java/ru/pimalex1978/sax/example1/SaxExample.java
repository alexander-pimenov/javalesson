package ru.pimalex1978.sax.example1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Цель этого урока - достать с помощью парсера XML всю информацию о
 * сотрудниках из файла xml_file1.xml.
 */

public class SaxExample {
    //список со всеми сотрудниками будем хранить в ArrayList
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        //Создать SAXParser
        //Создание фабрики и образца парсера
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        //Запарсим наш xml файл
        XMLHandler handler = new XMLHandler();

        // Передаем путь к xml файлу и обработчик, который мы создали: class XMLHandler
        parser.parse(new File(SaxExample.class.getResource("/xml_file1.xml").getFile()), handler);
//        parser.parse(new File("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\xml_file1.xml"), handler);

        //Выводим информацию о сотруднике
        for (Employee employee : employees) {
            System.out.println(String.format("Имя сотрудника: %s, его должность: %s", employee.getName(), employee.getJob()));
        }
    }

    /**
     * Этот класс, созданный для удобства обрабатывать события парсера.
     * Обработчики – полноценные классы, способные хранить в себе логику любой сложности.
     * <p>
     * Немного информации объясняющей параметры метода:
     * uri - это пространство, в котором находится элемент;
     * localName - это имя элемента без префикса;
     * qName - это имя элемента с префиксом (если он есть, иначе просто имя элемента).
     */
    private static class XMLHandler extends DefaultHandler {

        /* Логика метода простая:
         * если имя элемента — employee, мы просто будем получать информацию
         * про его атрибуту. В attributes есть полезный метод, где, зная название
         * атрибута, можно получить его значение. Именно его мы и использовали.*/

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("employee")) {
                String name = attributes.getValue("name");
                String job = attributes.getValue("job");
                employees.add(new Employee(name, job));
            }
        }
    }
}
