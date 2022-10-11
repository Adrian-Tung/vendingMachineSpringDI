package com.wileyedge.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wileyedge.dao.VendingMachineDao;
import com.wileyedge.dao.VendingMachineDaoFileImpl;
import com.wileyedge.dto.Item;

class VendingMachineDaoFileImplTest {

	VendingMachineDao testDao;

	@BeforeEach
	void setUp() throws Exception {
		String testFile = "testInventory.txt";
		// Use the FileWriter to quickly blank the file
		new FileWriter(testFile);
		testDao = new VendingMachineDaoFileImpl(testFile);

	}

	@Test
	void testUpdateGetAllItems() throws Exception {
		// create items
		Item item1 = new Item("Coke", new BigDecimal("2.20"), 3);
		Item item2 = new Item("Water", new BigDecimal("1.20"), 5);

		// add item to dao
		testDao.updateItem(item2.getName(), item1);
		testDao.updateItem(item2.getName(), item2);

		// Retrieve the list of all items within the DAO
		List<Item> allItems = testDao.getAllItems();

		// First check the general contents of the list
		assertNotNull(allItems, "The list of items must not null");
		assertEquals(2, allItems.size(), "List of items should have 2 items.");

		// Then the specifics
		assertTrue(testDao.getAllItems().contains(item1), "The list of items should include item Coke.");
		assertTrue(testDao.getAllItems().contains(item2), "The list of items should include item Water.");
	}

	@Test
	void testUpdateGetItem() throws Exception {
		// create item
		String itemName = "Coke";
		Item item = new Item(itemName, new BigDecimal("2.20"), 3);
		// add item to dao
		testDao.updateItem(item.getName(), item);
		Item retrivedItem = testDao.getItem(itemName);
		// assert data is equal
		assertEquals(item.getName(), retrivedItem.getName(), "Checking item name.");
		assertEquals(item.getPrice(), retrivedItem.getPrice(), "Checking item price.");
		assertEquals(item.getQuantity(), retrivedItem.getQuantity(), "Checking item quantity.");

	}

}
