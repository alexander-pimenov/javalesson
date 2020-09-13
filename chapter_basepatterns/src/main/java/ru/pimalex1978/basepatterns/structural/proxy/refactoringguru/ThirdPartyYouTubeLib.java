package ru.pimalex1978.basepatterns.structural.proxy.refactoringguru;

import java.util.HashMap;

/**
 * Интерфейс удалённого сервиса.
 */
public interface ThirdPartyYouTubeLib {
    HashMap<String, Video> popularVideos();

    Video getVideo(String videoId);
}
