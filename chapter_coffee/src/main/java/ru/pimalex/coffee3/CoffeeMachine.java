package ru.pimalex.coffee3;

public class CoffeeMachine {
    /* Name of the class has to be "Main" only if the class is public. */
        public static void main (String[] args) throws Exception
        {
            System.out.println("Кофе-машина");

            int moneyAmount = 40;

            int cappucinoPrice = 150;
            int espressoPrice = 80;
            int waterPrice = 20;
            int milkPrice = 50;

            boolean canBuyAnything = false;

            if(moneyAmount >= cappucinoPrice) {
                System.out.println("Вы можете купить капучино");
                canBuyAnything = true;
            }

            if(moneyAmount >= espressoPrice) {
                System.out.println("Вы можете купить эспрессо");
                canBuyAnything = true;
            }

            if(moneyAmount >= waterPrice) {
                System.out.println("Вы можете купить воду");
                canBuyAnything = true;
            }

            if(moneyAmount >= milkPrice) {
                System.out.println("Вы можете купить молоко");
                canBuyAnything = true;
            }

            if(canBuyAnything == false) {
                System.out.println("Недостаточно средств :( Изучайте Java и начинайте зарабатывать много! ))");
            }
        }
    }
