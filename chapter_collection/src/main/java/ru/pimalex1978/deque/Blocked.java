package ru.pimalex1978.deque;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Нижеприведенная программа Blocked, демонстрирует использование интерфейса Deque,
 * а вернее класса LinkedBlockingDeque с установленными границами очереди. Конечно,
 * это не лучший пример использования двусторонней очереди, но он позволяет показать
 * применение API и события, возникающие при достижении предела очереди. Данная
 * программа получает 23 названия месяцев (полные и сокращенные) и по одному добавляет
 * их в начало очереди, поддерживающей шесть элементов. Другой поток удаляет элементы
 * из начла и конца двусторонней очереди, основываясь на количестве элементов,
 * находящихся в коллекции.
 */

public class Blocked {
//    public static void main(String[] args) {
//        Calendar now = Calendar.getInstance();
//        Locale locale = Locale.getDefault();
//        final Console console = System.console();
//        final Map names = now.getDisplayNames(Calendar.MONTH,
//                Calendar.ALL_STYLES, locale);
//        console.printf("Starting names: %s%n", names);
//        final Deque deque = new LinkedBlockingDeque(6);
//        // Добавление элемента в начало
//        new Thread() {
//            public void run() {
//                Set keys = names.keySet();
//                Iterator itor = keys.iterator();
//                String element = null;
//                while (itor.hasNext() || element != null) {
//                    if (element == null) {
//                        element = (String)itor.next();
//                        console.printf("MapGot: %s%n", element);
//                    }
//                    console.printf("Offering: %s%n", element);
//                    if (deque.offerFirst(element)) {
//                        console.printf("MapRemoving: %s%n", element);
//                        itor.remove();
//                        element = null;
//                    } else {
//                        try {
//                            Thread.sleep(250);
//                        } catch (InterruptedException ignored) {
//                        }
//                    }
//                }
//                try {
//                    Thread.sleep(3500);
//                } catch (InterruptedException ignored) {
//                }
//                System.exit(0);
//            }
//        }.start();
//        while (true) {
//            if ((deque.size() % 2 == 1)) {
//                // удаление из начала
//                console.printf("Remove head: %s%n", deque.pollFirst());
//            } else {
//                // удаление из конца
//                console.printf("Remove tail: %s%n", deque.pollLast());
//            }
//            // пауза между циклами
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException ignored) {
//            }
//        }
//    }
}
