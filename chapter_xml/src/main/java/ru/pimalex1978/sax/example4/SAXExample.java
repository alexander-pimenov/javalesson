package ru.pimalex1978.sax.example4;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Когда у нас начинается парсинг xml документа, то первым делом у нас
 * происходит событие startDocument.
 * Для обработки этого события, вызывается метод startDocument().
 * Здесь можно делать всё что угодно. Мы выводим строку "Start XML parsing..."
 * Когда мы обработали событие startDocument, мы встречаем первый тег report и
 * генерируется событие startElement. Взывается метод startElement().
 * И в этом методе промимо многих других параметров передаются два интересных для нас:
 * qName - содержит имя нашего элемента (имя тега),
 * attributes - содержит набор атрибутов.
 * Нам необходимо обработать различные ситуации, когда мы встречаем тот или
 * иной элемент.
 *
 * По окончании разбора XML документа в нашей переменной Report будет лежать весь набор
 * наших работников.
 *
 * */

public class SAXExample {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //Инициализируем SAX парсер, создаем фабрику
        SAXParserFactory spf = SAXParserFactory.newInstance();
        //Достаем экземпляр парсера из фабрики
        SAXParser saxParser = spf.newSAXParser();

        //Для того чтобы начать парсить, нам нужен Ридер
        XMLReader xmlReader = saxParser.getXMLReader();

        //Необходимо реализовать класс MyHandler, который расширяет класс DefaultHandler
        //он отвечает за обработку событий при разборе ашего xml документа
        MyHandler handler = new MyHandler();

        xmlReader.setContentHandler(handler);

        //указали путь к xml файлу, который надо распарсить
        xmlReader.parse("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\xml_file9.xml");
        //saxParser.parse(new File("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\xml_file9.xml"), handler);

        //Report Сформирован внутри handler, содержит всю информацию на основе xml документа
        Report report = handler.getReport();

