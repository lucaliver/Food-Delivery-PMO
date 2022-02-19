/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.fooddelivery.model.Menu;
import it.fooddelivery.model.implementation.MenuImpl;
import it.fooddelivery.model.implementation.RestaurantImpl;

class TestRestaurantImpl {
	final RestaurantImpl r = new RestaurantImpl("McDonald's", null);

	@Test
	void testRestaurantImpl() {
		assertNotNull(r);
	}
	
	@Test
	void testGetName() {
		assertEquals("McDonald's", r.getName());
	}
	
	@Test
	void testGetMenuOffer() {
		Menu menu = new MenuImpl("HappyMeal", 5, 2);
		List<Menu> menuList = new ArrayList<>();
		menuList.add(menu);
		final RestaurantImpl r = new RestaurantImpl("", menuList);
		
		assertEquals(menuList, r.getMenuOffer());
	}
}
