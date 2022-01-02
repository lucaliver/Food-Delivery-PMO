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

	@Test
	void testGetName() {
		final RestaurantImpl r = new RestaurantImpl("McDonald's", null);
		assertEquals("McDonald's", r.getName());
	}
	
	@Test
	void testGetMenuOffer() {
		//TODO implementare meglio
		Menu m = new MenuImpl("HappyMeal", 5, 2);
		List<Menu> mList = new ArrayList<>();
		mList.add(m);
		final RestaurantImpl r = new RestaurantImpl("", mList);
		assertEquals(mList, r.getMenuOffer());
	}

}
