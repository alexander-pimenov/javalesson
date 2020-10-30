package ru.pimalex1978.refactoring.solution1;

public enum MessageType {
    /*PersonalMailTemplate.class и BusinessMailTemplate.class нужно чтобы наши имплиментации
     * мы инициализировали lazy, т.е. инициализировали по требованию, а не сразу вызвав их
     * через new BusinessMailTemplate()*/
    PERSONAL(0, PersonalMailTemplate.class), //PersonalMailTemplate.class - это имплиментация PersonalMailTemplate
    BUSINESS(1, BusinessMailTemplate.class);

    /**
     * Переменные класса
     * typeCode - числовой код
     * Class<? extends MailTemplate> - все классы наследующие MailTemplate
     */
    private int typeCode;
    private Class<? extends MailTemplate> mailTemplate;

    /**
     * Конструктор
     *
     * @param typeCode     числовой параметр письма
     * @param mailTemplate все классы, которые наследуют MailTemplate
     */
    MessageType(int typeCode, Class<? extends MailTemplate> mailTemplate) {
        this.typeCode = typeCode;
        this.mailTemplate = mailTemplate;
    }

    /**
     * Метод определяющий по коду, какая нам
     * нужна имплиментация.
     *
     * @param code код для шаблона
     * @return тип
     */
    public static MessageType getTemplateByCode(int code) {
        //.values() - позволяет достать все values из enum и пройтись по ним
        for (MessageType type : MessageType.values()) {
            if (type.typeCode == code) {
                return type;
            }
        }
        //если не будет найдено значение, то можно вернуть пустой объект,
        //а можно и кинуть exception
        throw new RuntimeException("Some message");
    }

    /**
     * Метод геттер на наш код темплейт письма
     * Возвращаем тип интерфейса
     *
     * @return MailTemplate
     * @throws IllegalAccessException возможно
     * @throws InstantiationException возможно
     */
    public MailTemplate getPersonalMailTemplate() throws IllegalAccessException, InstantiationException {
        return mailTemplate.newInstance();
    }
}
