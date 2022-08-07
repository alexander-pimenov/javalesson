package ru.pimalex1978.streamepam.beans;

import java.util.Map;

public class Developer {
    private String name;
    private Map<String, Integer> skillMatrix;

    public Developer(String name, Map<String, Integer> skillMatrix) {
        this.name = name;
        this.skillMatrix = skillMatrix;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getSkillMatrix() {
        return skillMatrix;
    }
}
