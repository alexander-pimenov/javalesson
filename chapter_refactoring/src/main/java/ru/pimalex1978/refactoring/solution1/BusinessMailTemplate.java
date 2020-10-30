package ru.pimalex1978.refactoring.solution1;

public class BusinessMailTemplate implements MailTemplate {
    /**
     * Метод возвращает наполнение для письма.
     *
     * @return бизнес письмо
     */
    @Override
    public String getTemplate() {
        return "Hi Jim, perfect day for business today...";
    }
}
