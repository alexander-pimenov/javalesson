package ru.pimalex1978;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Рано или поздно все крупные IT-компании создают свои дата-центры. Коля только устроился в
 * такую компанию и еще не успел во всем разобраться. В его компании есть N дата-цетров, в каждом
 * дата-центре установлено M серверов.
 * Из-за большой нагрузки серверы могут выключаться. Из-за спешки при постройке дата-центров
 * включить только один сервер не получается, поэтому приходится перезагружать весь дата-центр.
 * У каждого дата-центра есть два неотрицательных целочисленных параметра:
 * Ri — число перезапусков i-го дата-центра и Ai — число рабочих (не выключенных) серверов на текущий
 * момент в i-м дата-центре.
 * Коля получил задачу по сбору некоторых метрик, которые в будущем позволят улучшить работу дата-центов.
 * Для этого Коля собрал данные о Q событиях, произошедших за текущий день. Коля справился с этой задачей,
 * но просит помочь и проверить свои результаты.
 *
 * Формат ввода
 * В первой строке входных данных записано 3 положительных целых числа
 * n, m, q (1≤q≤10*5, 1≤n⋅m≤10*6) — число дата-центров, число серверов в каждом из дата-центров и число событий соответственно.
 * В последующих q строках записаны события, которые могут иметь один из следующих видов:
 * RESET i — был перезагружен i-й дата-центр (1≤i≤n)
 *
 * DISABLE i j — в i-м дата-центре был выключен j-й сервер (1≤i≤n,1≤j≤m)
 *
 * GETMAX — получить номер дата-центра с наибольшим произведением Ri∗Ai
 *
 * GETMIN — получить номер дата-центра с наименьшим произведением
 * R
 * i
 * ∗
 * A
 * i
 * Формат вывода
 * На каждый запрос вида GETMIN или GETMAX выведите единственное положительное целое число — номер дата-центра, подходящий под условие. В случае неоднозначности ответа выведите номер наименьшего из дата-центров.
 */

public class DataCenter {
    private static Scanner input = new Scanner(System.in);
    private static final String LN = System.lineSeparator();
    private static final String RESET = "RESET";
    private static final String DISABLE = "DISABLE";
    private static final String GETMAX = "GETMAX";
    private static final String GETMIN = "GETMIN";
    private static final String STOP = "стоп";
    private static final String FINISH = "закончить";
    private static final String CACHE = "кеш";

    private static final Map<String, DataCenterInfo> cache = new HashMap<>();

    public static void main(String[] args) {
        int numberOfDataCenters;
        int numberOfServers;
        int numberOfEvents;

        StringJoiner joiner = new StringJoiner(LN); //указал разделитель
        joiner.add("<<<<< Чтобы остановить программу, введите «Стоп» или «Закончить» >>>>>");
        joiner.add("Введите количество дата центров, количество серверов в дата-центрах, количество событий через пробел:");
        System.out.println(joiner);

        int[] a = new int[3];
        for (int i = 0; i < 3; i++) {
            a[i] = input.nextInt();
        }
        for (int j : a) {
            System.out.println("-= " + j);
        }
        numberOfDataCenters = a[0];
        numberOfServers = a[1];
        for (int i = 0; i < numberOfDataCenters; i++) {
            int i1 = i + 1;
            cache.put(String.valueOf(i1), new DataCenterInfo(i1, numberOfServers));
        }
        System.out.println("======кеш: " + cache);
        numberOfEvents = a[2];

        System.out.println("Введите название события:");
        doCalculation(numberOfEvents);

        input.close();
    }

    public static void doCalculation(int numberOfEvents) {
        int count = 0;
        boolean doAction = true;
        String userMessage;
        List<String> message;
        if (numberOfEvents >= 1) {
            do {
                userMessage = input.nextLine();
                if (FINISH.equalsIgnoreCase(userMessage)
                        || STOP.equalsIgnoreCase(userMessage)) {
                    System.out.println("До свидания!");
                    break;
                }
                if (CACHE.equalsIgnoreCase(userMessage)) {
                    System.out.println("Данные всех дата-центров:");
                    System.out.println(cache);
                }
                message = parseInputData(userMessage);
                if (!message.isEmpty()) {
                    for (String item : message) {
                        System.out.print(item + " ");
                    }
                    System.out.println();
                    if (!isMessageForOutput(message)) {
                        updateDataCenter(message);
                    } else {
                        calculateForOutput(message);
                    }
                }
                count++;
            } while (!userMessage.equalsIgnoreCase(FINISH)
                    || !userMessage.equalsIgnoreCase(STOP)
                    || count != numberOfEvents);
        }
    }

