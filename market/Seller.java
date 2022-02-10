package market;

import java.io.Serializable;
import java.util.ArrayList;

public class Seller extends Person implements Serializable, Display {
    private ArrayList<String> hisItemsIds = new ArrayList<>();
    private String contactNo;
    private String id;
    private Password passwordObject;

    Seller() {
        super();
        setContactNo();
        passwordObject = new Password();
        setId(String.format("%s%d", "QAM-Seller-", Record.sellersRecord.size() + 1));
    }

    public void sell() {
        Item item = new Item(this.id);
        Seller.addItem(item);
    }

    public void setHisItemsIds(ArrayList<String> hisItemsIds) {
        this.hisItemsIds = hisItemsIds;
    }

    public ArrayList<String> getHisItemsIds() {
        return hisItemsIds;
    }

    public void setContactNo() {
        String contactNo;
        char[] contactArray;
        for (int i = 0; true; i++) {
            contactNo = Input.inputWord("Enter Contact No : ");
            contactArray = contactNo.toCharArray();
            boolean correctNo = false;
            if (contactArray.length == 12) {
                for (int j = 0; j < 12; j++) {
                    if (contactArray[4] == '-') correctNo = true;
                    else if (Checks.isInteger(Character.toString(contactArray[j]))) correctNo = true;
                    else {
                        correctNo = false;
                        break;
                    }
                }
                if (correctNo) {
                    this.contactNo = contactNo;
                    break;
                }
            } else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER A VALID Contact No#");
                System.out.println("Format : 0 3 _ _ - _ _ _ _ _ _ _");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
    }

    public String getContactNo() {
        return contactNo;
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

    public static void removeItem(Item item) {
        for (Person person : Record.sellersRecord) {
            Seller seller = (Seller) person;
            if (item.getSellerId().equals(seller.getId())) {
                seller.getHisItemsIds().remove(item.getAnimal().getId());
                Store.remove(item);
                break;
            }
        }
    }

    public static void addItem(Item item) {
        for (Person person : Record.sellersRecord) {
            Seller seller = (Seller) person;
            if (item.getSellerId().equals(seller.getId())) {
                seller.getHisItemsIds().add(item.getAnimal().getId());
                Store.add(item);
                break;
            }
        }
    }

    public void displayProfile() {
        super.display();
        System.out.println("ID : " + this.getId());
        System.out.println("Contact N0# " + this.getContactNo());
    }

    public void editProfile() {
        setName(Input.inputLine("Enter Your Name : ").toUpperCase());
        setAddress(Input.inputLine("Enter Your Address : "));
        setContactNo();
    }

    public void display() {
        this.displayProfile();
        this.displayItemsThroughIds();
    }

    private void displayItemsThroughIds() {
        System.out.println("\n<--- Animals --->");
        if (this.getHisItemsIds().size() == 0) System.out.println("NO ANIMALS SOLD YET");
        else {
            System.out.printf("%-3s | %-10s | %-3s | %-10s | %-15s | %-5s | %-10s | %-12s | %-8s\n", "ID", "CATEGORY", "AGE", "GENDER", "COLOR", "WEIGHT", "COST", "DATE", "TIME");
            System.out.println("-".repeat(100));
            for (String itemId : getHisItemsIds()) {
                for (Item item : Store.storeItemsList) {
                    if (item.getSellerId().equals(this.getId()) && item.getAnimal().getId().equals(itemId)) {
                        Animal animal = item.getAnimal();
                        System.out.printf("%-3s | %-10s | %-2.1f | %-10s | %-15s | %-3.2f | %-10.2f | %-12s | %-8s\n", animal.getId(), animal.getCategory(), animal.getAge(), animal.getGender(), animal.getColor(), animal.getWeight(), animal.getCost(), item.getDate(), item.getTime());
                        break;
                    }
                }
            }
        }
    }

    public void PerformFunctionality() {
        this.displayProfile();
        SELLER_MENU:
        while (true) {
            int sellQuantity = this.getHisItemsIds().size();
            if (sellQuantity == 0) System.out.println("\n1 - TO SELL AN ANIMAL");
            else System.out.println("\n1 - TO SELL ANOTHER ANIMAL");

            System.out.println("2 - TO EDIT YOUR PROFILE");
            System.out.println("3 - TO RESET PASSWORD");
            if (sellQuantity > 0) System.out.println("4 - TO VIEW YOUR ANIMALS");

            System.out.println("0 - TO MOVE BACK TO MAIN MENU");
            String sellerMenuOptions;
            if (sellQuantity == 0) sellerMenuOptions = Checks.checkedInputThroughString("0 1 2 3");
            else sellerMenuOptions = Checks.checkedInputThroughString("0 1 2 3 4");

            switch (sellerMenuOptions) {
                case "1": //TO SELL AN ANIMAL
                    this.sell();
                    Store.writeStoreToFile();
                    Record.writeRecordToFile();
                    continue SELLER_MENU;
                case "2": // TO EDIT YOUR PROFILE
                    this.editProfile();
                    this.displayProfile();
                    Record.writeRecordToFile();
                    continue SELLER_MENU;
                case "3": //TO RESET PASSWORD
                    this.passwordObject.resetPassword();
                    Record.writeRecordToFile();
                    continue SELLER_MENU;
                case "4": //TO VIEW YOUR ANIMALS
                    this.displayItemsThroughIds();
                    while (true) {
                        System.out.println("\n1 - TO REMOVE AN ITEM");
                        System.out.println("2 - TO EDIT ANIMAL DETAILS");
                        System.out.println("0 - TO MOVE BACK");
                        String sellerItemsOptions = Checks.checkedInputThroughString("1 2 0");
                        switch (sellerItemsOptions) {
                            case "0": //TO MOVE BACK
                                continue SELLER_MENU;
                            case "1": //TO REMOVE AN ITEM
                                System.out.println("(ENTER ID OF ITEM YOU WANNA REMOVE)");
                                String removeItemId = Checks.checkedInputThroughArrayList(this.getHisItemsIds());
                                for (Item item : Store.storeItemsList) {
                                    if (item.getAnimal().getId().equals(removeItemId)) {
                                        Seller.removeItem(item);
                                        this.getHisItemsIds().remove(removeItemId);
                                        break;
                                    }
                                }
                                this.displayItemsThroughIds();
                                Store.writeStoreToFile();
                                Record.writeRecordToFile();
                                continue SELLER_MENU;
                            case "2": //TO EDIT ANIMAL DETAILS
                                System.out.println("(ENTER ID OF ITEM WHO'S DETAILS YOU WANNA MODIFY)");
                                String editDetailsId = Checks.checkedInputThroughArrayList(this.getHisItemsIds());
                                for (Item item : Store.storeItemsList) {
                                    if (item.getAnimal().getId().equals(editDetailsId)) {
                                        item.getAnimal().editProfile();
                                        this.displayItemsThroughIds();
                                        break;
                                    }
                                }
                                Store.writeStoreToFile();
                                Record.writeRecordToFile();
                        }//switch (sellerItemsOptions) is closing
                    } //SELLER_ITEMS_MENU while is closing
                case "0": // SELLER_MENU (TO MOVE BACK TO MAIN MENU)
                    Main.main(null);
                    System.exit(0);
            } //switch (sellerMenuOptions) is closing
        } //SELLER_MENU while closing
    }

    public static Seller signIn() {
        Person person = Record.returnPerson(Record.sellersRecord);
        if (person == null) System.out.println("\nNo Seller Found of This Name");
        return (Seller) person;
    }

    public static void signUp() {
        Person seller = new Seller();
        Record.sellersRecord.add(seller);
        Record.writeRecordToFile();
        System.out.println("CONGRATS! YOU HAVE SUCCESSFULLY BEEN REGISTERED AS A SELLER");
    }
}
