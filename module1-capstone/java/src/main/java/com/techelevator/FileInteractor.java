package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInteractor {
    public static void main(String[] args) { //TODO: verify that we can have 2 concurrent psvms
        File uploadFile = new File("vendingmachine.csv");
        try (Scanner inputScanner = new Scanner(uploadFile.getAbsoluteFile())) {
            String searchResults = "";
            while (inputScanner.hasNextLine()) {
                searchResults = inputScanner.nextLine().replace("|","@");
                String[] searchResultsArray = searchResults.split("@", 5);
                System.out.println(searchResultsArray[0]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops Something Went Wrong");

        }
    }
}

//could we do for loop to assign array[0] as key in the map
//and then array[1] we use to SET our item
//for each item

