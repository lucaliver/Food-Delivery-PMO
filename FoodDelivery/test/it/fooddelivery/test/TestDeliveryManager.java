/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.fooddelivery.controller.DeliveryManager;

class TestDeliveryManager {

	@Test
	void testAssignOrder() {
		//TODO Implementare.
		fail("Not yet implemented");
	}

	@Test
	void testGetWaitingOrders() {
		//TODO Finire di implementare.
		DeliveryManager dm = new DeliveryManager();
		assertNotNull(dm);
	}

	@Test
	void testGetRestaurants() {
		//TODO Finire di implementare.
		DeliveryManager dm = new DeliveryManager();
		assertNotNull(dm);
		assertEquals(dm.getRestaurants(), null);
	}
}
