/**
 * @author Giulia Costa
 */

package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.fooddelivery.model.Order;
import it.fooddelivery.model.City;
import it.fooddelivery.model.implementation.RiderImpl;
import it.fooddelivery.model.implementation.MenuImpl;
import it.fooddelivery.model.implementation.OrderImpl;

class TestRiderImpl {

	@Test
	void testDeliveryManImpl() {
		RiderImpl man = new RiderImpl("Fattorino_1", null);
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
	void testAddOrder() {
		RiderImpl man2 = new RiderImpl("Fattorino_2", null);
		OrderImpl o = new OrderImpl("iehfkj568", City.CAGLI, null, null);
		man2.addOrder(o);
		assertFalse(man2.getBag().isEmpty());
		assertEquals(1, man2.getBag().size());
	}

	@Test
	void testDeliverOrder() {
		RiderImpl delivery = new RiderImpl("Fattorino", null);
		OrderImpl o = new OrderImpl("67tyv90", City.FERMIGNANO, null, null);
		MenuImpl m = new MenuImpl("Ristorante", 100.00, 50);
		MenuImpl m2 = new MenuImpl("yuyub", 80.00, 40);				
		o.addMenu(m);
		o.addMenu(m2);
		delivery.addOrder(o);
		delivery.deliverOrder(o);
		assertFalse(delivery.getBag().contains(o));
		assertEquals(o.getTotalPrice()*RiderImpl.getPercentage(), delivery.getProfit());
	}

	@Test
	void testIsFull() {
		RiderImpl bagMan = new RiderImpl("Fattorino_5", null);
		assertTrue(!bagMan.isFull());
		assertFalse(bagMan.isFull());
	}
			
	@Test
	void testGetProfit() {
		RiderImpl man4 = new RiderImpl("Fattorino_4", null);
		assertEquals(0, man4.getProfit());
		assertNotEquals(-485,00, man4.getProfit());
	}

	@Test
	void testGetName() {
		RiderImpl man5 = new RiderImpl("Fattorino_5", null);
		assertEquals("Fattorino_5", man5.getName());
		assertNotEquals(24, man5.getName());
	}	
	
	@Test
	void testCanFit() {
		RiderImpl man = new RiderImpl("Fattorino", null);
		OrderImpl o = new OrderImpl("oiu78", City.FOSSOMBRONE, null, null);
		MenuImpl m = new MenuImpl("Dfgheu", 80.00, 50);
		MenuImpl m2 = new MenuImpl("Sushi", 50.00, 50);
		MenuImpl m3 = new MenuImpl("Pizzeria", 80.00, 101);
		o.addMenu(m);
		o.addMenu(m2);
		assertTrue(man.canFit(o));
		o.removeAllMenu();
		o.addMenu(m3);
		assertFalse(man.canFit(o));
	}
}
