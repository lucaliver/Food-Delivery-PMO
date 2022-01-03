/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

import java.util.ArrayList;
import java.util.List;

import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.Rider;
import it.fooddelivery.model.implementation.RestaurantImpl;
import it.fooddelivery.model.implementation.RiderImpl;

public class ManagerFactory {

	/**
	 * It creates a Manager, configurated with restaurants and riders.
	 */
	public static Manager create(){
		List<Restaurant> restaurants = new ArrayList<>();
		List<Rider> riders = new ArrayList<>();

		restaurants.add(new RestaurantImpl("McDonalds", null));
		restaurants.add(new RestaurantImpl("KFC", null));
		restaurants.add(new RestaurantImpl("Pizzeria da Mario", null));
		restaurants.add(new RestaurantImpl("Sushino", null));

		riders.add(new RiderImpl("Luca", null));
		riders.add(new RiderImpl("Giulia", null));
		riders.add(new RiderImpl("Giacomo", null));
		
		return new Manager(riders, restaurants);
	}
	
}
