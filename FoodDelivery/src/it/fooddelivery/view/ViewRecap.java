/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Menu;

/**
 * View component for the recap screen for the customer.
 *
 */
@SuppressWarnings("serial")
public class ViewRecap extends JFrame implements ActionListener {
	private final Manager controller;
	private final String title = "Il mio ordine :)";
	private JButton proceedButton;
	private JButton backButton;
	
	/**
	 * Constructor.
	 * @param controller for the MVC pattern.
	 */
	ViewRecap(final Manager controller){
		this.controller = controller;
		this.Init();
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * It initializes all the GUI.
	 */
	private void Init() {
		this.setTitle(this.title);
		this.setSize(600, 400);
		this.setLocation(700, 100);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		JTextArea menusArea = new JTextArea("Questo ordine è vuoto?");
		menusArea.setEditable(false);
		menusArea.setBorder(BorderFactory.createTitledBorder("Contenuto: "));
		StringBuilder sbMenus = new StringBuilder();
		for (Menu m: controller.getCurrentOrder().getMenusList()) {
			sbMenus.append(m.show());
			sbMenus.append('\n');
		}
		menusArea.setText(sbMenus.toString());
		
		JTextArea infoArea = new JTextArea();
		infoArea.setEditable(false);
		infoArea.setBorder(BorderFactory.createTitledBorder("Info ordine: "));
		StringBuilder sbInfo = new StringBuilder();
		sbInfo.append("Destinazione: " + this.controller.getCurrentOrder().getDestination() + ", "
				+ this.controller.getCurrentOrder().getAdress() + '\n'
				+ "Ristorante: " + this.controller.getCurrentOrder().getRestaurant() + '\n'
				+ "Totale: " + this.controller.getCurrentOrder().getTotalPrice() + "€" + '\n'
				//+ "Dimensione: " + this.controller.getCurrentOrder().getSize()
				);
		infoArea.setText(sbInfo.toString());

		proceedButton = new JButton("Procedi"); 
		proceedButton.addActionListener(event ->{
			this.controller.assignOrder(this.controller.getCurrentOrder());
			this.setVisible(false);
			this.dispose();	
		});
						
		backButton = new JButton("Indietro"); 
		backButton.addActionListener(event -> {
			this.setVisible(false);
			this.dispose();	
			new ViewPlacing(this.controller);
		});

		mainPanel.add(backButton);
		mainPanel.add(Box.createHorizontalStrut(30));
		mainPanel.add(infoArea);
		mainPanel.add(Box.createHorizontalStrut(30));
		mainPanel.add(menusArea);
		mainPanel.add(Box.createHorizontalStrut(30));
		mainPanel.add(proceedButton);

		this.getContentPane().add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Se ho cliccato procedi, l'ordine viene piazzato con successo.
		
		/*
		if (e.getSource()==proceedButton)
			this.controller.placeCurrentOrder();
		*/
	}
}
