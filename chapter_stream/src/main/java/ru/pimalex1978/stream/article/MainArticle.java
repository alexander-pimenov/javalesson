package ru.pimalex1978.stream.article;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainArticle {

    public static void main(String[] args) {
        Article article1 = new Article("Обучение Java", "Peterson", List.of("java", "basic code", "education"));
        Article article2 = new Article("Лучшие учебники по Java", "Horstmann", List.of("java"));
        Article article3 = new Article("Kotlin. Что лучше Java  Core или Kotlin", "Shildt", List.of("java", "kotlin", "best code"));
        Article article4 = new Article("Базы SQL. Знакомимся с реляционными базами данных", "Vitas", List.of("sql", "postgresql"));
        Article article5 = new Article("PostgreSQL и Java", "Lafore", List.of("java", "sql", "postgresql"));
        Article article6 = new Article("Изучаем Java.", "Shildt", List.of("java", "java core", "basic code"));
        Article article7 = new Article("Java forever.", "Shildt", List.of("java", "java bean", "best practice"));

        List<Article> articles = Arrays.asList(article1, article2, article3, article4, article5, article6, article7);

        System.out.println("##### Традиционное решение с помощью циклов #####");


        /*Находим первую статью в коллекции, которая имеет тег "Java"*/
        Article firstJavaArticle = getFirstJavaArticle(articles);
        System.out.println(firstJavaArticle);

        /*Находим все совпадающие элементы, а не только первый*/
        List<Article> allJavaArticles = getAllJavaArticles(articles);
        System.out.println(allJavaArticles);

        /*Сгруппируем все статьи на основе автора.*/
        Map<String, List<Article>> mapByAuthor = groupByAuthor(articles);
        System.out.println(mapByAuthor);

        System.out.println("##### Решение с помощью новых фукций Jav 8. Stream API #####");

        /*Находим первую статью в коллекции, которая имеет тег "Java"*/
        Optional<Article> firstArticleStream = getFirstArticleStream(articles);
        System.out.println(firstArticleStream);

        /*Находим все совпадающие элементы, а не только первый*/
        List<Article> allJavaArticlesStream = getAllJavaArticlesStream(articles);
        System.out.println(allJavaArticlesStream);

        /*Сгруппируем все статьи на основе автора.*/
        Map<String, List<Article>> mapByAuthorStream = groupByAuthorStream(articles);
        System.out.println(mapByAuthorStream);

        /*Преобразуем стрим в map*/
        Map<String, Article> collect = articles.stream()
                .collect(Collectors.toMap(Article::getTitle, Function.identity()));
        System.out.println(collect);

        /*Выведем только названия статей используя стрим.*/
        String collectTitles = articles.stream()
                .map(Article::getTitle)
                .collect(Collectors.joining(" || ", "<<Articles: ", ">>"));
        System.out.println(collectTitles);


    }

    /*Метод находит первую статью в коллекции, которая имеет тег "Java". С использованием цикла for.*/
    public static Article getFirstJavaArticle(List<Article> articles) {

        for (Article article : articles) {
            if (article.getTags().contains("Java".toLowerCase())) { //contains
                return article;
            }
        }
        return null;
    }

    /*Метод находит первую статью в коллекции, которая имеет тег "Java". Stream API*/
    public static Optional<Article> getFirstArticleStream(List<Article> articles) {
        return articles.stream()
                .filter(article -> article.getTags().contains("Java".toLowerCase()))
                .findFirst();
    }

    /*Метод возвращающий все совпадающие элементы, а не только первый. С использованием цикла for.*/
    public static List<Article> getAllJavaArticles(List<Article> articles) {
        List<Article> result = new ArrayList<>();

        for (Article article : articles) {
            if (article.getTags().contains("Java".toLowerCase())) {
                result.add(article);
            }
        }
        return result;
    }

    /**
     * Метод возвращающий все совпадающие элементы,
     * а не только первый. Stream API.
     */
    public static List<Article> getAllJavaArticlesStream(List<Article> articles) {
        return articles.stream()
                .filter(article -> article.getTags().contains("Java".toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Метод сгруппировывает все статьи на основе автора.
     * С использованием цикла for.
     */
    public static Map<String, List<Article>> groupByAuthor(List<Article> articles) {

        Map<String, List<Article>> result = new HashMap<>();

        for (Article article : articles) {
            if (result.containsKey(article.getAuthor())) {
                result.get(article.getAuthor()).add(article);
            } else {
                ArrayList<Article> articlesList = new ArrayList<>();
                articlesList.add(article);
                result.put(article.getAuthor(), articlesList);
            }
        }
        return result;
    }

    /*Метод сгруппировывает все статьи на основе автора. Stream API.*/
    public static Map<String, List<Article>> groupByAuthorStream(List<Article> articles) {
        return articles.stream()
                .collect(Collectors.groupingBy(Article::getAuthor));
    }
}
