package com.wileyedge.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import com.wileyedge.dto.Item;

public class VendingMachineDaoFileImpl implements VendingMachineDao {
	
	private Map<String, Item> inventory = new HashMap<>();
	public final String INVENTORY_FILE;
	public static final String DELIMITER = "::";
	
	public VendingMachineDaoFileImpl(){
		INVENTORY_FILE = "inventory.txt";
	}

	public VendingMachineDaoFileImpl(String INVENTORYFILE){
		INVENTORY_FILE = INVENTORYFILE;
	}
	
	@Override
	public List<Item> getAllItems() throws VendingMachineDaoException {
		loadInventory();
		return new ArrayList<Item>(inventory.values());
	}

	@Override
	public Item getItem(String itemName) throws VendingMachineDaoException {
		loadInventory();
		return inventory.get(itemName);
	}

	@Override
	public void updateItem(String itemName, Item item) throws VendingMachineDaoException {
		loadInventory();
		inventory.put(itemName, item);
		writeInventory();
	}
	

	private Item unmarshallItem(String ItemAsText) {

		String[] itemTokens = ItemAsText.split(DELIMITER);
		String itemName = itemTokens[0];
		BigDecimal itemPrice = new BigDecimal(itemTokens[1]);
		int itemQuantity = 0;
		try{
         itemQuantity = Integer.parseInt(itemTokens[2]);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
		
		Item itemFromFile = new Item(itemName,itemPrice,itemQuantity);
		return itemFromFile;
	}
	
	private void loadInventory() throws VendingMachineDaoException {
		Scanner scanner;

		try {

			scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
		} catch (FileNotFoundException e) {
			throw new VendingMachineDaoException("-_- Could not load inventory data into memory.", e);
		}

		String currentLine;

		Item curItem;

		while (scanner.hasNextLine()) {

			currentLine = scanner.nextLine();

			curItem = unmarshallItem(currentLine);

			inventory.put(curItem.getName(), curItem);
		}
		// close scanner
		scanner.close();
	}
	
	private String marshallItem(Item aItem) {

		String itemAsText = aItem.getName() + DELIMITER;
		itemAsText += aItem.getPrice() + DELIMITER;
		itemAsText += aItem.getQuantity();
		return itemAsText;
	}
	
	private void writeInventory() throws VendingMachineDaoException {

		PrintWriter out;

		try {
			out = new PrintWriter(new FileWriter(INVENTORY_FILE));
		} catch (IOException e) {
			throw new VendingMachineDaoException("Could not save Item data.", e);
		}

		String itemAsText;

		List<Item> itemList = new ArrayList<Item>(this.inventory.values());
		for (Item curItem : itemList) {

			itemAsText = marshallItem(curItem);
			out.println(itemAsText);
			out.flush();
		}
		out.close();
	}
	

}
