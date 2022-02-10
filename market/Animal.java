package market;

import java.io.Serializable;
import java.util.Arrays;

public class Animal implements Serializable, Display {
    private String category;
    private double age;
    private String gender;
    private String color;
    private double weight;
    private double cost;
    private String id;

    public Animal() {
        setCategory();
        setAge(getCategory());
        setGender();
        setColor();
        setWeight(getCategory());
        setCost();
        String id = (++Record.animalNo).toString();
        setId(id);
        System.out.println("SUCCESSFULLY A NEW ANIMAL IS ADDED TO STORE");
    }

    public void setCategory() {
        String[] animalCategories = {"COW", "BUFFALO", "GOAT", "SHEEP", "CAMEL"};
        String category;
        for (int i = 0; true; i++) {
            category = Input.inputWord("\nEnter Category: ").toUpperCase();
            if (Arrays.toString(animalCategories).contains(category))
                break;
            else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER A VALID CATEGORY");
                System.out.println("| COW-SHEEP-GOAT-BUFFALO-CAMEL |");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public double getAge() {
        return age;
    }

    public void setAge(String category) {
        String ageInput;
        double age;
        for (int i = 0; true; i++) {
            ageInput = Input.inputWord("\nEnter Age : ");
            if (Checks.isDouble(ageInput)) {
                age = Double.parseDouble(ageInput);
                if ((category.equals("SHEEP") || category.equals("GOAT")) && age >= 1) {
                    this.age = age;
                    break;
                } else if (((category.equals("COW") || category.equals("BUFFALO")) && age >= 2)) {
                    this.age = age;
                    break;
                } else if (category.equals("CAMEL") && age >= 5) {
                    this.age = age;
                    break;
                } else {
                    System.out.println("\nAGE IS SHORT FOR QURBANI");
                    System.out.println("Minimum Ages :");
                    System.out.println("GOAT and SHEEP  - 1 YEAR");
                    System.out.println("COW and BUFFALO - 2 YEAR");
                    System.out.println("CAMEL           - 5 YEAR");
                    System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
                    if (i == 3) {
                        Main.main(null);
                        System.exit(0);
                    }
                }
            } else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER A VALID AGE");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
    }

    public void setGender() {
        String[] animalGenders = {"MALE", "FEMALE"};
        String gender;
        for (int i = 0; true; i++) {
            gender = Input.inputWord("\nEnter Gender : ").toUpperCase();
            if (Arrays.toString(animalGenders).contains(gender))
                break;
            else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER A VALID GENDER");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setColor() {
        String[] animalColors = {"BLACK", "WHITE", "BROWN", "BLACK AND WHITE", "DOTTED"};
        String color;
        for (int i = 0; true; i++) {
            color = Input.inputWord("\nEnter Color : ").toUpperCase();
            if (Arrays.toString(animalColors).contains(color))
                break;
            else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER A VALID COLOR");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(String category) {
        String weightInput;
        double weight;
        for (int i = 0; true; i++) {
            weightInput = Input.inputWord("\nEnter Weight : ");
            if (Checks.isDouble(weightInput)) {
                weight = Double.parseDouble(weightInput);
                if ((category.equals("SHEEP") || category.equals("GOAT")) && weight >= 30) {
                    this.weight = weight;
                    break;
                } else if (((category.equals("COW") || category.equals("BUFFALO")) && weight >= 100)) {
                    this.weight = weight;
                    break;
                } else if (category.equals("CAMEL") && weight >= 200) {
                    this.weight = weight;
                    break;
                } else {
                    System.out.println("\nWEIGHT IS SHORT FOR QURBANI");
                    System.out.println("Minimum Weights :");
                    System.out.println("GOAT and SHEEP  - 30 KG");
                    System.out.println("COW and BUFFALO - 100 KG");
                    System.out.println("CAMEL           - 200 KG");
                    System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
                    if (i == 3) {
                        Main.main(null);
                        System.exit(0);
                    }
                }
            } else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER A VALID WEIGHT");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
    }

    public void setCost() {
        String costInput;
        for (int i = 0; true; i++) {
            costInput = Input.inputWord("\nEnter Cost : ");
            if (Checks.isDouble(costInput)) {
                this.cost = Double.parseDouble(costInput);
                break;
            } else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER A VALID COST");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
    }

    public double getCost() {
        return cost;
    }

    public boolean equals(Animal animal) {
        return this.getCategory().equalsIgnoreCase(animal.getCategory()) && this.getAge() == animal.getAge()
                && this.getColor().equalsIgnoreCase(animal.getColor()) && this.getCost() == animal.getCost()
                && this.getGender().equalsIgnoreCase(animal.getGender()) && this.getWeight() == animal.getWeight()
                && this.id.equals(animal.id);
    }

    public void editProfile() {
        setCategory();
        setAge(getCategory());
        setGender();
        setColor();
        setWeight(getCategory());
        setCost();
    }

    public void display() {
        System.out.printf("%-3s | %-10s | %-2.1f | %-10s | %-15s | %-3.2f | %-10.2f\n",
                getId(), getCategory(), getAge(), getGender(), getColor(), getWeight(), getCost());
    }
}