    private static void calculateForOutput(List<String> message) {
        if (message.size() == 1) {
            if (message.get(0).equalsIgnoreCase(GETMAX)) {
                //GETMAX — получить номер дата-центра с наибольшим произведением
                //Ri ∗ Ai
                int result = greatestMultiplicationOfNumbers();
                DataCenterInfo dataCenterInfo = findDataCenterInfoWithGreatestMultiplicationOfNumbers();
                System.out.println(" название-номер дата-центра: " + result);
                System.out.println(" объект дата-центра: " + dataCenterInfo);
            }
            if (message.get(0).equalsIgnoreCase(GETMIN)) {
                //GETMIN — получить номер дата-центра с наименьшим произведением
                //Ri ∗ Ai
                int result = leastMultiplicationOfNumbers();
                System.out.println(" название-номер дата-центра: " + result);
            }
        }
    }

    private static int leastMultiplicationOfNumbers() {
        Set<Map.Entry<String, DataCenterInfo>> entries = cache.entrySet();
        Map.Entry<String, DataCenterInfo> stringDataCenterInfoEntry = entries.stream().sorted((o1, o2) -> {
            if (o1 != null && o2 != null) {
                if (o1.getValue().getMultiplicationRandA() == o2.getValue().getMultiplicationRandA()) {
                    return (o1.getValue().getDataCenterNameLikeNumber() - o2.getValue().getDataCenterNameLikeNumber());
                } else {
                    return -(o1.getValue().getMultiplicationRandA() - o2.getValue().getMultiplicationRandA());
                }
            }
            return -1;
        }).collect(Collectors.toList()).get(0);
        String key = stringDataCenterInfoEntry.getKey();
        System.out.println(" ключ в мапе: " + key);
        return stringDataCenterInfoEntry.getValue().getDataCenterNameLikeNumber();
    }

    private static int greatestMultiplicationOfNumbers() {
        Set<Map.Entry<String, DataCenterInfo>> entries = cache.entrySet();
        Map.Entry<String, DataCenterInfo> stringDataCenterInfoEntry = entries.stream().sorted((o1, o2) -> {
            if (o1 != null && o2 != null) {
                if (o1.getValue().getMultiplicationRandA() == o2.getValue().getMultiplicationRandA()) {
                    return o1.getValue().getDataCenterNameLikeNumber() - o2.getValue().getDataCenterNameLikeNumber();
                } else {
                    return o1.getValue().getMultiplicationRandA() - o2.getValue().getMultiplicationRandA();
                }
            }
            return -1;
        }).collect(Collectors.toList()).get(0);
        String key = stringDataCenterInfoEntry.getKey();
        System.out.println(" ключ в мапе: " + key);
        return stringDataCenterInfoEntry.getValue().getDataCenterNameLikeNumber();
    }

    //этот метод не совсем верно сортирует, нужно разобраться, но принцип понятен
    private static DataCenterInfo findDataCenterInfoWithGreatestMultiplicationOfNumbers() {
        // инициализация мапы
        Map<String, DataCenterInfo> dataCenterInfoMap = cache;
        Comparator<DataCenterInfo> comparator = Comparator
                .comparing(DataCenterInfo::getDataCenterNameLikeNumber)
                .thenComparing(DataCenterInfo::getMultiplicationRandA, Comparator.reverseOrder())
                .thenComparing(DataCenterInfo::getDataCenterNameLikeNumber);

        // сортировка мапы (1 вариант)
//        Collection<DataCenterInfo> values = dataCenterInfoMap.values();
//        List<DataCenterInfo> collect = values.stream().sorted(comparator).collect(Collectors.toList());

        // сортировка мапы (2 вариант): получаем список значений и сортируем
        //List<Player> list = Collections.unmodifiableList(new ArrayList<>(dataCenterInfoMap.values()));
        //получаем из неё список (неизменяемый помещаем в new ArrayList, чтобы был изменяемым)
        List<DataCenterInfo> dataCenterInfos = new ArrayList<>(List.copyOf(dataCenterInfoMap.values()));
        dataCenterInfos.sort(comparator);

        // поиск объекта с максимальным multiplicationRandA и минимальным dataCenterNameLikeNumber
        DataCenterInfo maxDataCenterInfo = Collections.max(dataCenterInfoMap.values(), comparator);
        return maxDataCenterInfo;
    }


