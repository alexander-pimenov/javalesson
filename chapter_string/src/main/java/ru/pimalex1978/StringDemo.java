package ru.pimalex1978;

public class StringDemo {
    public static void main(String[] args) {
        String cat1 = "Cat";
        String cat2 = new String("Cat");
        String newString = String.valueOf(cat1.charAt(0)) + String.valueOf(cat2.charAt(1)) + cat1.charAt(2);
        System.out.println(newString); //Cat

        if (newString.endsWith("t")) {
            System.out.println("URA!");
        }

        System.out.println(newString.indexOf("t")); //2

        String cat11 = "Cat";
        String cat22 = "Cat";
        String cat33 = new String("Cat");
        String cat44 = cat33.intern();

        System.out.println(cat11 == cat22); //true - т.к. указывают на один и тот же объект
        System.out.println(cat11 == cat33); //false


//        Сравниваем массивы символов, т.е. массивы по символьно равны
        System.out.println(cat11.equals(cat22)); //true
        System.out.println(cat11.equals(cat33)); //true
        System.out.println(cat11.equals(cat44)); //true


        //Изменяемый объект строка - в итоге один объект стринг
        StringBuffer sb = new StringBuffer("Privet");
        for (int i = 0; i < 50; i++) {
//            sb += i;
            sb.append(i);
        }
        System.out.println(sb.toString());

        //Неизменяемый объект строка - в итоге 50 объектов стринг
        String sbs = new String("Privet");
        for (int i = 0; i < 50; i++) {
            sbs += i;
        }
        System.out.println(sbs.toString());
    }
}
