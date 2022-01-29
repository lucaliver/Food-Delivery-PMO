/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.model;

import java.util.List;

/**
 * An interface to represent a restaurant.
 */
public interface Restaurant {
	
	/**
	 * @return this restaurant's name
	 */
	String getName();
	
	/**
	 * @return a list of the menus offered by this restaurant
	 */
	List<Menu> getMenuOffer();
}
