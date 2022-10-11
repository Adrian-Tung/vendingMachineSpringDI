package com.wileyedge.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.wileyedge.dto.Item;

public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao {

	public static final String AUDIT_FILE = "audit.txt";

	@Override
	public void writeAuditLog(String entry) throws VendingMachineDaoException {
		PrintWriter out;

		try {
			out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
		} catch (IOException ex) {
			throw new VendingMachineDaoException("Could not write audit information.");
		}

		LocalDateTime timestamp = LocalDateTime.now();

		out.println(timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")) + " : " + entry);

		out.flush();
		out.close();
	}

	@Override
	public void depositLog(BigDecimal deposit) throws VendingMachineDaoException {
		String depositLogEntry = "$" + deposit.toPlainString() + " deposited by customer";
		writeAuditLog(depositLogEntry);

	}

	@Override
	public void changeLog(BigDecimal change) throws VendingMachineDaoException {
		String changeLogEntry = "$" + change.toPlainString() + " returned to customer";
		writeAuditLog(changeLogEntry);

	}

	@Override
	public void purchaseLog(Item item) throws VendingMachineDaoException {
		String purchaseLogEntry = "Item " + item.getName() + " purchased at $" + item.getPrice().toPlainString()
				+ " Quantity Left - " + item.getQuantity();
		writeAuditLog(purchaseLogEntry);
	}

}
