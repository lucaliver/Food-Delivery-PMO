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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Restaurant;

/**
 * Welcome screen for the customer, used to select restaurant and destination.
 */
@SuppressWarnings("serial")
public class ViewWelcome extends JFrame implements ActionListener {
	private final Manager controller;
	private final String title = "Benvenuto, inizia ad ordinare!";
	private JComboBox<City> citiesCombo;
	private JComboBox<Restaurant> restaurantCombo;
	private JTextField addressField;
	private JButton proceedButton;
	private ViewForWorker viewWorker;
	
	/**
	 * Constructor.
	 * @param controller for the MVC pattern.
	 */
	ViewWelcome(final Manager controller,ViewForWorker viewWorker){
		this.controller = controller;
		this.viewWorker = viewWorker;
		this.Init();
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * It initializes all the GUI.
	 */
	private void Init() {
		// TODO Usare un altro Layout e meno panel.
		this.setTitle(this.title);
		this.setSize(400, 200);
		this.setLocation(700, 100);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		JLabel citiesLabel = new JLabel("Selezionare la destinazione: ");
		citiesCombo = new JComboBox<>();
		for(City c: City.values())
			citiesCombo.addItem(c);
		
		JLabel addressLabel = new JLabel("Inserire indirizzo: ");
		addressField = new JTextField("Indirizzo");
		
		JLabel restaurantLabel = new JLabel("Selezionare il ristorante: ");
		restaurantCombo = new JComboBox<>();
		for(Restaurant r: controller.getRestaurants())
			restaurantCombo.addItem(r);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		proceedButton = new JButton("Procedi"); 
		proceedButton.addActionListener(this);
		buttonsPanel.add(proceedButton);
		
		JPanel destinationPanel = new JPanel();
		destinationPanel.setLayout(new BoxLayout(destinationPanel, BoxLayout.Y_AXIS));
		destinationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		destinationPanel.add(citiesLabel);
		destinationPanel.add(citiesCombo);
	    destinationPanel.add(Box.createVerticalStrut(20));
	    destinationPanel.add(addressLabel);
		destinationPanel.add(addressField);
		
		JPanel restaurantPanel = new JPanel();
		restaurantPanel.setLayout(new BoxLayout(restaurantPanel, BoxLayout.Y_AXIS));
		restaurantPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		restaurantPanel.add(restaurantLabel);
		restaurantPanel.add(restaurantCombo);
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.X_AXIS));
		selectionPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		selectionPanel.add(destinationPanel);
		selectionPanel.add(restaurantPanel);
		
		mainPanel.add(selectionPanel);
		mainPanel.add(buttonsPanel);
		
		this.getContentPane().add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Potrei implementare questo metodo con una lamdba come per gli
		// altri bottoni delle altre view, non so quale sia migliore.
		if (e.getSource()==proceedButton) {
			controller.createOrder((City)citiesCombo.getSelectedItem(), addressField.getText(), (Restaurant)restaurantCombo.getSelectedItem());
			this.setVisible(false);
			this.dispose();
			new ViewPlacing(this.controller, this.viewWorker);
		}

	}
	
}
