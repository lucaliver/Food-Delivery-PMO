/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.view;

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

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.implementation.CityImpl;

/**
 * Welcome screen for the customer, used to select restaurant and destination.
 */
@SuppressWarnings("serial")
public class ViewWelcome extends JFrame {
	
	private final Manager controller;
	private JPanel mainPanel;
	private final String title = "Benvenuto, inizia ad ordinare!";
	private JComboBox<CityImpl> citiesCombo;
	private JComboBox<Restaurant> restaurantCombo;
	private JTextField addressField;
	private JButton proceedButton;
	private JButton exitButton;
	
	/**
	 * Constructs a welcome screen for the user.
	 * 
	 * @param controller controller component for the MVC pattern
	 */
	ViewWelcome(final Manager controller){
		this.controller = controller;
		this.Init();
	}
	
	/**
	 * Initializes all the window.
	 */
	private void Init() {
		this.setTitle(this.title);
		this.setSize(400, 200);
		this.setLocation(800, 100);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		mainPanel.add(createChoicePanel());
		mainPanel.add(createButtonsPanel());
		
		this.getContentPane().add(mainPanel);
		this.pack();	
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);			
	}

	/**
	 * Creates the panel where the user choose restaurant and destination.
	 * 
	 * @return the panel itself
	 */
	private JPanel createChoicePanel() {
		JPanel orderInfoPanel = new JPanel();
		orderInfoPanel.setLayout(new BoxLayout(orderInfoPanel, BoxLayout.X_AXIS));
		orderInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		orderInfoPanel.add(createDestinationPanel());
		orderInfoPanel.add(createRestaurantPanel());
		
		return orderInfoPanel;
	}

	/**
	 * Creates the panel with the proceed button and exit button.
	 * 
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
			controller.createCurrentOrder((CityImpl)citiesCombo.getSelectedItem(), addressField.getText(), (Restaurant)restaurantCombo.getSelectedItem());
			this.setVisible(false);
			this.dispose();
			new ViewPlacing(this.controller);
		});
		buttonsPanel.add(proceedButton);
						
		return buttonsPanel;
	}

	/**
	 * Creates the panel for choosing the restaurant.
	 * 
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
	 * Creates the panel for the insertion of the destination.
	 * 
	 * @return the panel itself
	 */
	private JPanel createDestinationPanel() {
		JPanel destinationPanel = new JPanel();
		destinationPanel.setLayout(new BoxLayout(destinationPanel, BoxLayout.Y_AXIS));
		destinationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel citiesLabel = new JLabel("Selezionare la destinazione: ");
		destinationPanel.add(citiesLabel);
		citiesCombo = new JComboBox<>();
		for(CityImpl c: CityImpl.values())
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
