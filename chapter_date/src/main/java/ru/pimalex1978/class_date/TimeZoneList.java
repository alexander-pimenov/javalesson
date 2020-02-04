package ru.pimalex1978.class_date;

import java.util.Arrays;
import java.util.TimeZone;

/**
 * Рассмотрим пример, в котором на консоль последовательно выводятся:
 * - временная зона по умолчанию;
 * - список всех возможных временных зон;
 * - список временных зон, которые совпадают с временной зоной Москвы.
 * <p>
 * Код программы несложный.
 * Метод align выполняет выравнивание для отображения смещения по времени
 * TimeZone от среднего времени по Гринвичу GMT — времени меридиана,
 * проходящего через прежнее место расположения Гринвичской обсерватории
 * около Лондона.
 * Метод drawTimeZoneParam отображает параметры TimeZone.
 * В конструкторе класса TimeZoneList сначала определяется текущая TimeZone,
 * после чего выводится список возможных TimeZone.
 * И в заключении, используя метод getAvailableIDs получаем список TimeZone,
 * у которых смещение по времени соответствует текущей зоне.
 */

public class TimeZoneList {
    private final int hour = 1000 * 60 * 60;
    private final String TEMPL_TZ = "%s (%s)";
    private final String TEMPL_OFFS = "hour offset=(%d)";

    private String aling(String str, int len) {
        if (len - str.length() > 0) {
            char[] buf = new char[len - str.length()];
            Arrays.fill(buf, ' ');
            return str + new String(buf);
        } else
            return str.substring(0, len);
    }

    private void drawTimeZoneParam(final TimeZone tz) {
        String line = null;
        line = String.format(TEMPL_TZ, tz.getID(), tz.getDisplayName());
        line = aling(line, 64);
        line += String.format(TEMPL_OFFS, tz.getRawOffset() / hour);
        System.out.println(line);
    }

    public TimeZoneList() {
        TimeZone tz = TimeZone.getDefault();
        int rawOffset = tz.getRawOffset();
        System.out.println("Текущая TimeZone : "
                + tz.getID() + " ("
                + tz.getDisplayName()
                + ")\n");
        // Display all available TimeZones
        System.out.println("Доступные TimeZone\n");
        String[] timezones = TimeZone.getAvailableIDs();

        for (int cnt = 0; cnt < timezones.length; cnt++) {
            tz = TimeZone.getTimeZone(timezones[cnt]);
            drawTimeZoneParam(tz);
        }
        // All available TimeZones same as for Moscow
        System.out.println(
                "Список TimeZone, соответствующие текущей");
        timezones = TimeZone.getAvailableIDs(rawOffset);
        for (int cnt = 0; cnt < timezones.length; cnt++) {
            tz = TimeZone.getTimeZone(timezones[cnt]);
            drawTimeZoneParam(tz);
        }
    }

    public static void main(String[] args) {
        new TimeZoneList();
        System.exit(0);
    }
}
