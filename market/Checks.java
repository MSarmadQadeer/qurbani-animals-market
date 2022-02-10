package market;

import java.util.ArrayList;
import java.util.Arrays;

public interface Checks {

    public static String checkedInputThroughString(String options) {
        String choose;
        for (int i = 0; true; i++) {
            choose = Input.inputWord("\nChoose: ").toUpperCase();
            if (options.contains(choose)) break;
            else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER A VALID CHOICE");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
        return choose;
    }

    public static String checkedInputThroughStringForMainMenu(String options) {
        String choose;
        for (int i = 0; true; i++) {
            choose = Input.inputWord("\nChoose: ").toUpperCase();
            if (options.contains(choose)) break;
            else if (i == 3) {
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER A VALID CHOICE");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
        return choose;
    }

    public static String checkedInputThroughArrayList(ArrayList<String> options) {
        String choose;
        for (int i = 0; true; i++) {
            choose = Input.inputWord("\nChoose: ");
            if (options.contains(choose)) break;
            else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER A VALID CHOICE");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
        return choose;
    }

    public static String checkedInputThroughArray(String[] options) {
        String choose;
        for (int i = 0; true; i++) {
            choose = Input.inputWord("\nChoose: ");
            if (Arrays.toString(options).contains(choose)) break;
            else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER A VALID CHOICE");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
        return choose;
    }

    public static boolean isInteger(String str) {
        try {
            int a = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            double a = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
