/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.view;

import it.fooddelivery.controller.Manager;

/**
 * View component for MVC pattern of the Food Delivery application.
 */
public class View {
	
	/**
	 * Creates the first windows for the customer and the window for the workers.
	 * 
	 * @param controller the controller for the MVC pattern
	 */
	public View(final Manager controller) {
		ViewWorker.getInstance().initView(controller);
		new ViewWelcome(controller);								
	}
}
