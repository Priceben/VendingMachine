package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Scanner;

public class VendingMachineCLI {

	private VendingMachine vm = new VendingMachine(new FileInteractor("vendingmachine.csv"));

	//MAIN MENU CONFIG
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	//PURCHASE MENU CONFIG

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				vm.displayInventory();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				//display sub menu
				// 1. feed money-- call feedMoney--updates balance
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

				// 2. Display products- select product, userInput Scanner to validate user choice
				// -return to main purchase menu - if sold out, "Sold Out"
				// --calling purchase-- display stupid message

				// 3. finish transaction-- give change--return to main menu-- appends log
				// ** print out current balance-- update balance back to $0
				//register.feedMoney

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(1);
				//TODO: Make sure the VM resets at this point?
			}
		}
	}

	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();



	}


}
