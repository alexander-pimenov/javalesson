package ru.pimalex1978.yandex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ReadAndWriteFile {
    public static void main(String[] args) throws IOException {

        File file = new File("c:\\test\\test_Files2.txt");
        File file4 = new File("c:\\test\\test_Files4.txt");

        //Создадим файл №4:
        // Если файл не существует, то создадим его
        if (!file4.exists())
            file4.createNewFile();
        //Запишем в него данные:
        //В параметре new File("c:\\test\\test_Files4.txt") не присутствует append:true,
        //это позволяет нам каждый раз перезаписывать файл
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("c:\\test\\test_Files4.txt")));
        bufferedWriter.write("20" + "\n");
        bufferedWriter.write("40" + "\n");
        bufferedWriter.close();

        /*Читаем из файла №4*/
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file4));
        String lineFromFile4;
        List<String> dataList = new ArrayList<>();

        while ((lineFromFile4 = bufferedReader.readLine()) != null) {
            dataList.add(lineFromFile4);
        }
        bufferedReader.close();
        int sumData = dataList.stream().mapToInt(Integer::parseInt).sum();
        //Переделаем в тип String
        String valueString = String.valueOf(sumData);

        /*Записываем данные суммы в файл №5*/
        File file5 = new File("c:\\test\\test_Files5.txt");
        try {
            if (!file5.exists())
                file5.createNewFile();
            BufferedWriter writeFile5 = new BufferedWriter(new FileWriter(file5.getAbsoluteFile()));
            writeFile5.write(valueString);
            writeFile5.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Чтение из файла №2*/
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        List<String> arr = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            arr.add(line);
        }

        br.close();

        int sum = arr.stream().mapToInt(Integer::parseInt).sum();

        String resultSum = Integer.toString(sum);

        /*Запись данных в файл №2.*/
        try {

//            // Если файл не существует, то создадим его
//            if (!file.exists())
//                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("\n" + resultSum);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
