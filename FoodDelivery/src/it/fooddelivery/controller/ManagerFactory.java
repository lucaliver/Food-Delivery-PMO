/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.Rider;
import it.fooddelivery.model.implementation.CityImpl;
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
		return new ManagerImpl(createRiders(), createRestaurants());
	}

	/**
	 * Creates a Map of riders working in the PU province.
	 * 
	 * @return a Map of riders with their names as key
	 */
	private static Map<String, Rider> createRiders() {
		Map<String, Rider> riders = new HashMap<>();
		
		Set<CityImpl> citiesForLuca = new HashSet<CityImpl>(Arrays.asList(CityImpl.CAGLI, CityImpl.URBANIA));
		riders.put("Luca", new RiderImpl("Luca", citiesForLuca));

		Set<CityImpl> citiesForGiacomo = new HashSet<CityImpl>(Arrays.asList(CityImpl.FERMIGNANO, CityImpl.URBANIA));
		riders.put("Giacomo", new RiderImpl("Giacomo", citiesForGiacomo));

		Set<CityImpl> citiesForSara = new HashSet<CityImpl>(Arrays.asList(CityImpl.FERMIGNANO, CityImpl.URBANIA, CityImpl.TAVULLIA, CityImpl.CAGLI));
		riders.put("Sara", new RiderImpl("Sara", citiesForSara));
		
		Set<CityImpl> citiesForSaverio = new HashSet<CityImpl>(Arrays.asList(CityImpl.TAVULLIA, CityImpl.CAGLI));
		riders.put("Saverio", new RiderImpl("Saverio", citiesForSaverio));

		return riders;
	}

	/**
	 * Creates a list of restaurants located in the PU province.
	 * 
	 * @return a list of restaurants
	 */
	private static Set<Restaurant> createRestaurants() {
		Set<Restaurant> restaurants = new HashSet<>();
		
		List<Menu> menusForMc = Arrays.asList(new MenuImpl("HappyMeal", 5.90, 5),
				                              new MenuImpl("BigMac Menu", 9.90, 10),
				                              new MenuImpl("McChicken Menu", 8.90, 9),
				                              new MenuImpl("Crispy McBacon Menu", 8.90, 9),
				                              new MenuImpl("McNuggets 6pz.", 3.90, 3),
				                              new MenuImpl("McFlurry", 2.90, 2),
				                              new MenuImpl("Insalata", 3.90, 6),
				                              new MenuImpl("McVegan Menu", 8.50, 15));
		restaurants.add(new RestaurantImpl("McDonalds", menusForMc));
		
		List<Menu> menusForKFC = Arrays.asList(new MenuImpl("Ali di pollo piccanti 6pz.", 5.50, 5),
				                               new MenuImpl("Pollo fritto 4pz.", 4.50, 10),
				                               new MenuImpl("Patatine piccole", 3.50, 7),
				                               new MenuImpl("Patatine medie", 5.50, 9),
				                               new MenuImpl("Patatine grandi", 6.30, 10),
				                               new MenuImpl("Cestino mix pollo", 19.90, 20));
		restaurants.add(new RestaurantImpl("KFC", menusForKFC));

		List<Menu> menusForPizzeria = Arrays.asList(new MenuImpl("Margherita", 5.50, 5),
				                                    new MenuImpl("Diavola", 6.50, 5),
				                                    new MenuImpl("4 formaggi", 7.50, 5),
				                                    new MenuImpl("Rossini", 6.50, 5),
				                                    new MenuImpl("Frutti di mare", 9.50, 5),
				                                    new MenuImpl("Funghi", 7.50, 5),
				                                    new MenuImpl("Prosciutto crudo", 6.50, 5),
				                                    new MenuImpl("Vegetariana", 7.50, 5));
		restaurants.add(new RestaurantImpl("Pizzeria da Mario", menusForPizzeria));
		
		List<Menu> menusForSushi = Arrays.asList(new MenuImpl("Sushi", 5.90, 9),
				                                 new MenuImpl("Ramen", 10.30, 7),
				                                 new MenuImpl("Ravioli", 4.40, 4),
				                                 new MenuImpl("Riso cantonese", 5.50, 6),
				                                 new MenuImpl("Biscotto della fortuna", 0.50, 1));
		restaurants.add(new RestaurantImpl("Sushino", menusForSushi));
		
		return restaurants;
	}		
}
