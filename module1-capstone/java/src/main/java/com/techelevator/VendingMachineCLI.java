package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private VendingMachine vm = new VendingMachine(new FileInteractor("vendingmachine.csv"));

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

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
				//Register register = new Register() {};
				//display sub menu (
				// 1. feed money-- call feedMoney--updates balance
				// 2. Display products- select product, userInput Scanner -return to main purchase menu - if sold out, "Sold Out"
				// --calling purchase-- display stupid message
				// 3. finish transaction-- give change--return to main menu-- appends log
				// ** print out current balance-- update balance back to $0
				//register.feedMoney

			}
		}
	}

	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();



	}


}
