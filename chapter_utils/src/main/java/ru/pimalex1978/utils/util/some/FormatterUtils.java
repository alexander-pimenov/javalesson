package ru.pimalex1978.utils.util.some;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * <p>Утилитный класс для форматирования в строку и из строки в разные базовые форматы.
 * <p>
 * Created on 20.10.2022
 *
 * @author Kurskiy Alexey
 */
@Slf4j
@UtilityClass
public class FormatterUtils {
    public static final Pattern URL_PATTERN = Pattern.compile("\\b(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    public static final Pattern VERSION_PATTERN = Pattern.compile("^v(\\d_){2}\\d$");
    public static final Pattern BOOLEAN_PATTERN = Pattern.compile("^(true|false)$", Pattern.CASE_INSENSITIVE);
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");

    public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    public static final SimpleDateFormat DATE_TIME_FORMAT_WITHOUT_SEC = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static final SimpleDateFormat ONLY_TIME_FORMAT = new SimpleDateFormat("HH:mm");

    public static final DateTimeFormatter FORMATTER_WITH_T_LITERAL_AND_OFFSET_3SMALL_XXX = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 6, true)
            .appendPattern("xxx")
            .toFormatter();

    public static final DateTimeFormatter FORMATTER_WITH_T_LITERAL_AND_OFFSET_XXX = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 3, true)
            .appendPattern("XXX")
            .toFormatter();

    public static final DateTimeFormatter FORMATTER_WITH_T_LITERAL_AND_OFFSET_0_6_XXX = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 6, true)
            .appendPattern("XXX")
            .toFormatter();

    public static final DateTimeFormatter ISO_INSTANT_WITH_THREE_MILLIS = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 3, true)
            .appendPattern("'Z'")
            .toFormatter();

    public static final DateTimeFormatter ISO_INSTANT_WITH_SIX_MILLIS = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 6, true)
            .appendPattern("'Z'")
            .toFormatter();

    private static final long K = 1024;
    private static final long M = K * K;
    private static final long G = M * K;
    private static final long T = G * K;



    public static String formatBytes(final long value) {
        final long[] dividers = new long[]{T, G, M, K, 1};
        final String[] units = new String[]{"TB", "GB", "MB", "KB", "B"};
        if (value < 0) {
            throw new IllegalArgumentException("Invalid file size: " + value);
        }
        if (value == 0) { //если размер равен 0 B
            return "0";
        }
        for (int i = 0; i < dividers.length; i++) {
            if (value >= dividers[i]) {
                return format(value, dividers[i], units[i]);
            }
        }
        return null;
    }

    private static String format(final long value,
                                 final long divider,
                                 final String unit) {
        final double result =
                divider > 1 ? (double) value / (double) divider : (double) value;
        String strResult;
        if (unit.equalsIgnoreCase("KB") || unit.equalsIgnoreCase("MB")) {

            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("#,##0.##", symbols);

            //df.setDecimalSeparator('.');
            //String formattedNumber = df.format(number);
            strResult = df.format(result) + " " + unit;
            //strResult = new DecimalFormat("#,##0.###").format(result) + " " + unit;
        } else {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.'); //разделитель в виде точки. по-дефолту ставится запятая
            DecimalFormat df = new DecimalFormat("#,##0.##", symbols);
            strResult = df.format(result) + " " + unit;
            //strResult = new DecimalFormat("#,##0.######").format(result) + " " + unit;
        }
        return strResult;
    }

    //double number = 1234.56789;
    //
    //DecimalFormat df = new DecimalFormat("#.##");
    //df.setDecimalSeparator('.');
    //String formattedNumber = df.format(number);
    //
    //System.out.println(formattedNumber);

    public static String format(Date value) {
        return format(value, DATETIME_FORMAT);
    }

    public static String format(Date value, String defaultValue) {
        return format(value, DATETIME_FORMAT, defaultValue);
    }

    public static String format(Date value, DateFormat dateFormat) {
        return format(value, dateFormat, null);
    }

    public static String format(Date value, DateFormat dateFormat, String defaultValue) {
        return value == null ? defaultValue : dateFormat.format(value);
    }

    public static Optional<Date> parseDate(String value, DateFormat dateFormat) {
        try {
            return Optional.ofNullable(dateFormat.parse(value));
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

    public static String formatDate(Date value) {
        return formatDateTime(value, DATE_FORMAT);
    }

    public static String formatDateTime(Date value) {
        return formatDateTime(value, DATETIME_FORMAT);
    }

    public static String formatDateTime(Date value, String defaultValue) {
        return formatDateTime(value, DATETIME_FORMAT, defaultValue);
    }

    public static String formatDateTime(Date value, DateFormat dateFormat) {
        return formatDateTime(value, dateFormat, null);
    }

    public static String formatDateTime(Date value, DateFormat dateFormat, String defaultValue) {
        return value == null ? defaultValue : dateFormat.format(value);
    }

    public static XMLGregorianCalendar toCalendar(Date value) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(value);
            calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance()
                                                                       .newXMLGregorianCalendar(calendar);
            //xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED); // without Z
            xmlGregorianCalendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
            return xmlGregorianCalendar;
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static XMLGregorianCalendar toCalendarWithoutZone(Date value) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(value);
            calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance()
                                                                       .newXMLGregorianCalendar(calendar);
            xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED); // without Z
            xmlGregorianCalendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
            return xmlGregorianCalendar;
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static XMLGregorianCalendar toCalendarMskWithMilliseconds(Date value) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(value);
            calendar.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO некоторые тестовые данные, хотел проверить.
//    public static RestClientConfiguration loadConfiguration(Properties properties) {
//
//        RestClientConfiguration configuration = new RestClientConfiguration();
//        String url = properties.getProperty("url");
//        if (isStringValid(url) && URL_PATTERN.matcher(url).matches()) {
//            configuration.setJasperReportsServerUrl(url);
//        }
//
//        String connectionTimeout = properties.getProperty("connectionTimeout");
//        if (isStringValid(connectionTimeout) && NUMBER_PATTERN.matcher(connectionTimeout).matches()) {
//            configuration.setConnectionTimeout(Integer.valueOf(connectionTimeout));
//        }
//
//        String readTimeout = properties.getProperty("readTimeout");
//
//        if (isStringValid(readTimeout) && NUMBER_PATTERN.matcher(readTimeout).matches()) {
//            configuration.setReadTimeout(Integer.valueOf(readTimeout));
//        }
//        String jrsVersion = properties.getProperty("jasperserverVersion");
//        if (isStringValid(jrsVersion) && VERSION_PATTERN.matcher(jrsVersion).matches()) {
//            try {
//                configuration.setJrsVersion(JRSVersion.valueOf(jrsVersion));
//            } catch (Exception e) {
//                log.info("There is no version for JasperReportsServer or it isn't supported.", e);
//            }
//        }
//
//        String authenticationType = properties.getProperty("authenticationType");
//        if (isStringValid(authenticationType)) {
//            try {
//                configuration.setAuthenticationType(AuthenticationType.valueOf(authenticationType.toUpperCase()));
//            } catch (Exception e) {
//                log.info("There is no authentication type or it isn't supported.", e);
//            }
//        }
//
//        String logHttp = properties.getProperty("logHttp");
//        if (isStringValid(logHttp) && BOOLEAN_PATTERN.matcher(logHttp).matches()) {
//            configuration.setLogHttp(Boolean.valueOf(logHttp));
//        }
//
//        String logHttpEntity = properties.getProperty("logHttpEntity");
//        if (isStringValid(logHttpEntity) && BOOLEAN_PATTERN.matcher(logHttpEntity).matches()) {
//            configuration.setLogHttpEntity(Boolean.valueOf(logHttpEntity));
//        }
//
//        String restrictedHttpMethods = properties.getProperty("restrictedHttpMethods");
//        if (isStringValid(restrictedHttpMethods) && BOOLEAN_PATTERN.matcher(restrictedHttpMethods).matches()) {
//            configuration.setRestrictedHttpMethods(Boolean.valueOf(restrictedHttpMethods));
//        }
//
//        String handleErrors = properties.getProperty("handleErrors");
//        if (isStringValid(handleErrors) && BOOLEAN_PATTERN.matcher(handleErrors).matches()) {
//            configuration.setHandleErrors(Boolean.valueOf(handleErrors));
//        }
//
//        String contentMimeType = properties.getProperty("contentMimeType");
//        if (isStringValid(contentMimeType)) {
//            try {
//                configuration.setContentMimeType(MimeType.valueOf(contentMimeType));
//            } catch (Exception e) {
//                log.info("There is no mime type for content type or it isn't supported.", e);
//            }
//        }
//
//        String acceptMimeType = properties.getProperty("acceptMimeType");
//
//        if (isStringValid(acceptMimeType)) {
//            try {
//                configuration.setAcceptMimeType(MimeType.valueOf(acceptMimeType));
//            } catch (Exception e) {
//                log.info("There is no mime type for accept type or it isn't supported.", e);
//            }
//        }
//        return configuration;
//    }

    private static Boolean isStringValid(String string) {
        return (string != null && string.length() > 0);
    }

    public static long convertGigabyteToBytes(long gigabytes) {
        long result = 0;
        result = gigabytes * G;
        return result;
    }

    /**
     * Получает число соответсвующее месяцу из полной даты
     */
    public static String getMonthFromSource(Timestamp date) {
        if (date != null) {
            return new SimpleDateFormat("M").format(date);
        } else {
            return "";
        }
    }

    /**
     * Получает первое числа следующего месяца
     */
    public static Timestamp getFirstDayOfNextMonth() {
        LocalDate today = LocalDate.now();
        LocalDateTime firstDateOfNextMonth = today.with(TemporalAdjusters.firstDayOfMonth()).plusMonths(1).atStartOfDay().withNano(0);
        return Timestamp.valueOf(firstDateOfNextMonth);
    }

    /**
     * Получает последнее число следующего месяца
     */
    public static Timestamp getLastDayOfNextMonth() {
        LocalDate today = LocalDate.now();
        LocalDateTime lastDateOfNextMonth =
                today.with(TemporalAdjusters.lastDayOfMonth()).plusMonths(1).atStartOfDay().with(LocalTime.MAX).withNano(0);
        return Timestamp.valueOf(lastDateOfNextMonth);
    }

    /**
     * Получает последние две цифры из числа года представленного строкой
     */
    public static String getLastTwoDigitsFromYear(String year) {
        return (year.substring(year.length() - 2));
    }

    public static UUID getRandomUUID(String str) {
        // Генерация UUID
        if (str == null) {
            return UUID.randomUUID();
        } else {
            return UUID.nameUUIDFromBytes(str.getBytes());
        }
    }
    public static String getMonthOrYearOrFullDateFromSource(Timestamp startDate, Integer accuracyFlag) {
        if (startDate != null) {
            {
                if (accuracyFlag != null) {
                    switch (accuracyFlag) {
                        case 0: // MONTH
                            return new SimpleDateFormat("M").format(startDate);
                        case 1: // MONTH + YEAR
                            return new SimpleDateFormat("MM.yyyy").format(startDate);
                        case 2: // DAY + MONTH + YEAR
                            return new SimpleDateFormat("dd.MM.yyyy").format(startDate);
                        case 3: // YEAR
                            return new SimpleDateFormat("yyyy").format(startDate);
                        case 4: // FULL DATE_TIME WITH SEC
                            return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(startDate);
                        case 5: // FULL DATE_TIME WITHOUT SEC
                            return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(startDate);
                        case 6: // MONTH in full name + YEAR
                        {
                            String strDate = "";
                            //добавил массив месяцев, т.к. при прямом форматировании по патерну "MMMM yyyy" отдается не устраивающее нас
                            //сообщение, например, не "ноябрь 2020", а "ноября 2020"
                            DateFormatSymbols months = new DateFormatSymbols() {
                                @Override
                                public String[] getMonths() {
                                    return new String[]{
                                            "январь", "февраль", "март", "апрель", "май", "июнь",
                                            "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
                                }
                            };
                            SimpleDateFormat monthAndYearFormatter = new SimpleDateFormat("MMMM yyyy", months);
                            strDate = monthAndYearFormatter.format(startDate);
                            return strDate;
                        }
                        default:
                            return new SimpleDateFormat("MM").format(startDate);
                    }
                } else {
                    return new SimpleDateFormat("M").format(startDate);
                }
            }
        } else {
            return "";
        }
    }
}
