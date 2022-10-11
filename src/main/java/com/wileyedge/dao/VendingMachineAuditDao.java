package com.wileyedge.dao;

import java.math.BigDecimal;

import com.wileyedge.dto.Item;

public interface VendingMachineAuditDao {

	public void writeAuditLog(String entry) throws VendingMachineDaoException;
	public void purchaseLog(Item item) throws VendingMachineDaoException;
	public void depositLog(BigDecimal deposit) throws VendingMachineDaoException;
	public void changeLog(BigDecimal change) throws VendingMachineDaoException;
}
