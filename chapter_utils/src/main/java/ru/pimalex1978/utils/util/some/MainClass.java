package ru.pimalex1978.utils.util.some;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainClass {
    public static void main(String[] args) {
        try {
            String process;
            Process p = Runtime.getRuntime().exec("ps -few");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((process = input.readLine()) != null) {
                System.out.println(process);
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
