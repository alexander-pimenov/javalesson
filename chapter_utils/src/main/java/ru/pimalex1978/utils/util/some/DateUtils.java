package ru.pimalex1978.utils.util.some;

import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

@UtilityClass
public class DateUtils {

    private static final String MINIMAL_VALID_DATE = "1945-01-01 00:00:00";
    private static final String MAXIMAL_VALID_DATE = "2090-01-01 00:00:00";

    /**
     * Compare two dates with 2021-11-11T11:11:11.000Z and 2021-11-11T11:11:11Z formats
     */
    public static boolean isStringDatesEqual(String firstDate, String secondDate) {
        return firstDate == null
                ? secondDate == null
                : secondDate != null && Instant.parse(firstDate).equals(Instant.parse(secondDate));
    }

    public static Timestamp getEndOfYear() {
        LocalDate today = LocalDate.now();
        LocalDateTime lastDayOfYear =
                today.with(TemporalAdjusters.lastDayOfYear()).atStartOfDay().with(LocalTime.MAX).withNano(0);
        return Timestamp.valueOf(lastDayOfYear);
    }

    public static Timestamp getStartOfYear() {
        LocalDate today = LocalDate.now();
        LocalDateTime firstDayOfYear = today.with(TemporalAdjusters.firstDayOfYear()).atStartOfDay().withNano(0);
        return Timestamp.valueOf(firstDayOfYear);
    }

    /**
     * Получает последнее числа следующего месяца
     */
    public static Timestamp getLastDayOfNextMonth() {
        LocalDate today = LocalDate.now();
        LocalDateTime lastDateOfNextMonth =
                today.with(TemporalAdjusters.lastDayOfMonth()).plusMonths(1).atStartOfDay().with(LocalTime.MAX).withNano(0);
        return Timestamp.valueOf(lastDateOfNextMonth);
    }

    /**
     * Получает первое числа следующего месяца
     */
    public static Timestamp getFirstDayOfNextMonth() {
        LocalDate today = LocalDate.now();
        LocalDateTime firstDateOfNextMonth = today.with(TemporalAdjusters.firstDayOfMonth()).plusMonths(1).atStartOfDay().withNano(0);
        return Timestamp.valueOf(firstDateOfNextMonth);
    }

    public static String getMonthFromDate(Timestamp timestamp) {
        if (timestamp != null) {
            return new SimpleDateFormat("M").format(timestamp);
        } else {
            return "";
        }
    }

    /**
     * Получает строковое представление года
     */
    public static String getYearFromSource(Timestamp timestamp) {
        return timestamp != null ? new SimpleDateFormat("yyyy").format(timestamp) : "";
    }

    /**
     * Получает последние две цифры из строквого представления числа года
     */
    public static String getLastTwoDigitsFromYear(String year) {
        return (year.substring(year.length() - 2));
    }

    public static Timestamp getCorrectTimestamp(Object data) {
        Timestamp ts = null;
        if (data instanceof Timestamp) {
            ts = (Timestamp) data;
            if (((Timestamp) data).before(Timestamp.valueOf(MINIMAL_VALID_DATE))
                    || ((Timestamp) data).after(Timestamp.valueOf(MAXIMAL_VALID_DATE))) {
                return null;
            }
        }
        return ts;
    }
}
