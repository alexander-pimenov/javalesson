package ru.pimalex1978.jdom.example1;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Здесь рассматриваем Парсер JDOM для обновления или изменения существующего XML-файла xml_file7.xml
 * https://www.codeflow.site/ru/article/java__how-to-modify-xml-file-in-java-jdom
 *
 * Подключил зависимость в pom.xml
 * 		https://mvnrepository.com/artifact/org.jdom/jdom
 * 		<dependency>
 * 			<groupId>org.jdom</groupId>
 * 			<artifactId>jdom</artifactId>
 * 			<version>2.0.2</version>
 * 		</dependency>
 *
 * 	для работы с org.jdom2.*
 *
 * 	Имеели до изменения такое xml файл:
 * 	<company>
 *     <staff id="1">
 *         <firstname>yong</firstname>
 *         <lastname>mook kim</lastname>
 *         <nickname>mkyong</nickname>
 *         <salary>5000</salary>
 *     </staff>
 * </company>
 */



public class ModifyXMLFile {
    public static void main(String[] args) {

        try {

            String path = ModifyXMLFile.class.getResource("/xml_file7.xml").toURI().getPath();

            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path);

            Document doc = (Document) builder.build(xmlFile);
            Element rootNode = doc.getRootElement();

            //update staff id attribute
            Element staff = rootNode.getChild("staff");
            staff.getAttribute("id").setValue("2");

            //add new age element
            //при каждом новом вызове будет добавляться новый элемент <age>
            Element age = new Element("age").setText("50");
            staff.addContent(age);

            //update salary value
            staff.getChild("salary").setText("19500");

            //remove firstname element
            staff.removeChild("firstname");

            XMLOutputter xmlOutput = new XMLOutputter();

            //display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\xml_file7.xml"));
            //Эти нижние способы почему то не сработали.
            //Почему то напрямую в память по полному пути изменения происходят,
            //а с помощью других способов нет.
            //String path2 = ModifyXMLFile.class.getResource("/xml_file7.xml").toURI().getPath();
            //URL resource = ModifyXMLFile.class.getResource("/xml_file7.xml");
            //File file = Paths.get(ModifyXMLFile.class.getResource("/xml_file7.xml").toURI()).toFile();
            //xmlOutput.output(doc, new FileWriter(file));
            //ClassLoader classLoader = ModifyXMLFile.class.getClassLoader();
            //String path1 = classLoader.getResource("xml_file7.xml").getPath();
            //xmlOutput.output(doc, new FileWriter(path1));

            //xmlOutput.output(doc, System.out);

            System.out.println("File updated!");
        } catch (IOException | URISyntaxException io) {
            io.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }
}
