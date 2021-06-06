package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.security.Key;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class VendingMachine {
    //Instance Variables
    public Map<String, ItemInventory> actualInventory = new HashMap<>();
    private BigDecimal zero = new BigDecimal("0.00");
    private BigDecimal previousBalance = new BigDecimal("0.00");
    private BigDecimal newBalance = new BigDecimal("0.00");
    List<Item> purchasedItems = new ArrayList<>();
    BigDecimal quarterWorth = new BigDecimal(".25");
    BigDecimal dimeWorth = new BigDecimal(".10");
    BigDecimal nickelWorth = new BigDecimal(".05");

    //Getters
    public BigDecimal getPreviousBalance() {
        return previousBalance;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    //Setters
    public void setPreviousBalance(BigDecimal previousBalance) {
        this.previousBalance = zero;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
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

    public void feedMoney(BigDecimal money) {
        BigDecimal dollarBill = new BigDecimal("1.0");
        BigDecimal twoDollarBill = new BigDecimal("2.0");
        BigDecimal fiveDollarBill = new BigDecimal("5.0");
        BigDecimal tenDollarBill = new BigDecimal("10.0");

        if (money.compareTo(dollarBill) == 0 || money.compareTo(twoDollarBill) == 0 || money.compareTo(fiveDollarBill) == 0 || money.compareTo(tenDollarBill) == 0) {
            //this.previousBalance = previousBalance;
            this.newBalance = previousBalance.add(money);
        } else {
            this.previousBalance = previousBalance;
            System.out.println("This machine only accepts $1, $2, $5 & $10 bills.");
        }
        this.previousBalance = newBalance;
    }

    public void purchase(String key) {
        BigDecimal itemPrice = new BigDecimal(actualInventory.get(key).getItem().getPrice().toString());
        this.previousBalance = newBalance;
        if (this.previousBalance.compareTo(itemPrice) == 1) {
            this.newBalance = previousBalance.subtract(itemPrice);
            actualInventory.get(key).setInventoryCount(actualInventory.get(key).getInventoryCount() - 1);
        } else {
            this.newBalance = previousBalance;
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
        previousBalance = newBalance;
        double balance = (newBalance.doubleValue() * 100);
        int quarters = ((int) balance / 25);
        balance = balance - (quarters * 25);
        int dimes = ((int) balance / 10);
        balance = balance - (dimes * 10);
        int nickels = ((int) balance / 5);
        newBalance = zero;
        System.out.println("Your change is " + quarters + " quarter(s), " + dimes + " dime(s), and " + nickels + " nickel(s).");
    }

    public void purchaseLog(String key) {
        File logFile = new File("log.txt");
        if (logFile.exists()) {
            try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile, true),true)) {

                DateTimeFormatter americanDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String americanDate = LocalDate.now().format(americanDateFormat);

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
                String rightFormat = LocalTime.now().format(timeFormatter);
                if (actualInventory.containsKey(key)) {
                    logWriter.write(americanDate + " " + rightFormat + " " + actualInventory.get(key).getItem().getName() + " " + key.toUpperCase() + ": \\$" + getPreviousBalance() + " \\$" + getNewBalance() + "\n");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Oops Something Went Wrong");
            }
        }
    }

        public void log (String choice) {
            File logFile = new File("log.txt");
            if (logFile.exists()) {
                try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile,true),true)) {

                    DateTimeFormatter americanDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    String americanDate = LocalDate.now().format(americanDateFormat);

                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
                    String rightFormat = LocalTime.now().format(timeFormatter);

                    if (choice.equals("Add Money")) {
                        logWriter.write(americanDate + " " + rightFormat + " " + choice.toUpperCase() + ": \\$" + getPreviousBalance() + " \\$" + getNewBalance() + "\n");
                    }

                    if (choice.equals("Cash out")) {
                        logWriter.write(americanDate + " " + rightFormat + " " + choice.toUpperCase() + ": \\$" + getPreviousBalance() + " \\$" + getNewBalance() + "\n");
                        previousBalance = zero;
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("Oops Something Went Wrong");
                }
            }
        }


        public boolean isInStock (String key) {
            try {
                if (actualInventory.get(key).getInventoryCount() > 0) {
                    return true;
                }
                //return false;
            } catch (NullPointerException e) {
                e.getMessage();
            }
            return false;
        }

        public boolean isThereEnoughBalance (String key){
           this.previousBalance = newBalance;
            BigDecimal itemPrice = new BigDecimal(actualInventory.get(key).getItem().getPrice().toString());
            if (getPreviousBalance().compareTo(itemPrice) == 1 || getPreviousBalance().compareTo(itemPrice) == 0) {
                return true;
            }
            return false;
        }

    }


