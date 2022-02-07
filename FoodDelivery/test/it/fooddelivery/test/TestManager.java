package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.controller.ManagerFactory;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.implementation.OrderImpl;
import it.fooddelivery.model.implementation.RestaurantImpl;

class TestManager {
	
	private final Manager m = ManagerFactory.create();

	@Test
	void testManager() {
		assertTrue(m.getWaitingOrders().isEmpty());
		boolean res = false;
	    try {
			m.getCurrentOrderPresent();
		} catch (NoSuchElementException e) {
			res = true;
		}
		assertTrue(res);
		assertTrue(m.getRiderWithLastOrder().isEmpty());
		assertEquals(m.getSequetianlIdCounter(), 0);
	}

	@Test
	void testCanBeAssigned() {
		Order o = new OrderImpl(1, City.CAGLI, "Strada_1", new RestaurantImpl("Locanda", null));
		assertTrue(m.canBeAssigned(o).isPresent());
		
		
	}

	@Test
	void testCreateCurrentOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testAssignCurrentOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testHowManyInCurrent() {
		fail("Not yet implemented");
	}

	@Test
	void testRefreshWaitingOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testAddToCurrent() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveFromCurrent() {
		fail("Not yet implemented");
	}

}
