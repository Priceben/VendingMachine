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
                searchResults = inputScanner.nextLine().replace("|", "@");
                String[] searchResultsArray = searchResults.split("@", 5);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops Somthing Went Wrong");

        }
    }
}

