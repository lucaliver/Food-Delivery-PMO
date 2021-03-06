/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.fooddelivery.model.Order;
import it.fooddelivery.model.implementation.RiderImpl;
import it.fooddelivery.model.implementation.CityImpl;
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
		OrderImpl o = new OrderImpl(21, CityImpl.CAGLI, null, null);
		man2.addOrder(o);
		assertFalse(man2.getBag().isEmpty());
		assertEquals(1, man2.getBag().size());
	}

	@Test
	void testDeliverOrder() {
		RiderImpl delivery = new RiderImpl("Fattorino", null);
		OrderImpl o = new OrderImpl(22, CityImpl.FERMIGNANO, null, null);
		OrderImpl o1 = new OrderImpl(25, CityImpl.FERMIGNANO, null, null);
		MenuImpl m = new MenuImpl("Ristorante", 100.00, 50);
		MenuImpl m2 = new MenuImpl("uyub", 80.00, 40);	
		boolean res = false;
		o.increaseMenu(m);
		o.increaseMenu(m2);
		delivery.addOrder(o);
		delivery.deliverOrder(o);
		assertFalse(delivery.getBag().contains(o));
		assertEquals(o.getPrice()*RiderImpl.getPercentage(), delivery.getProfit());
		try {
			delivery.deliverOrder(o1);
		}catch (IllegalArgumentException e) {
			res = true;
		}
		assertTrue(res);
		assertFalse(delivery.getBag().contains(o1));
	}
	
	@Test
	void testDeliveryAll() {
		RiderImpl delivery = new RiderImpl("Fattorino", null);
		OrderImpl o = new OrderImpl(22, CityImpl.FERMIGNANO, null, null);
		OrderImpl o1 = new OrderImpl(25, CityImpl.FERMIGNANO, null, null);
		MenuImpl m = new MenuImpl("Ristorante", 100.00, 50);
		MenuImpl m2 = new MenuImpl("uyub", 80.00, 40);				
		o.increaseMenu(m);
		o1.increaseMenu(m2);
		delivery.addOrder(o);
		delivery.addOrder(o1);
		assertTrue(delivery.getBag().contains(o1));
		assertTrue(delivery.getBag().contains(o));
		System.out.println(delivery.showBagInfo());
		delivery.deliverAll();
		System.out.println(delivery.showBagInfo());
		assertTrue(delivery.getBag().isEmpty());
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
		OrderImpl o = new OrderImpl(23, CityImpl.FOSSOMBRONE, null, null);
		MenuImpl m = new MenuImpl("Dfgheu", 80.00, 50);
		MenuImpl m2 = new MenuImpl("Sushi", 50.00, 50);
		MenuImpl m3 = new MenuImpl("Pizzeria", 80.00, 101);
		o.increaseMenu(m);
		o.increaseMenu(m2);
		assertTrue(man.canFit(o));
		o.removeAllMenus();
		o.increaseMenu(m3);
		assertFalse(man.canFit(o));
	}
}