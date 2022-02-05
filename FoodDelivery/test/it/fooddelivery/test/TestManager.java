/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.controller.ManagerFactory;

class TestManager {
	// TODO Implementare i test per tutti i metodi di Manager (ssando la factory?)
	final Manager m = ManagerFactory.create();

	@Test
	void testAssignOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testGetWaitingOrders() {
		Manager dm = new Manager(null, null);
		assertNotNull(dm);
	}

	@Test
	void testGetRestaurants() {
		Manager dm = new Manager(null, null);
		assertNotNull(dm);
		assertEquals(dm.getRestaurants(), null);
	}
}
