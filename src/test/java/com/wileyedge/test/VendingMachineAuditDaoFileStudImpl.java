package com.wileyedge.test;

import java.math.BigDecimal;

import com.wileyedge.dao.VendingMachineAuditDao;
import com.wileyedge.dao.VendingMachineDaoException;
import com.wileyedge.dto.Item;

public class VendingMachineAuditDaoFileStudImpl implements VendingMachineAuditDao {

	@Override
	public void writeAuditLog(String entry) throws VendingMachineDaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void purchaseLog(Item item) throws VendingMachineDaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void depositLog(BigDecimal deposit) throws VendingMachineDaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeLog(BigDecimal change) throws VendingMachineDaoException {
		// TODO Auto-generated method stub

	}

}
