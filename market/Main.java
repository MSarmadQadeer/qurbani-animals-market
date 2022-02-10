package market;

public class Main implements Checks {
    public static void main(String[] args) {
        Store.readStoreFromFile();
        Record.readRecordFromFile();

        System.out.println("------------------------------------");
        System.out.println("<---   QURBANI ANIMALS MARKET   --->");
        System.out.println("------------------------------------\n\n");
        MAIN_MENU:
        while (true) {
            System.out.println("\n1 - IF You Are The Customer");
            System.out.println("2 - IF You Are The Seller");
            System.out.println("0 - IF You Want To Exit");
            String mainMenuOption = Checks.checkedInputThroughStringForMainMenu("0 1 2 ");
            switch (mainMenuOption) {
                case "0": //MAIN_MENU (EXIT)
                    System.exit(0);
                case "1": //MAIN_MENU (IF You Are The Customer)
                    SIGNING_MENU:
                    while (true) {
                        System.out.println("\n1 - To Sign In");
                        System.out.println("2 - To Sign Up");
                        System.out.println("0 - To Move Back");
                        String signingMenuOption = Checks.checkedInputThroughString("0 1 2");
                        switch (signingMenuOption) {
                            case "0": // MOVE BACK TO MAIN MENU
                                continue MAIN_MENU;
                            case "1": // Sign In Customer
                                Customer customer = Customer.signIn();
                                if (customer != null) customer.PerformFunctionality();
                                continue SIGNING_MENU;
                            case "2": // Sign up Customer
                                Customer.signUp();
                        }
                    }
                case "2":  //MAIN_MENU (IF You Are The Seller)
                    SIGNING_MENU:
                    while (true) {
                        System.out.println("\n1 - To Sign In");
                        System.out.println("2 - To Sign Up");
                        System.out.println("0 - To Move Back");
                        String signingMenuOption = Checks.checkedInputThroughString("0 1 2");
                        switch (signingMenuOption) {
                            case "0": // MOVE BACK TO MAIN MENU
                                continue MAIN_MENU;
                            case "1": // Sign In Seller
                                Seller seller = Seller.signIn();
                                if (seller != null) seller.PerformFunctionality();
                                continue SIGNING_MENU;
                            case "2": // Sign up Seller
                                Seller.signUp();
                        } //switch (signingMenuOptions) of SELLER is closing
                    } //SIGNING_MENU while of SELLER is closing
            } //switch (mainMenuOptions closing)
        } //MAIN_MENU while closing
    } //main method closing
} //Main class closing
