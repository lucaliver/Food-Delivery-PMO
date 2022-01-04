/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.implementation.RestaurantImpl;

/**
 * View component for the welcome screen that the customer will see at first.
 *
 */
@SuppressWarnings("serial")
public class ViewWelcome extends JFrame implements ActionListener {
	private final Manager controller;
	private final String title = "Benvenuto, inizia ad ordinare!";
	private JComboBox<City> citiesCombo;
	private JComboBox<Restaurant> restaurantCombo;
	private JTextField addressField;
	private JButton proceedButton;
	
	/**
	 * Constructor.
	 * @param controller for the MVC pattern.
	 */
	ViewWelcome(final Manager controller){
		this.controller = controller;
		this.Init();
		this.pack();
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
		
		JLabel citiesLabel = new JLabel("Selezionare la destinazione:");
		citiesCombo = new JComboBox<>();
		for(City c: City.values())
			citiesCombo.addItem(c);
		addressField = new JTextField("Inserire indirizzo qui");
		
		JLabel restaurantLabel = new JLabel("Selezionare il ristorante:");
		restaurantCombo = new JComboBox<>();
		for(Restaurant r: controller.getRestaurants())
			restaurantCombo.addItem(r);
		
		proceedButton = new JButton("Procedi"); 
		proceedButton.addActionListener(this);
		
		JPanel citiesPanel = new JPanel();
		citiesPanel.setLayout(new BoxLayout(citiesPanel, BoxLayout.Y_AXIS));
		citiesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel restaurantPanel = new JPanel();
		restaurantPanel.setLayout(new BoxLayout(restaurantPanel, BoxLayout.Y_AXIS));
		restaurantPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		citiesPanel.add(citiesLabel);
		citiesPanel.add(citiesCombo);
	    citiesPanel.add(Box.createVerticalStrut(20));
		citiesPanel.add(addressField);
		restaurantPanel.add(restaurantLabel);
		restaurantPanel.add(restaurantCombo);
		mainPanel.add(citiesPanel);
		mainPanel.add(restaurantPanel);
		mainPanel.add(proceedButton);
		
		this.getContentPane().add(mainPanel);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==proceedButton) {
			controller.createOrder((City)citiesCombo.getSelectedItem(), addressField.getText(), (Restaurant)restaurantCombo.getSelectedItem());
			this.setVisible(false);
			this.dispose();
		}

	}
	
}
