package ru.pimalex1978.utils.util.some;

import java.util.List;

public final class Filters {
    /**
     * Идентификаторы в БД, отвечающие за получение кредитных организаций
     */
    static final List<Long> KO_IDENTIFIERS = List.of(3601L, 3603L);

    /**
     * Идентификаторы в БД, отвечающие за получение нефинансовых (поднадзорных) организаций
     */
    static final List<Long> NFO_IDENTIFIERS = List.of(3602L, 3603L);

    /**
     * Идентификаторы в БД, отвечающие за получение нефинансовых (поднадзорных) организаций
     */
    static final List<Long> KO_NFO_IDENTIFIERS = List.of(3601L, 3602L, 3603L);

    static final String STRUCTURE_GIBR = "Структура ГИБР";

    static final String TERRITORIAL_INSTITUTIONS = "Территориальные учреждения БР";

    static final String TERRITORIAL_STRUCTURE = "Территориальная структура";

    private Filters() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
