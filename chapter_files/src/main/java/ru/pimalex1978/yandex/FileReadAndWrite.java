package ru.pimalex1978.yandex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * В файле находятся два числа 20 и 40 на разных строках, читаем их из файла,
 * сохраняем в список, т.к. их может быть и больше, с помощью стрима (мне так понравилось)
 * суммируем считанные числа и результат записываем с новой строки в этот же файл.
 */


public class FileReadAndWrite {
    public static void main(String[] args) throws IOException {

        /*Чтение данных из файла.*/
        //file - это путь к файлу, который предварительно создали
        File file = new File("c:\\test\\test_Files2.txt");

        //Строка для работы с консолью, выводим вопрос и в зависимости, как на него отвечаем
        //продолжаем работу или нет.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //Для чтения из файла используем "матрешку чтения" (BufferedReader(FileReader(File)))
        FileReader fr = new FileReader(file);
        BufferedReader fin = new BufferedReader(fr);
        String name;
        String line;
        List<String> a = new ArrayList<>();
        System.out.println("Print File " + file.getName() + "? y/n");
        name = br.readLine();
        if (name.equals("y")) {
            while ((line = fin.readLine()) != null) {
                System.out.println(line);
                a.add(line);
            }
        }
        fin.close();

        System.out.println("list a : " + a);

        //Вычислим сумму полученных чисел.
        int sum = a.stream().mapToInt(Integer::parseInt).sum();
        System.out.println(sum);

        //Преобразуем число int sum в String resultSum, чтобы записать в файл
        String resultSum = Integer.toString(sum);

        /*Запись данных в файл.*/
        try {
            //System.out.println("Запишем строку content в файл"); // если записать текст в файл, то будет Ошибка, мы не получим суммы
            //String content = "Данную строку запишем в файл"; // если записать текст в файл, то будет Ошибка, мы не получим суммы
            System.out.println("И значение sum запишем в файл");
            //File file = new File("c:\\test\\test_Files2.txt"); - здесь можно выбрать другой файл, указав путь к нему.

            // Если файл не существует, то создадим его
            if (!file.exists())
                file.createNewFile();

            //Для записи данных в файл используем "матрешку записи" (BufferedWriter(FileWriter(File)))
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); //true - говорит, что можем дописать данные в файл
            //если писать без true, то файл перезапишется новыми данными
            BufferedWriter bw = new BufferedWriter(fw);
            //bw.write(content + "\n"); // если записать текст в файл, то будет Ошибка, мы не получим суммы

            bw.write("\n" + resultSum);
            bw.close();

            System.out.println("Запись завершена");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
