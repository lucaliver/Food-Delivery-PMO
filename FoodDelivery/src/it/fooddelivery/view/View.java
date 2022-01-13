/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.view;

import it.fooddelivery.controller.Manager;

/**
 * View component for MVC pattern of the delivery management software.
 */
public class View {
	ViewForWorker vfworker;
	
	/**
	 * It creates the first windows for the customer and the window for the workers.
	 * @param controller
	 */
	public View(final Manager controller) {
		new ViewWelcome(controller);								
	}
}