        System.out.println("\nReport number: " + report.number);
        report.employeeList.forEach(System.out::println);
    }

    private static class MyHandler extends DefaultHandler {
        /*Для удобства названия тегов и атрибутов выносим в константы*/
        static final String REPORT_TAG = "report";
        static final String EMPLOYERS_TAG = "employers";
        static final String EMPLOYEE_TAG = "employee";
        static final String NAME_TAG = "name";
        static final String AGE_TAG = "age";
        static final String SALARY_TAG = "salary";

        static final String REPORT_NUMBER_ATTRIBUTE = "number";
        static final String EMPLOYERS_DEPARTMENT_ATTRIBUTE = "department";
        static final String EMPLOYEE_NUMBER_ATTRIBUTE = "number";
        static final String CURRENCY_ATTRIBUTE = "currency";

        //так же здесь определяем наш класс Report
        private Report report;

        //Работник
        private Employee currentEmployee;

        //здесь хранится номер Депертамента, чтобы запомнить информацию
        //о Депертаменте, чтобы в последствии когда будем встречать Employee,
        //мы эту информацию будем проставлять в объекты Employee.
        //Т.к. поле department есть внутри класса Employee
        private String employersDepartment;

        private String currentElement; //для запоминания текущего элемента при проходе
        // по xml документу

        Report getReport() {
            return report;
        }

        //здесь переопределяем метод startDocument() из класса DefaultHandler
        @Override
        public void startDocument() throws SAXException {
            System.out.println("Start XML parsing...");
        }

        /*
         * Немного информации объясняющей параметры метода:
         * uri - это пространство, в котором находится элемент;
         * localName - это имя элемента без префикса;
         * qName - это имя элемента с префиксом (если он есть, иначе просто имя элемента).
         *
         * Есть пару моментов:
         * когда встречаем тег name и age, мы их в этом методе никак не обрабатываем,
         * лиш проставляем в currentElement: name, а затем age.
         * Когда встречается тег name, следом идет генерация события, которая
         * означает что внутри тега name встретились какие то символы. В первом случае
         * это John Doe. За обработку такого события отвечает метод characters().
         * Обработали в методе characters() тег name. Читаем дальше. Доходим до
         * тега age. Вычитываем его. В startElement() он никак не обрабатывается,
         * кроме того что в currentElement выставляется age. Потом генерируется событие
         * characters , заходим в метод characters() и  в case AGE_TAG проставляем
         * возраст для нашего текущего Employee.
         * Далее читаем таг salary. Генерируетс ясобытие startElement.
         * Доходим до case SALARY_TAG, инициализируем нашу переменную в текущем
         * Employee и проставляем currency из атрибута. И далее нужно обработать
         * событие characters на содержимое этого тега.
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            //запоминаем название текущего элемента в currentElement
            currentElement = qName;

            //делаем анализ, какой тег мы сейчас вычитали
            //в первом случае это будет тег report
            switch (currentElement) {
                //вычитываем элемент Report
                case REPORT_TAG: {
                    report = new Report();
                    report.number = Integer.valueOf(attributes.getValue(REPORT_NUMBER_ATTRIBUTE));
                }
                break;

                //вычитываем следующий элемент Employers
                case EMPLOYERS_TAG: {

                    //проинициализируем список, в котором будут храниться Работники
                    report.employeeList = new ArrayList<>();

                    /*здесь запоминаем в строковую переменную department значение
                     * атрибута department для того чтобы проставлять это
                     * значение в каждый объект Employee по отдельности*/
                    if (employersDepartment == null) {
                        employersDepartment = attributes.getValue(EMPLOYERS_DEPARTMENT_ATTRIBUTE);
                    }
                }
                break;

                //вычитываем тег Employee
                case EMPLOYEE_TAG: {
                    //создаем нового работника
                    currentEmployee = new Employee();
                    //у нас есть доступ к атрибуту number, достаем его значение и
                    //подставляем его в текущий объект Employee
                    currentEmployee.number = Integer.valueOf(attributes.getValue(EMPLOYEE_NUMBER_ATTRIBUTE));
                    //проставляем название департамента на основе того что сохранили на предыдущем шаге
                    currentEmployee.department = employersDepartment;
                }
                break;

                //вычитываем тег salary
                case SALARY_TAG: {
                    currentEmployee.salary = new Employee.Salary();
                    currentEmployee.salary.currency = attributes.getValue(CURRENCY_ATTRIBUTE);
                }
                break;

                default: {
                    //nothing to do
                }
            }
        }

        /*
         * Когда между тегами встречаем текст, то за обработку такого события отвечает
         * вызов метода characters().
         * Событие characters вызывается на любое содержимое тегов, поэтому оно будет
         * вызываться и для содержимого тега employee и для содержимого тега name и
         * для тега age и для тега salary и для тега employers и для тега report,
         * но так нас интересуют конкретные значения, внутри тегов name, age,
         * salary то ставим проверку (text.contains("<") || currentElement == null)
         * Это значит, что если текст который вычитался содержит угловую скобку "<",
         * т.е. начало какого то элемента , то нам такой тект не интересен , либо
         * если текущий элемент currentElement никак не проинициализирован, т.е.
         * мы не зафиксировали, что у нас открыт какой то элемент, то  мы выходим из
         * обработки и ничего не делаем.
         */

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            //достаем байт массив символов, который соответствует символам внутри нашего
            //тега и преобразуем это в строку.
            String text = new String(ch, start, length);

            if (text.contains("<") || currentElement == null) {
                return;
            }

            switch (currentElement) {
                case NAME_TAG: {
                    //В поле name объекта Employee проставляем содержимое text
                    currentEmployee.name = text;
                }
                break;
                case AGE_TAG: {
                    currentEmployee.age = Integer.valueOf(text);
                }
                break;

                //вычитываем текстовое содержимое тега salary
                case SALARY_TAG: {
                    //проставим значение оклада в текущего Employee
                    currentEmployee.salary.value = Double.valueOf(text);
                }
                break;
                default: {
                    //nothing to do
                }
            }
        }

        /*Всякий раз когда мы будем встречать закрываеющий тег </..>
         * то мы должны зафиксировать, что формирование объекта Employee
         * закончилось и мы должны его добавить в наш целевой список работников.
         * И текущую переменную работника currentEmployee обнуляем и текущий
         * элемент тоже зануляем.
         * Этот метод будет срабатывать и для любых других событиц, когда будем
         * доходить до закрывающих тегов </name> </age> </salary> и т.д.
         * */

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case EMPLOYEE_TAG: {
                    report.employeeList.add(currentEmployee);
                    currentEmployee = null;
                }
                break;
                default: {
                    //nothing to do
                }
            }
            currentElement = null;
        }

        /*
         * Когда доходим до конца нашего документа, то сработает последнее событие
         * endDocument.
         * Выведем просто сообщение, что мы закончили.
         * */

        @Override
        public void endDocument() throws SAXException {
            System.out.println("XML parsing is completed.");
        }
    }
}