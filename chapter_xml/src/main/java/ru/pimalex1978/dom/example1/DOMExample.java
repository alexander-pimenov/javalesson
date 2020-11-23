package ru.pimalex1978.dom.example1;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Задача №1 – нам нужно достать информацию о всех сотрудниках и вывести её в консоль
 * из XML файла xml_file1.xml.
 * <p>
 * Теперь, когда у нас есть описание структуры для хранения данных Employee,
 * нам нужна коллекция, которая будет хранить сотрудников List<Employee>employees.
 * Её мы будем создавать в самом коде.
 * Так же нам нужно создать Document на основе нашего XML.
 * <p>
 * После получения документа, мы обладаем неограниченной властью над всей структурой
 * XML файла. Мы можем получать любые элементы в любое время, возвращаться обратно,
 * чтобы проверить какие-либо данные и, в целом, более гибкий подход, чем был у
 * нас в SAX.
 * <p>
 * В контексте данной задачи, нам нужно просто извлечь все элементы employee,
 * а после извлечь всю информацию про них. Это достаточно просто.
 */

public class DOMExample {

    //список для сотрудников из XML файла
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, URISyntaxException {
        //Получение фабрики, чтобы послс получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //Получили из фабрики билдер документов, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Получим путь к нашему xml файлу.(вариант 1) ОБЯЗАТЕЛЬНО ставить - "/"
        File xml_file1 = new File(DOMExample.class.getResource("/xml_file1.xml").toURI().getPath());

        //Получим путь к нашему xml файлу.(вариант 2)
//        ClassLoader classLoader = DOMExample.class.getClassLoader();
//        String path = classLoader.getResource("xml_file1.xml").getPath();

        //Получим путь к нашему xml файлу.(вариант 3) ОБЯЗАТЕЛЬНО ставить - "/"
//        URL resource1 = DOMExample.class.getResource("/xml_file1.xml");
//        File toFile = Paths.get(resource1.toURI()).toFile();

        //Получим путь к нашему xml файлу.(вариант 4) ОБЯЗАТЕЛЬНО ставить - "/"
        //File xml_file1 = new File(DOMExample.class.getResource("/xml_file1.xml").getFile());

        //Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        //Так прописывать путь к файлу не верно. На других машинах будет ошибка:
        //Document document = builder.parse(new File("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\xml_file1.xml"));
        //1-й вариант найденного пути к файлу
        Document document = builder.parse(xml_file1);

        //2-й вариант найденного пути к файлу
        //Document document = builder.parse(new File(path));

        //3-й вариант найденного пути к файлу
        //Document document = builder.parse(toFile);

        //4-й вариант найденного пути к файлу
        //Document document = builder.parse(xml_file1);

        // Получение списка всех элементов employee внутри корневого элемента (getDocumentElement возвращает ROOT элемент XML файла).
        NodeList employeeElements = document.getDocumentElement().getElementsByTagName("employee");

        // Перебор всех элементов employee
        for (int i = 0; i < employeeElements.getLength(); i++) {
            Node employee = employeeElements.item(i);

            //Получение атрибутов каждого элемента
            NamedNodeMap attributes = employee.getAttributes();

            //Добавление сотрудника.
            //Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue().
            employees.add(new Employee(attributes.getNamedItem("name").getNodeValue(),
                    attributes.getNamedItem("job").getNodeValue()));
        }

        //Вывод информации о каждом сотруднике.
        for (Employee employee : employees) {
            System.out.println(String.format("Информации о сотруднике: имя - %s, должность - %s.", employee.getName(), employee.getJob()));
        }
    }
}

//C:\projects\lessons-job4j\chapter_xml\src\main\resources
//