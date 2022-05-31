package ru.pimalex1978.basepatterns.creational.prototype;

public class VersionControlRunner {
    public static void main(String[] args) {
        Project master = new Project(1, "SuperProject", "SourceCode sourceCode = new SourceCode();");

        System.out.println(master);

        ProjectFactory factory = new ProjectFactory(master);
        Project masterClone = factory.cloneProject();

        System.out.println("\n=======================\n");
        System.out.println(masterClone);

        System.out.println("\n=======================\n");

        //Проверяем что у нас глубокое копирование, т.е. изменение полей основного класса
        //не приведет к изменению полей у откопированного класса
        master.setId(2);

        System.out.println(master);
        System.out.println(masterClone);
        System.out.println("\n=======================\n");

        Project otherProject = new Project(master);
        master.setId(3);
        System.out.println(otherProject);
    }
}
