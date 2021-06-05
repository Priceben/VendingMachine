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
				// display vending machine items
				vm.displayInventory();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				//display sub menu
				// 1. feed money-- call feedMoney--updates balance
				menuLoop = PURCHASE_LOOP;



				// 3. finish transaction-- give change--return to main menu-- appends log
				// ** print out current balance-- update balance back to $0
				//register.feedMoney

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
					// -return to main purchase menu - if sold out, "Sold Out"
					// --calling purchase-- display stupid message
					vm.displayInventory();
					System.out.println("Enter code for Item you wish to purchase: ");
					try {
						Scanner enteredKey = new Scanner(System.in);
						//TODO: IMPLEMENT THE IGNORE CASE THING IF WE WANT
						String key = enteredKey.nextLine().toUpperCase();


						if(vm.actualInventory.containsKey(key)){
							if(vm.actualInventory.get(key).getInventoryCount() > 0 && vm.getBalance() >= vm.actualInventory.get(key).getItem().getPrice()) {
								vm.purchase(vm.actualInventory.get(key).getItem());
								vm.actualInventory.get(key).setInventoryCount(vm.actualInventory.get(key).getInventoryCount() - 1);
								vm.dispense(vm.actualInventory.get(key).getItem());
								System.out.println("Remaining Money Available: $" + vm.getBalance());
							} else if(vm.actualInventory.get(key).getInventoryCount() == 0) {
								System.out.println("Sold Out!");
							} else if (vm.getBalance() < vm.actualInventory.get(key).getItem().getPrice()) {
								System.out.println("Not enough balance!");
							}
						} else {
							System.out.println("Key Not Recognized. Try Again!");
						}

					}
					catch (NumberFormatException e) {//todo: will need to change exception
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
