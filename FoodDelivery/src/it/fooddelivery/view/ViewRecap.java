/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.Manager;

/**
 * Window for the recap screen of the current order for the customer.
 */
@SuppressWarnings("serial")
public class ViewRecap extends JFrame {
	
	private final Manager controller;
	private final String title = "Il tuo ordine :)";
	private JButton sendButton;
	private JButton backButton;
	private ViewPlacing viewPlacing;
	
	/**
	 * Constructor.
	 * @param controller for the MVC pattern.
	 */
	ViewRecap(final Manager controller, final ViewPlacing viewPlacing){
		this.controller = controller;
		this.Init();
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		//this.viewForWorker = new ViewForWorker(this.controller);
		this.viewPlacing = viewPlacing;
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
		
		JTextArea menusArea = new JTextArea("Questo ordine � vuoto?");
		menusArea.setEditable(false);
		menusArea.setBorder(BorderFactory.createTitledBorder("Contenuto: "));
		menusArea.setText(controller.getCurrentOrder().showOrderContent());
		
		JTextArea infoArea = new JTextArea();
		infoArea.setEditable(false);
		infoArea.setBorder(BorderFactory.createTitledBorder("Info ordine: "));
		infoArea.setText(this.controller.getCurrentOrder().showOrderInfo());

		sendButton = new JButton("Invia ordine"); 
		sendButton.addActionListener(event ->{
			boolean res = this.controller.assignOrder(controller.getCurrentOrder());
			if(res) {
				JOptionPane.showMessageDialog(this, "Ordine assegnato a "+this.controller.getRiderWithLastOrder().get().getName());						          
			}else {
				JOptionPane.showMessageDialog(this, "Ordine ricevuto, in attesa che si liberi un fattorino.");
			}
			this.setVisible(false);
			this.dispose();
			new ViewWelcome(this.controller);
			ViewForWorker.getInstance(controller).receiveNewOrder(this.controller.getRiderWithLastOrder());
		});
						
		backButton = new JButton("Indietro"); 
		backButton.addActionListener(event -> {
			this.setVisible(false);
			this.dispose();	
			this.viewPlacing.setVisible(true);
		});

		mainPanel.add(backButton);
		mainPanel.add(Box.createHorizontalStrut(30));
		mainPanel.add(infoArea);
		mainPanel.add(Box.createHorizontalStrut(30));
		mainPanel.add(menusArea);
		mainPanel.add(Box.createHorizontalStrut(30));
		mainPanel.add(sendButton);
		this.getContentPane().add(mainPanel);
		
	}
}
