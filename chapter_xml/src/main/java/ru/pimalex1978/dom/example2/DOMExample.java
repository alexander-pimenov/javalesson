package ru.pimalex1978.dom.example2;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * https://www.codeflow.site/
 * Задача №2 – вводится с консоли имя элемента, про который нужно вывести
 * информацию об всех элементах внутри его и их атрибутах со следующего
 * XML файла: xml_file3.xml
 * <p>
 * Тут есть три возможных сценария: root, mysql, oracle.
 * <p>
 * Мы должны получить элемент по его имени, которое считаем, а после пройти
 * по всем дочерним узлам. Для этого нужно пройтись по всем дочерним узлам
 * всех дочерних узлов, которые являются элементами.
 */

public class DOMExample {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, URISyntaxException {

        System.out.println("Введите имя тега: ");

        // Ридер для считывания имени тега из консоли
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер документов, который парсит XML, создает
        //структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Запарсили XML, создав структуру Document.
        //Получим путь к нашему xml файлу. ОБЯЗАТЕЛЬНО ставить - "/"
        URL resource = DOMExample.class.getResource("/xml_file3.xml");
        File toFile = Paths.get(resource.toURI()).toFile();
        // Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        //Document document = builder.parse(new File("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\xml_file3.xml"));
        Document document = builder.parse(toFile);

        // Считывание имени тега из консоли для поиска его в файле.
        String element = reader.readLine();

        // Получение списка элементов, однако для удобства будем рассматривать только первое совпадение в документе.
        // Так же заметьте, что мы ищем элемент внутри документа, а не рут элемента.
        // Это сделано для того, чтобы рут элемент тоже искался.
        NodeList matchedElementsList = document.getElementsByTagName(element);

        // Даже если элемента нет, всегда будет возвращаться список, просто он будет пустым.
        // Потому, чтобы утверждать, что элемента нет в файле, достаточно проверить размер списка.
        if (matchedElementsList.getLength() == 0) {
            System.out.println("Тег " + element + " не был найден в файле.");
        } else {
            // Получение первого элемента.
            Node foundedElement = matchedElementsList.item(0);

            System.out.println("Элемент был найден!");

            // Если есть данные внутри, вызов метода для вывода всей информации
            if (foundedElement.hasChildNodes())
                printInfoAboutAllChildNodes(foundedElement.getChildNodes());
        }
    }


    /**
     * Рекурсивный метод, который будет выводить информацию про все узлы внутри
     * всех узлов, которые пришли параметром, пока не будут перебраны все узлы.
     *
     * @param list Список узлов.
     */

    private static void printInfoAboutAllChildNodes(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);

            // У элементов есть два вида узлов - другие элементы или текстовая информация.
            // Потому нужно разбираться две ситуации отдельно.
            if (node.getNodeType() == Node.TEXT_NODE) {
                // Фильтрация информации, так как пробелы и переносы строчек нам не нужны. Это не информация.
                String textInformation = node.getNodeValue().replace("\n", "").trim();

                if (!textInformation.isEmpty())
                    System.out.println("Внутри элемента найден текст: " + node.getNodeValue());
            }
            // Если это не текст, а элемент, то обрабатываем его как элемент.
            else {
                System.out.println("Найден элемент: <" + node.getNodeName() + ">, его атрибуты:");

                // Получение атрибутов
                NamedNodeMap attributes = node.getAttributes();

                // Вывод информации про все атрибуты
                for (int k = 0; k < attributes.getLength(); k++)
                    System.out.println("Имя атрибута: " + attributes.item(k).getNodeName() + ", его значение: " + attributes.item(k).getNodeValue());
            }

            // Если у данного элемента еще остались узлы, то вывести всю информацию про все его узлы.
            if (node.hasChildNodes())
                printInfoAboutAllChildNodes(node.getChildNodes());
        }
    }
}
