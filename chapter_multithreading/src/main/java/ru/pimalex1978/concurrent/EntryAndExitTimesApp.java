package ru.pimalex1978.concurrent;

import lombok.Data;
import ru.pimalex1978.utils.util.some.FormatterUtils;
import ru.pimalex1978.utils.util.some.Now;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

//Описание задачи:
//Люди входят и выходят из банка.
//Например, входные данные (время входа и время выхода в Банк):
//9:15 9:45 - 1-й чел
//9:30 10:30 - 2-й чел
//10:15 10:45 - 3-й чел
// - - - -    - N-й чел
//
//
//Нужен результат обработки - узнать максимальное количество посетителей в банке и время его достижения.
//
// Возможно иметь 1_000_000 записей событий.

/**
 * Консольное приложение, как решения поставленной выше задачи.
 */
public class EntryAndExitTimesApp {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        CountService countService = new CountService();
        startProgramm(countService);
    }

    /**
     * Основной метод работы с консольным меню.
     * 1. Выводится шапка меню.
     * 2. Читаются выбранные пункты меню.
     * 3. Выполняются некоторые действия,
     * заложенные в вызываемых классах,
     * находящихся в кейсах конструкции switch-case.
     */
    private static void startProgramm(CountService countService) {
        boolean exit = false;
        int scValue = 0;
        while (!exit) {
            //Вызываем метод рисования шапки меню
            printMenu();
            //Вызываем метод считывания из консоли только целого числа
            //если число не число или не целое, то будет ждать ввода
            //только целого числа.
            scValue = inInt();
            switch (scValue) {
                case 1:
                    System.out.println("Выбран пункт меню № 1");
                    countService.incr();
                    long countIn = countService.countPeopleByFlag(true);
                    System.out.println("Вошедших человек = " + countIn);
                    break;
                case 2:
                    System.out.println("Выбран пункт меню № 2");
                    countService.decr();
                    long countOut = countService.countPeopleByFlag(false);
                    System.out.println("Вышедших человек = " + countOut);
                    break;
                case 3:
                    System.out.println("Выбран пункт меню № 3");
                    Map<Boolean, Long> resAll = countService.countAll();
                    Now now = new Now();
                    System.out.printf("Вошедших людей в банк на %s - %d%n",
                            FormatterUtils.formatDateTime(now), resAll.get(true));
                    System.out.printf("Вышедших людей из банка на %s - %d%n",
                            FormatterUtils.formatDateTime(now), resAll.get(false));
                    break;
                case 4:
                    System.out.println("Введите дату в формате dd.MM.yyy HH:mm ");
                    Timestamp timestamp = inDate();
                    long peopleByTime = countService.countPeopleByTime(timestamp);
                    System.out.println("В банке на это время находилось " + peopleByTime + " человек");
                    break;
                case 5:
                    System.out.println("Выбран пункт меню № 5");
                    TimeInfo maxOfPeopleAndTime = countService.countMaxCapacity(countService.getStateOfPeople());
                    System.out.printf("В максимальное количество = %d человек находящихся в банке было в %s%n",
                            maxOfPeopleAndTime.getMaxCapacity(), FormatterUtils.formatDateTime(maxOfPeopleAndTime.getMaxCapacityTime()));
                    break;
                case 6:
                    System.out.println("Выбран ВЫХОД из программы");
                    exit = true;
                    break;
                default:
                    System.out.println("Не правильно выбран пункт из меню");
                    break;
            }
        }
    }

    /**
     * Метод читает из консоли данные и возвращает число,
     * если только это есть целое число.
     * Иначе крутимся в бесконечном цикле.
     *
     * @return цлое число.
     */
    private static int inInt() {
        //Крутимся в бесконечном цикле пока не будет введено
        //целое число.
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введено не целое число. Попробуйте еще раз.");
            }
        }
    }

    /**
     * Метод читает из консоли данные и возвращает дату,
     * если только это есть дата
     * Иначе крутимся в бесконечном цикле.
     *
     * @return дата Timestamp.
     */
    private static Timestamp inDate() {
        //Крутимся в бесконечном цикле пока не будет введена правильная дата
        //согласно формату
        while (true) {
            try {
                String strDate = sc.nextLine().trim();
                Timestamp date = FormatterUtils.parseDate(strDate, FormatterUtils.DATE_TIME_FORMAT_WITHOUT_SEC)
                        .map(Date::getTime)
                        .map(Timestamp::new)
                        .orElse(null);
                if (Objects.nonNull(date)) {
                    return date;
                } else {
                    System.out.println("Invalid input. Not a correct Date. Try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Not a correct Date. Try again");
            }
        }
    }

    /**
     * Метод рисует шапку нашего консольного меню.
     */
    private static void printMenu() {

        System.out.println("=================================================================");
        System.out.println("|                           ВЫБОР МЕНЮ                          |");
        System.out.println("=================================================================");
        System.out.println("| Пункты:                                                       |");
        System.out.println("|    1. Пункт 1 - вход сотрудника                               |");
        System.out.println("|    2. Пункт 2 - выход сотрудника                              |");
        System.out.println("|    3. Пункт 3 - получить общую статистику по кол-ву человек   |");
        System.out.println("|    4. Пункт 4 - ввести время в формате <dd.MM.yyy HH:mm>      |");
        System.out.println("|    5. Пункт 5 - получить макс кол-во посетителей в банке      |");
        System.out.println("|    6. Выход из приложения                                     |");
        System.out.println("=================================================================");
        System.out.println(" Выбирайте пункт из меню: \r");
    }
}

