package it.fooddelivery.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.fooddelivery.model.City;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.Rider;

public interface Manager {

	/**
	 * Checks if the order can be assigned.
	 * 
	 * @param order the order to check
	 * @return an Optional Rider if there is one that can take the order, empty if not
	 */
	Optional<Rider> canBeAssigned(Order order);
	
	/**
	 * Creates a new current order.
	 * 
	 * @param destination destination of the order
	 * @param address address of the order
	 * @param restuarant restaurant of the order
	 */
	void createCurrentOrder(City destination, String address, Restaurant restaurant);

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
	 * @param menu menu to look for
	 * @return quantity of that menu
	 */
	int howManyInCurrent(Menu menu);
	
	/**
	 * Tries to assign the orders in the waiting list to the Rider.
	 * 
	 * @param freeRider Rider that we are checking
	 * @return {@code true} if at least one new order was given to the Rider
	 */
	boolean refreshWaitingOrder(Rider freeRider);
	
	void addToCurrent(Menu menu);
	
	boolean removeFromCurrent(Menu menu);
	
	Set<Restaurant> getRestaurants();
	
	Map<String, Rider> getRiders();
	
	Optional<Rider> getRiderWithLastOrder();
	
	Order getCurrentOrderPresent();
	
	List<Order> getWaitingOrders();
	
	int getSequentialIdCounter();	
}
