/**
 * @author Giulia Costa
 */

package it.fooddelivery.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

// TODO- gestire eccezione nel momento in cui si spinge il bottone - e esce eccezione

/**
 * Second window for the customer, used to select menus.
 */
@SuppressWarnings("serial")
public class ViewPlacing extends JFrame implements ActionListener {
	private static final DecimalFormat df = new DecimalFormat("0.00"); //Per i centesimi
	
	private final Manager controller;
	private final String title = "Seleziona il menù più adatto a te!";
	private JTextArea totalOrderArea;
	private JTextArea sizeOrderArea;
	private JButton confirmButton;
	private JButton comeBackButton;
	private JButton emptyButton;
	private Map<JButton, JTextArea> infoQuantityMenu;
		
	/**
	 * Constructor.
	 * @param controller.
	 */
	ViewPlacing(final Manager controller){
		this.controller 	= controller;
		this.infoQuantityMenu = new HashMap<>();
		this.Init();
		this.updateInfo();
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * It initializes all the window.
	 */
	private void Init() {
		// setto le dimensioni e il titlo del JFrame
		this.setTitle(this.title);
		this.setLocation(700, 100);
		
		// aggiungo il pannello interno
		final JPanel panel = new JPanel();	// pannello
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(10, 20, 10, 20));
	
		// creo e aggiungo i bottoni per gestire incremento e decremento dell'ordine
		for (Menu m : controller.getCurrentOrder().getRestaurant().getMenuOffer()) {
					
			final JPanel quantityPanel = new JPanel();
			quantityPanel.setLayout(new FlowLayout());
			quantityPanel.setBorder(new EmptyBorder(10, 20, 20, 10));	
			quantityPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			panel.add(quantityPanel);
			
			final JTextArea descriptionArea;
			final JTextArea quantityArea;
		
			
			JButton removeButton 		= new JButton("-");
			JButton addButton 			= new JButton("+");
			quantityArea 		= new JTextArea();
			descriptionArea 	= new JTextArea();
			
			quantityPanel.add(removeButton);
			quantityPanel.add(quantityArea);
			quantityPanel.add(addButton);
			quantityPanel.add(descriptionArea);
			
			quantityArea.setPreferredSize(new Dimension(30, 20));
			descriptionArea.setPreferredSize(new Dimension(300, 20));
			
			descriptionArea.setText(m.showMenuInfo());
			quantityArea.setText("0");
			
			// Mappatura bottoni
			infoQuantityMenu.put(addButton, quantityArea);
			infoQuantityMenu.put(removeButton, quantityArea);	
			
			addButton.addActionListener(event -> {
				controller.getCurrentOrder().addMenu(m);
				this.updateInfo();

				controller.getCurrentOrder().incrementalMenu(m.getName());	
				infoQuantityMenu.get(addButton).setText("" + m.getQuantity());							
			});
			
			removeButton.addActionListener(event -> {
				//quantityArea.setText(" " + 
				controller.getCurrentOrder().decrementalMenu(m.getName());//);							

				controller.getCurrentOrder().removeMenu(m);
				infoQuantityMenu.get(removeButton).setText("" + m.getQuantity());
				this.updateInfo();
			});
		}
		
		// creo una textArea nella quale stampare totOrdine e dimensioneOrdine
		final JPanel result = new JPanel();
		result.setLayout(new FlowLayout());
		result.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.add(result);
		
		totalOrderArea 	= new JTextArea();
		sizeOrderArea 	= new JTextArea();
		
		totalOrderArea.setPreferredSize(new Dimension(100, 30));
		sizeOrderArea.setPreferredSize(new Dimension(100, 30));
		totalOrderArea.setEditable(false);
		sizeOrderArea.setEditable(false);

		result.add(totalOrderArea);
		result.add(Box.createHorizontalStrut(30));
		result.add(sizeOrderArea, BorderLayout.PAGE_END);
		
		// creo bottoni
		final JPanel dispositionBotton = new JPanel();
		confirmButton 	= new JButton("Conferma");
		comeBackButton 	= new JButton("Indietro");
		
		dispositionBotton.setLayout(new BorderLayout());
		panel.add(dispositionBotton);
		
		emptyButton 	= new JButton("Svuota tutto");
		emptyButton.addActionListener(event -> {
			controller.getCurrentOrder().removeAllMenus();
			
			System.out.println("SIZE: " + infoQuantityMenu.size());
			infoQuantityMenu.values().forEach(t -> t.setText("0"));
			//infoQuantityMenu.values().stream().findAny().get().setText("0");
			
			//.values().stream().map(t -> t + "0").forEach(System.out::print);	
			// prima potevo fare: quantityArea.setText("0");
			this.updateInfo();
		});
		
		confirmButton.addActionListener(this::actionPerformed);
		comeBackButton.addActionListener(this::comeBackHandler);
		dispositionBotton.add(confirmButton, BorderLayout.LINE_END);
		dispositionBotton.add(comeBackButton, BorderLayout.LINE_START);
		
		panel.add(emptyButton, BoxLayout.X_AXIS);
		
		this.getContentPane().add(panel);
		this.pack();
	}
	
	/**
	 * Handler for the event of  "confirm" button being pressed.
	 * @param event.
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == confirmButton) {
			if (controller.getCurrentOrder().getMenus().size() > 0) {
				this.setVisible(false);
				this.dispose();	
				new ViewRecap(this.controller);
			} else {
				JOptionPane.showMessageDialog(this, "Non hai selezionato nessun menu :(");
			}
			controller.getCurrentOrder().removeAllMenus();
		}
	}
	
	/**
	 * Handler for the event of the "back" button being pressed.
	 * @param event.
	 */
	public void comeBackHandler(final ActionEvent e) {
		if (e.getSource() == comeBackButton) {
			this.controller.getCurrentOrder().removeAllMenus();
			this.setVisible(false);
			this.dispose();
			new ViewWelcome(this.controller);
		}
	}
	
	/**
	 * It updates the price area and the size area.
	 */
	private void updateInfo() {
		this.totalOrderArea.setText("Totale: " + df.format(controller.getCurrentOrder().getOrderPrice()) + "€");
		this.sizeOrderArea.setText("Dimensione: " + controller.getCurrentOrder().getOrderSize() + "u.");
	}
}