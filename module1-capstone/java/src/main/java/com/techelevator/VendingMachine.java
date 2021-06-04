package com.techelevator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    //vendingmachine.csv
    Map<String, Integer> actualInventory = new HashMap<>();
 // Map<Integer, Item> actualInventory = new HashMap<>();
    Register register = new Register();

    public void updateInventory(Item item){
        actualInventory.put(item.getSlotLocation(), 5);
    }



}
