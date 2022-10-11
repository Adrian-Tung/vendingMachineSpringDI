package com.wileyedge.vendingmachine;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wileyedge.controller.VendingMachineController;
import com.wileyedge.dao.VendingMachineAuditDao;
import com.wileyedge.dao.VendingMachineAuditDaoFileImpl;
import com.wileyedge.dao.VendingMachineDao;
import com.wileyedge.dao.VendingMachineDaoFileImpl;
import com.wileyedge.service.VendingMachineServiceLayer;
import com.wileyedge.service.VendingMachineServiceLayerImpl;
import com.wileyedge.ui.UserIO;
import com.wileyedge.ui.UserIOConsoleImpl;
import com.wileyedge.ui.VendingMachineView;

public class App {
	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
		controller.run();
	}

}
