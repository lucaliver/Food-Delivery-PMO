/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import it.fooddelivery.controller.ManagerImpl;
import it.fooddelivery.model.Rider;

/**
 * The only screen riders will see. It shows the bag of everyone and let them empty it.
 */
@SuppressWarnings("serial")
public class ViewWorker extends JFrame{
	
	private static ViewWorker instance = null;
	
	private ManagerImpl controller;
	private JPanel mainPanel;
	private JTextArea riderArea;
	private JTextArea orderArea;
	private JTextArea waitingOrdersArea;
	private JButton deliveryButton;
	private final String mainPanelTitle = "Rider Screen";	
	private final String emptyBagTitle = "In attesa di ordini da consegnare!!!";
	private final String emptyWaitTitle = "In attesa di ordini da smistare!!!";
	private final Map<Rider, JTextArea> infoOrder;
	private final Map<Rider, JTextArea> infoRider;
	
	/**
	 * Private singleton Constructor.
	 */
	private ViewWorker (){
		this.infoOrder = new HashMap<>();
		this.infoRider = new HashMap<>();	
	}
	
	/**
	 * It initializes the controller and the view
	 * @param controller manager of the view
	 */
	public void initView(final ManagerImpl controller) {
		this.controller = controller;
		this.Init();
	}
	
	/**
	 * It creates one and only one object with ViewForWorker type
	 * @return the same object every time
	 */
	public static ViewWorker getInstance() {
		if(instance == null) {
			instance = new ViewWorker();
		}
		return instance;
	}
	
	/**
	 * It checks where the last order was sent
	 * @param r = rider who receives the last order, null if order is in waitingList
	 */
	public void receiveNewOrder(Optional<Rider> r) {
		if(r.isPresent()) {
			this.updateRiderData(r.get());
		}else {
			this.updateWaitingOrders();
		}
	}
	
	/**
	 * It initializes the window.
	 */
	private void Init(){
		
		this.setTitle(mainPanelTitle);
		//this.setSize(180,80);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		this.controller.getRiders().values().forEach(r -> 
			mainPanel.add(createRiderPanel(r)));
		mainPanel.add(createWaitingOrderPanel());
		
		this.getContentPane().add(mainPanel);
		this.pack();
	}

	/**
	 * It creates the GUI for one rider (text and button).
	 * @param r the rider where to get the data
	 * @return the panel itself.
	 */
	private JPanel createRiderPanel(Rider r) {
		JPanel riderPanel = new JPanel();
		riderPanel.setBorder(BorderFactory.createTitledBorder(r.getName()));
		final GroupLayout riderPanelLayout = new GroupLayout(riderPanel);
		
		riderArea = new JTextArea(2,8);
		riderArea.setEditable(false);
		riderArea.setText(r.showRiderInfo());
		riderArea.setBackground(getBackground());
		infoRider.put(r, riderArea);
		
		orderArea = new JTextArea(4,20);
		orderArea.setEditable(false);
		orderArea.setText(emptyBagTitle);
		orderArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		infoOrder.put(r, orderArea);
		
		JScrollPane scrollOrderArea = new JScrollPane(orderArea);
		scrollOrderArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		deliveryButton = new JButton("Parti e consegna!");
		deliveryButton.addActionListener(e -> {
			this.startDelivery(r);
			this.checkWaitingOrders(r);			
		});
					
		riderPanelLayout.setHorizontalGroup(
				riderPanelLayout.createSequentialGroup()
				.addComponent(riderArea)
				.addComponent(scrollOrderArea)
				.addComponent(Box.createHorizontalStrut(15))
				.addComponent(deliveryButton));	
		riderPanelLayout.setVerticalGroup(
				riderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(riderArea)
				.addComponent(scrollOrderArea)
				.addComponent(Box.createHorizontalStrut(15))
				.addComponent(deliveryButton));

		this.pack();
		return riderPanel;
	}
	
	/**
	 * It creates part of the window that shows the waiting orders.
	 * @return the panel itself.
	 */
	private JPanel createWaitingOrderPanel() {
		JPanel waitingSection = new JPanel();
		waitingSection.setBorder(BorderFactory.createTitledBorder("Ordini in attesa"));
		final GroupLayout waitingSectionLayout = new GroupLayout(waitingSection);
		
		waitingOrdersArea = new JTextArea(5,30);
		waitingOrdersArea.setText(emptyWaitTitle);
		waitingOrdersArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
		JScrollPane scrollWaitingArea = new JScrollPane(waitingOrdersArea);
		scrollWaitingArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		waitingSectionLayout.setHorizontalGroup(
				waitingSectionLayout.createSequentialGroup()
				.addComponent(scrollWaitingArea));				
		waitingSectionLayout.setVerticalGroup(
				waitingSectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(scrollWaitingArea));
		
		return waitingSection;
	}
	
	/**
	 * It start a new delivery
	 * @param r = rider who's making the delivery
	 */
	private void startDelivery (Rider r) {
		if(!r.getBag().isEmpty()){
			String bagProfit = String.format("%.2f",r.getBagProfit());
			this.controller.getRiders().get(r.getName()).deliverAll();
			this.updateRiderData(r);
			if(r.getBag().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Consegna effettuata :)\n"+
	                                          "Hai guadagnato: " +bagProfit+"�");
			}else {
				JOptionPane.showMessageDialog(this,"Error...");
			}
		}else{
			JOptionPane.showMessageDialog(this, "La tua bag � vuota :(");
		}		
	}
	
	/**
	 * It checks if some orders in waiting can be assign to the given rider
	 * @param r = rider who's finish the last delivery
	 */
	private void checkWaitingOrders(Rider r) {
		if(!this.controller.getWaitingOrders().isEmpty()) {
			if(this.controller.refreshWaitingOrder(r)) {				
				this.updateWaitingOrders();
				this.receiveNewOrder(Optional.of(r));
				JOptionPane.showMessageDialog(this, "Nuovi ordini aggiunti dalla lista d'attesa");
			}else {
				JOptionPane.showMessageDialog(this, "Nessun nuovo ordine al momento");
			}					
		}else {
			JOptionPane.showMessageDialog(this, "Lista d'attesa vuota");
		}
	}
	
	/**
	 * It update the given rider data and bag 
	 * @param r = given rider
	 */
	private void updateRiderData(Rider r) {
		if(!r.getBag().isEmpty())
			this.infoOrder.get(r).setText(r.showBagInfo());
		else
			this.infoOrder.get(r).setText(emptyBagTitle);
		this.infoRider.get(r).setText(r.showRiderInfo());
	}
	
	/**
	 * It update the waitingOrder area
	 */
	private void updateWaitingOrders() {
		if(!this.controller.getWaitingOrders().isEmpty())
			this.waitingOrdersArea.setText(this.controller.showWaitingOrders());
		else
			this.waitingOrdersArea.setText(emptyWaitTitle);
	}
}

