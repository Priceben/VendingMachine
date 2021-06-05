package com.techelevator;

import com.techelevator.view.Menu;


import java.sql.SQLOutput;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private VendingMachine vm = new VendingMachine(new FileInteractor("vendingmachine.csv"));

	//MAIN MENU CONFIG
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	//PURCHASE MENU CONFIG
	private static final String PURCHASE_MENU_OPTION_ADD_MONEY = "Add Money";
	private static final String PURCHASE_MENU_OPTION_BUY_ITEM = "Buy Item";
	private static final String PURCHASE_MENU_OPTION_CASH_OUT = "Cash out";
	private static final String PURCHASE_MENU_OPTION_PREVIOUS_MENU = "Previous Menu";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_ADD_MONEY, PURCHASE_MENU_OPTION_BUY_ITEM, PURCHASE_MENU_OPTION_CASH_OUT, PURCHASE_MENU_OPTION_PREVIOUS_MENU };

	//LOOP CONFIG
	private static final String MAIN_LOOP = "Main";
	private static final String PURCHASE_LOOP = "Purchase";

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {

		String menuLoop = MAIN_LOOP;

		while (true) {

			menuLoop = MAIN_LOOP;

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vm.displayInventory();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				menuLoop = PURCHASE_LOOP;

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(1);
				//TODO: Make sure the VM resets at this point?
			}

			// PURCHASE SUB MENU LOOP - LOOP LEVEL 0-1-0
			while(menuLoop.equals(PURCHASE_LOOP)) {
				choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				if (choice.equals(PURCHASE_MENU_OPTION_ADD_MONEY)) {
					System.out.println("Current Money Provided: $" + vm.getBalance());
					System.out.print("Enter Money: ");
					try {
						Scanner enteredMoney = new Scanner(System.in);
						int money = Integer.parseInt(enteredMoney.nextLine());
						vm.feedMoney(money);
						System.out.println("Current Money Provided: $" + vm.getBalance());
					}
					catch (NumberFormatException e) {
						System.out.println("Number entered is invalid! Can only enter integers in 100 increments.");
					}
				}
				if (choice.equals(PURCHASE_MENU_OPTION_BUY_ITEM)) {
					vm.displayInventory();
					System.out.println("Enter code for Item you wish to purchase: ");
					try {
						Scanner enteredKey = new Scanner(System.in);
						String key = enteredKey.nextLine().toUpperCase();

						if(vm.actualInventory.containsKey(key)){
							if(vm.isInStock(key) && vm.isThereEnoughBalance(key)) {
								vm.purchase(key);
								vm.dispense(key);
								vm.testLog(key, choice);
								//vm.log(key, choice);
								System.out.println("Remaining Money Available: $" + vm.getBalance());
							} else if(!vm.isInStock(key)) {
								System.out.println("Sold Out!");
							} else if (!vm.isThereEnoughBalance(key)) {
								System.out.println("Not enough balance!");
							}
						} else {
							System.out.println("Key Not Recognized. Try Again!");
						}

					}
					catch (NumberFormatException e) {
						System.out.println("Number entered is invalid! Can only enter integers in 100 increments.");
					}

				}
				if (choice.equals(PURCHASE_MENU_OPTION_CASH_OUT)) {
					vm.giveChange();
					menuLoop = MAIN_LOOP;
				}
				if (choice.equals(PURCHASE_MENU_OPTION_PREVIOUS_MENU)) {
					menuLoop = MAIN_LOOP;
				}
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();



	}


}
