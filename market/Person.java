package market;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private String name;
    private String address;

    public Person() {
        setName(Input.inputLine("Enter Your Name : ").toUpperCase());
        setAddress(Input.inputLine("Enter Your Address : "));
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void display() {
        System.out.printf("\n<--- %s --->\n", this.getName());
        System.out.println("Address: " + this.getAddress());
    }
}