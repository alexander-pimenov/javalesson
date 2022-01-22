package ru.pimalex1978.lambda;

public class LambdaMain2 {
    public static void main(String[] args) {
        //создадим кота и убедимся, что он пустой
        Cat cat = new Cat();
        System.out.println(cat);

        //создадим лямбду для интерфейса Settable
        Settable<Cat> settable = (obj, name, age) -> {
            obj.setName(name);
            obj.setAge(age);
        };

        //вызовем метод, в который передадим кота и лямбду
        //Поскольку переменная cat у нас ссылочного типа, то и объект
        //Кот вне лямбда-выражения изменится (получит переданные внутрь имя и возраст).
        changeEntity(cat, settable);
        System.out.println(cat);

    }

    // Лямбда-выражения отлично сочетаются с дженериками.
    // И если нам понадобится создать класс Dog, например, который тоже будет имплементить WithNameAndAge,
    // то в методе main() мы можем те же операции проделать и с Cобакой, абсолютно не меняя сами лямбда-выражение
    private static <T extends WithNameAndAge> void changeEntity(T entity, Settable<T> settable) {
        settable.set(entity, "Мурзик", 3);
    }
}

interface WithNameAndAge {
    void setName(String name);

    void setAge(int age);
}

interface Settable<C extends WithNameAndAge> {
    void set(C entity, String name, int age);
}

class Cat implements WithNameAndAge {
    private String name;
    private int age;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}