package ru.pimalex1978.basepatterns.structural.proxy.refactoringguru;

/**
 * Видеофайл.
 */
public class Video {
    public String id;
    public String title;
    public String data;

    Video(String id, String title) {
        this.id = id;
        this.title = title;
        this.data = "Random video.";
    }
}
