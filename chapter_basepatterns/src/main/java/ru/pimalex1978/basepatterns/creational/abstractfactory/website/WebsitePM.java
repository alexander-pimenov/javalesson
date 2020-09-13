package ru.pimalex1978.basepatterns.creational.abstractfactory.website;


import ru.pimalex1978.basepatterns.creational.abstractfactory.ProjectManger;

public class WebsitePM implements ProjectManger {
    @Override
    public void manageProject() {
        System.out.println("Website PM manages website project...");
    }
}
