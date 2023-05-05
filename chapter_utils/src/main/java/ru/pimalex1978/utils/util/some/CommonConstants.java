package ru.pimalex1978.utils.util.some;

import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Объект-склад совместно используемых констант
 * </p>
 * <p>
 * Created on 26.06.2017
 *
 * @author Maxim Avilov
 */
@UtilityClass
public class CommonConstants {
    /**
     * ОКАТО Москвы
     */
    public static final String MOSCOW_OKATO = "45";
    public static final String MOSCOW_CHECK_CODE_KO = "2i";
    public static final String MOSCOW_CHECK_CODE_NFO = "2f";

    /**
     * Дата начала действия новой структуры ГИБР (2014-03-01T00:00:00+00:00)
     */
    public static final Timestamp NEW_GIBR_STRUCTURE_BEGIN_DATE = new Timestamp(1393632000000L);

    /**
     * Длинна БИК в символах
     */
    public static final int BIC_LENGTH = 9;

    /**
     * Регистрационный номер Сбербанка
     */
    public static final BigInteger SBRF_REG_NUM_BIG_INT = BigInteger.valueOf(1481);
    public static final String SBRF_REG_NUM_WITH_DIVIDER = "1481/%";

    public static final Date ULTRA_FORMER_DATE = new Date(0L);
    public static final Date ULTRA_FUTURE_DATE = new Date(Long.MAX_VALUE - 1);

    public static final String MOST_COMMON_RUSSIAN_DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";
    public static final String MOST_USEFULL_RUSSIAN_DATE_FORMAT = "dd.MM.yyyy";
    public static final String QUESTIONS_DETAILS = "dd.MM.yyyy";
    public static final SimpleDateFormat DATETIME_FORMAT_FOR_REPORT_NAME = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat DATETIME_FORMAT_FOR_REPORT2_NAME = new SimpleDateFormat("yyyy-MM-dd HHmmss");

