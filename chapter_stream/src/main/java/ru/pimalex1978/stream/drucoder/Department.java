package ru.pimalex1978.stream.drucoder;

import java.util.HashSet;
import java.util.Set;

public class Department {
    //id департамента
    private final int id;
    //id родителя департамента
    private final int parent;
    //название департамента
    private final String name;
    //коллекция дочерних элементов
    private Set<Department> child = new HashSet<>();


    public Department(int id, int parent, String name) {
        this.id = id;
        this.parent = parent;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public int getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public Set<Department> getChild() {
        return child;
    }
}
