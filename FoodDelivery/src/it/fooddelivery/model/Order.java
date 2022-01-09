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
	List<Menu> getMenus();	
	
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
	 * @return the rastaurant that make the order
	 */
	public Restaurant getRestaurant(); 
	
	/**
	 * 
	 * @param name of the menu
	 * @return the quantity of the menu with that name
	 */
	public int menuQuantity(String menuName);
	
	/**
	 * 
	 * @return the info of all the menu in the order
	 */
	public String showOrderInfo();
	
}