    public static final String[] RUS_MONTH = {
            "", "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    public static final String[] AUDIT_TYPE_SHORT = {"Р", "М", "МО", "P", "M", "MO"}; // Тип проверки
    public static final String[] AUDIT_TYPE_LONG =
            {"Региональная", "Межрегиональная", "Межокружная", "Региональная", "Межрегиональная", "Межокружная"};

    public static final String[] AUDIT_KIND_SHORT = {"К", "Т", "С", "K", "T", "C"};  // Вид проверки
    public static final String[] AUDIT_KIND_LONG =
            {"Комплексная", "Тематическая", "Специализированная", "Комплексная", "Тематическая", "Специализированная"};

    public static final List<String> FPSID_LDAP_GROUPS =
            Arrays.asList("eisl-IODViewers", "eisl-VBIViewers", "eisu-zoeIDAnalystFileStore", "eisu-zoeIDAnalystInspectKo",
                    "eisu-zoeIDAnalystInspectNfo", "eisu-zoeIDAnalystPlanKo", "eisu-zoeIDAnalystPlanNfo",
                    "eisu-zoeIDAnalystRef", "eisu-zoeIDControlerLoad", "eisu-zoeIDControlerSync",
                    "eisu-zoeIDCoordinator", "eisu-zpeIDAnalystFileStore", "eisu-zpeIDAnalystInspectKo",
                    "eisu-zpeIDAnalystInspectNfo", "eisu-zpeIDAnalystPlanKo", "eisu-zpeIDAnalystPlanNfo",
                    "eisu-zpeIDAnalystRef", "eisu-zpeIDControlerLoad", "eisu-zpeIDControlerSync", "eisu-zpeIDCoordinator",
                    "eisu-zoeidAnalystPlan", "eisu-zoeidAnalystNotPlan", "eisu-zpeidAnalystPlan", "eisu-zpeidAnalystNotPlan");

    public static final List<String> APPID_LDAP_GROUPS =
            Arrays.asList("EHDTSU-ROLE-IOD", "EHDTSU-ROLE-VBI", "EHDPSU-ROLE-IOD", "EHDPSU-ROLE-VBI",
                    "39su-ppod-app-test-appid-AnalystFileStore",
                    "39su-ppod-app-test-appid-AnalystInspectKo",
                    "39su-ppod-app-test-appid-AnalystInspectNfo",
                    "39su-ppod-app-test-appid-AnalystPlanKo",
                    "39su-ppod-app-test-appid-AnalystPlanNfo",
                    "39su-ppod-app-test-appid-AnalystRef",
                    "39su-ppod-app-test-appid-ControlerLoad",
                    "39su-ppod-app-test-appid-ControlerSync",
                    "39su-ppod-app-test-appid-Coordinator",
                    "39su-ppod-app-appid-AnalystFileStore",
                    "39su-ppod-app-appid-AnalystInspectKo",
                    "39su-ppod-app-appid-AnalystInspectNfo",
                    "39su-ppod-app-appid-AnalystPlanKo",
                    "39su-ppod-app-appid-AnalystPlanNfo",
                    "39su-ppod-app-appid-AnalystRef",
                    "39su-ppod-app-appid-ControlerLoad",
                    "39su-ppod-app-appid-ControlerSync",
                    "39su-ppod-app-appid-Coordinator",
                    "39su-ppod-app-test-appid-AnalystPlan",
                    "39su-ppod-app-test-appid-AnalystNotPlan",
                    "39su-ppod-app-appid-AnalystPlan",
                    "39su-ppod-app-appid-AnalystNotPlan",
                    "39su-ppod-app-test-appid-Users",
                    "39su-ppod-app-appid-Users");

    public static final List<String> FPSID_PORTLETS_EN =
            Arrays.asList("ИОД ЗТ", "ВБИ ЗТ", "ИОД ЗПЭ", "ВБИ ЗПЭ",
                    "Работа с Файловым хранилищем",
                    "Контроль выполнения - КО",
                    "Контроль выполнения - НФО",
                    "Сводный план и внеплан КО, Предложения в Сводный план",
                    "Единый план и внеплан НФО, Предложения в Единый план",
                    "Справочник организаций",
                    "Загрузка", "Синхронизация ФХ", "Управление доступом",
                    "Работа с Файловым хранилищем",
                    "Контроль выполнения - КО",
                    "Контроль выполнения - НФО",
                    "Сводный план и внеплан КО, Предложения в Сводный план",
                    "Единый план и внеплан НФО, Предложения в Единый план",
                    "Справочник организаций",
                    "Загрузка", "Синхронизация ФХ", "Управление доступом",
                    "План проверок поднадзорных лиц", "Внеплановые проверки поднадзорных лиц",
                    "План проверок поднадзорных лиц", "Внеплановые проверки поднадзорных лиц",
                    "Корневая группа Приложения ИД", "Корневая группа Приложения ИД");

    public static final Map<String, String> appidLdapGroups = new HashMap<>();
    public static final boolean HISTORICAL_REPORT = true;
    public static final boolean ACTUAL_REPORT = false;
    public static boolean SHOW_INITIAL_AGGREGATED_EFFECTIVE_PERMISSION = true;

    static {
        for (int i = 0; i < APPID_LDAP_GROUPS.size(); i++) {
            appidLdapGroups.put(APPID_LDAP_GROUPS.get(i).toLowerCase(), FPSID_PORTLETS_EN.get(i));
        }
    }

    public interface RequestNoticeType {
        int REQUEST = 1;
        int NOTICE = 2;
    }

    public interface AccessStamp {
        String LIMITED_INSPECTION = "limited_inspection";
        String COMMON_INSPECTION = "common_inspection";
    }

    public interface RouteKey {
        String CHECKED_OMNI = "checked_omni"; //Входящие документы
        String CHECKED_WORKGROUP = "checked_workgroup"; //Входящие документы
        String WORKGROUP_CHECKED = "workgroup_checked"; //Исходящие документы
        String WORKGROUP_INTERNAL = "workgroup_internal"; //Исходящие документы
        String WORKGROUP_OMNI = "workgroup_omni"; //Исходящие документы
    }

    public interface StatusType {
        Integer REQUEST = 1;
        Integer ITEM = 3;
        Integer PACKAGE = 4;
        Integer NOTIFICATION = 5;
        Integer CABINET = 6;
        Integer FILE = 7;
    }

    public interface StatusKey {
        String FILE_ACCEPTED = "file_accepted";
        String FILE_DIFF = "file_diff";
        String FILE_IN_SADD = "file_insadd";
        String FILE_LOADED = "file_loaded";
        String FILE_PROJECT = "file_project";
        String FILE_REGISTERED = "file_registered";
        String FILE_RELEASED = "file_released";
        String FILE_SENT = "file_sent";
        String FILE_SIGNED = "file_signed";

        String ITEM_COMPLETED = "item_completed";
        String ITEM_LOADED = "item_loaded";
        String ITEM_NOT_COMPLETED = "item_not_completed";

        String PACKAGE_ACCEPTED = "package_accepted";
        String PACKAGE_ANSWER_ERROR = "package_answer_error";
        String PACKAGE_REGISTERED = "package_registered";
        String PACKAGE_INVALID = "package_invalid";
        String PACKAGE_ERROR = "package_error";

        String REQUEST_COMPLETED = "request_completed";
        String REQUEST_DELIVERED = "request_delivered";
        String REQUEST_DELIVERY_ERROR = "request_delivery_error";
        String REQUEST_DRAFT = "request_draft";
        String REQUEST_ERROR = "request_error";
        String REQUEST_SIGNED = "request_signed";
        String REQUEST_IN_SADD = "request_insadd";
        String REQUEST_IN_CHECKED = "request_inchecked";
        String REQUEST_NOT_COMPLETED = "request_not_completed";
        String REQUEST_PARTIALLY_COMPLETED = "request_partially_completed";
        String REQUEST_PREPARED = "request_prepared";
        String REQUEST_REGISTERED = "request_registered";
        String REQUEST_SENT = "request_sent";

        String NOTIFICATION_NOT_SENT = "notification_not_sent";
        String NOTIFICATION_SENT_NOT_CONFIRMED = "notification_sent_not_confirmed";
        String NOTIFICATION_ERROR = "notification_error";
        String NOTIFICATION_CONFIRMED = "notification_confirmed";

        String CABINET_CREATED = "cabinet_created";
        String CABINET_OPEN = "cabinet_open";
        String CABINET_BLOCKED = "cabinet_blocked";
        String CABINET_CLOSED = "cabinet_closed";
    }

    public interface FeedbackTypeKey {
        String DOWNLOAD_PACKAGE_ERROR = "feedback_download_package_error";
        String FULL_EPC_DOWNLOAD = "feedback_full_epc_download";
        String UPLOAD_PACKAGE_ERROR = "feedback_upload_package_error";
    }

    public interface WGCSection {
        String WGC_MANAGEMENT = "Управление КРГ";
        String WORK_AT_WGC = "Работа в КРГ";
    }

    public interface MessageCriticality {
        String INFORMATION_MESSAGE = "Информационное сообщение";
        String ERROR_MESSAGE = "Сообщение об ошибке";
    }



    public interface WGCStatusUser {
        String UNLOCKED = "Разблокирован";
        String BLOCKED = "Блокирован";
        String EXCLUDED = "Исключен";
    }

    public interface Filters {
        /**
         * Идентификаторы в БД, отвечающие за получение кредитных организаций
         */
        List<Long> KO_IDENTIFIERS = List.of(3601L, 3603L);

        /**
         * Идентификаторы в БД, отвечающие за получение нефинансовых (поднадзорных) организаций
         */
        List<Long> NFO_IDENTIFIERS = List.of(3602L, 3603L);

        /**
         * Идентификаторы в БД, отвечающие за получение нефинансовых (поднадзорных) организаций
         */
        List<Long> KO_NFO_IDENTIFIERS = List.of(3601L, 3602L, 3603L);

        String STRUCTURE_GIBR = "Структура ГИБР";

        String TERRITORIAL_INSTITUTIONS = "Территориальные учреждения БР";

        String TERRITORIAL_STRUCTURE = "Территориальная структура";
    }
}