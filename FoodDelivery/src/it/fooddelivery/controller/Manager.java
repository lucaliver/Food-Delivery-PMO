/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.Rider;
import it.fooddelivery.model.implementation.CityImpl;

/**
 * Interface for the controller component of the MVC pattern in the Food Delivery application.
 */
public interface Manager {

	/**
	 * Checks if the order can be assigned.
	 * 
	 * @param order the order to check
	 * @return an Optional Rider if there is one that can take the order, {@code empty} if not
	 */
	Optional<Rider> canBeAssigned(Order order);
	
	/**
	 * Creates a new current order.
	 * 
	 * @param destination destination of the order
	 * @param address address of the order
	 * @param restuarant restaurant of the order
	 */
	void createCurrentOrder(CityImpl destination, String address, Restaurant restaurant);

	/**
	 * Assigns the current order (to a rider or to waiting list) and empties the current order slot.
	 * 
	 * @return {@code true} if assigned to a Rider, {@code false} if put into the waiting list
	 */
	boolean assignCurrentOrder();
	
	/**
	 * Returns a String with info about the orders in the waiting list.
	 * 
	 * @return String with info about the orders in the waiting list
	 */
	String showWaitingOrders();
	
	/**
	 * Returns the quantity of a specific menu in the current order.
	 * 
	 * @param menu the menu to look for
	 * @return the quantity of that menu
	 */
	int howManyInCurrent(Menu menu);
	
	/**
	 * Tries to assign the orders in the waiting list to the Rider.
	 * 
	 * @param freeRider Rider that we are checking
	 * @return {@code true} if at least one new order was given to the Rider
	 */
	boolean refreshWaitingOrder(Rider freeRider);
	
	/**
	 * Increases the quantity of the menu in the current order.
	 * 
	 * @param menu the menu to increase
	 */
	void increaseInCurrent(Menu menu);
	
	/**
	 * Decreases the quantity of the menu in the current order.
	 * 
	 * @param menu the menu to decrease
	 */
	void decreaseInCurrent(Menu menu);
	
	/**
	 * @return a Set of all the restaurants
	 */
	Set<Restaurant> getRestaurants();
	
	/**
	 * @return a Map of all riders, mapped by their name
	 */
	Map<String, Rider> getRiders();
	
	/**
	 * @return an Optional Rider of the rider that took last order, {@code empty} if it went to the waiting list
	 */
	Optional<Rider> getRiderWithLastOrder();
	
	/**
	 * @return if current order is present returns the value, otherwise throws {@code NoSuchElementException}
	 */
	Order getCurrentOrderPresent();
	
	/**
	 * @return a List of the orders in waiting list
	 */
	List<Order> getWaitingOrders();
}
