package ru.pimalex1978;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class FileSystem {
    public static void main(String[] args) {
        final File f = new File("C:/projects/javalessons");
        final File[] listFiles = f.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });

        final String[] listStr = f.list((dir, name) -> {
            return name.endsWith(".git");
        });

        System.out.println(Arrays.toString(listFiles));

        System.out.println("==========");

        System.out.println(Arrays.toString(listStr));
    }
}
