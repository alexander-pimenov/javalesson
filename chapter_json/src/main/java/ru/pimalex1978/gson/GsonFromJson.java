package ru.pimalex1978.gson;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * В Gson мы можем использовать gson.fromJson для преобразования JSON обратно в объекты Java.
 */
public class GsonFromJson {
    public static void main(String[] args) {

        Gson gson = new Gson();

        try (Reader reader = new FileReader("c:\\test\\staff.json")) {

            // Convert JSON File to Java Object
            Staff staff = gson.fromJson(reader, Staff.class);

            // print staff
            System.out.println(staff);

            System.out.println("Staff name - "+staff.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
