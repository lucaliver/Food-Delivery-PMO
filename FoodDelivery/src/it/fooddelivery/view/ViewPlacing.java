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

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Menu;

@SuppressWarnings("serial")
public class ViewPlacing extends JFrame implements ActionListener {
	
	private final Manager controller;
	private final String title = "Seleziona il men� pi� adatto a te!";
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
	 * @param menuOffer = lista men� offerti dal ristorante (gi�) selezionato dall'utente.
	 */
	ViewPlacing(final Manager controller){
		this.controller 	= controller;
		this.Init();
	}
	
	private void Init() {
		// setto le dimensioni e il titlo del JFrame
		this.setTitle(this.title);
		
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
			quantityArea 		= new JTextArea("");
			descriptionArea 	= new JTextArea();
			
			quantityPanel.add(removeButton);
			quantityPanel.add(quantityArea);
			quantityPanel.add(addButton);
			quantityPanel.add(descriptionArea);
			
			quantityArea.setPreferredSize(new Dimension(30, 20));
			descriptionArea.setPreferredSize(new Dimension(300, 20));
			descriptionArea.setText(m.show());
			
			// TODO modificare 
			addButton.addActionListener(event -> controller.getCurrentOrder().addMenu(m));
			//removeButton.addActionListener( event -> nuovoOrdine.removeMenu(menuSelected));	
		}
		
		// creo una textArea nella quale stampare totOrdine e dimensioneOrdine
		final JPanel result = new JPanel();
		result.setLayout(new FlowLayout());
		result.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.add(result);
		
		totalOrderArea 	= new JTextArea("Totale: ");
		sizeOrderArea 	= new JTextArea("Dimensione: ");
		
		totalOrderArea.setPreferredSize(new Dimension(80, 30));
		sizeOrderArea.setPreferredSize(new Dimension(80, 30));
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
		
		confirmButton.addActionListener(this::actionPerformed);
		comeBackButton.addActionListener(this::comeBackHandler);
		dispositionBotton.add(confirmButton, BorderLayout.LINE_END);
		dispositionBotton.add(comeBackButton, BorderLayout.LINE_START);
		
		//emptyButton.addActionListener(this::emptyHandler);
		
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
			ViewRecap v = new ViewRecap(this.controller);
			v.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			v.setVisible(true);
		}
	}
		
	public void comeBackHandler(final ActionEvent e) {
		if (e.getSource() == comeBackButton) {
			ViewWelcome comeBack = new ViewWelcome(this.controller);
			comeBack.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			comeBack.setVisible(true);
		}
	}
	
	public void emptyHandler(final ActionEvent e) {
		if(e.getSource() == emptyButton) {
			controller.getCurrentOrder().removeAllMenus();
			this.setVisible(false);
			this.dispose();	
		}
	}
}