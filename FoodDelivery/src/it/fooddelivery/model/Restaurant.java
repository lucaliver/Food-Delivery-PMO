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
	 * @return the restaurant's name.
	 */
	public String name();
	
	/**
	 * @return a list of the menus offered by the restaurant.
	 */
	public List<Menu> menuOffer();
}
