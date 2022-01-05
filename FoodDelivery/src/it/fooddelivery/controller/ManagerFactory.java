/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.fooddelivery.model.City;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.Rider;
import it.fooddelivery.model.implementation.MenuImpl;
import it.fooddelivery.model.implementation.RestaurantImpl;
import it.fooddelivery.model.implementation.RiderImpl;

public class ManagerFactory {

	/**
	 * It creates a Manager, configurated with restaurants and riders.
	 */
	public static Manager create(){
		//TODO Finire configurazione ristoranti
		List<Restaurant> restaurants = new ArrayList<>();
		Map<String, Rider> riders = new HashMap<>();
		
		List<Menu> menusForMc = new ArrayList<>();
		menusForMc.add(new MenuImpl("HappyMeal", 5, 2));
		menusForMc.add(new MenuImpl("BigMac Menu", 10, 4));
		restaurants.add(new RestaurantImpl("McDonalds", menusForMc));

		List<Menu> menusForKFC = new ArrayList<>();
		menusForMc.add(new MenuImpl("Pollo crudo", 5, 2));
		menusForMc.add(new MenuImpl("Pollo fritto", 10, 66));
		restaurants.add(new RestaurantImpl("KFC", menusForKFC));

		List<Menu> menusForPizzeria = new ArrayList<>();
		menusForMc.add(new MenuImpl("Margherita", 5, 2));
		menusForMc.add(new MenuImpl("Diavola", 100, 4));
		restaurants.add(new RestaurantImpl("Pizzeria da Mario", menusForPizzeria));
		
		List<Menu> menusForSushi = new ArrayList<>();
		menusForMc.add(new MenuImpl("Sushi", 5, 9));
		menusForMc.add(new MenuImpl("Ramen", 10, 7));
		restaurants.add(new RestaurantImpl("Sushino", menusForSushi));

		riders.put("Luca", new RiderImpl("Luca", null));
		riders.put("Giulia", new RiderImpl("Giulia", null));
		riders.put("GIacomo", new RiderImpl("Giacomo", null));
		
		return new Manager(riders, restaurants);
	}
	
}
