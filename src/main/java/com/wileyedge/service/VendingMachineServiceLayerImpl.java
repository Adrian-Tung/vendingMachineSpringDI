package com.wileyedge.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.wileyedge.dao.VendingMachineAuditDao;
import com.wileyedge.dao.VendingMachineDao;
import com.wileyedge.dao.VendingMachineDaoException;
import com.wileyedge.dto.Change;
import com.wileyedge.dto.Item;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

	VendingMachineDao dao;
	VendingMachineAuditDao auditDao;
	BigDecimal curBalance;

	public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
		this.dao = dao;
		this.auditDao = auditDao;
		curBalance = new BigDecimal("0.00");
	}
	
	// get all items from inventory file, filtered items that are not 0 in qty and 
	// return in an array list
	@Override 
	public List<Item> getAllItems() throws VendingMachineDaoException {
		List<Item> inventory = dao.getAllItems();
		inventory = inventory.stream().filter(item -> item.getQuantity() > 0).collect(Collectors.toList());
		return inventory;
	}

	@Override
	public Change purchaseItem(String itemName)
			throws VendingMachineDaoException, NoItemInventoryException, InsufficientFundsException {
		BigDecimal change;
		Change changeDenomination = new Change();
		Item itemSelection = dao.getItem(itemName);
		if (itemSelection == null) {
			throw new NoItemInventoryException("No such Item!");
		} else if (itemSelection.getQuantity() == 0) {
			throw new NoItemInventoryException("Selected Item is out of stock!");
		} else if (this.getCurBalance().compareTo(itemSelection.getPrice()) == -1) {
			throw new InsufficientFundsException("Insufficient Fund! Deposit more money!");
		} else {
			// There is stock and sufficient funds
			// Update item qty (minus 1) for vending
			Item updateItem = new Item(itemSelection.getName(), itemSelection.getPrice(),
					itemSelection.getQuantity() - 1);
			dao.updateItem(itemSelection.getName(), updateItem);
			auditDao.purchaseLog(updateItem);

			// Calculate change in Pennies
			change = (this.getCurBalance().subtract(itemSelection.getPrice()));
			changeDenomination.doDenomination(change);
			this.setCurBalance(new BigDecimal("0"));
			auditDao.changeLog(change);

		}
		return changeDenomination;
	}

	@Override
	public void deposit(String deposit) throws VendingMachineDaoException {
		BigDecimal newdeposit = new BigDecimal(deposit);
		auditDao.depositLog(newdeposit);
		this.setCurBalance(this.curBalance.add(newdeposit));
	}

	@Override
	public BigDecimal getCurBalance() {
		return curBalance;
	}

	public VendingMachineDao getDao() {
		return dao;
	}

	public void setDao(VendingMachineDao dao) {
		this.dao = dao;
	}

	public VendingMachineAuditDao getAuditDao() {
		return auditDao;
	}

	public void setAuditDao(VendingMachineAuditDao auditDao) {
		this.auditDao = auditDao;
	}

	public void setCurBalance(BigDecimal curBalance) {
		this.curBalance = curBalance;
	}

}
