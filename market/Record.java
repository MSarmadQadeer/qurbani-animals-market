package market;

import java.io.*;
import java.util.ArrayList;

public abstract class Record {
    public static Integer animalNo = 0;
    public static ArrayList<Person> customersRecord = new ArrayList<>();
    public static ArrayList<Person> sellersRecord = new ArrayList<>();

    public static void writeAnimalNo() {
        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("val.ser"));
            outputStream.writeObject(animalNo);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readAnimalNo() {
        ObjectInputStream inputStream;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("val.ser"));
            Record.animalNo = (Integer) inputStream.readObject();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
        }
    }

    public static void writeRecordToFile() {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("Record.ser"));
            for (Person customer : customersRecord)
                outputStream.writeObject(customer);
            for (Person seller : sellersRecord)
                outputStream.writeObject(seller);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Record.writeAnimalNo();
            try {
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readRecordFromFile() {
        ObjectInputStream inputStream = null;
        ArrayList<Person> customers = new ArrayList<>();
        ArrayList<Person> sellers = new ArrayList<>();
        try {
            inputStream = new ObjectInputStream(new FileInputStream("Record.ser"));
            boolean EOF = false;
            while (!EOF) {
                try {
                    Person person = (Person) inputStream.readObject();
                    if (person.toString().contains("Seller")) sellers.add(person);
                    else if (person.toString().contains("Customer")) customers.add(person);
                } catch (EOFException e) {
                    sellersRecord = sellers;
                    customersRecord = customers;
                    EOF = true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            //System.out.println("Welcome Dear First Customer of Qurbani Animals Market");
        } finally {
            Record.readAnimalNo();
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Person returnPerson(ArrayList<Person> arrayList) {
        String name;
        Person person = null;
        boolean foundPerson = false;
        name = Input.inputLine("Enter Name : ").toUpperCase();
        for (Person value : arrayList) {
            if (name.equals(value.getName())) {
                foundPerson = true;
                person = value;
                break;
            }
        } //for loop for checking name is closing
        if (!foundPerson) return null;
        String password;
        Customer person2;
        Seller person3;
        /*
        I TOOK THESE DIFFERENT PERSONS FOR TYPE CASTING BECAUSE
        "PASSWORD" ATTRIBUTE IS NOT AVAILABLE IN PERSON.
        */
        for (int i = 0; true; i++) {
            password = Input.inputLine("Enter Password : ");
            if (arrayList == Record.customersRecord) {
                person2 = (Customer) person;
                if (password.equals(person2.getPasswordObject().getPassword())) return person2;
                else if (i == 3) {
                    return null;
                } else {
                    System.out.println("\nKINDLY ENTER IN THE RIGHT PASSWORD");
                    System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
                }
            } else if (arrayList == Record.sellersRecord) {
                person3 = (Seller) person;
                if (password.equals(person3.getPasswordObject().getPassword())) return person3;
                else if (i == 3) {
                    return null;
                } else {
                    System.out.println("\nKINDLY ENTER IN THE RIGHT PASSWORD");
                    System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
                }
            }
        }//for loop for checking password is closing
    }//returnPerson Closing
}
