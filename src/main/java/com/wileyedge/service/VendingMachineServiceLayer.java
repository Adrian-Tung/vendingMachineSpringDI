package com.wileyedge.service;

import java.math.BigDecimal;
import java.util.List;

import com.wileyedge.dao.VendingMachineDaoException;
import com.wileyedge.dto.Change;
import com.wileyedge.dto.Item;

public interface VendingMachineServiceLayer {
	
	List<Item> getAllItems()throws VendingMachineDaoException ;
	Change purchaseItem(String itemName) throws VendingMachineDaoException, NoItemInventoryException,InsufficientFundsException;
	void deposit(String deposit) throws VendingMachineDaoException;
	BigDecimal getCurBalance();

}
