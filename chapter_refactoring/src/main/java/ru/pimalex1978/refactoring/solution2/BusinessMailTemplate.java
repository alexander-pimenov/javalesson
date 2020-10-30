package ru.pimalex1978.refactoring.solution2;

public class BusinessMailTemplate implements MailTemplate {
    /**
     * Метод возвращает наполнение для письма.
     *
     * @return бизнес письмо
     */
    @Override
    public String generateTemplate() {
        return "Hi Jim, perfect day for business today...";
    }
}
