package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class VendingMachineTests {

    @Test
    public void isInStock_whenItemIsInStockAndHasValidKey_shouldReturnTrue() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        String key = "A4";

        //Act
        boolean result = testVM.isInStock(key);

        //Assert
        Assert.assertTrue(result);
    }

    @Test
    public void isInStock_whenItemIsOutOfStockAndHasValidKey_shouldReturnFalse() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        String key = "A4";
        testVM.actualInventory.get(key).setInventoryCount(0);

        //Act
        boolean result = testVM.isInStock(key);

        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void isInStock_whenGivenInvalidKey_shouldReturnFalse() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        String key = "E4";

        //Act
        boolean result = testVM.isInStock(key);

        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void isThereEnoughBalance_shouldReturnTrue() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        BigDecimal test = new BigDecimal("5.00");
        testVM.setPreviousBalance(test);
        boolean expected = true;

        //Act


        //Assert
    }

   /* @Test
    public void giveChange_shouldReturnLowestNumberOfCoins() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        BigDecimal testMoney = new BigDecimal("1.65");
        String expected = "Your change is 6 quarter(s), 1 dime(s), and 1 nickel(s).";

        //Act
       // BigDecimal result = testVM.giveChange();

        //Assert
       // Assert.assertEquals(expected, result);
    }

    @Test
    public void giveChange_withNoChangeEntered_shouldReturnZero() {
        //Arrange
        Register test = new Register();
        test.feedMoney(0);
        String expected = "Your change is 0 quarter(s), 0 dime(s), and 0 nickel(s).";

        //Act
        String result = test.giveChange();

        //Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void giveChange_withBalanceLessThan25_shouldReturn1DimeAnd1Nickel() {
        //Arrange
        Register test = new Register();
        test.feedMoney(15);
        String expected = "Your change is 0 quarter(s), 1 dime(s), and 1 nickel(s).";

        //Act
        String result = test.giveChange();

        //Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void giveChange_withBalance17_shouldReturn1DimeAnd1Nickel() {
        //Arrange
        Register test = new Register();
        test.feedMoney(17);
        String expected = "Your change is 0 quarter(s), 1 dime(s), and 1 nickel(s).";

        //Act
        String result = test.giveChange();

        //Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void feedMoney_startingWith0Balance_shouldReturnMoneyFed() {
        //Arrange
        Register test = new Register();
        test.feedMoney(17);
        int expected = 17;

        //Act
        int result = test.getBalance();

        //Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void feedMoney_startingWithABalance_shouldReturnSumOfBalances() {
        //Arrange
        Register test = new Register();
        test.feedMoney(17);
        test.feedMoney(13);
        int expected = 30;

        //Act
        int result = test.getBalance();

        //Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void purchase_purchasingOneItem_shouldReturnBalanceMinusPurchasePrice() {
        //Arrange
        Register test = new Register();
        Item testItem = new Item("candy", 20);
        test.feedMoney(50);
        test.purchase(testItem);
        int expected = 30;

        //Act
        int result = test.getBalance();;

        //Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void purchase_purchasingItemMoreThanBalance_shouldReturnUnchangedBalance() {
        //Arrange
        Register test = new Register();
        Item testItem = new Item("candy", 50);
        test.feedMoney(20);
        test.purchase(testItem);
        int expected = 20;

        //Act
        int result = test.getBalance();;

        //Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void purchase_purchasingOneItemOfRemainingBalance_shouldReturn0() {
        //Arrange
        Register test = new Register();
        Item testItem = new Item("candy", 20);
        test.feedMoney(20);
        test.purchase(testItem);
        int expected = 0;

        //Act
        int result = test.getBalance();;

        //Assert
        Assert.assertEquals(expected, result);
    }*/


}
