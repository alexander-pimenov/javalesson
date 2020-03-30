package ru.pimalex1978.currency_converter2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private Scanner sc;
    List<Currency> currencies = new ArrayList<>();

    // 1-й шаг: открыть файл
    public void openFile() {
        try {
            sc = new Scanner(new File("C:\\projects\\javalessons\\chapter_swing\\src\\main\\java\\ru\\pimalex1978\\currency_converter2\\rates.txt"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }

    // 2-й шаг: чтение строк из файла и сохранение в ArrayList
    public void readFile() {
        while (sc.hasNext()) {
            currencies.add(new Currency(sc.next(), Double.parseDouble(sc.next())));
        }
        sc.close();
    }


    public void printOut() {
        for (int i = 0; i < currencies.size(); i++) {
            System.out.print(currencies.get(i).getName() + " ");
            System.out.println(currencies.get(i).getRate());
        }
    }

    public int sizeOf() {
        return currencies.size();
    }

    public String getName(int i) {
        return currencies.get(i).getName();
    }

    public double getRate(int i) {
        return currencies.get(i).getRate();
    }
}
