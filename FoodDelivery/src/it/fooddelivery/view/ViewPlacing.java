/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.view;

import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import it.fooddelivery.controller.ManagerImpl;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.implementation.RiderImpl;

/**
 * Second window for the customer, used to select menus.
 */
@SuppressWarnings("serial")
public class ViewPlacing extends JFrame {
		
	private final ManagerImpl controller;
	private JPanel mainPanel;
	private JTextArea totalOrderArea;
	private JTextArea sizeOrderArea;
	private JButton confirmButton;
	private JButton backButton;
	private JButton removeAllButton;
	private final String title = "Seleziona i piatti del tuo ordine!";
	private final Map<JButton, JTextArea> infoButtons;
		
	/**
	 * Constructs a screen to select menus for the order.
	 * 
	 * @param controller controller component for the MVC pattern
	 */
	public ViewPlacing(final ManagerImpl controller){
		this.controller = controller;
		this.infoButtons = new HashMap<>();
		this.Init();
		this.updateInfo();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * Initializes all the window.
	 */
	private void Init() {
		this.setTitle(title);
		this.setLocation(700, 100);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		mainPanel = new JPanel();	
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
	
		this.controller.getCurrentOrderPresent().getRestaurant().getMenuOffer().forEach(m ->
			mainPanel.add(createMenuPanel(m)));
		
		mainPanel.add(createOrderDataPanel());
		mainPanel.add(createButtonsPanel());		
		
		this.getContentPane().add(mainPanel);
		this.pack();
	}
	
	/**
	 * Creates the proceed button, back button and empty button.
	 * 
	 * @return the panel itself
	 */
	private JPanel createButtonsPanel() {
		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		
		confirmButton 	= new JButton("Procedi");
		confirmButton.addActionListener(Event -> {
			if (controller.getCurrentOrderPresent().getSize() <= 0) {
				JOptionPane.showMessageDialog(this, "Non hai selezionato nessun menu :(");
			} else if(this.controller.getCurrentOrderPresent().getSize() > RiderImpl.getMaxCapacity()) {
				JOptionPane.showMessageDialog(this, "Non abbiamo zaini così grandi! \n"
						+ "La capacità massima dei nostri zaini è di "  +RiderImpl.getMaxCapacity() + " unità.");
			}
			else {				
				this.setVisible(false);
				new ViewRecap(this.controller,this);
			}
		});
		buttonsPanel.add(confirmButton, BorderLayout.LINE_END);
		
		backButton 	= new JButton("Indietro");
		backButton.addActionListener(event -> {
			this.controller.getCurrentOrderPresent().removeAllMenus();
			this.setVisible(false);
			this.dispose();
			new ViewWelcome(this.controller);
		});
		buttonsPanel.add(backButton, BorderLayout.LINE_START);

		removeAllButton 	= new JButton("Svuota tutto");
		removeAllButton.addActionListener(event -> {
			controller.getCurrentOrderPresent().removeAllMenus();
			infoButtons.values().forEach(t -> t.setText("0"));
			this.updateInfo();
		});
		buttonsPanel.add(removeAllButton, BorderLayout.CENTER);
							
		return buttonsPanel;
	}

	/**
	 * Creates the part of the window that shows total size and price of the current order.
	 * 
	 * @return the panel itself
	 */
	private JPanel createOrderDataPanel() {
		final JPanel orderInfoPanel = new JPanel();
		orderInfoPanel.setLayout(new FlowLayout());
		orderInfoPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		totalOrderArea 	= new JTextArea();
		totalOrderArea.setPreferredSize(new Dimension(100, 30));
		totalOrderArea.setEditable(false);
		totalOrderArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		sizeOrderArea 	= new JTextArea();		
		sizeOrderArea.setPreferredSize(new Dimension(100, 30));		
		sizeOrderArea.setEditable(false);
		sizeOrderArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		orderInfoPanel.add(totalOrderArea);
		orderInfoPanel.add(Box.createHorizontalStrut(30));
		orderInfoPanel.add(sizeOrderArea, BorderLayout.PAGE_END);
		return orderInfoPanel;
	}
	
	/**
	 * Creates the part of the window for a single menu, with its descriptions and the buttons to select it.
	 * 
	 * @param menu the menu
	 * @return the panel itself
	 */
	private JPanel createMenuPanel(Menu menu) {
		JPanel quantityPanel = new JPanel();
		quantityPanel.setLayout(new FlowLayout());
		quantityPanel.setBorder(new EmptyBorder(10, 20, 20, 10));	
		quantityPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		final JTextArea descriptionArea = new JTextArea(menu.showMenuInfo());
		final JTextArea quantityArea = new JTextArea("0");
		final JButton removeButton 	= new JButton("-");
		final JButton addButton 	= new JButton("+");	
		
		quantityArea.setPreferredSize(new Dimension(30, 20));
		descriptionArea.setPreferredSize(new Dimension(300, 20));
		descriptionArea.setFont(new Font("", Font.ITALIC, 12));
		
		quantityPanel.add(removeButton);
		quantityPanel.add(quantityArea);
		quantityPanel.add(addButton);
		quantityPanel.add(descriptionArea);
		
		infoButtons.put(addButton, quantityArea);
		infoButtons.put(removeButton, quantityArea);	
		
		addButton.addActionListener(event -> {
			controller.increaseInCurrent(menu);
			infoButtons.get(addButton).setText("" + controller.howManyInCurrent(menu));	
			this.updateInfo();						
		});
		
		removeButton.addActionListener(event -> {		
			this.controller.decreaseInCurrent(menu);
				infoButtons.get(removeButton).setText("" + controller.howManyInCurrent(menu));
			this.updateInfo();
		});
		return quantityPanel;
	}
	
	/**
	 * Updates the price area and the size area.
	 */
	private void updateInfo() {
		this.totalOrderArea.setText("Totale: " + String.format("%.2f",controller.getCurrentOrderPresent().getPrice()) + "€");
		this.sizeOrderArea.setText("Dimensione: " + controller.getCurrentOrderPresent().getSize() + "u.");
	}
}