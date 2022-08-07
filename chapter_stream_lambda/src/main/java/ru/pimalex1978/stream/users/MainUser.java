package ru.pimalex1978.stream.users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainUser {
    private static List<User> users = new ArrayList<>();

    static { // создаем наших юзеров и заполняем ими коллекцию
        User user1 = new User(1, "Ivan", 23, "man");
        User user2 = new User(2, "Olga", 45, "woman");
        User user3 = new User(3, "John", 12, "man");
        User user4 = new User(4, "Vitaliy", 25, "man");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }

    /**
     * Что если мы хотим получить данные по некоторым условиям?
     * Не проблема: используем функцию filter и возвращаем коллекцию
     *
     * @param bottomAge
     * @param topAge
     * @param sex
     * @return
     */
    public static List<User> getUsersByAgeRangeAndSex(int bottomAge, int topAge, String sex) {
        List<User> usersByAgeAndSex = users.stream()
                .filter((p) -> p.getAge() > bottomAge && p.getAge() < topAge && p.getSex().equals(sex))
                .collect(Collectors.toList());
        return usersByAgeAndSex;
    }

    /**
     * Функция average(), совместно с filter позволяют нам
     * получить среднее значение по некоторым полям и по фильтрам в одну строчку
     *
     * @return
     */
    public static double getMensAverage() {
        return users.stream().filter((p) -> p.getSex().equals("man")).mapToInt(User::getAge).average().getAsDouble();
    }

    /**
     * Если нужно использовать несколько фильтров, можете смело это делать
     * можно добавить сколько условий, сколько этого требует задача
     *
     * @return
     */
    public static int findCountOfWorkingPeople() {
        return (int) users.stream().filter((p) -> p.getAge() >= 18).filter(
                (p) -> (p.getSex().equals("woman") && p.getAge() < 55) || (p.getSex().equals("man") && p.getAge() < 60))
                .count();
    }

    /**
     * Удалить елемент из списка можно в одну строчку безо всякого труда
     *
     * @param user
     * @return
     */
    public static List<User> removeUser(User user) {
        users.removeIf(i -> i.equals(user));
        return users;
    }

    public static void main(String[] args) {
        System.out.println(getUsersByAgeRangeAndSex(18, 30, "man"));
        System.out.println(getMensAverage());
        System.out.println(findCountOfWorkingPeople());
        System.out.println(removeUser(users.get(1)));

    }
}
