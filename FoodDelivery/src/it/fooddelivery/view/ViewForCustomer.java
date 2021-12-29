package it.fooddelivery.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.DeliveryManager;

public class ViewForCustomer extends JFrame {
	
	private final DeliveryManager controller;
	private final String TITLE = "Customer Screen";

	ViewForCustomer(final DeliveryManager controller){
		this.controller = controller;
		this.setTitle(TITLE);		
		this.initUI();
		this.pack();
		
	}
	/**
	 * Metodo per le componenti base dell'interfaccia (prova)
	 */
	private void initUI() {
		//this.setResizable(false);
		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
	}
	
	
}
