package com.wileyedge.dao;

import java.util.List;

import com.wileyedge.dto.Item;

public interface VendingMachineDao {
	
	List<Item> getAllItems() throws VendingMachineDaoException;
    Item getItem(String itemName)throws VendingMachineDaoException;
    void updateItem(String itemName, Item item)throws VendingMachineDaoException;
	

}
