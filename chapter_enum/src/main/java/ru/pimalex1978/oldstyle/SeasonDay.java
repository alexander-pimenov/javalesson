package ru.pimalex1978.oldstyle;

public class SeasonDay {
    public static final int WINTER = 0;
    public static final int SPRING = 1;
    public static final int SUMMER = 2;
    public static final int AUTUMN = 3;

    private int season;
    private int day;

    public SeasonDay(int season, int day) {
        this.season = season;
        this.day = day;
    }
}
