package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public abstract class Register {
    //Instance Variables
    private int balance;//may be public
    List<Item> purchasedItems = new ArrayList<>();


    //Getters
    public int getBalance() {
        return balance;
    }

    public List<Item> getPurchasedItems() {
        return purchasedItems;
    }

    //Setters
    public void setBalance(int balance) {
        this.balance = 0;
    }

    public void setPurchasedItems(List<Item> itemsOnReceipt) {
        this.purchasedItems = itemsOnReceipt;
    }

    //Constructors
    public Register(){}

    //Methods
    //TODO: How does Register class access Actual Inventory Map from Vending Machine Class?
    public void purchase(Item item) {
       if(this.balance >= item.getPrice()) {
           this.balance = balance - item.getPrice();
       } else {
           balance = balance;
       }
    }

    public void feedMoney(int money) {
        if (money % 100 == 0) {
            this.balance = balance + money;
        } else{this.balance = balance;}
    }

    public String giveChange(){
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
        return "Your change is " + quarters + " quarter(s), " + dimes + " dime(s), and " + nickels + " nickel(s).";
    }

    //TODO: find how many items are in purchased items and update the inventory of each item in Vending Machine

}
