package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileInteractor {
    //Instance Variables
    private File uploadFile ;

    //Constructor
    public FileInteractor(String sourcefile){
        uploadFile = new File(sourcefile);
    }

    //Method
    public Map<String, ItemInventory> load() {

        Map<String, ItemInventory> actualInventory = new HashMap<>();

        try (Scanner inputScanner = new Scanner(uploadFile.getAbsoluteFile())) {
            String searchResults = "";
            while (inputScanner.hasNextLine()) {
                searchResults = inputScanner.nextLine().replace("|", "@");
                String[] searchResultsArray = searchResults.split("@", 5);

                //Extracting the values and making them how I want them
                String slotNumber = searchResultsArray[0];
                String name = searchResultsArray[1];
                double price = (Double.parseDouble(searchResultsArray[2]));
                String type = searchResultsArray[3];

                //Instantiate an item using my new values
                Item testItem = new Item (name, (int)(price * 100), type);
                ItemInventory testItemInventory = new ItemInventory(5, testItem);
                //slotLocation, name, price, type

                //Create a Map
                actualInventory.put(slotNumber,testItemInventory);
                }
                

            /**/

            } catch(FileNotFoundException e) {
                    System.out.println("Oops Something Went Wrong");

        }finally {
            return actualInventory;
        }
        }
    }


//could we do for loop to assign array[0] as key in the map
//and then array[1] we use to SET our item
//for each item

