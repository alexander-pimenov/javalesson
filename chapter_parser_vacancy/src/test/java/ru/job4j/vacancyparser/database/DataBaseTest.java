package ru.job4j.vacancyparser.database;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class DataBaseTest {

    @Test
    public void checkConnection() {
        DataBase dataBase = new DataBase();
        assertThat(dataBase.init(), is(true));
    }
}