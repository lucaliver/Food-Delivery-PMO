/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import it.fooddelivery.controller.Manager;

/**
 * View component for MVC pattern of the delivery management software.
 *
 */
public class View {
	//TODO Sistemare chiusura finestre.
	
	/**
	 * It creates the first windows for the customer and the window for the workers.
	 * @param controller
	 */
	public View(Manager controller) { //Al momento crea tutte e 4 le finestre
		createViews(new ViewWelcome(controller),
				    new ViewForWorker(controller),
				    new ViewPlacing(controller, null),
				    new ViewRecap(controller));								
	}

	private void createViews(JFrame... views) {
		for(JFrame view : views) {
			view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			view.setVisible(true);
		}
		
	}
	
}
