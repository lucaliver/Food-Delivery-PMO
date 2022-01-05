/**
 * @author Giulia Costa
 */

// SISTEMARE LA GUI, APPENA SI DECIDE COME GESTIRE I PRODOTTI DI OGNI MENU
// DEFINIRE METODO PER PRESSIONE + O - -> AGGIUNTA DEL PREZZO E DELLA DIMENSIONE ALLA PRESSIONE DEL PULSANTE

package it.fooddelivery.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;

@SuppressWarnings("serial")
public class ViewPlacing extends JFrame implements ActionListener {
	
	private final Manager controller;
	private final String title = "Seleziona il men� pi� adatto a te!";
	private JLabel titleLabel;
	private Order nuovoOrdine;
	private JButton addButton;
	private JButton removeButton;
	private JTextArea description;
	private JTextArea descriptionPrice;
	private JTextArea descriptionSize;
	private JTextArea totalOrder;
	private JTextArea sizeOrder;
	private JButton confirmButton;
	private JButton comeBackButton;
	private JButton emptyButton;
		
	/**
	 * Al costruttore viene passato il controller,
	 * ed i menu offerti dal ristorante selezionato nella schermata precedente.
	 * @param controller.
	 * @param menuOffer = lista men� offerti dal ristorante (gi�) selezionato dall'utente.
	 */
	ViewPlacing(final Manager controller, Menu menuSelected, Order newOrder){
		this.controller 	= controller;
		this.titleLabel 	= new JLabel(title);
		this.nuovoOrdine 	= newOrder;
		this.Init(menuSelected);
		
		titleLabel.setFont(new Font("Arial", Font.ITALIC, 50));
	}
	
	private void Init(Menu menuSelected) {
		// setto le dimensioni e il titlo del JFrame
		this.setTitle(this.title);
		
		// aggiungo il pannello interno
		final JPanel panel = new JPanel();	// pannello
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(10, 20, 10, 20));
		
		// creo e aggiungo i bottoni per gestire incremento e decremento dell'ordine

		for(int i = 0; i < 10; i++) {
			final JPanel quantityPanel = new JPanel();
			quantityPanel.setLayout(new FlowLayout());
			quantityPanel.setBorder(new EmptyBorder(10, 20, 20, 10));	
			panel.add(quantityPanel);
			
			addButton 			= new JButton("+");
			removeButton 		= new JButton("-");
			description 		= new JTextArea("");
			descriptionPrice 	= new JTextArea();
			descriptionSize  	= new JTextArea();
			
			description.setPreferredSize(new Dimension(50, 20));
			descriptionPrice.setPreferredSize(new Dimension(50, 20));
			descriptionSize.setPreferredSize(new Dimension(50, 20));
			
			quantityPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			
			addButton.addActionListener( event -> nuovoOrdine.addMenu(menuSelected));
			removeButton.addActionListener( event -> nuovoOrdine.removeMenu(menuSelected));	
				
			quantityPanel.add(addButton);
			quantityPanel.add(description);
			quantityPanel.add(removeButton);
			quantityPanel.add(descriptionPrice);
			quantityPanel.add(descriptionSize);
		}
		
		// creo una textArea nella quale stampare totOrdine e dimensioneOrdine
		final JPanel result = new JPanel();
		result.setLayout(new FlowLayout());
		result.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.add(result);
		
		totalOrder 	= new JTextArea("Totale: ");
		sizeOrder 	= new JTextArea("Dimensione: ");
		
		totalOrder.setPreferredSize(new Dimension(80, 30));
		sizeOrder.setPreferredSize(new Dimension(80, 30));

		result.add(totalOrder);
		result.add(sizeOrder, BorderLayout.PAGE_END);
		
		// creo bottoni per 
		final JPanel dispositionBotton = new JPanel();
		confirmButton 	= new JButton("Conferma");
		comeBackButton 	= new JButton("Indietro");
		
		dispositionBotton.setLayout(new BorderLayout());
		panel.add(dispositionBotton);
		
		emptyButton 	= new JButton("Svuota tutto");
		
		confirmButton.addActionListener(this::actionPerformed);
		comeBackButton.addActionListener(this::comeBackHandler);
		dispositionBotton.add(confirmButton, BorderLayout.LINE_END);
		dispositionBotton.add(comeBackButton, BorderLayout.LINE_START);
		
		emptyButton.addActionListener(this::emptyHandler);
		
		panel.add(emptyButton, BoxLayout.X_AXIS);
		
		this.getContentPane().add(panel);
		this.pack();
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == confirmButton) {
			controller.getCurrentOrder();
			this.setVisible(false);
			this.dispose();	
		}
	}
		
	public void comeBackHandler(final ActionEvent e) {
		ViewWelcome comeBack;
		
		if (e.getSource() == comeBackButton) {
			comeBack = new ViewWelcome(controller);
			this.setVisible(false);
			this.dispose();	
		}
	}
	
	public void emptyHandler(final ActionEvent e) {
		if(e.getSource() == emptyButton) {
			nuovoOrdine.removeAllMenu();
			this.setVisible(false);
			this.dispose();	
		}
	}
}