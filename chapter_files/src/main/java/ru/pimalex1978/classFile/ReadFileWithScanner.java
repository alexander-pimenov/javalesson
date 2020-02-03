package ru.pimalex1978.classFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileWithScanner {
    public static void main(String[] args) throws IOException {

        File file = new File("c:\\test\\test_sum.txt");
        Scanner sc;
        String line;
        int index = 0;
        List<Integer> intList = new ArrayList<>();


        //нужно открыть файл
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null) {
            sc = new Scanner(line);
            sc.useDelimiter(" ");
            while (sc.hasNext()) {
                String next = sc.next();
                intList.add(Integer.parseInt(next));
                index++;
                System.out.println(index + " + " + next);
            }

            System.out.println(intList);
        }
        //находим сумму с помощью стрима
        int sum = intList.stream().mapToInt(p -> p).sum();

        //находим сумму с помощью цикла
//        int result = 0;
//        for (int i : intList) {
//            result = result + i;
//        }
        reader.close();
        System.out.println(sum);
//        System.out.println(result);

        //запишем данные в этот же файл но на другой строке
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);

        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("\n" + sum);

        //А также запищем ответ в другой файл
        File fileWrite = new File("c:\\test\\test_sum_new.txt");
        // Если файл не существует, то создадим его
        if (!fileWrite.exists())
            fileWrite.createNewFile();
        FileWriter fw2 = new FileWriter(fileWrite.getAbsoluteFile(), true);
        BufferedWriter bw2 = new BufferedWriter(fw2);
        bw2.write(sum + "\n");


        bw.close();
        bw2.close();
    }
}