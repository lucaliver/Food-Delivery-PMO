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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Rider;

/**
 * The only screen riders will see. It shows the bag of every rider and let them manage it.
 */
@SuppressWarnings("serial")
public class ViewWorker extends JFrame{
	
	private static ViewWorker instance = null;
	private Manager controller;
	private JPanel mainPanel;
	private JTextArea riderArea;
	private JTextArea orderArea;
	private JTextArea waitingOrdersArea;
	private JButton deliveryButton;
	private final String mainPanelTitle = "Schermata fattorini: gestite i vostri ordini!";	
	private final String emptyBagTitle = "Nessun ordine nel tuo ziano";
	private final String emptyWaitTitle = "Nessun ordine in attesa";
	private final Map<Rider, JTextArea> infoOrder;
	private final Map<Rider, JTextArea> infoRider;
	
	/**
	 * Private singleton constructor.
	 */
	private ViewWorker (){
		this.infoOrder = new HashMap<>();
		this.infoRider = new HashMap<>();	
	}
	
	/**
	 * Set the controller and initializes the view.
	 * 
	 * @param controller controller component for the MVC pattern
	 */
	public void initView(Manager controller) {
		this.controller = controller;
		this.Init();
	}
	
	/**
	 * Creates one and only one object of the ViewForWorker type.
	 * 
	 * @return the same object every time
	 */
	public static ViewWorker getInstance() {
		if(instance == null) {
			instance = new ViewWorker();
		}
		return instance;
	}
	
	/**
	 * Checks where the last order was sent.
	 * 
	 * @param ride rider who received the last order, {@code empty} if order was put in waitingList
	 */
	public void receiveNewOrder(Optional<Rider> rider) {
		if(rider.isPresent()) {
			this.updateRiderData(rider.get());
		}else {
			this.updateWaitingOrders();
		}
	}
	
	/**
	 * Initializes all the window.
	 */
	private void Init(){
		this.setTitle(mainPanelTitle);
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
	 * Creates the GUI for one rider (text and button).
	 * 
	 * @param rider the rider where to get the data
	 * @return the panel itself
	 */
	private JPanel createRiderPanel(Rider rider) {
		JPanel riderPanel = new JPanel();
		riderPanel.setBorder(BorderFactory.createTitledBorder(rider.getName()));
		final GroupLayout riderPanelLayout = new GroupLayout(riderPanel);
		
		riderArea = new JTextArea(2,8);
		riderArea.setEditable(false);
		/*StringBuilder riderCities = new StringBuilder("Destinazioni:");
		rider.getCities().forEach(c -> riderCities.append(c.getName()+"\n                     "));*/
		riderArea.setText(rider.showRiderInfo());//+'\n'+riderCities);
		riderArea.setBackground(getBackground());
		infoRider.put(rider, riderArea);
		
		orderArea = new JTextArea(4,30);
		orderArea.setEditable(false);
		orderArea.setText(emptyBagTitle);
		orderArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		infoOrder.put(rider, orderArea);
		
		JScrollPane scrollOrderArea = new JScrollPane(orderArea);
		scrollOrderArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		deliveryButton = new JButton("Consegna ordini");
		deliveryButton.addActionListener(e -> {
			this.startDelivery(rider);
			this.checkWaitingOrders(rider);			
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

		return riderPanel;
	}
	
	/**
	 * Creates the part of the window that shows the waiting orders.
	 * 
	 * @return the panel itself
	 */
	private JPanel createWaitingOrderPanel() {
		JPanel waitingSection = new JPanel();
		waitingSection.setBorder(BorderFactory.createTitledBorder("Ordini in attesa:"));
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
	 * Starts a new delivery.
	 * 
	 * @param rider the rider who's making the delivery
	 */
	private void startDelivery (Rider rider) {
		if(!rider.getBag().isEmpty()){
			String bagProfit = String.format("%.2f",rider.getBagProfit());
			this.controller.getRiders().get(rider.getName()).deliverAll();
			this.updateRiderData(rider);
			if(rider.getBag().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Consegna effettuata :)\n"+
	                                          "Hai guadagnato " +bagProfit+"€");
			}else {
				JOptionPane.showMessageDialog(this,"Errore.");
			}
		}else{
			JOptionPane.showMessageDialog(this, "Non puoi consegnare, la tua bag è vuota :(");
		}		
	}
	
	/**
	 * Checks if some orders in waiting can be assigned to the given rider.
	 * 
	 * @param rider the rider that is looking for new orders
	 */
	private void checkWaitingOrders(Rider rider) {
		if(!this.controller.getWaitingOrders().isEmpty()) {
			if(this.controller.refreshWaitingOrder(rider)) {				
				this.updateWaitingOrders();
				this.receiveNewOrder(Optional.of(rider));
				JOptionPane.showMessageDialog(this, "Hai nuovi ordini presi dalla lista d'attesa ;)");
			}
		}
	}
	
	/**
	 * Updates the given rider data and bag.
	 *  
	 * @param rider given rider
	 */
	private void updateRiderData(Rider rider) {
		if(!rider.getBag().isEmpty())
			this.infoOrder.get(rider).setText(rider.showBagInfo());
		else
			this.infoOrder.get(rider).setText(emptyBagTitle);
		this.infoRider.get(rider).setText(rider.showRiderInfo());
	}
	
	/**
	 * Update the waiting orders area.
	 */
	private void updateWaitingOrders() {
		if(!this.controller.getWaitingOrders().isEmpty())
			this.waitingOrdersArea.setText(this.controller.showWaitingOrders());
		else
			this.waitingOrdersArea.setText(emptyWaitTitle);
	}
}