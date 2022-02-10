package market;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<Item> cartList = new ArrayList<>();

    public void addToCart(Item item) {
        cartList.add(item);
        Seller.removeItem(item);
        System.out.println("Successfully Added To Cart");
    }

    public void removeFromCart(Item item) {
        if (cartList.contains(item)) {
            cartList.remove(item);
            Seller.addItem(item);
            System.out.println("Successfully Removed From Cart");
        }
    }

    public void setCartList(ArrayList<Item> cartList) {
        this.cartList = cartList;
    }

    public ArrayList<Item> getCartList() {
        return cartList;
    }

    public double calculateTotalCost() {
        double totalCost = 0;
        for (Item item : this.cartList) {
            totalCost += item.getAnimal().getCost();
        }
        return totalCost;
    }

    public void displayBill() {
        System.out.println("BILL : " + this.calculateTotalCost());
    }

    public ArrayList<String> displayAndReturnIds() {
        ArrayList<String> cartItemsIds = new ArrayList<>();
        System.out.println("<--- Animals --->");
        if (cartList.size() == 0) System.out.println("Cart Is Empty");
        else {
            System.out.printf("%-3s | %-10s | %-3s | %-10s | %-15s | %-5s | %-10s\n", "ID", "CATEGORY", "AGE", "GENDER", "COLOR", "WEIGHT", "COST");
            System.out.println("-".repeat(74));
            for (Item item : cartList) {
                Animal animal = item.getAnimal();
                System.out.printf("%-3s | %-10s | %-2.1f | %-10s | %-15s | %-3.2f | %-10.2f\n", animal.getId(), animal.getCategory(), animal.getAge(), animal.getGender(), animal.getColor(), animal.getWeight(), animal.getCost());
                cartItemsIds.add(item.getAnimal().getId());
            }
        }
        return cartItemsIds;
    }
}
