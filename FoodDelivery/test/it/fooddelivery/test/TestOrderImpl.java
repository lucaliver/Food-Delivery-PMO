/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.implementation.MenuImpl;
import it.fooddelivery.model.implementation.OrderImpl;
import it.fooddelivery.model.implementation.RestaurantImpl;

class TestOrderImpl {
	
	final private List<Menu> menus = new ArrayList<>();
	final private RestaurantImpl restaurant = new RestaurantImpl("MasterChef", menus);
	private OrderImpl o = new OrderImpl(01, City.CAGLI, "Strada_1", restaurant);
	private MenuImpl m1 = new MenuImpl("Panino", 4.00, 3);
	private MenuImpl m2 = new MenuImpl("Pizza", 7.00, 10);
	private MenuImpl m3 = new MenuImpl("Pasta", 10.00, 8);

	@Test
	void testOrderImpl() {	
		assertNotNull(o);
		assertEquals(o.getId(), 01);
		assertEquals(o.getDestination(), City.CAGLI);
		assertEquals(o.getAdress(), "Strada_1");
		assertEquals(o.getRestaurant(), this.restaurant);
		assertTrue(o.getMenus().isEmpty());
		assertEquals(o.getPrice(), 0);
		assertEquals(o.getSize(), 0);
	}

	@Test
	void testAddMenu() {
		assertTrue(o.getMenus().isEmpty()); 
		o.addMenu(m1);
		assertTrue(!o.getMenus().isEmpty());
		assertEquals(1, o.getMenus().size());
		assertTrue(o.getMenus().containsKey(m1));
		assertEquals(o.getMenus().get(m1), 1);
		o.addMenu(m2);
		assertEquals(o.getMenus().get(m2), 1);
		assertEquals(o.getMenus().get(m1), 1);
		o.addMenu(m1);
		assertEquals(o.getMenus().get(m1), 2);
	}

	@Test
	void testRemoveMenu() {
		o.addMenu(m1);
		o.addMenu(m2);
		o.addMenu(m1);
		o.addMenu(m2);
		o.removeMenu(m1);
		assertTrue(o.getMenus().containsKey(m1));
		assertEquals(o.getMenus().get(m1), 1);
		o.removeMenu(m1);
		assertFalse(o.getMenus().containsKey(m1));
		o.removeMenu(m2);
		o.removeMenu(m2);
		assertTrue(o.getMenus().isEmpty());
	}
	
	@Test
	void testRemoveAllMenus() {
		o.addMenu(m1);
		o.addMenu(m1);
		o.addMenu(m2);
		o.addMenu(m3);
		o.removeAllMenus();
		assertTrue(o.getMenus().isEmpty());
	}
	
	@Test
	void testGetMenus() {
		assertTrue(o.getMenus().isEmpty());		
		o.addMenu(m1);
		assertNotNull(o.getMenus());
		assertTrue(o.getMenus().containsKey(m1));
	}

	@Test
	void testGetSize() {
		o.addMenu(m1); 
		o.addMenu(m2); 
		o.addMenu(m2); 
		o.addMenu(m3);
		int sizeTot = m1.getSize()+m2.getSize()+m2.getSize()+m3.getSize(); 
		assertEquals(sizeTot, o.getSize());
	}

	@Test
	void testTotalPrice() {
		o.addMenu(m1); 
		o.addMenu(m2); 
		o.addMenu(m3); 
		o.addMenu(m3);
		double priceTot = m1.getPrice()+m2.getPrice()+m3.getPrice()+m3.getPrice();
		assertEquals(priceTot, o.getPrice());
	}

	@Test
	void testGetIdOrder() {
		assertEquals(01, o.getId());
	}

	@Test
	void testGetDestination() {
		assertEquals(City.CAGLI, o.getDestination());
	}

}
