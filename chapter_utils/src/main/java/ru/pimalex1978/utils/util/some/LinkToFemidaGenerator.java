package ru.pimalex1978.utils.util.some;

import lombok.experimental.UtilityClass;

/**
 * Builds link to femida.
 */
@UtilityClass
public class LinkToFemidaGenerator {

    public static final String LINK_TO_FEMIDA = "https://femida.yandex-team.ru/%s/";
    public static final String TEMPLATE_LINKS_TO_FEMIDA = "<div><a href=\"%s%s%s\" target=\"_blank\">%s%s%s</a></div>";
    public static final String FORWARD_SLASH = "/";

    public static String getLinkToFemida(Long femidaId, String type) {
        return String.format(TEMPLATE_LINKS_TO_FEMIDA, String.format(LINK_TO_FEMIDA, type), femidaId,
                FORWARD_SLASH, String.format(LINK_TO_FEMIDA, type), femidaId, FORWARD_SLASH);
    }
}
