package ru.pimalex1978.basepatterns.behavioral.template;

public class WelcomePage extends WebsiteTemplate{

    @Override
    public void showPageContent() {
        System.out.println("Welcome");
    }
}
