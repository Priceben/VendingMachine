package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {
    //Instance Variables
    public Map<String, ItemInventory> actualInventory = new HashMap<>();
    private int balance;
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
    public VendingMachine(FileInteractor fileInteractor) {
        this.actualInventory = fileInteractor.load();
    }

    //Methods
    public void displayInventory() {
        Map<String, ItemInventory> orderedInventory = new TreeMap<String, ItemInventory>(actualInventory);
        for (Map.Entry<String, ItemInventory> entry : orderedInventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getItem().getName() + ", $" + entry.getValue().getItem().getPrice() + ", " + entry.getValue().getItem().getType() + ", " + entry.getValue().getInventoryCount());
        }
    }

    public void feedMoney(int money) {
        if (money % 100 == 0) {
            this.balance = balance + money;
        } else {
            this.balance = balance;
        }
    }

    public void purchase(String key) {
        if (this.balance >= actualInventory.get(key).getItem().getPrice()) {
            this.balance = balance - actualInventory.get(key).getItem().getPrice();
            actualInventory.get(key).setInventoryCount(actualInventory.get(key).getInventoryCount() - 1);
        } else {
            balance = balance;
        }
    }

    public void dispense(String key) {
        if (actualInventory.get(key).getItem().getType().equals("Chip")) {
            System.out.println("Crunch Crunch, Yum!");
        } else if (actualInventory.get(key).getItem().getType().equals("Candy")) {
            System.out.println("Munch Munch, Yum!");
        } else if (actualInventory.get(key).getItem().getType().equals("Drink")) {
            System.out.println("Glug Glug, Yum!");
        } else if (actualInventory.get(key).getItem().getType().equals("Gum")) {
            System.out.println("Chew Chew, Yum!");
        }
    }

    public void giveChange() {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        while (balance >= 25) {
            this.balance = balance - 25;
            quarters++;
        }
        while (balance >= 10) {
            this.balance = balance - 10;
            dimes++;
        }
        nickels = this.balance / 5;
        this.balance = 0;
        System.out.println("Your change is " + quarters + " quarter(s), " + dimes + " dime(s), and " + nickels + " nickel(s).");
    }


    public void log(String key) {
        File logFile = new File("C:\\Users\\Student\\workspace\\green-mod1-capstone-team2\\module1-capstone\\java\\src\\main\\java\\com\\techelevator\\log.txt");
        try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile.getAbsoluteFile(), true), true)) {
           // logWriter.write(actualInventory.get(key).getItem().getName());
            DateTimeFormatter americanDateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String americanDate = LocalDate.now().format(americanDateFormat);
          //  logWriter.write(americanDate + " " + LocalTime.now() + LocalDateTime.now());
            logWriter.write(americanDate + " " + LocalTime.now());


        } catch (FileNotFoundException e) {
            System.out.println("Oops Something Went Wrong");
        }


        //01/01/2016 12:00:00 PM FEED MONEY: \$5.00 \$5.00
        //
        //>01/01/2016 12:00:15 PM FEED MONEY: \$5.00 \$10.00
        //>01/01/2016 12:00:20 PM Crunchie B4 \$10.00 \$8.50
        //>01/01/2016 12:01:25 PM Cowtales B2 \$8.50 \$7.50
        //>01/01/2016 12:01:35 PM GIVE CHANGE: \$7.50 \$0.00

    }

    public boolean isInStock(String key){
        if(actualInventory.get(key).getInventoryCount() > 0){
            return true;
        } return false;
    }

    public boolean isThereEnoughBalance (String key){
        if(getBalance() >= actualInventory.get(key).getItem().getPrice()){
            return true;
        } return false;
    }

}

