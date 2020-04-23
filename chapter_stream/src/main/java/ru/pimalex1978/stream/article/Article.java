package ru.pimalex1978.stream.article;

import java.util.List;

/**
 * Здесь мы работем со статьями.
 * Статья имеет заголовок, имя автора и несколько тегов.
 */

public class Article {
    private final String title;
    private final String author;
    private final List<String> tags;

    Article(String title, String author, List<String> tags) {
        this.title = title;
        this.author = author;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", tags=" + tags +
                '}';
    }
}
