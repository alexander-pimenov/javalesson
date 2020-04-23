package ru.pimalex1978;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GetDocument {
    private static List<Valute> valutesList = new ArrayList<>();
    private static Map<String, Valute> valutesMap = new HashMap<>();

    public static void main(String[] args) throws IOException, ParseException {

        /*Различные примеры применения парсинга с помощью Jsoup*/

//        getDocumentFromURL();
//
//        getDocumentFromFile();
//
//        getDocumentFromString();
//
//        getBodyFragment();
//
//         readHtmlForm();
//
//        getAllLinks();
//
//        queryLinks();
//
//        selectorDemo();

        getCrbCourse();

        //в цикле вывели все валюты из списка
        for (Valute valute2 : valutesList) {
            System.out.println(valute2.getDate() + " " + valute2.getNominal() + " " + valute2.getName() + " = "
                    + valute2.getValue() + " руб.");
            System.out.println("-----------------------------------------");
        }

        System.out.println("============================================");

        //достаем валюту по ключу из мапы
        Valute valuteAUD = valutesMap.get("AUD");
        LocalDate dateAUD = valuteAUD.getDate();
        String nominalAUD = valuteAUD.getNominal();
        String nameAUD = valuteAUD.getName();
        BigDecimal valueAUD = valuteAUD.getValue();
        System.out.println(dateAUD + " " + nominalAUD + " " + nameAUD + " = " + valueAUD + " руб.");
        System.out.println("============================================");

        Valute valuteUSD = valutesMap.get("USD");
        LocalDate dateUSD = valuteUSD.getDate();
        String nominalUSD = valuteUSD.getNominal();
        String nameUSD = valuteUSD.getName();
        BigDecimal valueUSD = valuteUSD.getValue();
        System.out.println(dateUSD + " " + nominalUSD + " " + nameUSD + " = " + valueUSD + " руб.");
        System.out.println("============================================");

        //выводим валюту из списка по индексу
//        Valute valute10 = valutesList.get(10);
//        System.out.println(valute10);

    }

    //Метод распарсивания страницы курсов валют от ЦРБ.
    //на странице представлен XML файл. Распарсили его благодаря тегам.
    private static void getCrbCourse() throws IOException {

//        1) Получение Document при обращении к URL через connect()
//        String url = "http://www.cbr.ru/scripts/XML_daily.asp";
//        Document doc = Jsoup.connect(url).get();

//        2) Получение Document при обращении к URL через parse()
        Document doc = Jsoup.parse(new URL("http://www.cbr.ru/scripts/XML_daily.asp"), 2000);

        Elements valCurs = doc.getElementsByTag("ValCurs");
        Elements valutes = doc.getElementsByTag("Valute");

        //Проходим циклом по всем валютам находящимся в Elements valutes
        //и создаем объекты этих валют, заполняем ими список и мапу
        for (Element element : valutes) {
            String date = valCurs.attr("Date");
            //значение даты в String 24.04.2020 -> переводим в LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
            LocalDate dateTime = LocalDate.parse(date, formatter);
            String id = element.attr("id");
            String numCode = element.getElementsByTag("NumCode").text();
            String charCode = element.getElementsByTag("CharCode").text();
            String nominal = element.getElementsByTag("Nominal").text();
            String name = element.getElementsByTag("Name").text();
            String value = element.getElementsByTag("Value").text();
            Valute valuteItem = new Valute(dateTime, id, numCode, charCode, nominal, name, new BigDecimal(value.replace(',', '.')));

            valutesList.add(valuteItem);

            valutesMap.put(charCode, valuteItem);
        }
    }


    private static void getDocumentFromURL() throws IOException {
        //Создаем Document из URL
        Document doc = Jsoup.connect("http://eclipse.org").get();
        String location = doc.location();
        String title = doc.title();

        System.out.println("Location : " + location);
        System.out.println("Title : " + title);
    }

    private static void getDocumentFromFile() throws IOException {
        //Создаем Document из File
        File htmlFile = new File("c:/test/index.html");
        Document doc = Jsoup.parse(htmlFile, "UTF-8");
        String title = doc.title();
        System.out.println("Title from file : " + title);
    }

    private static void getDocumentFromString() {
        //Создаем Document из Строки
        String htmlString = "<html><head><title>Simple Page</title></head>" +
                "<body>Hello</body></html>";
        Document doc = Jsoup.parse(htmlString);
        String title = doc.title();
        System.out.println("Title from String : " + title);
        System.out.println("Content:\n");
        System.out.println(doc.toString());
    }

    private static void getBodyFragment() {
        //Возмем из Документа часть его Body
        String htmlFragment = "<h1>Hi you!</h1><p>What is this?</p>";
        Document doc = Jsoup.parseBodyFragment(htmlFragment);
        String fullHtml = doc.html();
        System.out.println(fullHtml);
    }


    private static void readHtmlForm() throws IOException {
        //Читаем данные из HTML Формы
        Document doc = Jsoup.parse(new File("c:/test/register.html"), "utf-8");
        Element form = doc.getElementById("registerForm");
        System.out.println("Form action = " + form.attr("action"));
        System.out.println("Form Method = " + form.attr("method"));
        Elements inputElements = form.getElementsByTag("input");

        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            System.out.println(key + " = " + value);
        }
    }

    private static void getAllLinks() throws IOException {
        //
        Document doc = Jsoup.connect("https://o7planning.org").get();

        //Elements расширяет ArrayList <Element>
        Elements aElements = doc.getElementsByTag("a");

        for (Element aElement : aElements) {
            String href = aElement.attr("href");
            String text = aElement.text();
            System.out.println(text);
            System.out.println("\t" + href);
        }
    }

    private static void queryLinks() throws IOException {
        /*
         * Вы хотите найти или воспользоваться элементами используя
         * синтаксисом похожим на CSS или jQuery?
         * JSoup предоставляет вам некоторые методы для того, чтобы сделать это:
         * Element.select(String selector)
         * Elements.select(String selector)
         *
         * Элементы JSoup поддерживают синтаксис похожий на CSS (или JQuery)
         * помогающий вам найти соответствующие элементы. Такие поддержки очень
         * сильны. Методы выбора есть наготове в классе Document, Element или  Elements.
         * */

        Connection conn = Jsoup.connect("https://o7planning.org");

        Document doc = conn.get();

        // a with href
        Elements links = doc.select("a[href]");

        // img with src ending .png
        Elements pngs = doc.select("img[src$=.png]");

        // div with class=masthead
        Element masthead = doc.select("div.masthead").first();

        // direct a after h3
        Elements resultLinks = doc.select("h3.r > a");

        // Query <a> elements, href contain /document/
        String cssQuery = "a[href*=/document/]";
        Elements elements = doc.select(cssQuery);

        Iterator<Element> iterator = elements.iterator();

        while (iterator.hasNext()) {
            Element e = iterator.next();
            System.out.println(e.attr("href"));
        }
    }

    private static void selectorDemo() throws IOException {
        File htmlFile = new File("c:/test/document.html");
        Document doc = Jsoup.parse(htmlFile, "UTF-8");

        // First <div> element has class ="related-container"
        Element div = doc.select("div.related-container").first();

        // List the <h3>, the direct child elements of the current element.
        Elements h3Elements = div.select("> h3");

        // Get first <h3> element
        Element h3 = h3Elements.first();

        System.out.println(h3.text());

        // List <a> elements, is a descendant of the current element
        Elements aElements = div.select("a");

        // Query the current element list.
        // The element that href contains 'installing'.
        Elements aEclipses = aElements.select("[href*=Installing]");

        Iterator<Element> iterator = aEclipses.iterator();

        while (iterator.hasNext()) {
            Element a = iterator.next();
            System.out.println("Document: " + a.text());
        }
    }
}

