package ru.pimalex1978.utils.util.some;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.function.Supplier;

@SuppressWarnings("all")
public interface Constants {

    String FEMIDA_LINK_PINNED_NOTE_DATE = "2099-01-01T12:00:00Z";
    String UUID_CONST = "uuid";
    String EXECUTION_EXCEPTION_MESSAGE = "Execution exception occurred. Exception message: ";
    String ERROR_PREFIX = "ERROR/";
    String INTERRUPTED_EXCEPTION_MESSAGE = "Interrupted exception thrown: ";
    String DTO_WITH_ID_NOT_FOUND = "DTO with id %s not found";
    String BASE_URL = "https://frontier.beamery.ru/v1/";
    String URL = BASE_URL + "%s/%s";
    String POOL_URL = BASE_URL + "pool/%s/contacts";
    String CONTACT_POOLS_URL = BASE_URL + "contact/%s/pools";
    String CONTACT_REQUEST_TYPE = "contact";
    String VACANCY_REQUEST_TYPE = "vacancy";
    String APPLICATION_REQUEST_TYPE = "application";
    String NOTE_REQUEST_TYPE = "note";
    String POOL_REQUEST_TYPE = "pool";
    String ATTACHMENT_REQUEST_TYPE = "attachment";
    String CONTACTS_REQUEST_TYPE = "contacts";
    String VACANCIES_REQUEST_TYPE = "vacancies";
    String NOTES_REQUEST_TYPE = "notes";
    String ATTACHMENTS_REQUEST_TYPE = "attachments";
    String AUTH_URL = BASE_URL + "auth/";
    String UPDATED_CANDIDATES_AND_VACANCIES_QUERY_PARAMS_STRING
            = "?updated_at=%s&updated_at_op=gte&limit=150&offset=%s&sort_order=asc&sort_field=updatedAt";
    String UPDATED_NOTE_QUERY_PARAMS_STRING = "?after=%s&before=%s&limit=500";
    String JSON_EXCEPTION_MESSAGE = "Exception while processing JSON. Exception message: ";
    String CANDIDATES = "CANDIDATES";
    String VACANCIES = "VACANCIES";
    String NOTES = "NOTES";
    String APPLICATIONS = "APPLICATIONS";
    String ATTACHMENTS = "ATTACHMENTS";
    String EMPTY_STRING = "";
    String SPACE = " ";
    String POOL_WITH_ID_PREFIX = "Pool with id";
    String NOT_FOUND_SUFFIX = "not found";
    String CONTACT_WITH_ID_PREFIX = "Contact with id";
    String DASH = "-";
    String BEAMERY = "beamery";
    String USERS_URL = BASE_URL + "users";
    String USERS_CACHE_NAME = "users";

    DateTimeFormatter ISO_INSTANT_WITH_ZERO_TO_NINE_MILLIS = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 9, true)
            .appendPattern("'Z'")
            .toFormatter();
    DateTimeFormatter FORMATTER_WITH_OFFSET_X = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 9, true)
            .appendPattern("X")
            .toFormatter();
    DateTimeFormatter FORMATTER_WITH_T_LITERAL_AND_OFFSET_X = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 9, true)
            .appendPattern("X")
            .toFormatter();
    DateTimeFormatter ISO_OFFSET_DATE_TIME_WITH_ZERO_TO_NINE_NANOS = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 9, true)
            .appendOffset("+HH:MM", "+00:00")
            .toFormatter();
    DateTimeFormatter DEFAULT_CREATED_TIME_FROM_DATABASE_FORMATTER = new DateTimeFormatterBuilder()
            .append(ISO_LOCAL_DATE_TIME)
            .appendOffset("+HH:MM", "+00:00")
            .toFormatter();

    Supplier<String> DEFAULT_UTC_CREATED_TIME_FROM_DATABASE_AS_STRING
            = () -> ISO_INSTANT.format(Instant.now());
}
