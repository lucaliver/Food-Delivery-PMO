/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.view;

import javax.swing.JFrame;
import it.fooddelivery.controller.DeliveryManager;

public class ViewForWorker extends JFrame{
	
	private final DeliveryManager controller;
	private final String TITLE = "Worker Screen";
	
	ViewForWorker (final DeliveryManager controller){
		this.controller = controller;
		
		//TODO Implementare dopo aver fatto ViewForCustomer.
	}
}
