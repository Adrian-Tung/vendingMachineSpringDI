package com.wileyedge.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wileyedge.dao.VendingMachineAuditDao;
import com.wileyedge.dao.VendingMachineDao;
import com.wileyedge.dao.VendingMachineDaoException;
import com.wileyedge.service.InsufficientFundsException;
import com.wileyedge.service.NoItemInventoryException;
import com.wileyedge.service.VendingMachineServiceLayer;
import com.wileyedge.service.VendingMachineServiceLayerImpl;

class VendingMachineServiceLayerImplTest {

	private VendingMachineServiceLayer service;

	public VendingMachineServiceLayerImplTest() {
//		VendingMachineDao dao = new VendingMachineDaoStudImpl();
//		VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileStudImpl();
//
//		service = new VendingMachineServiceLayerImpl(dao, auditDao);
		
	    ApplicationContext ctx = 
	            new ClassPathXmlApplicationContext("applicationContext.xml");
	        service = 
	            ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
	    }
	

	@Test
	void testValidPurchase() {

		try { // deposit
			service.deposit("3");
		} catch (VendingMachineDaoException e) {
			e.printStackTrace();
		}

		try {
			service.purchaseItem("Coke");
		} catch (VendingMachineDaoException | NoItemInventoryException | InsufficientFundsException e) {
			fail("Purchase was valid. No exception should have been thrown");
		}
	}

	@Test
	void testNoInventoryPurchase() {
		try { //deposit
			service.deposit("3");
		} catch (VendingMachineDaoException e) {
			e.printStackTrace();
		}
		
		try {
			service.purchaseItem("Water");
			fail("Expected NoItemInventoryException was not thrown");
		} catch (VendingMachineDaoException | InsufficientFundsException e) {
			fail("Incorrect exception was thrown.");
		} catch ( NoItemInventoryException e ) {
			return;
		}
	}
	@Test
	void testInsufficientFundsPurchase() {
		try { // deposit insufficientfund
			service.deposit("1");
		} catch (VendingMachineDaoException e) {
			e.printStackTrace();
		}

		try {
			service.purchaseItem("Coke");
			fail("Expected InsufficientFundsException was not thrown");
		} catch (VendingMachineDaoException | NoItemInventoryException e) {
			fail("Incorrect exception was thrown.");
		} catch (InsufficientFundsException e) {
			return;
		}

	}

}
