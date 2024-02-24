package ru.pimalex1978.utils.util.some;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.function.Supplier;

@SuppressWarnings("all")
public final class Constants {

    static final String FEMIDA_LINK_PINNED_NOTE_DATE = "2099-01-01T12:00:00Z";
    static final String UUID_CONST = "uuid";
    static final String EXECUTION_EXCEPTION_MESSAGE = "Execution exception occurred. Exception message: ";
    static final String ERROR_PREFIX = "ERROR/";
    static final String INTERRUPTED_EXCEPTION_MESSAGE = "Interrupted exception thrown: ";
    static final String DTO_WITH_ID_NOT_FOUND = "DTO with id %s not found";
    static final String BASE_URL = "https://frontier.beamery.ru/v1/";
    static final String URL = BASE_URL + "%s/%s";
    static final String POOL_URL = BASE_URL + "pool/%s/contacts";
    static final String CONTACT_POOLS_URL = BASE_URL + "contact/%s/pools";
    static final String CONTACT_REQUEST_TYPE = "contact";
    static final String VACANCY_REQUEST_TYPE = "vacancy";
    static final String APPLICATION_REQUEST_TYPE = "application";
    static final String NOTE_REQUEST_TYPE = "note";
    static final String POOL_REQUEST_TYPE = "pool";
    static final String ATTACHMENT_REQUEST_TYPE = "attachment";
    static final String CONTACTS_REQUEST_TYPE = "contacts";
    static final String VACANCIES_REQUEST_TYPE = "vacancies";
    static final String NOTES_REQUEST_TYPE = "notes";
    static final String ATTACHMENTS_REQUEST_TYPE = "attachments";
    static final String AUTH_URL = BASE_URL + "auth/";
    static final String UPDATED_CANDIDATES_AND_VACANCIES_QUERY_PARAMS_STRING
            = "?updated_at=%s&updated_at_op=gte&limit=150&offset=%s&sort_order=asc&sort_field=updatedAt";
    static final String UPDATED_NOTE_QUERY_PARAMS_STRING = "?after=%s&before=%s&limit=500";
    static final String JSON_EXCEPTION_MESSAGE = "Exception while processing JSON. Exception message: ";
    static final String CANDIDATES = "CANDIDATES";
    static final String VACANCIES = "VACANCIES";
    static final String NOTES = "NOTES";
    static final String APPLICATIONS = "APPLICATIONS";
    static final String ATTACHMENTS = "ATTACHMENTS";
    static final String EMPTY_STRING = "";
    static final String SPACE = " ";
    static final String POOL_WITH_ID_PREFIX = "Pool with id";
    static final String NOT_FOUND_SUFFIX = "not found";
    static final String CONTACT_WITH_ID_PREFIX = "Contact with id";
    static final String DASH = "-";
    static final String BEAMERY = "beamery";
    static final String USERS_URL = BASE_URL + "users";
    static final String USERS_CACHE_NAME = "users";

    static final DateTimeFormatter ISO_INSTANT_WITH_ZERO_TO_NINE_MILLIS = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 9, true)
            .appendPattern("'Z'")
            .toFormatter();
    static final DateTimeFormatter FORMATTER_WITH_OFFSET_X = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 9, true)
            .appendPattern("X")
            .toFormatter();
    static final DateTimeFormatter FORMATTER_WITH_T_LITERAL_AND_OFFSET_X = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 9, true)
            .appendPattern("X")
            .toFormatter();
    static final DateTimeFormatter ISO_OFFSET_DATE_TIME_WITH_ZERO_TO_NINE_NANOS = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 9, true)
            .appendOffset("+HH:MM", "+00:00")
            .toFormatter();
    static final DateTimeFormatter DEFAULT_CREATED_TIME_FROM_DATABASE_FORMATTER = new DateTimeFormatterBuilder()
            .append(ISO_LOCAL_DATE_TIME)
            .appendOffset("+HH:MM", "+00:00")
            .toFormatter();

    static final Supplier<String> DEFAULT_UTC_CREATED_TIME_FROM_DATABASE_AS_STRING
            = () -> ISO_INSTANT.format(Instant.now());

    private Constants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
