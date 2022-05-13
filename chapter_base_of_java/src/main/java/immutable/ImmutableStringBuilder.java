package immutable;

/* Пример, как получить неизменяемый (immutable) класс.
 * Т.е. такой класс, который мы получим при его создании и далее изменить его не сможем !!!
 * !!! Нужно СОХРАНЯТЬ и ОТДАВАТЬ копии объекта, а не прямой объект !!!
 */
public class ImmutableStringBuilder {

    private StringBuilder builder;

    public ImmutableStringBuilder(StringBuilder builder) {
        //создавая копию, так мы отвязываемся о того StringBuilder который приходит из вне !!!
        this.builder = new StringBuilder(builder); // <- ВНИМАНИЕ на этот участок кода !!!
    }

    public StringBuilder getBuilder() {
        // Здесь так же мы отдадим через геттер не ссылку на наш существующий объект
        // в поле builder StringBuilder, и потом сможем через неё как то изменять
        // его, а копию объекта (это другой объект).
        // Тоже отвязываемся от нашего поля builder !!!
        // И те кто получат эту ссылку снаружи ни как не смогут изменить то
        // что находится у нас здесь внутри, в поле builder !!!
        return new StringBuilder(builder);
    }
}

