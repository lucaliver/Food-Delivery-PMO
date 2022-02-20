/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.model;

import java.util.List;
import java.util.Set;

import it.fooddelivery.model.implementation.CityImpl;

/**
 * Interface to represent a rider.
 */
public interface Rider {
	
	/** 
	 * @return a list of all the orders in this rider's bag
	 */
	List<Order> getBag();
	
	/**
	 * Adds the order to this rider's bag.
	 * 
	 * @param order the order to add
	 */
	void addOrder(Order order);
	
	/**
	 * Delivers the order, removing it from the bag and earning the profit.
	 * 
	 * @param order the order to deliver
	 */
	void deliverOrder(Order order);	
	
	/**
	 * Delivers every order of this rider.
	 */
	void deliverAll();

	/**
	 * @return this rider's name
	 */
	String getName();
	
	/** 
	 * @return the total profit of this rider
	 */
	double getProfit();
	
	/**
	 * Checks if the order could fit in this rider's bag
	 * 
	 * @param order the order to check
	 * @return {@code true} if it could fit, {@code false} if it couldn't
	 */
	boolean canFit(Order order);
	
	/**
	 * @return a list of all the cities where this rider can deliver
	 */
	Set<CityImpl> getCities();
	
	/**
	 * @return the space used in this rider's bag
	 */
	int getUsedSpace();
	
	/**
	 * @return a String with the info about this rider (profit and capacity)
	 */
	String showRiderInfo();
	
	/**
	 * @return a String with the content of this rider's bag
	 */
	String showBagInfo();
	
	/**
	 * @return how much this rider would earn with the orders that are in his bag
	 */
	double getBagProfit();
	
}