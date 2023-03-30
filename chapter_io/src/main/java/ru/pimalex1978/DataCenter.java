package ru.pimalex1978;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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
                System.out.println(" название-номер дата-центра: " + result);
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
