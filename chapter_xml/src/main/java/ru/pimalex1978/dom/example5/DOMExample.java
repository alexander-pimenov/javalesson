package ru.pimalex1978.dom.example5;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Для парсинга XML файлов в какую-то структуру,
 * на сегодня существует по сути одна унифицированная структура,
 * которая называется Document Object Model (DOM).
 * DOM представляет собой набор интерфейсов (и их реализаций), которые
 * являются специализированными объектами для хранения “узлов”
 * (node) XML-документа. По сути каждый тэг — это “узел” (нода — я буду
 * использовать этот термин, т.к. очень привык). Информация внутри тэга —
 * тоже нода. По сути любой разобранный XML — это набор элементов типа
 * Node и еще более специализированных, построенных в дерево.
 * Почему дерево ? Да потому что у каждой ноды может быть список дочерних
 * нод и у каждой из них тоже может быть “детки”. Так и строится дерево.
 * <p>
 * Самое главное преимущество DOM (на мой взгляд) заключается в возможности
 * редактировать данные — ведь по сути это деревообразная структура, состоящая
 * из унифицированных объектов, она загружена полностью в память и мы можем туда
 * добавлять новые элементы, исправлять или удалять существующие. Также можно
 * осуществлять поиск и делать выборку. Это дает хорошие возможности для решения
 * конкретных задач при работе с данными.
 */

public class DOMExample {
    public static void main(String[] args) {
        try {
            // Для того, чтобы загрузить структуру мы должны создать объект класса DocumentBuilder.
            // Создается построитель документа.
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // Загружаем текстовый XML-файл и разбираем его, получая объект Document.
            // Создается дерево DOM документа из файла.
            // Это и есть объектное представление всей информации внутри XML — наше дерево.
            Document document = documentBuilder.parse(new File("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\BookCatalog.xml"));

            // Дальше мы начинаем «обход» нашего дерева, используя методы, которые предоставляют
            // стандартные объекты DOM — Node, NodeList.
            // Класс Node предназначен для любого элемента XML — текст или тэг или атрибут. Т.е. все в XML есть Node.
            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("List of books:");
            System.out.println();
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                // Если нода не текст, то это книга - заходим внутрь
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for (int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);
                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println("==================>>>>>");
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
