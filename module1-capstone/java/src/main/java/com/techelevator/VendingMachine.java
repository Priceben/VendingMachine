package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

    String choice = "";
    public void log(String key, String choice) {
        File logFile = new File("C:\\Users\\Student\\workspace\\green-mod1-capstone-team2\\module1-capstone\\java\\src\\main\\java\\com\\techelevator\\log.txt");
        try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile.getAbsoluteFile(), true), true)) {

            DateTimeFormatter americanDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String americanDate = LocalDate.now().format(americanDateFormat);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
            String rightFormat = LocalTime.now().format(timeFormatter);

            logWriter.write(americanDate + " " + rightFormat);


        } catch (FileNotFoundException e) {
            System.out.println("Oops Something Went Wrong");
        }
        }

    public void testLog(String key, String choice) {
        if (choice.equals("PURCHASE_MENU_OPTION_BUY_ITEM")) {
            File logFile = new File("C:\\Users\\Student\\workspace\\green-mod1-capstone-team2\\module1-capstone\\java\\src\\main\\java\\com\\techelevator\\log.txt");
            try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile.getAbsoluteFile(), true), true)) {
                logWriter.write("hiiiiiiiiiiii it's me!");
            } catch (FileNotFoundException e) {
                System.out.println("Oops Something Went Wrong");
            }
        }
        if (choice.equals("PURCHASE_MENU_OPTION_BUY_ITEM")) {
            //write the logic to pull the name and price of the key given
        }
        if (choice.equals("PURCHASE_MENU_OPTION_CASH_OUT")) {
            //write the logic
        }
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

