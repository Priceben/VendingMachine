package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileInteractor {
    public static void main(String[] args) { //TODO: verify that we can have 2 concurrent psvms
        File uploadFile = new File("vendingmachine.csv");

        Map<String, Item> actualInventory = new HashMap<>();
        Map<String, Integer> testMap = new HashMap<>();

        try (Scanner inputScanner = new Scanner(uploadFile.getAbsoluteFile())) {
            String searchResults = "";
            int testCounter = 0;
            while (inputScanner.hasNextLine()) {
                searchResults = inputScanner.nextLine().replace("|", "@");
                String[] searchResultsArray = searchResults.split("@", 5);

//                String slotNumber = searchResultsArray[0];
//                for(String index0 : slotNumber ) {
//                    String[] newArray += index0 + "@";
//                }

                //Extracting the values and making them how I want them
                String slotNumber = searchResultsArray[0];
                String name = searchResultsArray[1];
                double price = (Double.parseDouble(searchResultsArray[2]));
                String type = searchResultsArray[3];

                //Instantiate an item using my new values
                Item testItem = new Item (name, type, (int)(price * 100));
                //slotLocation, name, price, type

                //Create a Map
                actualInventory.put(slotNumber,testItem);
                }
                

            for(Map.Entry<String, Item> entry : actualInventory.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue().getName() + " " + entry.getValue().getType() + " " + entry.getValue().getPrice());
            }
                System.out.println(testMap);

            } catch(FileNotFoundException e) {
                    System.out.println("Oops Something Went Wrong");

        }
        }
    }


//could we do for loop to assign array[0] as key in the map
//and then array[1] we use to SET our item
//for each item

