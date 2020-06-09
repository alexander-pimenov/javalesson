package ru.pimalex1978.dom.example4;

import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class DOMExample {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException, URISyntaxException {

        //System.out.println("Введите название файла: ");

        // Ридер для считывания имени файла, имени тега из консоли
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //String file = reader.readLine();

        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер документов, который парсит XML, создает
        //структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Запарсили XML, создав структуру Document.
        // Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        //Document document = builder.parse("c:/projects/lessons-job4j/chapter_xml/src/main/resources/Storage.xml");
        Document document = builder.parse(new File(DOMExample.class
                .getResource("/Storage.xml")
                .toURI().getPath()));
        //Document document = builder.parse("c:/projects/lessons-job4j/chapter_xml/src/main/resources/" + file);

        Element documentElement = document.getDocumentElement();
        //System.out.println(document);

        System.out.println("Введите имя тега: ");

        //Box, Item, Storage
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
        System.out.println("=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=");
//        System.out.println("==========DOM-way-2=========");
//
//        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//        Document document2 = documentBuilder.parse("c:/projects/lessons-job4j/chapter_xml/src/main/resources/Storage.xml");
//        document2.getDocumentElement().normalize();
//
//        //Here comes the root node
//        Element root = document2.getDocumentElement();
//        System.out.println(root.getNodeName());
//
//        //Получить все Box
//        NodeList boxList = document2.getElementsByTagName("Box");
//        NodeList itemList = document2.getElementsByTagName("Item");
//        System.out.println("=====================================");
//
//        visitChildNodes(boxList);
//        visitChildNodes(itemList);


//        Node root = document2.getFirstChild();
//        System.out.println("ROOT: " + root.getNodeName());
//        Element root2 = document.getDocumentElement();
//        System.out.println("ROOT2: " + root2.getNodeName());


//        NodeList children = root.getChildNodes();
//        System.out.println(children.getLength());
//        for (int i = 0; i < children.getLength(); i++) {
//            Node child = children.item(i);
//            //System.out.println("CHILD: " + child.getNodeName());
//
//            System.out.println("");
//            if (child.getNodeType() == Node.ELEMENT_NODE) {
//                Element eElement = (Element) child;
//                System.out.println("id " + eElement.getTagName() + " " + eElement.getAttribute("id"));
//                System.out.println("color " + eElement.getTagName() + " " + eElement.getAttribute("color"));
//
//            }
//
//            if (child.getNodeType() != Node.TEXT_NODE) {
//                System.out.println("CHILD: " + child.getNodeName());
//            }
//        }


//        System.out.println("=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=");
//        System.out.println("==========STAX-way-1=========");
//        XMLInputFactory factory2 = XMLInputFactory.newInstance();
//        XMLStreamReader parser2 = factory2.createXMLStreamReader(new FileInputStream("C:/projects/lessons-job4j/chapter_xml/src/main/resources/Storage.xml"));
//        //XMLStreamReader parser2 = factory2.createXMLStreamReader(Files.newInputStream(Paths.get("Storage.xml")));
//
//        while (parser2.hasNext()) {
//            int event = parser2.next();
//            if (event == XMLStreamConstants.START_ELEMENT) {
//                System.out.println(parser2.getLocalName());
//            } else if (event == XMLStreamConstants.ATTRIBUTE) {
//                System.out.println(parser2.getAttributeCount());
//                System.out.println(parser2.getAttributeLocalName(0));
//            }
//        }

        System.out.println("==========STAX-way-2=========");
        final String fileName3 = "C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\Storage.xml";
        try {
            XMLStreamReader xmlr3 = XMLInputFactory.newInstance()
                    .createXMLStreamReader(new FileInputStream(fileName3));

            int count = 0;
            while (xmlr3.hasNext()) {
                xmlr3.next();
                if (xmlr3.isStartElement()) {
                    String localName = xmlr3.getLocalName();
                    System.out.println(localName);
                } else if (xmlr3.isEndElement()) {
                    System.out.println("/" + xmlr3.getLocalName());
                } else if (xmlr3.hasText() && xmlr3.getText().trim().length() > 0) {
                    System.out.println("   " + xmlr3.getText());
                }
//                else if (xmlr3.isCharacters()) {
//                    System.out.println(xmlr3.getTextCharacters());
//                }
                count++;
            }
            System.out.println("количество проходов по циклу = " + count);
        } catch (
                XMLStreamException ex) {
            ex.printStackTrace();
        }


        System.out.println("==========SAX-way=========");


        final String fileName4 = "C:/projects/lessons-job4j/chapter_xml/src/main/resources/Storage.xml";
        final List<Box> boxes = new ArrayList<>();
        final List<Item> items = new ArrayList<>();
        Box box = new Box();
        Item item = new Item();
        try {
            SAXParserFactory factory1 = SAXParserFactory.newInstance();
            SAXParser saxParser = factory1.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean name = false;

                private String thisElement, lastElementName;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    thisElement = qName;
                    System.out.println(thisElement);
                    if (qName.equalsIgnoreCase("box")) {
                        name = true;
                        Integer idBox = Integer.parseInt(attributes.getValue("id"));

                        boxes.add(new Box(idBox));
                        System.out.println("\tidBox: " + idBox);

                    }
                    if (qName.equalsIgnoreCase("item")) {
                        Integer idItem = Integer.parseInt(attributes.getValue("id"));
                        String colorItem = attributes.getValue("color");

                        items.add(new Item(idItem, colorItem));
                        System.out.println("\tidItem: " + idItem);
                        System.out.println("\tcolorItem: " + colorItem);
                    }
                    //System.out.println("количество атрибутов в этом элементе: " + attributes.getLength());

                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (name) {
                        //System.out.println("Name: " + new String(ch, start, length));
                        //names.add(new String(ch, start, length));
                        name = false;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    //thisElement = qName;
                    System.out.println("/" + thisElement);
                    //очистим элемент
                    thisElement = "";
                }
            };

            saxParser.parse(fileName4, handler);

            boxes.forEach(System.out::println);
            items.forEach(System.out::println);

        } catch (
                Exception ex) {
            ex.printStackTrace();
        }

    }

    //эта функция вызывается рекурсивно
    private static void visitChildNodes(NodeList nList) {
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println("Node Name = " + node.getNodeName()
                        //        + "; Value = " + node.getTextContent()
                );
                //Проверяем все атрибуты
                if (node.hasAttributes()) {
                    //получить имена и значения атрибутов
                    NamedNodeMap nodeMap = node.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++) {
                        Node tempNode = nodeMap.item(i);
                        System.out.println("Attribute name : " + tempNode.getNodeName()
                                + "; Value = " + tempNode.getNodeValue());
                    }
                    if (node.hasChildNodes()) {
                        //У нас больше детей; Давайте посетим их также
                        visitChildNodes(node.getChildNodes());
                    }
                }
            }
        }
    }

    private static void printInfoAboutAllChildNodes(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);

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

