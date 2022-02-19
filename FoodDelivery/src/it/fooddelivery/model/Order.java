/**
 * * @author Giacomo Tombari
 */

package it.fooddelivery.model;

import java.util.Map;

/**
 * Interface to represent an order.
 */
public interface Order {

	/**
	 * @return all the menus in this order
	 */
	Map<Menu, Integer> getMenus();

	/**
	 * @param menu menu to add at this order
	 */
	void increaseMenu(Menu menu);

	/**
	 * @param menu menu to remove from this order
	 */
	void decreaseMenu(Menu menu);

	/**
	 * Remove all the menus from this order
	 */
	void removeAllMenus();

	/**
	 * @return the size of this order
	 */
	int getSize();

	/**
	 * @return the price of this order
	 */
	double getPrice();

	/**
	 * @return the ID of this order
	 */
	int getId();

	/**
	 * @return the destination of this order
	 */
	City getDestination();

	/**
	 * @return the adress of this order
	 */
	String getAdress();

	/**
	 * @return the restaurant that is making this order
	 */
	Restaurant getRestaurant();

	/**
	 * @return the all the menus in this order
	 */
	String showOrderContent();

	/**
	 * @return a String with all the info about this order
	 */
	String showOrderInfo();

	/**
	 * @return a String with basic info about this order 
	 */
	String showBasicInfo();
}
