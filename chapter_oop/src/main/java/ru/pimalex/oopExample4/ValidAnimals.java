package ru.pimalex.oopExample4;

public enum ValidAnimals {
    DUCK(Duck.class),
    DOG(Dog.class);

    private ValidAnimals(Class animal) {
        this.animal = animal;
    }

    private final Class animal;

    public Class getAnimalClass() {
        return animal;
    }

    public static boolean isValid(Animal otherAnimal) {
        for (ValidAnimals animal : ValidAnimals.values()) {
            if (animal.getAnimalClass() == otherAnimal.getClass()) {
                return true;
            }
        }
        return false;
    }
}
