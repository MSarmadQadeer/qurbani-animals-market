package market;

import java.io.Serializable;

public class Item implements Serializable {
    private Animal animal;
    private String date;
    private String time;
    private String sellerId;

    /*
    I am giving seller's id to item because if the
    customer wants to see the details of the seller
    of that particular item then the program will
    find the seller with the help of his id and display
    the details.
     */
    public Item(String sellerId) {
        animal = new Animal();
        setDate(DateAndTime.getCurrentDate());
        setTime(DateAndTime.getCurrentTime());
        setSellerId(sellerId);
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public boolean equals(Item item) {
        return this.getAnimal().equals(item.getAnimal()) && this.getSellerId().equals(item.getSellerId());
    }
}
