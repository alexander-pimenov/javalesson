package ru.pimalex1978.refactoring.solution1;

public class PersonalMailTemplate implements MailTemplate {
    /**
     * Метод возвращает наполнение для письма.
     *
     * @return личное письмо
     */
    @Override
    public String getTemplate() {
        return "Hi Jim, perfect day today...";
    }
}
