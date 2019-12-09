package ru.pimalex1978.classFile;

import java.io.File;

public class ClassFile {
    public static void main(String[] args) {

        //обращаемся к файлу, указав путь к нему. Через двойные бэкслеши
        File x = new File("c:\\test\\greg.txt");

        if (x.exists()) {
            System.out.println(x.getName() + " exist!");
        } else
            System.out.println("This thing doesn't exist!");

        //проверим работу класса CreateFile. После запуска программы
        //загляни в "c:\\test\\chinese.txt"
        CreateFile cf = new CreateFile();

        cf.createFile();
        cf.addRecords();
        cf.closeFile();

        //проверим работу класса ReadFile
        //смотрим консоль
        ReadFile rf = new ReadFile();

        rf.openFile();
        rf.readFile();
        rf.closeFile();
    }
}
