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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Rider;
import it.fooddelivey.utils.RiderObserver;

/**
 * The only screen riders will see. It shows the bag of everyone and let them empty it.
 */
@SuppressWarnings("serial")
public class ViewForWorker extends JFrame implements ActionListener,RiderObserver{
	private final Manager controller;
	private final String TITLE = "Rider Screen";
	private JTextArea riderArea;
	private JTextArea orderArea;
	private JTextArea waitingOrdersArea;
	private JButton startDeliveryButton;
	private Map<Rider, JTextArea> infoOrder;
	private Map<Rider, JTextArea> infoRider;
	private static JPanel mainPanel;
	
	/**
	 * Constructor.
	 * @param controller .
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
		this.setTitle(this.TITLE);
		this.setSize(100, 50);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		for (Rider r : this.controller.getRiders().values()) {
			mainPanel.add(createRiderPanel(r));
		}
		mainPanel.add(createWaitingOrderPanel());		
		this.getContentPane().add(mainPanel);
		this.pack();
	}

	/**
	 * It creates the GUI for one rider (text and button).
	 * @param r is the rider.
	 * @return the panel itself
	 */
	private JPanel createRiderPanel(Rider r) {
		JPanel riderPanel = new JPanel();
		riderPanel.setBorder(BorderFactory.createTitledBorder(r.getName()));
		final GroupLayout riderPanelLayout = new GroupLayout(riderPanel);
		
		riderArea = new JTextArea();
		riderArea.setEditable(false);
		riderArea.setText(r.showRiderInfo());
		riderArea.setBackground(getBackground());
		infoRider.put(r, riderArea);
		
		orderArea = new JTextArea(5,20);
		orderArea.setEditable(false);
		orderArea.setAutoscrolls(true);
		orderArea.setText("PROVA");
		orderArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		infoOrder.put(r, orderArea);
		
		startDeliveryButton = new JButton("Parti e consegna!!!");
		startDeliveryButton.addActionListener(e ->{
			//TODO mettere ! nella 1° istr quando arrivano effettivamente gli ordini
			if(!r.getBag().isEmpty() && !infoOrder.get(r).getText().isEmpty()){
				this.controller.getRiders().get(r.getName()).deliverAll();
				infoRider.get(r).setText("" + r.showRiderInfo());
				JOptionPane.showMessageDialog(this, "Consegna effettuata");
			}else{
				JOptionPane.showMessageDialog(this, "Error...!!!");
			}		
		});	
		
		//startDeliveryButton.addActionListener(e -> r.deliverAll());  //IMPLEMENTATO L.	
		riderPanelLayout.setHorizontalGroup(
				riderPanelLayout.createSequentialGroup()
				.addComponent(riderArea)
				.addComponent(orderArea)
				.addComponent(startDeliveryButton));	
		riderPanelLayout.setVerticalGroup(
				riderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(riderArea)
				.addComponent(orderArea)
				.addComponent(startDeliveryButton));
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
		waitingOrdersArea.setText("Prova");
		waitingOrdersArea.setLineWrap(true);
		waitingOrdersArea.setAutoscrolls(true);
		waitingOrdersArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		waitingSectionLayout.setHorizontalGroup(
				waitingSectionLayout.createSequentialGroup()
				.addComponent(waitingOrdersArea));
		waitingSectionLayout.setVerticalGroup(
				waitingSectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(waitingOrdersArea));
		return waitingSection;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO qui in teoria dovrei intercettare l'evento generato dal pulsante "Procedi" in ViewRecap
		// ma come lo intercetto?
		//if(e.getSource() == proceedButton) {
		this.updateTextArea();
		//}								
	}
	
	
	// TODO in teoria questo sarebbe il metodo da chiamare quando si è finito di creare un ordine,
	// ma non mi è chiaro dove chiamarlo
	// Forse il parametro Optional non serve visto che il controller mi da anche il rider con l'ultimo ordine assegnato
	// Non so se questo deve essere un metodo a se o se metterlo in actionPerformed
	@Override
	public void updateTextArea() {
		Optional<Rider> r = this.controller.getRiderWithLastOrder();
		if(r.isPresent()) {
			this.infoOrder.get(r.get()).setText(r.get().showBagInfo());		 
		}else {
			this.waitingOrdersArea.setText(this.controller.showWaitingOrder());
		}
	}
}

