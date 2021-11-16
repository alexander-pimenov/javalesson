package ru.pimalex1978.class_date;
// Java program to demonstrate the
// use of before() function

import java.sql.Timestamp;

public class TestTimestamp {
    public static void main(String args[]) {

        // Create two timestamp objects
        //Можем их менять, чтобы было видно как работает сравнение значений
        Timestamp ts1 = new Timestamp(10000); //11000
        Timestamp ts2 = new Timestamp(10000); //11000

        boolean b = ts2.before(ts1);

        // Check if the second timestamp occurs
        // before first timestamp
        if (b) {

            // If true print that the Second Timestamp
            // occurs before the first timestamp
            System.out.println("Second Timestamp occurs"
                    + " before first timestamp");
        } else {

            // If false print that the Second Timestamp
            // does not occur before the first timestamp
            System.out.println("Second Timestamp does not"
                    + " occur before first timestamp");
        }
    }
}
