package ru.pimalex1978.dom.example6;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DOMExample {

    private static List<Box> boxes = new ArrayList<>();
    private static List<Item> items = new ArrayList<>();

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        parseXML(DOMExample.class.getResource("/Storage.xml").getFile());

        boxes.forEach(System.out::println);
        items.forEach(System.out::println);

    }

    public static void parseXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(fileName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        LinkedList<Node> els = new LinkedList<>(children(doc.getDocumentElement()));
        while (!els.isEmpty()) {
            Node node = els.poll();
            Element e = (Element) node;
            //int parentId = -1;
            Integer parentId = null;
            if (node.getParentNode() != null) {
                Element parent = (Element) node.getParentNode();
                if (parent.hasAttribute("id")) {
                    parentId = Integer.parseInt(parent.getAttribute("id"));
                }
            }
            if ("Box".equals(node.getNodeName())) {
                boxes.add(new Box(Integer.parseInt(e.getAttribute("id")), parentId));
            } else if ("Item".equals(node.getNodeName())) {
                items.add(new Item(Integer.parseInt(e.getAttribute("id")), parentId, e.getAttribute("color")));
            }
            els.addAll(children(node));
        }
    }

    public static List<Node> children(Node nodes) {
        LinkedList<Node> els = new LinkedList<>();
        NodeList list = nodes.getChildNodes();
        for (int index = 0; index < list.getLength(); index++) {
            Node node = list.item(index);
            if ("Box".equals(node.getNodeName()) || "Item".equals(node.getNodeName())) {
                els.add(node);
            }
        }
        return els;
    }
}


//    public void parseXML(String fileName) {
//
//
//        try {
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser saxParser = factory.newSAXParser();
//
//            DefaultHandler handler = new DefaultHandler() {
//                boolean check = false;
//                private String thisElement;
//
//                @Override
//                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//                    thisElement = qName;
//                    check = true;
//                    System.out.println(thisElement);
//                    if (qName.equalsIgnoreCase("box")) {
//                        Integer idBox = Integer.parseInt(attributes.getValue("id"));
//
//                        boxes.add(new Box(idBox));
//                        System.out.println("\tidBox: " + idBox);
//
//                    }
//                    if (qName.equalsIgnoreCase("item")) {
//                        Integer idItem = Integer.parseInt(attributes.getValue("id"));
//                        String colorItem = attributes.getValue("color");
//
//                        items.add(new Item(idItem, colorItem));
//                        System.out.println("\tidItem: " + idItem);
//                        System.out.println("\tcolorItem: " + colorItem);
//                    }
//                }
//
//                /*Можем использовать этот метод, если между тегами будет текст.*/
//                @Override
//                public void characters(char[] ch, int start, int length) throws SAXException {
//                    if (check) {
//                        //System.out.println("Text: " + new String(ch, start, length));
//                        //boxes.add(new String(ch, start, length));
//                        //items.add(new String(ch, start, length));
//                        check = false;
//                    }
//                }
//
//                @Override
//                public void endElement(String uri, String localName, String qName) throws SAXException {
//                    System.out.println("/" + thisElement);
//                    //очистим элемент
//                    thisElement = "";
//                }
//            };
//
//            //Парсим наш XML-файл
//            saxParser.parse(fileName, handler);
//
//            //Для просмотра списков полученных boxes и items
//            // boxes.forEach(System.out::println);
//            //items.forEach(System.out::println);
//
//        } catch (
//                Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }


