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
	
	/**
	 * 
	 * @return the adress of the order
	 */
	public String getAdress(); 
		
	/**
	 * 
	 * @param adress = thedestination adress of the order
	 */
	public void setAdress(String adress); 
	
	/**
	 * 
	 * @return the rastaurant that make the order
	 */
	public Restaurant getRestaurant(); 
	
	/**
	 * 
	 * @param restaurant = the restaurant that made this order
	 */
	public void setRestaurant(Restaurant restaurant); 
	
}
