package ru.pimalex1978.make_type_without_declaring_it;

public interface Кот {
    default void мяукать(){
        System.out.println("мяу");
    }
}
