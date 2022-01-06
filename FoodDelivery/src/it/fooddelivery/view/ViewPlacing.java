/**
 * @author Giulia Costa
 */

// SISTEMARE LA GUI, APPENA SI DECIDE COME GESTIRE I PRODOTTI DI OGNI MENU
// DEFINIRE METODO PER PRESSIONE + O - -> AGGIUNTA DEL PREZZO E DELLA DIMENSIONE ALLA PRESSIONE DEL PULSANTE

// PER INTERFACCIA: CREO 2 PANNELLI DIVISI: 
//1 FLOWLAYOUT(necessario per mettere la descrizione dell'articolo (menu)
//1 LAYOUT VERTICALE (necessario per inserire prezzo, collegato a totPrezzo)
//1 LAYOUT VERTICALE (necesssario per inserire dimensione ordine, collegato a totSize)

// COLLEGARE BOTTONI +,- PER VISUALIZZARE A SCHERMO LE QUANTITA' SELEZIONATE

package it.fooddelivery.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Menu;

@SuppressWarnings("serial")
public class ViewPlacing extends JFrame implements ActionListener {
	
	private final Manager controller;
	private final String title = "Seleziona il menù più adatto a te!";
	private JTextArea quantityArea;
	private JTextArea totalOrderArea;
	private JTextArea sizeOrderArea;
	private JButton addButton;
	private JButton removeButton;
	private JButton confirmButton;
	private JButton comeBackButton;
	private JButton emptyButton;
		
	/**
	 * Al costruttore viene passato il controller,
	 * ed i menu offerti dal ristorante selezionato nella schermata precedente.
	 * @param controller.
	 * @param menuOffer = lista menù offerti dal ristorante (già) selezionato dall'utente.
	 */
	ViewPlacing(final Manager controller){
		this.controller 	= controller;
		this.Init();
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
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
			
			removeButton 		= new JButton("-");
			addButton 			= new JButton("+");
			quantityArea 		= new JTextArea("0");
			descriptionArea 	= new JTextArea();
			
			quantityPanel.add(removeButton);
			quantityPanel.add(quantityArea);
			quantityPanel.add(addButton);
			quantityPanel.add(descriptionArea);
			
			quantityArea.setPreferredSize(new Dimension(30, 20));
			descriptionArea.setPreferredSize(new Dimension(300, 20));
			descriptionArea.setText(m.show());
			
			// TODO I tasti + e - aggiornano totale e dimensione ad ogni click. 
			addButton.addActionListener(event -> {
				controller.getCurrentOrder().addMenu(m);
				// TODO usato come suggerito da Giulia un metodo quantity di order, funzionamento parziale
				/*this.quantityArea.setText(this.controller.getCurrentOrder().quantity(m)+"");
				// Altrimenti metodo alternativo
				int i = Integer.parseInt(quantityArea.getText())+1;
				this.quantityArea.setText(i+"");*/
				this.updateInfo();
			});
			
			removeButton.addActionListener(event -> {
				controller.getCurrentOrder().removeMenu(m);
				// TODO usato come suggerito da Giulia un metodo quantity di order, funzionamento parziale
				/*this.quantityArea.setText(this.controller.getCurrentOrder().quantity(m)+"");
				// Altrimenti metodo alternativo
				int i = Integer.parseInt(quantityArea.getText())-1;
				this.quantityArea.setText(i+"");*/
				this.updateInfo();
			});
		}
		
		// creo una textArea nella quale stampare totOrdine e dimensioneOrdine
		final JPanel result = new JPanel();
		result.setLayout(new FlowLayout());
		result.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.add(result);
		
		totalOrderArea 	= new JTextArea("Totale: 0€");
		sizeOrderArea 	= new JTextArea("Dimensione: 0u.");
		
		totalOrderArea.setPreferredSize(new Dimension(100, 30));
		sizeOrderArea.setPreferredSize(new Dimension(100, 30));
		totalOrderArea.setEditable(false);
		sizeOrderArea.setEditable(false);

		result.add(totalOrderArea);
		result.add(Box.createHorizontalStrut(30));
		result.add(sizeOrderArea, BorderLayout.PAGE_END);
		
		// creo bottoni per 
		final JPanel dispositionBotton = new JPanel();
		confirmButton 	= new JButton("Conferma");
		comeBackButton 	= new JButton("Indietro");
		
		dispositionBotton.setLayout(new BorderLayout());
		panel.add(dispositionBotton);
		
		emptyButton 	= new JButton("Svuota tutto");
		emptyButton.addActionListener(event -> {
			controller.getCurrentOrder().removeAllMenus();
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
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == confirmButton) {
			this.setVisible(false);
			this.dispose();	
			ViewRecap v = new ViewRecap(this.controller);
		}
	}
		
	public void comeBackHandler(final ActionEvent e) {
		if (e.getSource() == comeBackButton) {
			this.controller.getCurrentOrder().removeAllMenus();
			this.setVisible(false);
			this.dispose();
			new ViewWelcome(this.controller);
		}
	}
	
	/*
	public void emptyHandler(final ActionEvent e) {
		if(e.getSource() == emptyButton) {
			controller.getCurrentOrder().removeAllMenus();
			this.setVisible(false);
			this.dispose();	
		}
	} */
	
	private void updateInfo() {
		this.totalOrderArea.setText("Totale: " + controller.getCurrentOrder().getTotalPrice() + "€");
		this.sizeOrderArea.setText("Dimensione: " + controller.getCurrentOrder().getSize() + "u.");
	}
}