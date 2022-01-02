/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.application;

import it.fooddelivery.controller.DeliveryManager;
import it.fooddelivery.view.View;

public class Main {

	public static void main(String[] args) {
		final DeliveryManager controller = new DeliveryManager();

		new View(controller);
	}

}
