/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.application;

import it.fooddelivery.controller.ManagerImpl;
import it.fooddelivery.controller.ManagerFactory;
import it.fooddelivery.view.View;

/**
 * The Main class of the Food Delivery application.
 */
public class Main {
	public static void main(String[] args) {
		final ManagerImpl controller = ManagerFactory.create();
		new View(controller);
	}
}
