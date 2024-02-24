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

    public static final List<String> LIST_APPID_LDAP_GROUPS =
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

    public static final Map<String, String> MAP_APPID_LDAP_GROUPS = new HashMap<>();
    public static final boolean HISTORICAL_REPORT = true;
    public static final boolean ACTUAL_REPORT = false;
    public static final boolean SHOW_INITIAL_AGGREGATED_EFFECTIVE_PERMISSION = true;

    static {
        for (int i = 0; i < LIST_APPID_LDAP_GROUPS.size(); i++) {
            MAP_APPID_LDAP_GROUPS.put(LIST_APPID_LDAP_GROUPS.get(i).toLowerCase(), FPSID_PORTLETS_EN.get(i));
        }
    }
}