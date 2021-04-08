package ru.pimalex.oopExample3;

public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("+ + + +");
        pic.append(System.lineSeparator());
        pic.append("+     +");
        pic.append(System.lineSeparator());
        pic.append("+     +");
        pic.append(System.lineSeparator());
        pic.append("+ + + +");
        return pic.toString();
    }
}
