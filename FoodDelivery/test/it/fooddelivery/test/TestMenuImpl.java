/**
 * * @author Giacomo Tombari
 */
package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import it.fooddelivery.model.implementation.MenuImpl;

class TestMenuImpl {

	@Test
	void testMenuImpl() {
		final MenuImpl m = new MenuImpl("Name1", 23.70, 10);
		assertNotNull(m);
		assertEquals("Name1", m.getName());
		assertEquals(23.70, m.getPrice());
		assertEquals(10, m.getSize());
	}

	@Test
	void testGetPrice() {
		final MenuImpl m = new MenuImpl("Name1", 10.35, 10);
		assertNotNull(m);
		assertEquals(10.35, m.getPrice());
	}

	@Test
	void testGetName() {
		final MenuImpl m = new MenuImpl("Panino", 23.70, 10);
		assertEquals("Panino", m.getName());
	}

	@Test
	void testGetSize() {
		final MenuImpl m = new MenuImpl("name1", 23.70, 49);
		assertEquals(49, m.getSize());
	}
	
}
