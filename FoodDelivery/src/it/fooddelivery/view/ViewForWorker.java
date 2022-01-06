/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.view;

import java.awt.Component;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.text.AttributeSet.ColorAttribute;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.BorderLayout;
import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.Rider;
import it.fooddelivery.model.implementation.RiderImpl;

public class ViewForWorker extends JFrame implements ActionListener{
	private final Manager controller;
	private final String TITLE = "Rider Screen";
	private JTextArea riderArea;
	private JTextArea orderArea;
	private JTextArea waitingOrdersArea;
	private JButton startDeliveryButton;
	private Map<Rider, JTextArea> infoOrder;
	private Map<Rider, JTextArea> infoRider;
	/**
	 * 
	 * @param controller 
	 */
	ViewForWorker (final Manager controller){
		this.controller = controller;
		this.infoOrder = new HashMap<>();
		this.infoRider = new HashMap<>();
		this.Init();	
	}
	
	private void Init(){
		this.setTitle(this.TITLE);
		this.setSize(100, 50);
		JPanel mainPanel = new JPanel();
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

	private JPanel createRiderPanel(Rider r) {
		JPanel riderPanel = new JPanel();
		riderPanel.setBorder(BorderFactory.createTitledBorder(r.getName()));
		final GroupLayout riderPanelLayout = new GroupLayout(riderPanel);
		
		riderArea = new JTextArea();
		riderArea.setEditable(false);
		riderArea.setText(printRiderInfo(r));
		//riderArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
			if(r.getBag().isEmpty() && !orderArea.getText().isEmpty()){
				//this.controller.getRiders().get(r.getName()).deliverAll();
				infoOrder.get(r).setText("");
				infoRider.get(r).setText(printRiderInfo(r));
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
	
	private JPanel createWaitingOrderPanel() {
		JPanel waitingSection = new JPanel();
		waitingSection.setBorder(BorderFactory.createTitledBorder("Ordini in attesa"));
		final GroupLayout waitingSectionLayout = new GroupLayout(waitingSection);
		
		waitingOrdersArea = new JTextArea(10,30);
		waitingOrdersArea.setText("Prova");
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
		
		if(e.getActionCommand().equals("Procedi")){
			if(this.controller.getLastOrderAssign().isEmpty()) {
				this.waitingOrdersArea.setText(this.controller.getWaitingOrders().toString());
			}else {
				this.infoRider.get(this.controller.getRiders()
							  .get(this.controller.getLastOrderAssign()
							  .get())).setText(this.controller.getRiders()
							  .get(this.controller.getLastOrderAssign()
							  .get()).getBag().toString());
			}
		}							
	}
	/**
	 * 
	 * @param r = rider that gives the info
	 * @return a string with his info
	 */
	private String printRiderInfo(Rider r) {
		return "Profito: "+r.getProfit()+
	           "€"+'\n'+"Capacità: "+r.getCapacity()+
				"/"+RiderImpl.getMaxCapacity();
	}
}

