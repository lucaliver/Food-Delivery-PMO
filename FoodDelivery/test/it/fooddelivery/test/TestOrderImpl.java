package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.fooddelivery.model.implementation.OrderImpl;
import it.fooddelivery.model.implementation.ZoneImpl;

class TestOrderImpl {

	@Test
	void testOrderImpl() {
		OrderImpl o = new OrderImpl("8ft9", ZoneImpl.CAGLI);
		assertEquals("8ft9", o.getIdOrder());
		assertEquals(ZoneImpl.CAGLI, o.getDestination());
	}

	@Test
	void testMenusList() {
		fail("Not yet implemented");
	}

	@Test
	void testAddMenu() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveMenu() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSize() {
		fail("Not yet implemented");
	}

	@Test
	void testTotalPrice() {
		fail("Not yet implemented");
	}

	@Test
	void testGetIdOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDestination() {
		fail("Not yet implemented");
	}

}
