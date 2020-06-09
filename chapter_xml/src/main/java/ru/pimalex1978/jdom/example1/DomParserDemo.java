package ru.pimalex1978.jdom.example1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URISyntaxException;

/**
 * Ниже приведены шаги, используемые при анализе документа с использованием JDOM Parser.
 * <p>
 * 1. Импорт пакетов, связанных с XML.
 * 2. Создайте SAXBuilder.
 * 3. Создать документ из файла или потока
 * 4. Извлечь корневой элемент
 * 5. Изучить атрибуты
 * 6. Изучите подэлементы
 * <p>
 * Пример из https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
 */

public class DomParserDemo {
    public static void main(String[] args) throws URISyntaxException {



        try {

            //Можно так задавать путь к нашему файлу
            // String path = DomParserDemo.class.getResource("/xml_file5.xml").toURI().getPath();
            //File inputFile = new File(path);

            //А можно и так.
            File inputFile = new File(DomParserDemo.class.getResource("/xml_file5.xml").getFile());

            //Получение фабрики, чтобы после получить билдер документов.
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // Получили из фабрики билдер документов, который парсит XML, создает
            //структуру Document в виде иерархического дерева.
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Запарсили XML, создав структуру Document.
            // Теперь у нас есть доступ ко всем элементам, каким нам нужно.
            Document doc = dBuilder.parse(inputFile);

            //Этот метод normalize() убирает из xml файла не нужные для информации
            // отступы, пробелы.
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("student");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Student roll no : "
                            + eElement.getAttribute("rollno"));
                    System.out.println("First Name : "
                            + eElement
                            .getElementsByTagName("firstname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Last Name : "
                            + eElement
                            .getElementsByTagName("lastname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Nick Name : "
                            + eElement
                            .getElementsByTagName("nickname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Marks : "
                            + eElement
                            .getElementsByTagName("marks")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
