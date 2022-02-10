package market;

import java.util.Scanner;

public interface Input {

    public static int inputInt(String inputText) {
        Scanner input = new Scanner(System.in);
        System.out.print(inputText);
        return input.nextInt();
    }

    public static double inputDouble(String inputText) {
        Scanner input = new Scanner(System.in);
        System.out.print(inputText);
        return input.nextDouble();
    }

    public static String inputLine(String inputText) {
        Scanner input = new Scanner(System.in);
        System.out.print(inputText);
        return input.nextLine();
    }

    public static String inputWord(String inputText) {
        Scanner input = new Scanner(System.in);
        System.out.print(inputText);
        return input.next();
    }
}
