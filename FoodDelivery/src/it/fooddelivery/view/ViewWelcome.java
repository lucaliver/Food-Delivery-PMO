/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.Manager;

public class ViewWelcome extends JFrame {
	private final Manager controller;
	private final String title = "Customer Screen";
	
	ViewWelcome(final Manager controller){
		this.controller = controller;
		this.setTitle(this.title);
		this.setSize(600, 400);
		
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
		
		//this.pack();
	}
	
	
}
