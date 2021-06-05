package com.techelevator;

import java.util.*;

public class VendingMachine{
    //Instance Variables
    public Map<String, ItemInventory> actualInventory = new HashMap<>();
    private int balance;//may be public
    List<Item> purchasedItems = new ArrayList<>();

    //Getters
    public int getBalance() {
        return balance;
    }

    //Setters
    public void setBalance(int balance) {
        this.balance = 0;
    }

    //Constructors
    public VendingMachine(FileInteractor fileInteractor){
        this.actualInventory = fileInteractor.load();
    }

    //Methods
    public void displayInventory(){
        Map<String, ItemInventory> orderedInventory = new TreeMap<String, ItemInventory>(actualInventory);
        for (Map.Entry<String, ItemInventory> entry : orderedInventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getItem().getName() + ", $" + entry.getValue().getItem().getPrice() + ", " + entry.getValue().getItem().getType() + ", " + entry.getValue().getInventoryCount());
        }
    }

    public void feedMoney(int money) {
        if (money % 100 == 0) {
            this.balance = balance + money;
        } else{this.balance = balance;}
    }

    public void purchase(Item item) {
        if(this.balance >= item.getPrice()) {
            this.balance = balance - item.getPrice();

        } else {
            balance = balance;
        }
    }

    public void dispense(Item item) {
        if(item.getType().equals("Chip")) {
            System.out.println("Crunch Crunch, Yum!");
        } else if (item.getType().equals("Candy")) {
            System.out.println("Munch Munch, Yum!");
        } else if (item.getType().equals("Drink")) {
            System.out.println("Glug Glug, Yum!");
        } else if (item.getType().equals("Gum")){
            System.out.println("Chew Chew, Yum!");
        }
    }


    public void giveChange(){
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        while(balance >= 25){
            this.balance = balance - 25;
            quarters++;
        }
        while(balance >= 10){
            this.balance = balance - 10;
            dimes++;
        }
        nickels = this.balance / 5;
        this.balance = 0;
        System.out.println("Your change is " + quarters + " quarter(s), " + dimes + " dime(s), and " + nickels + " nickel(s).");
    }

    }

    //TODO: Make a method for vm to dispense based on user selection

