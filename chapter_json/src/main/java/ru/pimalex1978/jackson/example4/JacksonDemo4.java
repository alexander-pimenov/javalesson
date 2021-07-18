package ru.pimalex1978.jackson.example4;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * Jackson десериализация полиморфных типов.
 */

public class JacksonDemo4 {
    public static void main(String[] args) throws IOException {
        Skeleton sk = new Skeleton();
        sk.setBones(10);
        sk.setHealth(100);

        Skeleton sk2 = new Skeleton();
        sk2.setBones(12);
        sk2.setHealth(120);

        Vampire vm = new Vampire();
        vm.setBloodCollected(20);
        vm.setHealth(200);

        Vampire vm2 = new Vampire();
        vm2.setBloodCollected(22);
        vm2.setHealth(220);

        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(sk);
        monsters.add(vm);
        monsters.add(vm2);
        monsters.add(sk2);

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, monsters);
        System.out.println(writer.toString());
        //[{"health":100,"bones":10},{"health":200,"bloodCollected":20}]

        //можно записать иначе, одной строкой, как для отдельного объекта cat так и house:
        String stringCat = new ObjectMapper().writeValueAsString(sk);
        System.out.println(stringCat);
        //{"@class":"ru.pimalex1978.jackson.example4.Skeleton","health":100,"bones":10}
        //{"type":"s_k_e_l_e_t","health":100,"bones":10}

        String stringHouse = new ObjectMapper().writeValueAsString(monsters);
        System.out.println(stringHouse);
        //[{"health":100,"bones":10},{"health":200,"bloodCollected":20}]
    }
}
