package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class Register {
    //Instance Variables
    private int balance;//may be public
    List<Item> purchaseditems = new ArrayList<>();
    private final int quarter = 25;
    private final int dime = 10;
    private final int nickel = 5;

    //Getters
    public int getBalance() {
        return balance;
    }

    public List<Item> getItemsOnReceipt() {
        return purchaseditems;
    }

    public int getQuarter() {
        return quarter;
    }

    public int getDime() {
        return dime;
    }

    public int getNickel() {
        return nickel;
    }

    //Setters
    public void setBalance(int balance) {
        this.balance = 0;
    }

    public void setItemsOnLog(List<Item> itemsOnReceipt) {
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
    public int giveChange(){
        int countQuarters = 0;
        int countDimes = 0;
        int countNickels = 0;
        while(balance >= 25){
            this.balance = balance - 25;
            countQuarters++;
        }
    }
}
