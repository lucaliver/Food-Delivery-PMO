/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.view;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.ManagerImpl;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Restaurant;

/**
 * Welcome screen for the customer, used to select restaurant and destination.
 */
@SuppressWarnings("serial")
public class ViewWelcome extends JFrame {
	private final ManagerImpl controller;
	private JPanel mainPanel;
	private final String title = "Benvenuto, inizia ad ordinare!";
	private JComboBox<City> citiesCombo;
	private JComboBox<Restaurant> restaurantCombo;
	private JTextField addressField;
	private JButton proceedButton;
	private JButton exitButton;
	
	/**
	 * Constructs a welcome screen for the user.
	 * 
	 * @param controller controller component for the MVC pattern.
	 */
	ViewWelcome(final ManagerImpl controller){
		this.controller = controller;
		this.Init();
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * Initializes all its GUI.
	 */
	private void Init() {
		this.setTitle(this.title);
		this.setSize(400, 200);
		this.setLocation(700, 100);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		mainPanel.add(createOrderInfoPanel());
		mainPanel.add(createButtonsPanel());
		
		this.getContentPane().add(mainPanel);				
	}

	/**
	 * It creates the panel where the user choose the info of his order
	 * @return the panel itself
	 */
	private JPanel createOrderInfoPanel() {
		JPanel orderInfoPanel = new JPanel();
		orderInfoPanel.setLayout(new BoxLayout(orderInfoPanel, BoxLayout.X_AXIS));
		orderInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		orderInfoPanel.add(createDestinationPanel());
		orderInfoPanel.add(createRestaurantPanel());
		
		return orderInfoPanel;
	}

	/**
	 * It creates the panel with all the button 
	 * @return the panel itself
	 */
	private JPanel createButtonsPanel() {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		exitButton = new JButton("Esci");
		exitButton.addActionListener(event -> {
			this.dispose();
			ViewWorker.getInstance().dispose();
		});
		buttonsPanel.add(exitButton);
		
		buttonsPanel.add(Box.createHorizontalStrut(60));
		
		proceedButton = new JButton("Procedi"); 
		proceedButton.addActionListener(event -> {
			controller.createCurrentOrder((City)citiesCombo.getSelectedItem(), addressField.getText(), (Restaurant)restaurantCombo.getSelectedItem());
			this.setVisible(false);
			this.dispose();
			new ViewPlacing(this.controller);
		});
		buttonsPanel.add(proceedButton);
						
		return buttonsPanel;
	}

	/**
	 * It Creates the panel where to choose the order's restaurant
	 * @return the panel itself
	 */
	private JPanel createRestaurantPanel() {
		JPanel restaurantPanel = new JPanel();
		restaurantPanel.setLayout(new BoxLayout(restaurantPanel, BoxLayout.Y_AXIS));
		restaurantPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel restaurantLabel = new JLabel("Selezionare il ristorante: ");
		restaurantPanel.add(restaurantLabel);
		
		restaurantCombo = new JComboBox<>();
		for(Restaurant r: controller.getRestaurants())
			restaurantCombo.addItem(r);
		restaurantPanel.add(restaurantCombo);
		
		return restaurantPanel;
	}

	/**
	 * It creates the panel where to input all the order's destination data
	 * @return the panel itself
	 */
	private JPanel createDestinationPanel() {
		JPanel destinationPanel = new JPanel();
		destinationPanel.setLayout(new BoxLayout(destinationPanel, BoxLayout.Y_AXIS));
		destinationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel citiesLabel = new JLabel("Selezionare la destinazione: ");
		destinationPanel.add(citiesLabel);
		citiesCombo = new JComboBox<>();
		for(City c: City.values())
			citiesCombo.addItem(c);
		destinationPanel.add(citiesCombo);
		
	    destinationPanel.add(Box.createVerticalStrut(20));
		JLabel addressLabel = new JLabel("Inserire indirizzo: ");
	    destinationPanel.add(addressLabel);
		addressField = new JTextField("Indirizzo");
		destinationPanel.add(addressField);
		
		return destinationPanel;
	}
}
