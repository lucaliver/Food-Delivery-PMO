package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.controller.ManagerFactory;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.implementation.CityImpl;
import it.fooddelivery.model.implementation.MenuImpl;
import it.fooddelivery.model.implementation.OrderImpl;
import it.fooddelivery.model.implementation.RestaurantImpl;

class TestManager {
	
	private final Manager m = ManagerFactory.create();
	private final Order o1 = new OrderImpl(01, CityImpl.FOSSOMBRONE, "Strada", null);
	private final Order o2 = new OrderImpl(02, CityImpl.CAGLI, "Via", null);
	private final Order o3 = new OrderImpl(03, CityImpl.URBANIA, "Piazza", null);
	private final Menu m1 = new MenuImpl("Insalata", 3.90, 6);
	private final Menu m2 = new MenuImpl("HappyMeal", 5.90, 5);
	private final Menu m3 = new MenuImpl("Pollo fritto 4pz.", 4.50, 10);
	private final Menu m4 = new MenuImpl("Vegetariana", 7.50, 5);
	private final Menu m5 = new MenuImpl("Sushi", 5.90, 9);
	private final Menu m6 = new MenuImpl("Ramen", 10.30, 100);

	@Test
	void testManager() {
		assertTrue(m.getWaitingOrders().isEmpty());
		/* TODO Penso che il costrutto try-catch possa essere sostituito
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
	}

	@Test
	void testCanBeAssigned() {
		this.o1.increaseMenu(m1);
		this.o1.increaseMenu(m3);
		this.o1.increaseMenu(m4);
		this.o2.increaseMenu(m2);
		this.o2.increaseMenu(m3);
		this.o2.increaseMenu(m5);
		this.o2.increaseMenu(m6);
		this.o3.increaseMenu(m1);
		this.o3.increaseMenu(m2);
		this.o3.increaseMenu(m5);
		
		// Ordine creato correttamente, ci aspettiamo venga assegnato
		assertTrue(this.m.canBeAssigned(o3).isPresent());
		// Ordine che supera la size massima, ci aspettiamo che non venga assegnato
		assertTrue(this.m.canBeAssigned(o2).isEmpty());
		// Ordine con destinazione non compresa in nessun fattorino, ci aspettiamo che non venga assegnato
		assertTrue(this.m.canBeAssigned(o1).isEmpty());
		
		// Verifica del corretto smistamento favorevole a Rider con guadagno minore
		this.m.createCurrentOrder(CityImpl.TAVULLIA, "Casa", null);
		this.m.getCurrentOrderPresent().increaseMenu(m1);
		this.m.getCurrentOrderPresent().increaseMenu(m2);
		assertTrue(this.m.assignCurrentOrder());
		assertTrue(this.m.getRiderWithLastOrder().isPresent());
		System.out.println(this.m.getRiderWithLastOrder().get().getName());
		this.m.getRiderWithLastOrder().get().deliverAll();
		System.out.println(this.m.getRiders().get("Saverio").getProfit());
		this.m.createCurrentOrder(CityImpl.TAVULLIA, "Villa", null);
		this.m.getCurrentOrderPresent().increaseMenu(m4);
		this.m.getCurrentOrderPresent().increaseMenu(m5);
		assertTrue(this.m.assignCurrentOrder());
		assertTrue(this.m.getRiderWithLastOrder().isPresent());
		System.out.println(this.m.getRiderWithLastOrder().get().getName());
		assertNotEquals(this.m.getRiderWithLastOrder().get().getName(), "Saverio");
		assertEquals(this.m.getRiderWithLastOrder().get(), this.m.getRiders()
				                                                 .entrySet()
				                                                 .stream()
				                                                 .filter(s -> s.getValue().getCities().contains(CityImpl.TAVULLIA))
				                                                 .filter(s -> s.getValue().getProfit() == 0)
				                                                 .findAny()
				                                                 .get()
				                                                 .getValue());
	}

	@Test
	void testCreateCurrentOrder() {
		this.m.createCurrentOrder(CityImpl.URBANIA, "Via", new RestaurantImpl("Trattoria", null));
		boolean res = false;
		try {
			this.m.getCurrentOrderPresent();
		} catch (NoSuchElementException e) {
			res = true;
		}
		assertFalse(res);
		assertEquals(this.m.getCurrentOrderPresent().getDestination(), CityImpl.URBANIA);
		assertEquals(this.m.getCurrentOrderPresent().getAdress(), "Via");
		assertEquals(this.m.getCurrentOrderPresent().getRestaurant().getName(), "Trattoria");
		assertEquals(this.m.getCurrentOrderPresent().getRestaurant().getMenuOffer(), null);
	}

	@Test
	void testAssignCurrentOrder() {
		// Creazione di un currentOrder con destinazione non compresa fra i fattorini
		this.m.createCurrentOrder(CityImpl.FOSSOMBRONE, "Piazza", null);
		assertFalse(this.m.assignCurrentOrder());
		
		// Creazione di un currentOrder con destinazionecompresa fra i fattorini
		this.m.createCurrentOrder(CityImpl.URBANIA, "Casa", null);
		assertTrue(this.m.assignCurrentOrder());
	}

	@Test
	void testHowManyInCurrent() {
		this.m.createCurrentOrder(CityImpl.CAGLI, null, null);
		assertTrue(this.m.howManyInCurrent(m1) == 0);
		this.m.getCurrentOrderPresent().increaseMenu(m1);
		assertTrue(this.m.howManyInCurrent(m1) == 1);
		this.m.getCurrentOrderPresent().increaseMenu(m1);
		assertTrue(this.m.howManyInCurrent(m1) == 2);
		this.m.getCurrentOrderPresent().decreaseMenu(m1);
		assertTrue(this.m.howManyInCurrent(m1) == 1);
		for(int i = 1; i <= 3; i++)
			this.m.getCurrentOrderPresent().increaseMenu(m3);
		assertTrue(this.m.howManyInCurrent(m3) == 3);
	}

	@Test
	void testRefreshWaitingOrder() {
		this.o1.increaseMenu(m1);
		this.o1.increaseMenu(m5);
		this.o2.increaseMenu(m1);
		this.o2.increaseMenu(m2);
		this.o2.increaseMenu(m3);
		this.o3.increaseMenu(m3);
		this.o3.increaseMenu(m4);
		this.o3.increaseMenu(m5);
		this.m.getWaitingOrders().add(o1);	
		this.m.getWaitingOrders().add(o2);	
		this.m.getWaitingOrders().add(o3);
		
		// Mi aspetto false poichè nella waitingList non ci sono ordini assegnabili ad il rider Giulia
		assertFalse(this.m.refreshWaitingOrder(this.m.getRiders().get("Sara")));
		
		// Mi aspetto true poichè nella waitingList ci sono ordini assegnabili ad il Rider Giacomo
		assertTrue(this.m.refreshWaitingOrder(this.m.getRiders().get("Giacomo")));
		assertTrue(this.m.getRiders().get("Giacomo").getBag().contains(o3));
		assertTrue(this.m.getRiders().get("Giacomo").getBag().size() == 1);
		assertFalse(this.m.getRiders().get("Giacomo").getBag().contains(o1) || this.m.getRiders().get("Giacomo").getBag().contains(o2));
		
		// Mi aspetto true poichè nella waitingList ci sono ordini assegnabili ad il Rider Saverio
		assertTrue(this.m.refreshWaitingOrder(this.m.getRiders().get("Saverio")));
		assertTrue(this.m.getRiders().get("Saverio").getBag().contains(o2));
		assertTrue(this.m.getRiders().get("Saverio").getBag().size() == 1);
		assertFalse(this.m.getRiders().get("Saverio").getBag().contains(o1));
	}
}
