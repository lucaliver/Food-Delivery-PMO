/**
 * * @author Giacomo Tombari
 */

package it.fooddelivery.model;

import java.util.Map;

/**
 * Interface to represent an order.
 */
public interface Order{
	
	/**
	 * @return all the menus in the order.
	 */
	Map<Menu, Integer> getMenus();	
	
	/**
	 * @param m = menu to add at the order.
	 */
	void addMenu(Menu m);
	
	/**
	 * @param m = menu to remove from the order.
	 */
	boolean removeMenu(Menu m);
	
	/**
	 * Remove all the menus from the order.
	 */
	void removeAllMenus();
	
	/**
	 * @return the size of the whole order.
	 */
	int getOrderSize();
	
	/**
	 * @return the price of the whole order.
	 */
	double getOrderPrice();
	
	/**
	 * @return the ID of the order.
	 */
	int getIdOrder();
	/**
	 * 
	 * @return the string to print with the id
	 */
	public String printIdOrder();
	
	/**
	 * @return the destination of the order.
	 */
	City getDestination();
	
	/**
	 * 
	 * @return the adress of the order
	 */
	String getAdress();  
	
	/**
	 * 
	 * @return the restaurant that is making the order
	 */
	Restaurant getRestaurant(); 	
	
	/**
	 * 
	 * @return the all the menus in the order
	 */
	String showOrderContent();
	
	/**
	 * 
	 * @return infos about the order (city and address of destination, restaurant, total price).
	 */
	String showOrderInfo();
	
}
