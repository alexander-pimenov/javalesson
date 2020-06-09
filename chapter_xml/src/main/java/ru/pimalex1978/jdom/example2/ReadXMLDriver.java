package ru.pimalex1978.jdom.example2;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

public class ReadXMLDriver {
    public static void main(String[] args) throws JDOMException, IOException, URISyntaxException {
        ReadXMLDriver.readXMLFile();
    }

    public static void readXMLFile() throws JDOMException, IOException, URISyntaxException {
        Element topicElement = null;
        Element tutorialElement = null;
        Element channelElement = ((Document) (new SAXBuilder())
                .build(new File(ReadXMLDriver.class.getResource("/channel.xml")
                        .toURI().getPath())))
                .getRootElement();
        System.out.println("<" + channelElement.getName() + ">");
        List<Element> topicsList = channelElement.getChildren("topic");
        for (int i = 0; i < topicsList.size(); i++) {
            topicElement = (Element) topicsList.get(i);
            System.out.println("\t<" + topicElement.getName() + ">");
            System.out.println("\t\t<name>" + topicElement.getChildText("name") + "</name>");
            Iterator<Element> tutorialsIterator = topicElement.getChildren("tutorial").iterator();
            while (tutorialsIterator.hasNext()) {
                tutorialElement = (Element) tutorialsIterator.next();
                System.out.println("\t\t<" + tutorialElement.getName() + ">"
                        + tutorialElement.getText()
                        + "</" + tutorialElement.getName() + ">");
            }
            System.out.println("\t</" + topicElement.getName() + ">");
        }
        System.out.println("</" + channelElement.getName() + ">");
    }
}
