package ru.pimalex1978.sax.example2;

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
 * Наша цель: достать всю информацию про всех сотрудников из данного файла company.xml.
 * <p>
 * Задача хорошо продемонстрирует, каким образом плохо структурированный XML файл может
 * приводить к усложнению написания кода.
 * <p>
 * Как вы видите, информация про имя и должность (в отличие от примера в пакете example1)
 * теперь хранится, как текстовая информация внутри элементов name и job.
 * Для считывания текста внутри элементов у нас есть метод characters.
 * В примере в пакете example1 информация о сотрудниках была в атрибутах элемента.
 * <p>
 * Для этого, нам нужно создать новый класс-обработчик с улучшенной логикой.
 * Не забывайте, что обработчики – полноценные классы, способные хранить в себе логику
 * любой сложности.
 * Потому, сейчас мы будем тюнинговать наш обработчик.
 * <p>
 * На самом деле, достаточно заметить, что у нас всегда name и job идут по очереди, и
 * не важно, в каком порядке,
 * мы можем спокойно сохранить имя и профессию в отдельные переменные, и когда обе
 * переменные сохранены – создать нашего сотрудника.
 * Только вот вместе с началом элемента у нас нет параметра для текста внутри элемента.
 * Нам нужно использовать методы для текста.
 * <p>
 * Но как нам получить текстовую информацию внутри элемента, если это совершенно разные
 * методы?
 * Мое решение: нам достаточно запомнить имя последнего элемента, а в characters проверять,
 * в каком элементе мы считываем информацию.
 * <p>
 * Так же нужно помнить, что characters считывает все символы внутри элементов, а это значит,
 * что будут считываться все пробелы и даже переносы строчек.
 * А они нам не нужны. Нам нужно игнорировать эти данные, так как они неправильные.
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
        AdvancedXMLHandler handler = new AdvancedXMLHandler();

        // Передаем путь к xml файлу и обработчик, который мы создали: class AdvancedXMLHandler
        parser.parse(new File("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\company.xml"), handler);

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
     * <p>
     * Мы создали переменные для хранения данных про сотрудника (name, job), а так же
     * переменную lastElementName, чтобы фиксировать, внутри какого элемента мы находимся.
     * <p>
     * После этого, в методе characters мы фильтруем информацию, и если там еще осталась
     * информация, то, значит, это нужный нам текст, а далее мы определяем, имя это или
     * профессия, используя lastElementName.
     * В методе endElement мы проверяем, считана ли вся информация, и если считана, то мы
     * создаем сотрудника и сбрасываем информацию.
     */
    private static class AdvancedXMLHandler extends DefaultHandler {
        private String name, job, lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastElementName.equals("name")) {
                    name = information;
                }
                if (lastElementName.equals("job")) {
                    job = information;
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ((name != null && !name.isEmpty()) && (job != null && !job.isEmpty())) {
                employees.add(new Employee(name, job));
                name = null;
                job = null;
            }
        }
    }
}
