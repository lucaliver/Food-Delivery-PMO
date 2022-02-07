/**
 * @author Giulia Costa
 */

package it.fooddelivery.view;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.implementation.RiderImpl;

/**
 * Second window for the customer, used to select menus.
 */
@SuppressWarnings("serial")
public class ViewPlacing extends JFrame {
	private static final DecimalFormat df = new DecimalFormat("0.00"); //Per i centesimi
	
	private final Manager controller;
	private final String title = "Seleziona i piatti più adatti a te!";
	private JTextArea totalOrderArea;
	private JTextArea sizeOrderArea;
	private JButton confirmButton;
	private JButton backButton;
	private JButton emptyButton;
	private Map<JButton, JTextArea> buttonsToQuantityAreas;
		
	/**
	 * Constructor.
	 * 
	 * @param controller.
	 */
	ViewPlacing(final Manager controller){
		this.controller = controller;
		this.buttonsToQuantityAreas = new HashMap<>();
		this.Init();
		this.updateInfo();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * Initializes all the window.
	 */
	private void Init() {
		this.setTitle(this.title);
		this.setLocation(700, 100);
		
		final JPanel mainPanel = new JPanel();	
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
	
		for (Menu m : controller.getCurrentOrderPresent().getRestaurant().getMenuOffer()) {
			final JPanel quantityPanel = new JPanel();
			quantityPanel.setLayout(new FlowLayout());
			quantityPanel.setBorder(new EmptyBorder(10, 20, 20, 10));	
			quantityPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			mainPanel.add(quantityPanel);
			
			final JTextArea descriptionArea = new JTextArea(m.showMenuInfo());
			final JTextArea quantityArea = new JTextArea("0");
			final JButton removeButton 	= new JButton("-");
			final JButton addButton 	= new JButton("+");	
			quantityArea.setPreferredSize(new Dimension(30, 20));
			descriptionArea.setPreferredSize(new Dimension(300, 20));
			
			quantityPanel.add(removeButton);
			quantityPanel.add(quantityArea);
			quantityPanel.add(addButton);
			quantityPanel.add(descriptionArea);
			
			buttonsToQuantityAreas.put(addButton, quantityArea);
			buttonsToQuantityAreas.put(removeButton, quantityArea);	
			
			addButton.addActionListener(event -> {
				controller.addToCurrent(m);
				buttonsToQuantityAreas.get(addButton).setText("" + controller.howManyInCurrent(m));	
				this.updateInfo();						
			});
			
			removeButton.addActionListener(event -> {		
				if (controller.removeFromCurrent(m))
					buttonsToQuantityAreas.get(removeButton).setText("" + controller.howManyInCurrent(m));
				this.updateInfo();
			});
		}
		
		final JPanel orderInfoPanel = new JPanel();
		orderInfoPanel.setLayout(new FlowLayout());
		orderInfoPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		mainPanel.add(orderInfoPanel);
		
		totalOrderArea 	= new JTextArea();
		sizeOrderArea 	= new JTextArea();
		totalOrderArea.setPreferredSize(new Dimension(100, 30));
		sizeOrderArea.setPreferredSize(new Dimension(100, 30));
		totalOrderArea.setEditable(false);
		sizeOrderArea.setEditable(false);

		orderInfoPanel.add(totalOrderArea);
		orderInfoPanel.add(Box.createHorizontalStrut(30));
		orderInfoPanel.add(sizeOrderArea, BorderLayout.PAGE_END);

		final JPanel buttonsPanel = new JPanel();
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

		backButton 	= new JButton("Indietro");
		backButton.addActionListener(event -> {
			this.controller.getCurrentOrderPresent().removeAllMenus();
			this.setVisible(false);
			this.dispose();
			new ViewWelcome(this.controller);
		});

		emptyButton 	= new JButton("Svuota tutto");
		emptyButton.addActionListener(event -> {
			controller.getCurrentOrderPresent().removeAllMenus();
			buttonsToQuantityAreas.values().forEach(t -> t.setText("0"));
			this.updateInfo();
		});
		
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(confirmButton, BorderLayout.LINE_END);
		buttonsPanel.add(emptyButton, BorderLayout.CENTER);
		buttonsPanel.add(backButton, BorderLayout.LINE_START);
		mainPanel.add(buttonsPanel);
		
		this.getContentPane().add(mainPanel);
		this.pack();
	}
	
	/**
	 * Updates the price area and the size area.
	 */
	private void updateInfo() {
		this.totalOrderArea.setText("Totale: " + df.format(controller.getCurrentOrderPresent().getPrice()) + "€");
		this.sizeOrderArea.setText("Dimensione: " + controller.getCurrentOrderPresent().getSize() + "u.");
	}
}