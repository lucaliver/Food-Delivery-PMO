/**
 * @author Luca Procicchiani
 * 
 */
package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.fooddelivery.model.implementation.RestaurantImpl;

class TestRestaurantImpl {

	@Test
	void testName() {
		final RestaurantImpl r = new RestaurantImpl("McDonald's", null);
		assertEquals("McDonald's", r.name());
	}
	
	void testMenuOffer() {
		//TODO implementare correttamente
		final RestaurantImpl r = new RestaurantImpl("", null);
		fail("This test hasn't been implemented.");
	}

}
