package ru.pimalex1978.stax;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * В версии Java SE 6 появился еще один вариант разбора XML —
 * Streaming API for XML (StAX).
 * У этой технологии я хотел бы выделить два важных момента:
 * <p>
 * 1. В отличии от SAX, который вызывает ваш обработчик тогда,
 * когда считает нужным, в StAX вы сами управляете перемещением по тэгам
 * 2. StAX позволяет не только читать, но и писать
 * <p>
 * Давайте не будем сильно заниматься копанием в особенностях StAX,
 * а посмотрим пример, который иллюстрирует базовые основы работы StAX при чтении файла.
 */

public class StaxExample {
    public static void main(String[] args) throws URISyntaxException {
        final String fileName = "C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\BookCatalog.xml";

        /*
         * Как видите, все достаточно просто — вы создаете объект класса XMLStreamReader,
         * который позволяет вам перемещаться последовательно по всем элементам XML
         * и проверять, что за элемент вы нашли. У класса XMLStreamReader есть ряд
         * методов, которые позволяют получить доступ к свойствам элемента XML
         * — имя, текст, атрибуты. Что с этой информацией делать — опять же зависит
         * от вашей задачи.
         * Самый главный вопрос — а зачем все это надо ? Ответ такой же, как и для SAX
         * — обработка больших объемов данных.
         */
        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(fileName, new FileInputStream(fileName));

            while (xmlr.hasNext()) {
                xmlr.next();
                if (xmlr.isStartElement()) {
                    System.out.println(xmlr.getLocalName());
                } else if (xmlr.isEndElement()) {
                    System.out.println("/" + xmlr.getLocalName());
                } else if (xmlr.hasText() && xmlr.getText().trim().length() > 0) {
                    System.out.println("   " + xmlr.getText());
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }

        System.out.println("===========Next file============");
        final String fileName2 = "C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\Phonebook.xml";
        try {
            XMLStreamReader xmlr2 = XMLInputFactory.newInstance().createXMLStreamReader(fileName2, new FileInputStream(fileName2));

            while (xmlr2.hasNext()) {
                xmlr2.next();
                if (xmlr2.isStartElement()) {
                    System.out.println(xmlr2.getLocalName());
                } else if (xmlr2.isEndElement()) {
                    System.out.println("/" + xmlr2.getLocalName());
                } else if (xmlr2.hasText() && xmlr2.getText().trim().length() > 0) {
                    System.out.println("   " + xmlr2.getText());
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }

        System.out.println("===========Next file============");
        //final String fileName3 = "C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\Storage.xml";
        String fileName3 = StaxExample.class.getResource("/Storage.xml").getPath();
        try {
            XMLStreamReader xmlr3 = XMLInputFactory.newInstance().createXMLStreamReader(fileName3, new FileInputStream(fileName3));

            while (xmlr3.hasNext()) {
                xmlr3.next();
                if (xmlr3.isStartElement()) {
                    System.out.println(xmlr3.getLocalName());
                } else if (xmlr3.isEndElement()) {
                    System.out.println("/" + xmlr3.getLocalName());
                } else if (xmlr3.hasText() && xmlr3.getText().trim().length() > 0) {
                    System.out.println("   " + xmlr3.getText());
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
    }
}

