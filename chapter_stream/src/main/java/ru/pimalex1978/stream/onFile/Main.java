package ru.pimalex1978.stream.onFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        File file = new File("text.txt");
        File file2 = new File("c://test//text.txt");
        //далее работаем в блоке try,т.к. мы в методе printStatistic делегировали обработку исключения
        try {
            printStatistic(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printStatistic(File file) throws IOException {

        Files.lines(Paths.get(file.getAbsolutePath())) //порождаем поток из файла, указываем абсолютный путь к нему file.getAbsolutePath()
                //получаем поток строк
                .map(n -> n.toUpperCase())      //переводим каждую строку в верхний регистр
                .flatMapToInt(n -> n.chars())   //нам нужен поток чаров и мы основании каждой строки генерируем поток чаров
                .filter(n -> n >= 'A' && n <= 'Z')      //отбрасываем все символы, которые не попадают в диапазон от A до Z
                .mapToObj(n -> (Character.valueOf((char) n)))   //(преобразование)пакуем каждый чар в Чарактер (объект),
                                                                // т.к. потом упакуем все это в карту, а карта работает только с объектами
                .collect(Collectors.groupingBy(n -> n, Collectors.counting())) //всё собираем в карту.
                                                                            //С помощью метода groupingBy, где ключом выступает элемент,
                                                                        //а значением сколько элемент этот втречался Collectors.counting()
                .entrySet() //получаем для карты набор ключ-значение
                .stream() //на основании этого набора порождаем поток
                .sorted((a, b) -> (int) (b.getValue() - a.getValue())) //теперь сортируем этот поток по значению
                .forEach(n -> System.out.println(n)); //теперь как то потребляем то, что делали))))
    }
}

//L=5
//E=3
//O=3
//R=2
//D=2
//H=2
//F=1
//W=1
//I=1
//N=1


