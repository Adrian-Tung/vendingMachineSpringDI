package com.wileyedge.controller;

import com.wileyedge.dao.VendingMachineDaoException;
import com.wileyedge.dto.Change;
import com.wileyedge.service.InsufficientFundsException;
import com.wileyedge.service.NoItemInventoryException;
import com.wileyedge.service.VendingMachineServiceLayer;
import com.wileyedge.ui.UserIO;
import com.wileyedge.ui.UserIOConsoleImpl;
import com.wileyedge.ui.VendingMachineView;

public class VendingMachineController {
	VendingMachineView view;
	VendingMachineServiceLayer service;
	private UserIO io = new UserIOConsoleImpl();

	public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
		this.service = service;
		this.view = view;
	}

	private int getMenuSelection() {
		return view.printMenuAndGetSelection();
	}

	private void getDeposit() throws VendingMachineDaoException {
		String amountDeposit = view.getDepositAmount();
		service.deposit(amountDeposit);
		view.displayDepositSuccessBanner();
	}

	public void purchase() throws VendingMachineDaoException, NoItemInventoryException, InsufficientFundsException {
		view.displayPurchaseItemBanner();
		String itemSelection = view.getItemSelection();
		Change change = service.purchaseItem(itemSelection);
		view.displayChange(change);
		view.displayPurchaseSuccessBanner();
	}

	public void run() {
		boolean keepGoing = true;
		int menuSelection = 0;

		while (keepGoing) {
			try {
				view.displayDepositedBal(service.getCurBalance());
				view.displayDisplayItemsBanner();
				view.displayItems(service.getAllItems());

				menuSelection = getMenuSelection();

				switch (menuSelection) {
				case 1:
					purchase();
					break;
				case 2:
					getDeposit();
					break;
				case 3:
					keepGoing = false;
					break;
				default:
					view.displayUnknownCommandBanner();
				}
			} catch (Exception e) {
				view.displayErrorMessage(e.getMessage());

			}

		}

		view.displayExitBanner();
	}

}
