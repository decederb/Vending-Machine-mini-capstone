package com.techelevator;

public class Beverage extends Item{

    private String name;
    private double price;
    private String slot;
    private int inventory = 5;
    private String sound = "Glug Glug, Yum!";

    public Beverage(String slot, String name, int price) {
        super(slot, name, price, "");
    }


    public String getSound() {
        return sound;
    }

    public String getName() {
        return null;
    }

    public int getPrice() {
        return 0;
    }

    public String getSlot() {
        return null;
    }


    public int getInventory() {
        return 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }


    public void purchase() {
        if (this.inventory > 0){
            this.inventory -= 1;
        }
    }


    public String dispense(String getName) {
        String dispenser = "";
        if (this.inventory == 0){
            dispenser = "This item is sold out";
        } else {
            System.out.println("Glug Glug, Yum!");
            this.inventory -= 1;
        }
        return dispenser;
    }
}
