/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.model;

import java.util.List;

/**
 * Interface to represent a rider.
 */
public interface Rider {
	
	/** 
	 * @return a list of all the orders in the rider's bag.
	 */
	List<Order> getBag();
	
	/**
	 * Adds the order to the rider's bag.
	 * @param order the order to add
	 */
	void addOrder(Order order);
	
	/**
	 * Delivers the order, removing it from the bag and earning its part.
	 * 
	 * @param order the order to deliver
	 */
	void deliverOrder(Order order);	
	
	/**
	 * Delivers every order of this rider.
	 */
	void deliverAll();

	/**
	 * @return rider's name
	 */
	String getName();
	
	/** 
	 * @return total rider's profit at the moment
	 */
	double getProfit();
	
	/**
	 * @param order the order to check if can fit in this rider's bag
	 * @return {true} if it could fit, {false} if it couldn't.
	 */
	boolean canFit(Order order);
	
	/**
	 * @return a list of all the cities where this rider's can deliver
	 */
	List<City> getCities();
	
	/**
	 * @return the space used in this rider's bag at the moment
	 */
	int getCapacity();
	
	/**
	 * @return a String with the info about this rider (profit and capacity)
	 */
	String showRiderInfo();
	
	/**
	 * @return a String with the content of the rider's bag at the moment
	 */
	String showBagInfo();
	
	/**
	 * @return how much profit he will make with the orders that are in his bag right now
	 */
	double getBagProfit();
	
}