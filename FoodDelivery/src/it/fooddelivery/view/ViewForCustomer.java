package it.fooddelivery.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.fooddelivery.controller.DeliveryManager;

public class ViewForCustomer extends JFrame {
	private final DeliveryManager controller;

	ViewForCustomer(final DeliveryManager controller){
		this.controller = controller;
		this.setTitle("Customer Screen");
		//this.setResizable(false);

		this.pack();
		
	}
	
	
}
