/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Rider;

public class ViewForWorker extends JFrame{
	private final Manager controller;
	private final String TITLE = "Rider Screen";
	private JScrollPane waitingOrder;
	
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
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		for(Rider r : this.controller.getRiders()) {
			JLabel riderInfo = new JLabel(r.getName()+ "\n" + "Profit:" +r.getProfit()+
	                                      "€\n" + "Capacità:" +"0/");	
			mainPanel.add(riderInfo);
		}
		
		this.getContentPane().add(mainPanel);
	}
}
