/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import it.fooddelivery.model.implementation.MenuImpl;

class TestMenuImpl {
	
	final MenuImpl m = new MenuImpl("Panino", 23.70, 49);

	@Test
	void testMenuImpl() {	
		assertNotNull(m);
		assertEquals(23.70, m.getPrice());
		assertEquals("Panino", m.getName());
		assertEquals(49, m.getSize());
	}

	@Test
	void testGetPrice() {
		assertEquals(23.70, m.getPrice());
	}

	@Test
	void testGetName() {
		assertEquals("Panino", m.getName());
	}

	@Test
	void testGetSize() {
		assertEquals(49, m.getSize());
	}
	
}