@Data
class CountService {
    private List<StateOfPeople> stateOfPeople = new ArrayList<>();

    /**
     * Увеличиваем количество людей в хранилище 'stateOfPeople'
     */
    public void incr() {
        Now now = new Now();
        stateOfPeople.add(new StateOfPeople(now, true));
    }

    /**
     * Уменьшаем количество людей в хранилище 'stateOfPeople'
     */
    public void decr() {
        Now now = new Now();
        stateOfPeople.add(new StateOfPeople(now, false));
    }

    /**
     * Метод считает вошедших/вышедших людей из банка.
     * Вошедшие это флаг true, вышедшие - false.
     *
     * @param flag вошедшие/вышедшие - true/false
     * @return количество
     */
    public long countPeopleByFlag(boolean flag) {
        return stateOfPeople.stream().filter(e -> e.isInputOrOutput() == flag).count();
    }

    /**
     * Метод считает вошедших/вышедших людей из банка.
     * Вошедшие это флаг true, вышедшие - false.
     * А также указываем время, в которое делаем запрос.
     *
     * @param flag вошедшие/вышедшие - true/false
     * @param time время
     * @return количество
     */
    public long countPeopleByFlagAndByTimestamp(boolean flag, Timestamp time) {
        long count = -1;
        if (Objects.nonNull(time)) {
            count = stateOfPeople.stream().filter(e -> e.isInputOrOutput() == flag)
                    .filter(e -> e.getDate().before(time))
                    .count();
        }
        return count;
    }

    /**
     * Считаем всех людей в банке: вошедших/вышедших
     *
     * @return результирующая мапа, где по ключу true - это вошедшие, false - вышедшие
     */
    public Map<Boolean, Long> countAll() {
        Map<Boolean, Long> res = new HashMap<>();
        res.put(true, countPeopleByFlag(true));
        res.put(false, countPeopleByFlag(false));
        return res;
    }

    /**
     * Считаем имещихся в банке людей на определенное врремя.
     *
     * @param time указанное время
     * @return количество
     */
    public long countPeopleByTime(Timestamp time) {
        long result = -1;
        long input = countPeopleByFlagAndByTimestamp(true, time);
        long output = countPeopleByFlagAndByTimestamp(false, time);
        if (input != -1 && output != -1) {
            result = input - output;
        }
        return result;
    }

    /**
     * Метод принимает на вход список статусов-событий "вход" или "выход" в/из здания.
     * Мы создаем переменные `maxCapacity`, `currentCapacity`, `maxCapacityTime` и `currentCapacityTime`.
     * Инициализируем `maxCapacity` и `currentCapacity` значением 0 и `maxCapacityTime` и `currentCapacityTime` значением `null`.
     * Сортируем список событий по времени.
     * Проходим по списку статусов-событий в цикле и для каждого обновляем переменную `currentCapacity`,
     * увеличивая ее на 1 для событий "вход" и уменьшая на 1 для событий "выход".
     * Если текущая вместимость больше максимальной вместимости, мы обновляем `maxCapacity`, `maxCapacityTime` и
     * `currentCapacityTime`.
     * Возвращаем объект `TimeInfo`, который содержит информацию о максимальной вместимости,
     * и времени, когда это произошло.
     *
     * @param events список статусов-событий
     * @return результирующий объект
     */
    public TimeInfo countMaxCapacity(List<StateOfPeople> events) {
        int maxCapacity = 0; //max вместимость
        int currentCapacity = 0; //текущая вместимость
        Timestamp maxCapacityTime = null; //время max вместимости
        Timestamp currentCapacityTime = null; //время текущей вместимости

        // Сортируем список событий по времени
        events.sort(Comparator.comparing(StateOfPeople::getDate));

        // Подсчитываем количество людей в здании в каждый момент времени
        for (StateOfPeople event : events) {
            if (event.isInputOrOutput()) {
                currentCapacity++; //вход
            } else {
                currentCapacity--; //выход
            }
            // Если текущая вместимость больше максимальной вместимости, то обновляем значения
            if (currentCapacity > maxCapacity) {
                maxCapacity = currentCapacity;
                maxCapacityTime = event.getDate();
                currentCapacityTime = event.getDate();
            } else if (currentCapacity == maxCapacity) {
                currentCapacityTime = event.getDate();
            }
        }
        return new TimeInfo(maxCapacity, maxCapacityTime, currentCapacityTime);
    }
}

/**
 * Класс для хранения состояния-события человека, он вошел или вышел.
 */
@Data
class StateOfPeople {
    private Timestamp date;
    private boolean inputOrOutput; //вход - true, выход - false

    public StateOfPeople(Timestamp date, boolean inputOrOutput) {
        this.date = date;
        this.inputOrOutput = inputOrOutput;
    }
}

/**
 * Класс для хранения информации о времени и максимальной вместимости
 */
@Data
class TimeInfo {
    private int maxCapacity;
    private Timestamp maxCapacityTime;
    private Timestamp currentCapacityTime;

    public TimeInfo(int maxCapacity, Timestamp maxCapacityTime, Timestamp currentCapacityTime) {
        this.maxCapacity = maxCapacity;
        this.maxCapacityTime = maxCapacityTime;
        this.currentCapacityTime = currentCapacityTime;
    }
}
