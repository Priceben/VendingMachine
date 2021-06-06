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
    public void isThereEnoughBalance_ifBalanceIsGreaterThanItemPriceAndKeyIsInCorrectCase_shouldReturnTrue() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        BigDecimal test = new BigDecimal("5.00");
        testVM.feedMoney(test);

        //Act
        boolean result = testVM.isThereEnoughBalance("A4");


        //Assert
        Assert.assertTrue(result);
    }
    @Test
    public void isThereEnoughBalance_ifBalanceIsLessThanItemPriceAndKeyIsInCorrectCase_shouldReturnFalse() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        BigDecimal test = new BigDecimal("0.00");
        testVM.feedMoney(test);

        //Act
        boolean result = testVM.isThereEnoughBalance("A4");


        //Assert
        Assert.assertFalse(result);
    }
    @Test
    public void isThereEnoughBalance_ifBalanceIsEqualToItemPriceAndKeyIsInCorrectCase_shouldReturnTrue() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        BigDecimal test = new BigDecimal("3.65");
        testVM.setNewBalance(test);

        //Act
        boolean result = testVM.isThereEnoughBalance("A4");


        //Assert
        Assert.assertTrue(result);
    }

   @Test
    public void giveChange_shouldSetPreviousBalanceBackToZero() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        BigDecimal testMoney = new BigDecimal("2.00");
        BigDecimal expected = new BigDecimal("0.00");
        testVM.feedMoney(testMoney);
        testVM.giveChange();

        //Act
       BigDecimal result = testVM.getNewBalance();

        //Assert
       Assert.assertEquals(expected, result);
    }

    @Test
    public void giveChange_withNoChangeEntered_shouldReturnZero() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        BigDecimal expected = new BigDecimal("0.00");
        testVM.giveChange();

        //Act
        BigDecimal result = testVM.getNewBalance();

        //Assert
        Assert.assertEquals(expected, result);
    }
    @Test
    public void feedMoney_startingWith0Balance_shouldReturnMoneyFed() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        BigDecimal expected = new BigDecimal("5.00");
        testVM.feedMoney(expected);

        //Act
        BigDecimal result = testVM.getNewBalance();

        //Assert
        Assert.assertEquals(expected, result);
    }
    @Test
    public void feedMoney_startingWithNon0Balance_shouldReturnSumOfMoneyFedAndPreviousBalance() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        BigDecimal expected = new BigDecimal("7.00");
        BigDecimal moneyFed = new BigDecimal("5.00");
        BigDecimal setBalance = new BigDecimal("2.00");
        testVM.feedMoney(setBalance);
        testVM.feedMoney(moneyFed);

        //Act
        BigDecimal result = testVM.getNewBalance();

        //Assert
        Assert.assertEquals(expected, result);
    }
/*
@Test
    public void giveChange_withBalanceLessThan25_shouldReturn1DimeAnd1Nickel() {
        //Arrange
        VendingMachine testVM = new VendingMachine(new FileInteractor("vendingmachine.csv"));
        BigDecimal test = new BigDecimal("0.15");
        testVM.feedMoney(test);
        int expected = 1;
        testVM.giveChange();

        //Act
        int result = testVM.get

        //Assert
       // Assert.assertEquals(expe);
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
