package market;

import java.io.*;
import java.util.ArrayList;

public abstract class Store {
    public static ArrayList<Item> storeItemsList = new ArrayList<>();

    public static void add(Item item) {
        storeItemsList.add(item);
    }

    public static void remove(Item item) {
        storeItemsList.remove(item);
    }

    public static void writeStoreToFile() {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("Store.ser"));
            for (Item item : storeItemsList)
                outputStream.writeObject(item);
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readStoreFromFile() {
        ObjectInputStream inputStream = null;
        ArrayList<Item> items = new ArrayList<>();
        try {
            inputStream = new ObjectInputStream(new FileInputStream("Store.ser"));
            boolean EOF = false;
            Item item = null;
            while (!EOF) {
                try {
                    item = (Item) inputStream.readObject();
                    items.add(item);
                } catch (EOFException e) {
                    EOF = true;
                    storeItemsList = items;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            //System.out.println("This Program Is Running For The First Time");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<String> displayAndReturnIds(String category) {
        ArrayList<String> ids = new ArrayList<>();
        System.out.printf("\n%-3s | %-10s | %-3s | %-10s | %-15s | %-5s | %-12s\n", "ID", "CATEGORY", "AGE", "GENDER", "COLOR", "WEIGHT", "COST");
        System.out.println("-".repeat(71));
        int countingAnimals = 0;
        for (Item item : storeItemsList) {
            if (item.getAnimal().getCategory().equalsIgnoreCase(category)) {
                item.getAnimal().display();
                ids.add(item.getAnimal().getId());
                countingAnimals++;
            }
        }
        if (countingAnimals == 0) System.out.println("SORRY ... NO ANIMAL OF THIS CATEGORY IS REGISTERED YET");
        return ids;
    }

    public static Item checkSelectAndReturnItem(ArrayList<String> ids) {
        if (ids.size() == 0) return null;
        String id;
        for (int i = 0; true; i++) {
            id = Input.inputWord("\nEnter ID to Buy : ");
            if (ids.contains(id)) break;
            else if (i == 3) {
                return null;
            } else {
                System.out.println("\nKINDLY ENTER A VALID ID");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
        for (Item item : storeItemsList) {
            if (item.getAnimal().getId().equals(id)) {
                return item;
            }
        }
        return null;
    } //checkSelectAndReturnItem closing
}
