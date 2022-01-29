/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

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
	// TODO Factory pattern da guardare.

	/**
	 * Creates a Manager, configurated with restaurants and riders for the Pesaro and Urbino (PU) province.
	 */
	public static Manager create(){
		// TODO Prima di consegnare, aggiungere riders, ristoranti, menu, assegnamenti sensati.
		return new Manager(createRiders(), createRestaurants());
	}

	/**
	 * Creates a Map of riders working in the PU province.
	 * 
	 * @return a Map of riders with their names as key
	 */
	private static Map<String, Rider> createRiders() {
		Map<String, Rider> riders = new HashMap<>();
		
		List<City> citiesForLuca = new ArrayList<>();
		citiesForLuca.add(City.CAGLI);
		citiesForLuca.add(City.URBANIA);
		riders.put("Luca", new RiderImpl("Luca", citiesForLuca));
		
		List<City> citiesForGiulia = new ArrayList<>();
		citiesForGiulia.add(City.TAVULLIA);;
		citiesForGiulia.add(City.URBANIA);;
		riders.put("Giulia", new RiderImpl("Giulia", citiesForGiulia));
		

		List<City> citiesForGiacomo = new ArrayList<>();
		citiesForGiacomo.add(City.FERMIGNANO);
		citiesForGiacomo.add(City.URBANIA);
		riders.put("Giacomo", new RiderImpl("Giacomo", citiesForGiacomo));

		return riders;
	}

	/**
	 * Creates a list of restaurants located in the PU province.
	 * 
	 * @return a list of restaurants
	 */
	private static List<Restaurant> createRestaurants() {
		List<Restaurant> restaurants = new ArrayList<>();
		
		List<Menu> menusForMc = new ArrayList<>();
		menusForMc.add(new MenuImpl("HappyMeal", 5.00, 6));
		menusForMc.add(new MenuImpl("BigMac Menu", 10.00, 4));
		menusForMc.add(new MenuImpl("McChicken Menu", 8.00, 4));
		menusForMc.add(new MenuImpl("Crispy McBacon Menu", 12.00, 99));
		restaurants.add(new RestaurantImpl("McDonalds", menusForMc));

		List<Menu> menusForKFC = new ArrayList<>();
		menusForKFC.add(new MenuImpl("Pollo crudo", 5.30, 2));
		menusForKFC.add(new MenuImpl("Pollo fritto", 10, 66));
		restaurants.add(new RestaurantImpl("KFC", menusForKFC));

		List<Menu> menusForPizzeria = new ArrayList<>();
		menusForPizzeria.add(new MenuImpl("Margherita", 5.60, 2));
		menusForPizzeria.add(new MenuImpl("Diavola", 100.00, 4));
		restaurants.add(new RestaurantImpl("Pizzeria da Mario", menusForPizzeria));
		
		List<Menu> menusForSushi = new ArrayList<>();
		menusForSushi.add(new MenuImpl("Sushi", 5.90, 9));
		menusForSushi.add(new MenuImpl("Ramen", 10.30, 7));
		restaurants.add(new RestaurantImpl("Sushino", menusForSushi));
		
		return restaurants;
	}
	
}
