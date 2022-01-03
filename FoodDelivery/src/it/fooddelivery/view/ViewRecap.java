/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.Manager;

/**
 * View component for the recap screen for the customer.
 *
 */
public class ViewRecap extends JFrame implements ActionListener {
	private final Manager controller;
	private final String title = "Il mio ordine :)";
	private JButton proceedButton;
	
	/**
	 * Constructor.
	 * @param controller for the MVC pattern.
	 */
	ViewRecap(final Manager controller){
		this.controller = controller;
		this.Init();
	}
	
	/**
	 * It initializes all the GUI.
	 */
	private void Init() {
		this.setTitle(this.title);
		this.setSize(400, 200);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		JLabel orderLabel = new JLabel("dfsnjdfnuifewnufewouefwnoufweonu");		//controller.getCurrentOrder().getMenus);
		orderLabel.setBorder(BorderFactory.createTitledBorder("Contenuto ordine: "));
		
		JLabel infoLabel = new JLabel("..........................");
		infoLabel.setBorder(BorderFactory.createTitledBorder("Info ordine: "));

		proceedButton = new JButton("Procedi"); 
		proceedButton.addActionListener(this);

		mainPanel.add(orderLabel);
		mainPanel.add(Box.createHorizontalStrut(50));
		mainPanel.add(infoLabel);
		mainPanel.add(Box.createHorizontalStrut(50));
		mainPanel.add(proceedButton);

		this.getContentPane().add(mainPanel);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
