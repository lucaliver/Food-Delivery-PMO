/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.application;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.view.View;

public class Main {

	public static void main(String[] args) {
		final Manager controller = new Manager();

		new View(controller);
	}

}
