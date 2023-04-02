package pimalex1978.console_menu;

public class ConsoleMenu3 {
    public static void main(String[] args) {
        startPO();
    }

    private static void startPO() {
        boolean exit = false;
        while (!exit) {
            int swValue;
            System.out.println("============================");
            System.out.println("|   MENU SELECTION DEMO    |");
            System.out.println("============================");
            System.out.println("| Options:                 |");
            System.out.println("|        1. Option 1       |");
            System.out.println("|        2. Option 2       |");
            System.out.println("|        3. Exit           |");
            System.out.println("============================");
            swValue = Keyin.inInt(" Select option: ");

            // Switch construct
            switch (swValue) {
                case 1:
                    System.out.println("Option 1 selected");
                    break;
                case 2:
                    System.out.println("Option 2 selected");
                    break;
                case 3:
                    System.out.println("Exit selected");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid selection");
                    break; // This break is not really necessary
            }
        }
    }
}

class Keyin {

    //*******************************
    //   support methods
    //*******************************
    //Method to display the user's prompt string
    //Метод отображения строки приглашения пользователя
    public static void printPrompt(String prompt) {
        System.out.print(prompt + " ");
        System.out.flush();
    }

    //Method to make sure no data is available in the
    //input stream
    //Метод проверки отсутствия данных во входном потоке
    public static void inputFlush() {
        int dummy;
        int bAvail;

        try {
            while ((System.in.available()) != 0)
                dummy = System.in.read();
        } catch (java.io.IOException e) {
            System.out.println("Input error");
        }
    }

    public static String inString() {
        int aChar;
        String s = "";
        boolean finished = false;

        while (!finished) {
            try {
                aChar = System.in.read();
                if (aChar < 0 || (char) aChar == '\n') {
                    finished = true;
                } else if ((char) aChar != '\r') {
                    s = s + (char) aChar; // Enter into string
                }
            } catch (java.io.IOException e) {
                System.out.println("Input error");
                finished = true;
            }
        }
        return s;
    }

    public static int inInt(String prompt) {
        while (true) {
            inputFlush();
            printPrompt(prompt);
            try {
                return Integer.parseInt(inString().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Not an integer");
            }
        }
    }

}
