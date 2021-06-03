package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class Register {
    //Instance Variables
    private int balance;//may be public
    List<Item> purchaseditems = new ArrayList<>();

    //Getters
    public int getBalance() {
        return balance;
    }

    public List<Item> getItemsOnReceipt() {
        return purchaseditems;
    }

    //Setters
    public void setBalance(int balance) {
        this.balance = 0;
    }

    public void setItemsOnReceipt(List<Item> itemsOnReceipt) {
        this.purchaseditems = itemsOnReceipt;
    }

    //Constructors -- Don't think we need them

    //Methods
    public void purchase(Item item) {
       if(this.balance >= item.getPrice()) {
           purchaseditems.add(item);
           this.balance = balance - item.getPrice();
       } else {
           System.out.println("Not enough balance!");
       }
    }

    public void feedMoney(int money){
        this.balance = balance + money;
    }
}
