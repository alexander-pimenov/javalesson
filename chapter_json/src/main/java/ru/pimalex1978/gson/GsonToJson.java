package ru.pimalex1978.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Gson предоставляет простые методы toJson () и fromJson () для преобразования объектов Java в / из JSON.
 * В Gson мы можем использовать gson.toJson () для преобразования объектов Java в JSON.
 */
public class GsonToJson {

    public static void main(String[] args) {

        //pretty print - красивая печать
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Staff staff = createStaffObject();

        //Java object to String
        String json = gson.toJson(staff);

        System.out.println(json);

        //Java object to File
        try (FileWriter writer = new FileWriter("c:\\test\\staff.json")) {
            gson.toJson(staff, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Staff createStaffObject() {

        Staff staff = new Staff();

        staff.setName("mkyong");
        staff.setAge(35);
        staff.setPosition(new String[]{"Founder", "CTO", "Writer"});
        Map<String, BigDecimal> salary = new HashMap<>() {{
            put("2010", new BigDecimal(10000));
            put("2012", new BigDecimal(12000));
            put("2018", new BigDecimal(14000));
        }};
        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));

        return staff;
    }
}
