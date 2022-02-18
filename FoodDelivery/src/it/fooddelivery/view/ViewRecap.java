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
	private JPanel mainPanel;
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
		this.viewPlacing = viewPlacing;
	}
	
	/**
	 * Initializes all the GUI.
	 */
	private void Init() {
		this.setTitle(this.title);
		this.setSize(600, 400);
		this.setLocation(700, 100);
		
		this.mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		mainPanel.add(createOrderRecapPanel());
		mainPanel.add(createButtonPanel());
		
		this.getContentPane().add(mainPanel);		
	}

	private JPanel createButtonPanel() {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
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
		buttonsPanel.add(sendButton);
						
		backButton = new JButton("Indietro"); 
		backButton.addActionListener(event -> {
			this.setVisible(false);
			this.dispose();	
			this.viewPlacing.setVisible(true);
		});
		buttonsPanel.add(backButton);
		
		return buttonsPanel;
	}

	private JPanel createOrderRecapPanel() {
		
		JPanel orderDataPanel = new JPanel();
		orderDataPanel.setLayout(new BoxLayout(orderDataPanel, BoxLayout.X_AXIS));
		orderDataPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JTextArea orderInfoArea = new JTextArea();
		orderInfoArea.setEditable(false);
		orderInfoArea.setBorder(BorderFactory.createTitledBorder("Info ordine: "));
		orderInfoArea.setText(this.controller.getCurrentOrderPresent().showOrderInfo());
		orderDataPanel.add(orderInfoArea);
				
		JTextArea orderContentArea = new JTextArea("Questo ordine è vuoto?");
		orderContentArea.setEditable(false);
		orderContentArea.setBorder(BorderFactory.createTitledBorder("Contenuto: "));
		orderContentArea.setText(controller.getCurrentOrderPresent().showOrderContent());
		orderDataPanel.add(orderContentArea);

		return orderDataPanel;
	}
}
