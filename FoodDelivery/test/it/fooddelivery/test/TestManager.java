package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.controller.ManagerFactory;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.implementation.MenuImpl;
import it.fooddelivery.model.implementation.OrderImpl;
import it.fooddelivery.model.implementation.RestaurantImpl;

class TestManager {
	
	private final Manager m = ManagerFactory.create();
	private final Order o1 = new OrderImpl(01, City.FOSSOMBRONE, "Strada", null);
	private final Order o2 = new OrderImpl(01, City.CAGLI, "Via", null);
	private final Order o3 = new OrderImpl(01, City.URBANIA, "Piazza", null);
	private final Menu m1 = new MenuImpl("Insalata", 3.90, 6);
	private final Menu m2 = new MenuImpl("HappyMeal", 5.90, 5);
	private final Menu m3 = new MenuImpl("Pollo fritto 4pz.", 4.50, 10);
	private final Menu m4 = new MenuImpl("Vegetariana", 7.50, 5);
	private final Menu m5 = new MenuImpl("Sushi", 5.90, 9);
	private final Menu m6 = new MenuImpl("Ramen", 10.30, 100);

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
		this.o1.addMenu(m1);
		this.o1.addMenu(m3);
		this.o1.addMenu(m4);
		this.o2.addMenu(m2);
		this.o2.addMenu(m3);
		this.o2.addMenu(m5);
		this.o2.addMenu(m6);
		this.o3.addMenu(m1);
		this.o3.addMenu(m2);
		this.o3.addMenu(m5);
		System.out.println(this.o1.showOrderInfo());
		System.out.println(this.o2.showOrderInfo());
		System.out.println(this.o3.showOrderInfo());
		// Ordine creato correttamente, ci aspettiamo venga assegnato
		assertTrue(this.m.canBeAssigned(o3).isPresent());
		// Ordine che supera la size massima, ci aspettiamo che non venga assegnato
		assertTrue(this.m.canBeAssigned(o2).isEmpty());
		// Ordine con destinazione non compresa in nessun fattorino, ci aspettiamo che non venga assegnato
		assertTrue(this.m.canBeAssigned(o1).isEmpty());
		
		
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
