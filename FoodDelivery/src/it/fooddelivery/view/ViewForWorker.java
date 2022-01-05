/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.view;

import java.awt.Component;
import javax.swing.Action;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.Rider;
import it.fooddelivery.model.implementation.RiderImpl;

public class ViewForWorker extends JFrame implements ActionListener{
	private final Manager controller;
	private final String TITLE = "Rider Screen";
	private JTextArea riderInfo;
	private JTextArea orderInfo;
	private JTextArea waitingOrders;
	private JButton startDelivery;
	//private JScrollPane waitingOrder;
	
	/**
	 * 
	 * @param controller 
	 */
	ViewForWorker (final Manager controller){
		this.controller = controller;
		this.Init();	
	}
	
	private void Init(){
		this.setTitle(this.TITLE);
		this.setSize(400, 200);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		for (Rider r : this.controller.getRiders()) {
			mainPanel.add(createRiderData(r));
		}
		mainPanel.add(createWaitingOrder());
		this.getContentPane().add(mainPanel);
		this.pack();
	}

	private JPanel createRiderData(Rider r) {
		riderInfo = new JTextArea(3,10);
		orderInfo = new JTextArea(5,30);
		startDelivery = new JButton("Parti e consegna!!!");
		
		JPanel riderData = new JPanel();
		riderData.setBorder(new EmptyBorder(20, 20, 20, 0));
		final GroupLayout riderDataLayout = new GroupLayout(riderData);
		riderInfo.setEditable(false);
		orderInfo.setEditable(false);
		riderInfo.setText(r.getName()+'\n'+"Profito: "+r.getProfit()+
				             "€"+'\n'+"Capacità: "+"0/"+RiderImpl.getMaxCapacity());
		orderInfo.setText("PROVA");
		startDelivery.addActionListener(this::confirmDelivery);
			riderDataLayout.setHorizontalGroup(
					riderDataLayout.createSequentialGroup()
					.addComponent(riderInfo)
					.addComponent(orderInfo)
					.addComponent(startDelivery));	
			riderDataLayout.setVerticalGroup(
					riderDataLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(riderInfo)
					.addComponent(orderInfo)
					.addComponent(startDelivery));	
		return riderData;
	}
	
	private JPanel createWaitingOrder() {
		waitingOrders = new JTextArea(10,30);
		JPanel waitingSection = new JPanel();
		waitingSection.setBorder(new EmptyBorder(10,10,10,10));
		final GroupLayout waitingSectionLayout = new GroupLayout(waitingSection);
		waitingOrders.setText("PROVA");
		waitingSectionLayout.setHorizontalGroup(
				waitingSectionLayout.createSequentialGroup()
				.addComponent(waitingOrders));
		waitingSectionLayout.setVerticalGroup(
				waitingSectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(waitingOrders));
		return waitingSection;
	}
	
	private void confirmDelivery(final ActionEvent event) {
		//Rider r
		String orders = orderInfo.getText();
		boolean resText = !orderInfo.getText().isEmpty(); 
				
		if(resText) {
			JOptionPane.showMessageDialog(this, "Consegna effettuata");
			orderInfo.setText("");
		}else {
			JOptionPane.showMessageDialog(this, "Error...!!!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
	}
	
}
