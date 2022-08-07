package ru.pimalex1978.stream.human;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class MainHuman {

    //с помощью stream получим список имен питомцев человека.
    public static void main(String[] args) {
        List<Human> humans = asList(
                new Human("Sam", asList("Buddy", "Lucy")),
                new Human("Bob", asList("Frankie", "Rosie")),
                new Human("Marta", asList("Simba", "Tilly")));

        List<String> petNames = humans.stream()
                .map(human -> human.getPets()) //Преобразовываем Stream<Human> в Stream <List<Pet>>
                .flatMap(pets -> pets.stream()) //"разворачиваем" Stream<List<Pet>> в Stream<Pet>
                .collect(Collectors.toList());
        System.out.println("Имена питомцев: " + petNames);

        /*Верхний код, но со ссылками на методы!!!*/
        humans.stream()
                .map(Human::getPets)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        /*И еще более сокращенный верхний код*/
        humans.stream()
                .flatMap(human -> human.getPets().stream())
                .collect(Collectors.toList());


    }
}
