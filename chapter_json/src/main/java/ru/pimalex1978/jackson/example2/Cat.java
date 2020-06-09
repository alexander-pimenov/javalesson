package ru.pimalex1978.jackson.example2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Cat extends Pet {
    public int age;
}
