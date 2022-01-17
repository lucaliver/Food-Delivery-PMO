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
	
	private static final String TITLE_PANEL = "Rider Screen";
	private static final String TITLE_BAG_VUOTA = "In attesa di ordini da consegnare!!!";
	private static final String TITLE_WAIT_VUOTA = "In attesa di ordini da smistare!!!";
	
	private final Manager controller;
	private  JPanel mainPanel;
	private JTextArea riderArea;
	private JTextArea orderArea;
	private JTextArea waitingOrdersArea;
	private JButton startDeliveryButton;
	private JButton refreshWaitingButton;
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
		this.setTitle(this.TITLE_PANEL);
		this.setSize(100,50);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		//mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
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
		//riderPanel.setLayout(new BoxLayout(riderPanel, BoxLayout.Y_AXIS));
		
		riderArea = new JTextArea(2,10);
		riderArea.setEditable(false);
		riderArea.setText(r.showRiderInfo());
		riderArea.setBackground(getBackground());
		infoRider.put(r, riderArea);
		
		orderArea = new JTextArea(5,30);
		orderArea.setEditable(false);
		//orderArea.setAutoscrolls(true);
		orderArea.setText(TITLE_BAG_VUOTA);
		orderArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		infoOrder.put(r, orderArea);
		
		JScrollPane scrollOrderArea = new JScrollPane(orderArea);
		scrollOrderArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		startDeliveryButton = new JButton("Parti e consegna!");
		startDeliveryButton.addActionListener(e ->{
			if(!r.getBag().isEmpty() && !infoOrder.get(r).getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "Consegna effettuata :)");
				this.controller.getRiders().get(r.getName()).deliverAll();
				infoRider.get(r).setText("" + r.showRiderInfo());
				infoOrder.get(r).setText(TITLE_BAG_VUOTA);				
			}else{
				JOptionPane.showMessageDialog(this, "La tua bag è vuota :(");
			}		
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
		/*riderPanel.add(riderArea);
		riderPanel.add(orderArea);
		riderPanel.add(startDeliveryButton);*/
		return riderPanel;
	}
	
	/**
	 * It creates the GUI for the waiting list of orders.
	 * @return the panel itself
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
		
		// TODO aggiungere eventi al pulsante
		refreshWaitingButton = new JButton("Refresh");
		refreshWaitingButton.addActionListener(e ->{
			if(!this.controller.getWaitingOrders().isEmpty() && !this.waitingOrdersArea.getText().isEmpty()) {
				this.controller.getWaitingOrders().forEach(o ->{
					this.controller.assignOrder(o);
				});
				
			}
		});
		
		JScrollPane scrollWaitingArea = new JScrollPane(waitingOrdersArea);
		scrollWaitingArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		waitingSectionLayout.setHorizontalGroup(
				waitingSectionLayout.createSequentialGroup()
				.addComponent(scrollWaitingArea)
				.addComponent(refreshWaitingButton));
		waitingSectionLayout.setVerticalGroup(
				waitingSectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(scrollWaitingArea)
				.addComponent(refreshWaitingButton));
		return waitingSection;
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	//
	public void updateTextArea() {
		Optional<Rider> r = this.controller.getRiderWithLastOrder();
		if(r.isPresent()) {
			this.infoOrder.get(r.get()).setText(r.get().showBagInfo());
			this.infoRider.get(r.get()).setText(r.get().showRiderInfo());
		}else {
			this.waitingOrdersArea.setText(this.controller.showWaitingOrders());
		}
	}
}

