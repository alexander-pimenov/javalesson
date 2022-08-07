package ru.pimalex1978.lambda;
/*Получение списка файлов из какой либо директории.
 * Используем лямбда-выражения.
 * Т.е. лямбда-выражением мы более компактно записываем вызов метода
 * интерфейса с одним методом (функциональный интерфейс).
 * Лямбда-выражение представляет собой две части.
 * 1 часть - это набор входных парметров. Они могут быть и нулевые.
 * 2 часть - это сама функция, к-рая возвращает какое то значение.*/

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Starter {
    public static void main(String[] args) {


        File src = new File("."); //указали текущую директорию
        //метод listFiles() вернет нам массив файлов.
        //он может быть без параметров, выведет все файлы, а так же можем иметь параметры.
        //один из параметров это данные типа интерфейс FileFilter, который
        //накладывает ограничения на то, какие файлы нам выводить: директории,
        //или все, или не видимые.
        File[] files = src.listFiles(new MyFilter());

        //напечатаем наши файлы
        for (File file : files) {
            System.out.println(file.getName());
        }

        //напишем этот же код но с помощью аноним.класса
        File[] filesAnon = src.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory(); //выводить только директории
            }
        });
        //напечатаем наши файлы
        for (File file : filesAnon) {
            System.out.println(file.getName());
        }

        //теперь вариант с лямбдами
        File[] filesLamb = src.listFiles(p -> p.isDirectory());
        //или так:
        //File[] filesLamb = src.listFiles(p -> { return p.isDirectory(); });
        //проверка, что файлы есть. И вывод на экран с помощью стримов.
        if (filesLamb != null) {
            Arrays.stream(filesLamb).forEach(System.out::println);
        }

        //создаем список строк
        List<String> list = new ArrayList<>();
        //заполним его. Этот лист имеет правильный порядок
        for (int i = 0; i < 10; i++) {
            list.add(String.format("%02d", i));
        }

//        //напечатаем лист
//        for (String s : list){
//            System.out.println(s);
//        }

        //отсортируем список в неправильном порядке, т.е. вверх ногами
        Collections.sort(list, (o1, o2) -> o2.compareTo(o1));
        //напечатаем лист
        for (String s : list) {
            System.out.println(s);
        }
    }
}

//создал класс который имплиментирует интерфейс FileFilter,
//нужный нам для наложения ограничений. 
class MyFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        return !pathname.isDirectory(); //включать в класс файлы только не директории
    }
}
