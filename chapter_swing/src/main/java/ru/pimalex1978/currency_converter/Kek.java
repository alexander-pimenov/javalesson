package ru.pimalex1978.currency_converter;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Kek {
    public static void main(String[] args) {
        JDialog.setDefaultLookAndFeelDecorated(true);
        String[] selectionValues = {"$", "€", "Руб"};
        String initialSelection = "Currency";

        String selection = (String) JOptionPane.showInputDialog(null, "Какая валюта вам нужна?",
                "Обмен валют", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        String userCurrency = (String) JOptionPane.showInputDialog(null, "Какую валюту предложите?",
                "Обмен валют", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        double cource = Double.parseDouble(JOptionPane.showInputDialog("Какой курс " + userCurrency + " относительно " + selection + " ?" + "\n" + "1" + userCurrency + " ="));
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Сколько " + userCurrency + " вы собираетесь обменять?"));
        JOptionPane.showMessageDialog(null, "При конвертации" + amount + userCurrency + " в " + selection + " Вы получите " + (cource * amount) + selection);

    }
}
