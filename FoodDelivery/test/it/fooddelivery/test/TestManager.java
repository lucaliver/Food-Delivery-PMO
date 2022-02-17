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
	private final Order o2 = new OrderImpl(02, City.CAGLI, "Via", null);
	private final Order o3 = new OrderImpl(03, City.URBANIA, "Piazza", null);
	private final Menu m1 = new MenuImpl("Insalata", 3.90, 6);
	private final Menu m2 = new MenuImpl("HappyMeal", 5.90, 5);
	private final Menu m3 = new MenuImpl("Pollo fritto 4pz.", 4.50, 10);
	private final Menu m4 = new MenuImpl("Vegetariana", 7.50, 5);
	private final Menu m5 = new MenuImpl("Sushi", 5.90, 9);
	private final Menu m6 = new MenuImpl("Ramen", 10.30, 100);

	@Test
	void testManager() {
		assertTrue(m.getWaitingOrders().isEmpty());
		/* Penso che il costrutto try-catch possa essere sostituito
		 * da un assertThrows o assertDoesNotThrows, ma non ho capito
		 * come funzionano
		 */
		boolean res = false;
	    try {
			m.getCurrentOrderPresent();
		} catch (NoSuchElementException e) {
			res = true;
		}
		assertTrue(res);
		assertTrue(m.getRiderWithLastOrder().isEmpty());
		assertEquals(m.getSequentialIdCounter(), 0);
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
		
		// Ordine creato correttamente, ci aspettiamo venga assegnato
		assertTrue(this.m.canBeAssigned(o3).isPresent());
		// Ordine che supera la size massima, ci aspettiamo che non venga assegnato
		assertTrue(this.m.canBeAssigned(o2).isEmpty());
		// Ordine con destinazione non compresa in nessun fattorino, ci aspettiamo che non venga assegnato
		assertTrue(this.m.canBeAssigned(o1).isEmpty());
		
		// Verifica del corretto smistamento favorevole a Rider con guadagno minore
		this.m.createCurrentOrder(City.TAVULLIA, "Casa", null);
		this.m.getCurrentOrderPresent().addMenu(m1);
		this.m.getCurrentOrderPresent().addMenu(m2);
		assertTrue(this.m.assignCurrentOrder());
		assertTrue(this.m.getRiderWithLastOrder().isPresent());
		System.out.println(this.m.getRiderWithLastOrder().get().getName());
		this.m.getRiderWithLastOrder().get().deliverAll();
		System.out.println(this.m.getRiders().get("Saverio").getProfit());
		this.m.createCurrentOrder(City.TAVULLIA, "Villa", null);
		assertEquals(this.m.getSequentialIdCounter(), 2);
		this.m.getCurrentOrderPresent().addMenu(m4);
		this.m.getCurrentOrderPresent().addMenu(m5);
		assertTrue(this.m.assignCurrentOrder());
		assertTrue(this.m.getRiderWithLastOrder().isPresent());
		System.out.println(this.m.getRiderWithLastOrder().get().getName());
		assertNotEquals(this.m.getRiderWithLastOrder().get().getName(), "Saverio");
		assertEquals(this.m.getRiderWithLastOrder().get(), this.m.getRiders()
				                                                 .entrySet()
				                                                 .stream()
				                                                 .filter(s -> s.getValue().getCities().contains(City.TAVULLIA))
				                                                 .filter(s -> s.getValue().getProfit() == 0)
				                                                 .findAny()
				                                                 .get()
				                                                 .getValue());
	}

	@Test
	void testCreateCurrentOrder() {
		this.m.createCurrentOrder(City.URBANIA, "Via", new RestaurantImpl("Trattoria", null));
		assertTrue(this.m.getSequentialIdCounter() == 1);
		boolean res = false;
		try {
			this.m.getCurrentOrderPresent();
		} catch (NoSuchElementException e) {
			res = true;
		}
		assertFalse(res);
		assertEquals(this.m.getCurrentOrderPresent().getDestination(), City.URBANIA);
		assertEquals(this.m.getCurrentOrderPresent().getAdress(), "Via");
		assertEquals(this.m.getCurrentOrderPresent().getRestaurant().getName(), "Trattoria");
		assertEquals(this.m.getCurrentOrderPresent().getRestaurant().getMenuOffer(), null);
	}

	@Test
	void testAssignCurrentOrder() {
		// Creazione di un currentOrder con destinazione non compresa fra i fattorini
		this.m.createCurrentOrder(City.FOSSOMBRONE, "Piazza", null);
		assertFalse(this.m.assignCurrentOrder());
		
		// Creazione di un currentOrder con destinazionecompresa fra i fattorini
		this.m.createCurrentOrder(City.URBANIA, "Casa", null);
		assertTrue(this.m.assignCurrentOrder());
	}

	@Test
	void testHowManyInCurrent() {
		this.m.createCurrentOrder(City.CAGLI, null, null);
		assertTrue(this.m.howManyInCurrent(m1) == 0);
		this.m.getCurrentOrderPresent().addMenu(m1);
		assertTrue(this.m.howManyInCurrent(m1) == 1);
		this.m.getCurrentOrderPresent().addMenu(m1);
		assertTrue(this.m.howManyInCurrent(m1) == 2);
		this.m.getCurrentOrderPresent().removeMenu(m1);
		assertTrue(this.m.howManyInCurrent(m1) == 1);
		for(int i = 1; i <= 3; i++)
			this.m.getCurrentOrderPresent().addMenu(m3);
		assertTrue(this.m.howManyInCurrent(m3) == 3);
	}

	@Test
	void testRefreshWaitingOrder() {
		this.o1.addMenu(m1);
		this.o1.addMenu(m5);
		this.o2.addMenu(m1);
		this.o2.addMenu(m2);
		this.o2.addMenu(m3);
		this.o3.addMenu(m3);
		this.o3.addMenu(m4);
		this.o3.addMenu(m5);
		this.m.getWaitingOrders().add(o1);	
		this.m.getWaitingOrders().add(o2);	
		this.m.getWaitingOrders().add(o3);
		
		// Mi aspetto false poichè nella waitingList non ci sono ordini assegnabili ad il rider Giulia
		assertFalse(this.m.refreshWaitingOrder(this.m.getRiders().get("Giulia")));
		
		// Mi aspetto true poichè nella waitingList ci sono ordini assegnabili ad il Rider Giacomo
		assertTrue(this.m.refreshWaitingOrder(this.m.getRiders().get("Giacomo")));
		assertTrue(this.m.getRiders().get("Giacomo").getBag().contains(o3));
		assertTrue(this.m.getRiders().get("Giacomo").getBag().size() == 1);
		assertFalse(this.m.getRiders().get("Giacomo").getBag().contains(o1) || this.m.getRiders().get("Giacomo").getBag().contains(o2));
		
		// Mi aspetto true poichè nella waitingList ci sono ordini assegnabili ad il Rider Luca
		assertTrue(this.m.refreshWaitingOrder(this.m.getRiders().get("Luca")));
		assertTrue(this.m.getRiders().get("Luca").getBag().contains(o2));
		assertTrue(this.m.getRiders().get("Luca").getBag().size() == 1);
		assertFalse(this.m.getRiders().get("Luca").getBag().contains(o1));
	}



}
