package ru.pimalex1978.dom.example3;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
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
 * Задача №3 – из следующего XML файла xml_file4.xml, где сохранена информация про студентов,
 * профессоров и сотрудников, нужно считать информацию и вывести её в консоль.
 * <p>
 * Задача довольно простая, однако интересная.
 * Для начала, нам нужно создать 4 класса:
 * сотрудника, профессора и студента, а так же общий абстрактный класс Human,
 * чтобы вынести переменную name из каждого класса под общий знаменатель.
 * <p>
 * Когда наши классы готовы, нам достаточно написать код для получения всех
 * элементов student, professor и member, а потом достать их атрибуты.
 * <p>
 * Для хранения мы будем использовать коллекцию, которая будет хранить
 * объекты общего для всех родительского класса – Human.
 * <p>
 * Нам достаточно только названия элемента, чтобы получить вообще все эти
 * элементы из документа. Это значительно упрощает процесс поиска нужной информации.
 */
public class DOMExample {

    //Коллекция для хранения всех людей.
    private static List<Human> humans = new ArrayList<>();

    //Константы для элементов
    private static final String PROFESSOR = "professor";
    private static final String MEMBER = "member";
    private static final String STUDENT = "student";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, URISyntaxException {
        //Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер документов, который парсит XML, создает
        //структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Запарсили XML, создав структуру Document.
        // Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        //Получим путь к нашему xml файлу.
        //Document document = builder.parse(new File("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\xml_file4.xml"));
        Document document = builder.parse(new File(DOMExample.class
                .getResource("/xml_file4.xml")
                .toURI().getPath()));

        // Получение информации про каждый элемент отдельно
        collectInformation(document, PROFESSOR);
        collectInformation(document, MEMBER);
        collectInformation(document, STUDENT);

        // Вывод информации
        humans.forEach(System.out::println);
    }

    /**
     * Метод ищет информацию про теги по имени element и вносит всю информацию в коллекцию humans.
     *
     * @param document Документ, в котором будем искать элементы.
     * @param element  Имя элемента, теги которого нужно найти. Должна быть одна из констант, которые определяются выше.
     */
    private static void collectInformation(Document document, final String element) {
        // Получение всех элементов по имени тега.
        NodeList elements = document.getElementsByTagName(element);

        // Перебор всех найденных элементов
        for (int i = 0; i < elements.getLength(); i++) {
            // Получение всех атрибутов элемента
            NamedNodeMap attributes = elements.item(i).getAttributes();
            String name = attributes.getNamedItem("name").getNodeValue();

            // В зависимости от типа элемента, нам нужно собрать свою дополнительну информацию
            // про каждый подкласс, а после добавить нужные образцы в коллекцию.
            switch (element) {
                case PROFESSOR: {
                    String experience = attributes.getNamedItem("experience").getNodeValue();
                    String discipline = attributes.getNamedItem("discipline").getNodeValue();

                    humans.add(new Professor(name, experience, discipline));
                }
                break;
                case STUDENT: {
                    String course = attributes.getNamedItem("course").getNodeValue();
                    String specialization = attributes.getNamedItem("specialization").getNodeValue();

                    humans.add(new Student(name, course, specialization));
                }
                break;
                case MEMBER: {
                    String position = attributes.getNamedItem("position").getNodeValue();

                    humans.add(new Member(name, position));
                }
                break;
            }
        }
    }
}
