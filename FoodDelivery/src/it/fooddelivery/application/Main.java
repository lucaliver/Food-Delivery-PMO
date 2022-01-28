/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.application;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.controller.ManagerFactory;
import it.fooddelivery.view.View;

public class Main {

	public static void main(String[] args) {
		final Manager controller = ManagerFactory.create();
		new View(controller);
	}
}
