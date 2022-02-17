/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.ManagerImpl;

/**
 * Window for the recap screen of the current order for the customer.
 */
@SuppressWarnings("serial")
public class ViewRecap extends JFrame {
	private final ManagerImpl controller;
	private final String title = "Il tuo ordine :)";
	private JButton sendButton;
	private JButton backButton;
	private ViewPlacing viewPlacing;
	
	/**
	 * Constructor.
	 * 
	 * @param controller for the MVC pattern.
	 */
	ViewRecap(final ManagerImpl controller, final ViewPlacing viewPlacing){
		this.controller = controller;
		this.Init();
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		//this.viewForWorker = new ViewForWorker(this.controller);
		this.viewPlacing = viewPlacing;
	}
	
	/**
	 * Initializes all the GUI.
	 */
	private void Init() {
		this.setTitle(this.title);
		this.setSize(600, 400);
		this.setLocation(700, 100);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		JTextArea menusArea = new JTextArea("Questo ordine è vuoto?");
		menusArea.setEditable(false);
		menusArea.setBorder(BorderFactory.createTitledBorder("Contenuto: "));
		menusArea.setText(controller.getCurrentOrderPresent().showOrderContent());
		
		JTextArea infoArea = new JTextArea();
		infoArea.setEditable(false);
		infoArea.setBorder(BorderFactory.createTitledBorder("Info ordine: "));
		infoArea.setText(this.controller.getCurrentOrderPresent().showOrderInfo());

		sendButton = new JButton("Invia ordine"); 
		sendButton.addActionListener(event ->{
			boolean res = this.controller.assignCurrentOrder();
			if(res) {
				JOptionPane.showMessageDialog(this, "Ordine assegnato a "+this.controller.getRiderWithLastOrder().get().getName());						          
			}else {
				JOptionPane.showMessageDialog(this, "Ordine ricevuto, in attesa che si liberi un fattorino.");
			}
			this.setVisible(false);
			this.dispose();
			new ViewWelcome(this.controller);
			ViewWorker.getInstance().receiveNewOrder(this.controller.getRiderWithLastOrder());
		});
						
		backButton = new JButton("Indietro"); 
		backButton.addActionListener(event -> {
			this.setVisible(false);
			this.dispose();	
			this.viewPlacing.setVisible(true);
		});

		JPanel areasPanel = new JPanel();
		areasPanel.setLayout(new BoxLayout(areasPanel, BoxLayout.X_AXIS));
		areasPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		areasPanel.add(infoArea);
		areasPanel.add(menusArea);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonsPanel.add(backButton);
		buttonsPanel.add(sendButton);
		
		mainPanel.add(areasPanel);
		mainPanel.add(buttonsPanel);
		this.getContentPane().add(mainPanel);
		
	}
}
