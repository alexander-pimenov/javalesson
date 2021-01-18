package ru.job4j.vacancyparser.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.vacancyparser.model.Vacancy;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class HtmlParser {
    //    private static final Logger LOG = LogManager.getLogger(HtmlParser.class.getName());
    private static final Logger LOG = LoggerFactory.getLogger(HtmlParser.class.getName());

    private Integer page = 1;
    private boolean lastPage = false;
    private Set<Vacancy> vacansies = new LinkedHashSet<>();
    private final String url = "https://www.sql.ru/forum/job-offers";

    /**
     * Метод для первого парсинга с начала года
     *
     * @return set вакансий
     */
    public Set<Vacancy> parse() {
        try {
            Document doc = Jsoup.connect(url + "/" + page).get();
            Elements forumTable = doc.getElementsByClass("forumTable");
            Elements rows = forumTable.first().getElementsByTag("tbody").first().getElementsByTag("tr");
            for (int j = 1; j < rows.size(); j++) {
                Element row = rows.get(j);
                String link, name, dateCreated;
                link = row.getElementsByTag("a").attr("href");
                name = row.child(1).getElementsByTag("a").first().text();
                dateCreated = row.child(5).text();
                String text = null;
                try {
                    Document document = Jsoup.connect(link).get();
                    Elements msgTable = document.getElementsByClass("msgTable");
                    Elements lines = msgTable.first().getElementsByTag("tbody").first().getElementsByTag("tr");
                    text = lines.get(1).child(1).text();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
                if (checkName(link) && checkYear(dateCreated)) {
                    lastPage = true;
                    Vacancy vacancy = new Vacancy(name, text, link);
                    vacansies.add(vacancy);
                }
            }
            page++;
            if (lastPage) {
                lastPage = false;
                parse();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacansies;
    }

    /**
     * Метод для апдейта парсинга. Сохраняет в set только сегодняшние вакансии
     *
     * @return set вакансий
     */
    public Set<Vacancy> updateParse() {
        try {
            Document doc = Jsoup.connect(url + "/" + 1).get();
            Elements forumTable = doc.getElementsByClass("forumTable");
            Elements rows = forumTable.first().getElementsByTag("tbody").first().getElementsByTag("tr");
            for (int j = 1; j < rows.size(); j++) {
                Element row = rows.get(j);
                String link, name, dateCreated;
                link = row.getElementsByTag("a").attr("href");
                name = row.child(1).getElementsByTag("a").first().text();
                dateCreated = row.child(5).text();
                String text = null;
                try {
                    Document document = Jsoup.connect(link).get();
                    Elements msgTable = document.getElementsByClass("msgTable");
                    Elements lines = msgTable.first().getElementsByTag("tbody").first().getElementsByTag("tr");
                    text = lines.get(1).child(1).text();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
                if (checkName(link) && checkToday(dateCreated)) {
                    Vacancy vacancy = new Vacancy(name, text, link);
                    vacansies.add(vacancy);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacansies;
    }

    public Set<Vacancy> getVacansySet() {
        return this.vacansies;
    }

    private boolean checkName(String name) {
        boolean result = false;
        if (name.contains("java")
                && !name.contains("javascript")
                && !name.contains("java script")) {
            result = true;
        }
        return result;
    }

    private boolean checkYear(String date) {
        boolean result = false;
        String[] spl = date.split("\\s");
        if (spl[0].contains("сегодня") || spl[0].contains("вчера")) {
            result = true;
        } else if (spl.length == 4 && spl[2].contains("20")) {
            result = true;
        }
        return result;
    }

    private boolean checkToday(String date) {
        boolean result = false;
        String[] spl = date.split("\\s");
        if (spl[0].contains("сегодня")) {
            result = true;
        }
        return result;
    }
}
