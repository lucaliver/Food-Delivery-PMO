/**
 * * @author Giacomo Tombari
 */

package it.fooddelivery.model;

import java.util.List;

/**
 * Interface to represent an order.
 */
public interface Order{
	
	/**
	 * @return all the menus in the order.
	 */
	List<Menu> menusList();	
	
	/**
	 * @param m = menu to add at the order.
	 */
	void addMenu(Menu m);
	
	/**
	 * @param m = menu to remove from the order.
	 */
	void removeMenu(Menu m);
	
	/**
	 * Remove all the menus from the order.
	 */
	void removeAllMenu();
	
	/**
	 * @return the size of the whole order.
	 */
	int getSize();
	
	/**
	 * @return the price of the whole order.
	 */
	double totalPrice();
	
	/**
	 * @return the ID of the order.
	 */
	String getIdOrder();
	
	/**
	 * @return the destination of the order.
	 */
	City getDestination();
		
}
