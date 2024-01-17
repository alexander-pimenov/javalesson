package ru.pimalex1978.class_date;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeExample {
    public static void main(String[] args) {
        try {
            String format = "yyyy-MM-dd HH:mm:ss";

            SimpleDateFormat df = new SimpleDateFormat(format);

            // just create some random dates for this example...

            Date from = df.parse("2014-01-01 10:05:12");
            Date to = df.parse("2016-02-01 11:03:23");
            RangeQueryBuilder dateRangeFilter = QueryBuilders
                    .rangeQuery("first_occurance")
                    .format("yyyy-MM-dd HH:mm:ss");
            dateRangeFilter.gte(df.format(from));
            dateRangeFilter.lte(df.format(to));
            System.out.println(dateRangeFilter.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
