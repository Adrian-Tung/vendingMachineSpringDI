package com.wileyedge.ui;

import java.math.BigDecimal;
import java.util.List;

import com.wileyedge.dto.Change;
import com.wileyedge.dto.Item;

public class VendingMachineView {

	private UserIO io;

	public VendingMachineView(UserIO io) {
		this.io = io;
	}

	public String getDepositAmount() {
		return io.readString("Please enter deposit amount:$");
	}
	
	public void displayErrorMessage(String errorMsg) {
		io.print("=== ERROR ===");
		io.print(errorMsg);
	}
	
	public void displayUnknownCommandBanner() {
	    io.print("Unknown Command!!!");
	}
	

	public void displayPurchaseItemBanner() {
		io.print("=== Purchasing Item ===");
	}

	public String getItemSelection() {
		return io.readString("Please enter the name of your Item choice: ").toUpperCase();
	}

	public void displayPurchaseSuccessBanner() {
		io.readString("Purchase successfully.  Please hit enter to continue");
	}

	public void displayDepositedBal(BigDecimal depositBal) {
		io.print("Your deposited balance: $" + depositBal.toPlainString());
	}

	public void displayDepositSuccessBanner() {
		io.readString("Deposited successfully.  Please hit enter to continue");
	}

	public void displayChange(Change change) {
		if (change != null) {
			io.print("Your change is $" + change.getChange().toPlainString());
			io.print("Here are your coins");
			io.print("Quarters: " + change.getCountQuarters());
			io.print("Dimes: " + change.getCountDimes());
			io.print("Nickels: " + change.getCountNickels());
			io.print("Pennies: " + change.getCountPennies());
		} else {
			System.out.println("No change return.");
		}

	}

	public void displayExitBanner() {
		io.print("Good Bye!!!");
	}

	public void displayDisplayItemsBanner() {
		io.print("=== Items Available ===");
	}

	public void displayItems(List<Item> inventory) {
		for (Item item : inventory) {
			String itemInfo = String.format("Item: %s - Qty: %s - Price: %s", item.getName(), item.getQuantity(),
					item.getPrice());
			io.print(itemInfo);
		}
		io.print("================================");
	}

	public int printMenuAndGetSelection() {
		io.print("Main Menu");
		io.print("1. Purchase Item");
		io.print("2. Deposit Money");
		io.print("3. Exit");

		return io.readInt("Please select from the above choices.", 1, 3);
	}

}
