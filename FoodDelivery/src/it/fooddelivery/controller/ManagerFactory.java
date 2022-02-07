/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.fooddelivery.model.City;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.Rider;
import it.fooddelivery.model.implementation.MenuImpl;
import it.fooddelivery.model.implementation.RestaurantImpl;
import it.fooddelivery.model.implementation.RiderImpl;

/**
 * Factory component for the Factory Method used to create Manager objects.
 */
public class ManagerFactory {

	/**
	 * Creates a Manager, configurated with restaurants and riders for the Pesaro and Urbino (PU) province.
	 */
	public static Manager create(){
		// TODO Prima di consegnare: aggiungere riders, ristoranti, menu, assegnamenti sensati.
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

		List<City> citiesForSara = new ArrayList<>();
		citiesForSara.add(City.FERMIGNANO);
		citiesForSara.add(City.URBANIA);
		citiesForSara.add(City.TAVULLIA);
		citiesForSara.add(City.CAGLI);
		riders.put("Sara", new RiderImpl("Sara", citiesForSara));
		
		List<City> citiesForSaverio = new ArrayList<>();
		citiesForSaverio.add(City.TAVULLIA);
		riders.put("Saverio", new RiderImpl("Saverio", citiesForSaverio));

		riders.put("Marco", new RiderImpl("Marco", new ArrayList<>()));

		return riders;
	}

	/**
	 * Creates a list of restaurants located in the PU province.
	 * 
	 * @return a list of restaurants
	 */
	private static Set<Restaurant> createRestaurants() {
		Set<Restaurant> restaurants = new HashSet<>();
		
		List<Menu> menusForMc = new ArrayList<>();
		menusForMc.add(new MenuImpl("HappyMeal", 5.90, 5));
		menusForMc.add(new MenuImpl("BigMac Menu", 9.90, 10));
		menusForMc.add(new MenuImpl("McChicken Menu", 8.90, 9));
		menusForMc.add(new MenuImpl("Crispy McBacon Menu", 8.90, 9));
		menusForMc.add(new MenuImpl("McNuggets 6pz.", 3.90, 3));
		menusForMc.add(new MenuImpl("McFlurry", 2.90, 2));
		menusForMc.add(new MenuImpl("Insalata", 3.90, 6));
		menusForMc.add(new MenuImpl("McVegan Menu", 8.50, 15));
		restaurants.add(new RestaurantImpl("McDonalds", menusForMc));

		List<Menu> menusForKFC = new ArrayList<>();
		menusForKFC.add(new MenuImpl("Ali di pollo piccanti 6pz.", 5.50, 5));
		menusForKFC.add(new MenuImpl("Pollo fritto 4pz.", 4.50, 10));
		menusForKFC.add(new MenuImpl("Patatine piccole", 3.50, 7));
		menusForKFC.add(new MenuImpl("Patatine medie", 5.50, 9));
		menusForKFC.add(new MenuImpl("Patatine grandi", 6.30, 10));
		menusForKFC.add(new MenuImpl("Cestino mix pollo", 19.90, 20));
		restaurants.add(new RestaurantImpl("KFC", menusForKFC));

		List<Menu> menusForPizzeria = new ArrayList<>();
		menusForPizzeria.add(new MenuImpl("Margherita", 5.50, 5));
		menusForPizzeria.add(new MenuImpl("Diavola", 6.50, 5));
		menusForPizzeria.add(new MenuImpl("4 formaggi", 7.50, 5));
		menusForPizzeria.add(new MenuImpl("Rossini", 6.50, 5));
		menusForPizzeria.add(new MenuImpl("Frutti di mare", 9.50, 5));
		menusForPizzeria.add(new MenuImpl("Funghi", 7.50, 5));
		menusForPizzeria.add(new MenuImpl("Prosciutto crudo", 6.50, 5));
		menusForPizzeria.add(new MenuImpl("Vegetariana", 7.50, 5));
		restaurants.add(new RestaurantImpl("Pizzeria da Mario", menusForPizzeria));
		
		List<Menu> menusForSushi = new ArrayList<>();
		menusForSushi.add(new MenuImpl("Sushi", 5.90, 9));
		menusForSushi.add(new MenuImpl("Ramen", 10.30, 7));
		restaurants.add(new RestaurantImpl("Sushino", menusForSushi));
		
		return restaurants;
	}
	
}
