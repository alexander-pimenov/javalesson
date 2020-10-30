package ru.pimalex1978.refactoring.solution1;

import ru.pimalex1978.refactoring.util.MessageSenderUtil;

/**
 * Класс занимается отправкой письма.
 * Перед отправкой определяет какое письмо нужно
 * отправлять: личного характера или по бизнесу.
 * Изначально выбор что отправлять вели через if-else
 * затем всё сделали с помощью enum
 */
public class MessageSender {

    private void sendEmail() throws InstantiationException, IllegalAccessException {

        //Из утилитного класса получаем код типа письма
        int emailTemplateCode = MessageSenderUtil.getTemplateTypeCodeFromDB();

        /*берем MessageType enum, получим код по id с помощью .getTemplateByCode(emailTemplateCode),
         * обратимся к имплиментации .getPersonalMailTemplate() и заберем у неё темплейт .getTemplate()*/
        String template = MessageType.getTemplateByCode(emailTemplateCode).getPersonalMailTemplate().getTemplate();

        /*и передадим наш теплейт в утилитный класс для дальней отправки письма*/
        MessageSenderUtil.sendMessage(template);
    }


    /*////////////////////////////////////////////
    * Здесь показан код до рефакторинга с использованием enum
    */////////////////////////////////////////////

//    private static final int TYPE_PERSONAL = 0;
//    private static final int TYPE_BUSINESS = 1;
//
//    private void sendEmail() {
//        //Из утилитного класса получаем код типа письма
//        int emailTemplateCode = MessageSenderUtil.getTemplateTypeCodeFromDB();
//        String emailTemplateAsString = "";
//        if (emailTemplateCode == TYPE_PERSONAL) {
//            emailTemplateAsString = getPersonalTemplateMessage();
//        } else if (emailTemplateCode == TYPE_BUSINESS) {
//            emailTemplateAsString = getBusinessTemplateMessage();
//        }
//        //для отправки обращаемся к утилитному классу
//        MessageSenderUtil.sendMessage(emailTemplateAsString);
//    }
//
//    /**
//     * Метод возвращает наполнение для письма.
//     *
//     * @return бизнес обращение
//     */
//    private String getBusinessTemplateMessage() {
//        return "Hi Jim, perfect day for business today...";
//    }
//
//    /**
//     * Метод возвращает наполнение для письма.
//     *
//     * @return личное обращение
//     */
//    private String getPersonalTemplateMessage() {
//        return "Hi Jim, perfect day today...";
//    }
}