    private static void updateDataCenter(List<String> message) {
        if (!message.isEmpty()) {
            String nameDataCenter = message.get(1);
            DataCenterInfo dataCenterInfo = cache.get(nameDataCenter);
            if (message.size() == 3) {
                List<ServerInfo> serversList = dataCenterInfo.getServersList();
                ServerInfo serverInfo = serversList.get(Integer.parseInt(message.get(2)) - 1);
                serverInfo.setEnabled(false);
                System.out.println("отключил в дата-центре " + dataCenterInfo.getDataCenterNameLikeNumber()
                        + " сервер №" + serverInfo.getServerNameLikeNumber());
            } else {
                int numberOfRestarts = dataCenterInfo.getNumberOfRestarts();
                System.out.println(" увеличил количество перезапусков дата-центра № " + dataCenterInfo.getDataCenterNameLikeNumber());
                dataCenterInfo.setNumberOfRestarts(++numberOfRestarts);
                dataCenterInfo.setServersToOn();
            }
        }
    }

    private static boolean isMessageForOutput(List<String> message) {
        boolean result = false;
        if (message.size() == 1) {
            if (message.get(0).equalsIgnoreCase(GETMAX)
                    || message.get(0).equalsIgnoreCase(GETMIN)) {
                result = true;
            }
        }
        return result;
    }

    private static List<String> parseInputData(String userMessage) {
        List<String> resultList = new ArrayList<>();
        String trim = userMessage.trim();
        String[] s = trim.split(" ");
        if (s.length == 3) {
            if (s[0].equalsIgnoreCase(DISABLE)) {
                if (isInteger(s[1])) {
                    if (isInteger(s[2])) {
                        resultList.add(s[0]);
                        resultList.add(s[1]);
                        resultList.add(s[2]);
                    }
                }
            }
        } else if (s.length == 2) {
            if (s[0].equalsIgnoreCase(RESET)) {
                if (isInteger(s[1])) {
                    resultList.add(s[0]);
                    resultList.add(s[1]);
                }
            }
        } else if (s.length == 1) {
            if (s[0].equalsIgnoreCase(GETMAX)
                    || s[0].equalsIgnoreCase(GETMIN)) {
                resultList.add(s[0]);
            }
        }
        System.out.println("сообщение " + resultList);
        return resultList;
    }

    private static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class DataCenterInfo {
    private int dataCenterNameLikeNumber;
    private int numberOfServers;

    private int numberOfRestarts; //число перезапусков - R
    private int numberOfWorkingServers; //число работающих серверов - A
    private int multiplicationRandA;
    private List<ServerInfo> serversList = new ArrayList<>();

    public int getMultiplicationRandA() {
        return numberOfRestarts * numberOfWorkingServers;
    }

    public int getNumberOfWorkingServers() {
        int res = 0;
        if (!serversList.isEmpty()) {
            for (ServerInfo server : serversList) {
                if (server.isEnabled()) {
                    res++;
                }
            }
        }
        return res;
    }

    public DataCenterInfo(int dataCenterNameLikeNumber, int numberOfServers) {
        this.dataCenterNameLikeNumber = dataCenterNameLikeNumber;
        this.numberOfServers = numberOfServers;
        this.serversList = createServersList(numberOfServers);
    }

    private List<ServerInfo> createServersList(int numberOfServers) {
        List<ServerInfo> result = new ArrayList<>();
        for (int i = 0; i < numberOfServers; i++) {
            result.add(new ServerInfo(i + 1, true));
        }
        return result;
    }

    public void setServersToOn() {
        for (ServerInfo server : getServersList()) {
            server.setEnabled(true);
        }
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ServerInfo {
    private int serverNameLikeNumber;
    private boolean enabled;
}
