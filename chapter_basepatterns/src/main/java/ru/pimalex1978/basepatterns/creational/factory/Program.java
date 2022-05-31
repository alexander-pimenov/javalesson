package ru.pimalex1978.basepatterns.creational.factory;

/**
 * Класс Program демонстрирует нам класс Клиент.
 * Здесь показано, что для того, чтобы изменить разработчика,
 * нам нужно изменить только название фабрики или CppDeveloperFactory()
 * или JavaDeveloperFactory() и от этого будет зависеть какой
 * разработчик создается и соответственно какой метод writeCode()
 * запускается.
 */
public class Program {
    public static void main(String[] args) {
        /*
         * Можно напрямую использовать вызов DeveloperFactory, как видим ниже,
         * а можно сделать еще гибче и делать вызов через метод
         * createDeveloperBySpecialty("specialty")
         */
//        DeveloperFactory developerFactory = new JavaDeveloperFactory();
//        DeveloperFactory developerFactory = new CppDeveloperFactory();

        /*
         * Используя метод createDeveloperBySpecialty("specialty") мы еще
         * гибче делаем клиентский код. Просто указываем специальность в параметре.
         */
        DeveloperFactory developerFactory = createDeveloperBySpecialty("go");

        Developer developer = developerFactory.createDeveloper();

        developer.writeCode();
    }

    /**
     * Метод нужен, чтобы возвращать определенную фабрику
     * разработчиков в зависимости от той специальности,
     * которая нам необходима.
     *
     * @param specialty название специальности
     * @return фабрика разработчиков
     */
    private static DeveloperFactory createDeveloperBySpecialty(String specialty) {
        if (specialty.equalsIgnoreCase("java")) {
            return new JavaDeveloperFactory();
        } else if (specialty.equalsIgnoreCase("c++")) {
            return new CppDeveloperFactory();
        } else if (specialty.equalsIgnoreCase("php")) {
            return new PhpDeveloperFactory();
        } else if (specialty.equalsIgnoreCase("python")) {
            return new PythonDeveloperFactory();
        } else if (specialty.equalsIgnoreCase("go")) {
            return new GoDeveloperFactory();
        } else {
            /*
             * Если введена не известная специализация, то бросаем
             * исключение с сообщением.
             */
            throw new RuntimeException(specialty + " is unknown specialty.");
        }
    }
}
