package market;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends Person implements Serializable, Display {
    private Cart cart;
    private String id;
    private Password passwordObject;

    public Customer() {
        super();
        passwordObject = new Password();
        this.cart = new Cart();
        this.setId(String.format("%s%d", "QAM-Customer-", Record.customersRecord.size() + 1));
    }

    public void buy() {
        ArrayList<String> animalIds;
        String animal;
        System.out.println("\n------------------------------------");
        System.out.println("| COW  SHEEP  GOAT  BUFFALO  CAMEL |");
        System.out.println("------------------------------------\n");
        animal = Checks.checkedInputThroughString("| COW  SHEEP  GOAT  BUFFALO  CAMEL |");
        animalIds = Store.displayAndReturnIds(animal);
        Item item = Store.checkSelectAndReturnItem(animalIds);
        if (item != null) cart.addToCart(item);
    }

    public void emptyCart() {
        this.setCart(new Cart());
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPasswordObject(Password passwordObject) {
        this.passwordObject = passwordObject;
    }

    public Password getPasswordObject() {
        return passwordObject;
    }

    public void editProfile() {
        setName(Input.inputLine("Enter Your Name : ").toUpperCase());
        setAddress(Input.inputLine("Enter Your Address : "));
    }

    @Override
    public void display() {
        super.display();
        System.out.println("ID : " + getId());
    }

    public void PerformFunctionality() {
        ArrayList<String> cartItemsIds = null;
        this.display();
        CUSTOMER_MENU:
        while (true) {
            int buyQuantity = this.getCart().getCartList().size();
            if (buyQuantity == 0) System.out.println("\n1 - TO BUY AN ANIMAL");
            else System.out.println("\n1 - TO BUY ANOTHER ANIMAL");

            System.out.println("2 - TO EDIT YOUR PROFILE");
            System.out.println("3 - TO RESET PASSWORD");
            if (buyQuantity > 0) {
                System.out.println("4 - TO VIEW YOUR CART");
                System.out.println("5 - TO CHECKOUT");
            }
            System.out.println("0 - TO MOVE BACK TO MAIN MENU");
            String customerMenu;
            if (buyQuantity == 0) customerMenu = Checks.checkedInputThroughString("0 1 2 3");
            else customerMenu = Checks.checkedInputThroughString("0 1 2 3 4 5");

            switch (customerMenu) {
                case "1": //TO BUY AN ANIMAL
                    this.buy();
                    Store.writeStoreToFile();
                    Record.writeRecordToFile();
                    continue CUSTOMER_MENU;
                case "2": //TO EDIT YOUR PROFILE
                    this.editProfile();
                    this.display();
                    Record.writeRecordToFile();
                    continue CUSTOMER_MENU;
                case "3": //TO RESET PASSWORD
                    this.passwordObject.resetPassword();
                    Record.writeRecordToFile();
                    continue CUSTOMER_MENU;
                case "4": //TO VIEW YOUR CART
                    if (this.getCart() != null) {
                        cartItemsIds = this.getCart().displayAndReturnIds();
                    }
                    CART_MENU:
                    while (true) {
                        System.out.println("\n1 - TO REMOVE AN ITEM FROM CART");
                        System.out.println("2 - TO VIEW SELLER'S PROFILE");
                        System.out.println("0 - TO MOVE BACK");
                        String cartOption = Checks.checkedInputThroughString("1 2 0");
                        switch (cartOption) {
                            case "0": //TO MOVE BACK
                                continue CUSTOMER_MENU;
                            case "1": //TO REMOVE AN ITEM FROM CART
                                System.out.println("(ENTER ID OF ITEM YOU WANNA REMOVE)");
                                String removeItemId = Checks.checkedInputThroughArrayList(cartItemsIds);
                                for (Item item : this.getCart().getCartList()) {
                                    if (item.getAnimal().getId().equals(removeItemId)) {
                                        this.getCart().removeFromCart(item);
                                        break;
                                    }
                                }
                                cartItemsIds = this.getCart().displayAndReturnIds();
                                Store.writeStoreToFile();
                                Record.writeRecordToFile();
                                continue CUSTOMER_MENU;
                            case "2": //TO VIEW SELLER'S PROFILE
                                if (cartItemsIds == null) cartItemsIds = this.getCart().displayAndReturnIds();
                                System.out.println("(ENTER ID OF ITEM WHO'S SELLER DETAILS YOU WANNA SEE)");
                                String viewSellerId = Checks.checkedInputThroughArrayList(cartItemsIds);
                                for (Item item : this.getCart().getCartList()) {
                                    if (item.getAnimal().getId().equals(viewSellerId)) {
                                        viewSellerId = item.getSellerId();
                                        break;
                                    }
                                }
                                for (Person person : Record.sellersRecord) {
                                    Seller seller = (Seller) person;
                                    if (seller.getId().equals(viewSellerId)) {
                                        seller.displayProfile();
                                        continue CART_MENU;
                                    }
                                }
                        }
                    }
                case "5": //TO CHECKOUT
                    this.display();
                    this.getCart().displayAndReturnIds();
                    this.getCart().displayBill();
                    this.emptyCart();
                    System.out.println("<<< ALLAH HAFIZ >>>");
                    Record.writeRecordToFile();
                    Store.writeStoreToFile();
                    continue CUSTOMER_MENU;
                case "0":
                    Main.main(null);
                    System.exit(0);
            }
        }
    }

    public static Customer signIn() {
        Person person = Record.returnPerson(Record.customersRecord);
        if (person == null) System.out.println("\nNo Customer Found of This Name");
        return (Customer) person;
    }

    public static void signUp() {
        Person customer = new Customer();
        Record.customersRecord.add(customer);
        Record.writeRecordToFile();
        System.out.println("CONGRATS! YOU HAVE SUCCESSFULLY BEEN REGISTERED AS A CUSTOMER");
    }
}