package com.wileyedge.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.wileyedge.dao.VendingMachineDao;
import com.wileyedge.dao.VendingMachineDaoException;
import com.wileyedge.dto.Item;

public class VendingMachineDaoStudImpl implements VendingMachineDao {

	public Item onlyItem;
	
	public VendingMachineDaoStudImpl() {
		onlyItem = new Item("Coke", new BigDecimal("2.20"), 10);
	}
	
	public VendingMachineDaoStudImpl(Item testItem) {
		
		this.onlyItem = testItem;
	}

	@Override
	public List<Item> getAllItems() throws VendingMachineDaoException {
		List<Item> itemList = new ArrayList<>();
		itemList.add(onlyItem);
		return itemList;
	}

	@Override
	public Item getItem(String itemName) throws VendingMachineDaoException {
		if(itemName.equals(onlyItem.getName())) {
			return onlyItem;
		}
		return null;
	}

	@Override
	public void updateItem(String itemName, Item item) throws VendingMachineDaoException {
		if(itemName.equals(onlyItem.getName())) {
			this.onlyItem = item;
		}

	}

}
