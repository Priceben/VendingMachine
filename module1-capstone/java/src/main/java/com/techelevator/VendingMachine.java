package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
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

    public BigDecimal getNewBalance() { return newBalance; }

    //Setters
    public void setPreviousBalance(BigDecimal previousBalance) {
        this.previousBalance = zero;
    }

    public void setNewBalance(BigDecimal newBalance) { this.newBalance = newBalance; }

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
        this.previousBalance = newBalance;

        if (money.compareTo(dollarBill) == 0 || money.compareTo(twoDollarBill) == 0 || money.compareTo(fiveDollarBill) == 0 || money.compareTo(tenDollarBill) == 0) {
            //this.previousBalance = previousBalance;
            this.newBalance = previousBalance.add(money);
        } else {
            this.previousBalance = previousBalance;
            System.out.println("This machine only accepts $1, $2, $5 & $10 bills.");
        }
    }

    public void purchase(String key) {
        BigDecimal itemPrice = new BigDecimal(actualInventory.get(key).getItem().getPrice().toString());
        this.previousBalance = newBalance;
        if (this.previousBalance.compareTo(itemPrice) == 1) {
            this.newBalance = previousBalance.subtract(itemPrice);
            actualInventory.get(key).setInventoryCount(actualInventory.get(key).getInventoryCount() - 1);
        } else {
            previousBalance = previousBalance;
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
        double balance1;
        int quarters1;
        int dimes1;
        int nickels1;
        this.previousBalance = newBalance;

        balance1 = (newBalance.doubleValue() * 100);
        quarters1 = ((int) balance1 / 25);
        balance1 = balance1 - (quarters1 * 25);
        dimes1 = ((int) balance1 / 10);
        balance1 = balance1 - (dimes1 * 10);
        nickels1 = ((int) balance1 / 5);
        newBalance = zero;
        System.out.println("Your change is " + quarters1 + " quarter(s), " + dimes1 + " dime(s), and " + nickels1 + " nickel(s).");
    }


    //TODO: FIX THIS BEAUTIFUL CODE
    /*public void giveChange() {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        while (this.balance.compareTo(quarterWorth) == 1 || this.balance.compareTo(quarterWorth) == 0) {
            this.balance.subtract(quarterWorth);
            quarters++;
        }
        while (this.balance.compareTo(dimeWorth) == 1 || this.balance.compareTo(dimeWorth) == 0) {
            this.balance.subtract(dimeWorth);
            dimes++;
        }
        while (this.balance.compareTo(nickelWorth) == 1 || this.balance.compareTo(nickelWorth) == 0) {
            this.balance.subtract(nickelWorth);
            nickels++;
        }
        this.balance.equals(zero);
        System.out.println("Your change is " + quarters + " quarter(s), " + dimes + " dime(s), and " + nickels + " nickel(s).");
    }*/




    public void log(String choice) {
        File logFile = new File("C:\\Users\\Student\\workspace\\green-mod1-capstone-team2\\module1-capstone\\java\\src\\main\\java\\com\\techelevator\\log.txt");
        try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile.getAbsoluteFile(), true), true)) {

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

            if (choice.equals("Buy Item")) {

            }

          //  if (choice.equals("Buy Item")) {
          //      logWriter.write(americanDate + " " + rightFormat + " " + choice.toUpperCase() + ": \\$" + getPreviousBalance() + ": \\$" + getNewBalance());
           // }

            //>01/01/2016 12:00:20 PM Crunchie B4 \$10.00 \$8.50


        } catch (FileNotFoundException e) {
            System.out.println("Oops Something Went Wrong");
        }
        }

        //TODO: DELETE THIS ONCE EVERYTHING IS WORKING :)
   /* public void testLog(String choice) {
        if (choice.equals("Add Money")) {
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
    }*/

    public boolean isInStock(String key){
        if(actualInventory.get(key).getInventoryCount() > 0){
            return true;
        } return false;
    }

    public boolean isThereEnoughBalance (String key){
        this.previousBalance = newBalance;
        BigDecimal itemPrice = new BigDecimal(actualInventory.get(key).getItem().getPrice().toString());
        if(getPreviousBalance().compareTo(itemPrice) == 1 || getPreviousBalance().compareTo(itemPrice) == 0){
            return true;
        } return false;
    }

}

