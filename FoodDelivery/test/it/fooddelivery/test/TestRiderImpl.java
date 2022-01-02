package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.Test;

import it.fooddelivery.model.Order;
import it.fooddelivery.model.City;
import it.fooddelivery.model.implementation.RiderImpl;
import it.fooddelivery.model.implementation.MenuImpl;
import it.fooddelivery.model.implementation.OrderImpl;

class TestRiderImpl {

	@Test
	void testDeliveryManImpl() {
		RiderImpl man = new RiderImpl(0, "Fattorino_1");
		assertNotNull(man);
		assertEquals("Fattorino_1", man.getName());
		assertNotEquals(12, man.getName());
		assertEquals(0,00,man.getProfit());
		assertNotEquals("jkwhdjkgef", man.getName());
	}

	@Test
	void testgetBag() {
		List<Order> orderList = new ArrayList<Order>();
		assertNotNull(orderList);
	}

	@Test
	void testAddOrder(Order o) {
		RiderImpl man2 = new RiderImpl(0, "Fattorino_2");
		man2.addOrder(o);
		assertTrue(man2.getBag().isEmpty());
		assertFalse(man2.isFull());
		assertEquals(1, man2.getBag().size());
	}

	@Test
	void testDeliverOrder(Order o) {
		RiderImpl delivery = new RiderImpl(0, "Fattorino");
		delivery.addOrder(o);
		delivery.deliverOrder(o);
		assertTrue(delivery.getBag().remove(o));
	}

	@Test
	void testIsFull() {
		RiderImpl bagMan = new RiderImpl(0, "Fattorino_5");
		assertTrue(!bagMan.isFull());
		assertFalse(bagMan.isFull());
	}

/*	@Test
	void testTotalProfit(Order o) {
		DeliveryManImpl man6 = new DeliveryManImpl(0, "Fattorino_6");
		assertNotNull(man6);
		assertEquals(123,00, man6.totalProfit(o));
		assertNotEquals(-859,00, man6.totalProfit(o));
	}*/
			
	@Test
	void testGetProfit() {
		RiderImpl man4 = new RiderImpl(0, "Fattorino_4");
		assertEquals(0, man4.getProfit());
		assertNotEquals(-485,00, man4.getProfit());
	}

	@Test
	void testGetName() {
		RiderImpl man5 = new RiderImpl(0, "Fattorino_5");
		assertEquals("Fattorino_5", man5.getName());
		assertNotEquals(24, man5.getName());
	}	
	
	@Test
	void testCanFit(Order o) {
		RiderImpl man = new RiderImpl(0, "Fattorino");
		assertTrue(!man.canFit(o));
		assertFalse(man.canFit(o));
	}
}
