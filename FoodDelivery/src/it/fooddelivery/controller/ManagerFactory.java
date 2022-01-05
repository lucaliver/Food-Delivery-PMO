/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

import java.util.ArrayList;
import java.util.List;

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
		List<Rider> riders = new ArrayList<>();
		
		List<Menu> menusForMc = new ArrayList<>();
		menusForMc.add(new MenuImpl("HappyMeal", 5, 2));
		menusForMc.add(new MenuImpl("BigMac Menu", 10, 4));
				
		restaurants.add(new RestaurantImpl("McDonalds", menusForMc));
		restaurants.add(new RestaurantImpl("KFC", null));
		restaurants.add(new RestaurantImpl("Pizzeria da Mario", null));
		restaurants.add(new RestaurantImpl("Sushino", null));

		riders.add(new RiderImpl("Luca", null));
		riders.add(new RiderImpl("Giulia", null));
		riders.add(new RiderImpl("Giacomo", null));
		
		return new Manager(riders, restaurants);
	}
	
}
