/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.view;

import javax.swing.BorderFactory;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Rider;

/**
 * The only screen riders will see. It shows the bag of everyone and let them empty it.
 */
@SuppressWarnings("serial")
public class ViewForWorker extends JFrame implements ActionListener{
	
	private final String TITLE_PANEL = "Rider Screen";	// Non credo vadano statici questi
	private final String TITLE_BAG_VUOTA = "In attesa di ordini da consegnare!!!";
	private final String TITLE_WAIT_VUOTA = "In attesa di ordini da smistare!!!";
	
	private final Manager controller;
	private JPanel mainPanel;
	private JTextArea riderArea;
	private JTextArea orderArea;
	private JTextArea waitingOrdersArea;
	private JButton startDeliveryButton;
	private Map<Rider, JTextArea> infoOrder;
	private Map<Rider, JTextArea> infoRider;
	
	/**
	 * Constructor.
	 * @param controller.
	 */
	ViewForWorker (final Manager controller){
		this.controller = controller;
		this.infoOrder = new HashMap<>();
		this.infoRider = new HashMap<>();
		this.Init();	
	}
	
	/**
	 * It initializes the window.
	 */
	private void Init(){
		this.setTitle(TITLE_PANEL);
		this.setSize(200,50);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		this.controller.getRiders().values().forEach(r -> mainPanel.add(createRiderPanel(r)));
		mainPanel.add(createWaitingOrderPanel());		
		this.getContentPane().add(mainPanel);
		this.pack();
	}

	/**
	 * It creates the GUI for one rider (text and button).
	 * @param r is the rider.
	 * @return the panel itself.
	 */
	private JPanel createRiderPanel(Rider r) {
		JPanel riderPanel = new JPanel();
		riderPanel.setBorder(BorderFactory.createTitledBorder(r.getName()));
		final GroupLayout riderPanelLayout = new GroupLayout(riderPanel);
		
		riderArea = new JTextArea(2,10);
		riderArea.setEditable(false);
		riderArea.setText(r.showRiderInfo());
		riderArea.setBackground(getBackground());
		infoRider.put(r, riderArea);
		
		orderArea = new JTextArea(7,30);
		orderArea.setEditable(false);
		orderArea.setText(TITLE_BAG_VUOTA);
		orderArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		infoOrder.put(r, orderArea);
		
		JScrollPane scrollOrderArea = new JScrollPane(orderArea);
		scrollOrderArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		startDeliveryButton = new JButton("Parti e consegna!");
		startDeliveryButton.addActionListener(e -> {
			this.deliveryOrders(r);
			this.checkWaitingOrders(r);			
		});
					
		riderPanelLayout.setHorizontalGroup(
				riderPanelLayout.createSequentialGroup()
				.addComponent(riderArea)
				.addComponent(scrollOrderArea)
				.addComponent(startDeliveryButton));	
		riderPanelLayout.setVerticalGroup(
				riderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(riderArea)
				.addComponent(scrollOrderArea)
				.addComponent(startDeliveryButton));

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
		
		waitingOrdersArea = new JTextArea(10,30);
		waitingOrdersArea.setText(TITLE_WAIT_VUOTA);
		waitingOrdersArea.setLineWrap(true);
		waitingOrdersArea.setAutoscrolls(true);
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
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void receiveNewOrder(Optional<Rider> r) {
		if(r.isPresent()) {
			this.updateRiderData(r.get());
		}else {
			this.updateWaitingOrders();
		}
	}
	
	private void deliveryOrders (Rider r) {
		if(!r.getBag().isEmpty()){
			JOptionPane.showMessageDialog(this, "Consegna effettuata :)");
			this.controller.getRiders().get(r.getName()).deliverAll();
			infoRider.get(r).setText(r.showRiderInfo());
			infoOrder.get(r).setText(TITLE_BAG_VUOTA);				
		}else{
			JOptionPane.showMessageDialog(this, "La tua bag è vuota :(");
		}		
	}
	//
	private void checkWaitingOrders(Rider r) {
		if(!this.controller.getWaitingOrders().isEmpty()) {
			if(this.controller.refreshWaitingOrder(r)) {
				JOptionPane.showMessageDialog(this, "Nuovi ordini aggiunti dalla lista d'attesa");
				this.updateWaitingOrders();
				this.receiveNewOrder(Optional.of(r));
			}else {
				JOptionPane.showMessageDialog(this, "Nessun nuovo ordine al momento");
			}					
		}
	}
	
	private void updateRiderData(Rider r) {
		this.infoOrder.get(r).setText(r.showBagInfo());
		this.infoRider.get(r).setText(r.showRiderInfo());
	}
	
	private void updateWaitingOrders() {
		if(!this.controller.getWaitingOrders().isEmpty())
			this.waitingOrdersArea.setText(this.controller.showWaitingOrders());
		else
			this.waitingOrdersArea.setText(TITLE_WAIT_VUOTA);
	}
}

