package it.fooddelivery.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import it.fooddelivery.controller.DeliveryManager;

/**
 * View component for MVC pattern of the delivery management software.
 *
 */
public class View {

	public View(DeliveryManager controller) {
		createViews(new ViewForCustomer(controller),
				    new ViewForWorker(controller));								
	}

	private void createViews(JFrame... views) {
		for(JFrame view : views) {
			view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			view.setVisible(true);
		}
		
	}
	
}
