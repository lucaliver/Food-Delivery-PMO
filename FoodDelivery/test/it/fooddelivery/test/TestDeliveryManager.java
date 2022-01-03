/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.fooddelivery.controller.Manager;

class TestDeliveryManager {

	@Test
	void testAssignOrder() {
		//TODO Implementare.
		fail("Not yet implemented");
	}

	@Test
	void testGetWaitingOrders() {
		//TODO Finire di implementare.
		Manager dm = new Manager(null, null);
		assertNotNull(dm);
	}

	@Test
	void testGetRestaurants() {
		//TODO Finire di implementare.
		Manager dm = new Manager(null, null);
		assertNotNull(dm);
		assertEquals(dm.getRestaurants(), null);
	}
}
