/**
 * * @author Giacomo Tombari
 */
package it.fooddelivery.model;

import java.util.List;

/**
 * Interface for a order
 */
public interface Order{
	
	/**
	 * @return the menu of the order
	 */
	List<Menu> menusList();	
	
	/**
	 * @param m = menu to add at the order
	 */
	void addMenu(Menu m);
	
	/**
	 * @param m = menu to remove from the order
	 */
	void removeMenu(Menu m);
	
	/**
	 * Remove all the menu from the order
	 */
	void removeAllMenu();
	
	/**
	 * @return the size of the order
	 */
	int getSize();
	
	/**
	 * @return the total price of the order
	 */
	double totalPrice();
	
	/**
	 * 
	 * @return the ID of the order
	 */
	String getIdOrder();
	
	/**
	 * 
	 * @return the destination of the order
	 */
	ZoneType getDestination();
		
}
