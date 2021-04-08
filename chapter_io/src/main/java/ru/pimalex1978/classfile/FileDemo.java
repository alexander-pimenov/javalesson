package ru.pimalex1978.classfile;

import java.io.File;

/**
 * В приведенном ниже примере программы демонстрируется
 * применение некоторых методов из класса Fi1е.
 * При этом подразумевается, что
 * в корневом каталоге существует каталог java с файлом COPYRIGHT.
 */
public class FileDemo {
    static void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        File f1 = new File("/test/COPYRIGHT.txt");
        print("Имя файла: " + f1.getName());
        print("Путь: " + f1.getPath());
        print("Абсолютный путь: " + f1.getAbsolutePath());
        print("Родительский каталог: " + f1.getParent());
        print(f1.exists() ? "существует" : "не существует");
        print(f1.canWrite() ? "доступен для записи" : "не доступен для записи");
        print(f1.canRead() ? "доступен для чтения" : "не доступен для чтения");
        print(f1.isDirectory() ? "является каталогом" : "не является каталогом");
        print(f1.isFile() ? "является обычным файлом" : "может быть именованным каналом");
        print(f1.isAbsolute() ? "является абсолютным" : "не является абсолютным");
        print("Последние из менения в файле: " + f1.lastModified());
        print("Размер " + f1.length() + " байт");
    }
}
