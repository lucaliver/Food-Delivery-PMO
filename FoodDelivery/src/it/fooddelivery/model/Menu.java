/**
 * * @author Giacomo Tombari
 */

package it.fooddelivery.model;

/**
 * Interface to represent a menu.
 */
public interface Menu {
	
	/**
	 * @return a String with all the info of this menu (name, price, size)
	 */
	String showMenuInfo();

	/**
	 * @return the price of this menu
	 */
	double getPrice();
	
	/**
	 * @return the name of this menu
	 */
	String getName();
	
	/**
	 * @return the size of this menu
	 */
	int getSize();
}