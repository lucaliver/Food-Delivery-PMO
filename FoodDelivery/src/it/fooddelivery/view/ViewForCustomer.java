package it.fooddelivery.view;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.DeliveryManager;

public class ViewForCustomer extends JFrame {
	private final DeliveryManager controller;

	ViewForCustomer(final DeliveryManager controller){
		this.controller = controller;
		this.setTitle("Customer Screen");
		
		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		final JTextField text = new JTextField(10);
		final JButton button = new JButton("Tasto"); 
		
		final JComboBox restaurants = new JComboBox();
		//TODO Finire GUI.
		
		mainPanel.add(text);
		mainPanel.add(button);
		getContentPane().add(mainPanel);
		
		this.setVisible(true);
		this.pack();
	}
	
	
}
