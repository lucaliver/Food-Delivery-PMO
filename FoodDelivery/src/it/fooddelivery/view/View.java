package it.fooddelivery.view;

import it.fooddelivery.controller.DeliveryManager;

/**
 * View component for MVC pattern of the delivery management software.
 *
 */
public class View {

	public View(DeliveryManager controller) {
		new ViewForCustomer(controller);
		new ViewForWorker(controller);
	}
	
}
