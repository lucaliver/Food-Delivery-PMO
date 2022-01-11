/**
 * * @author Giacomo Tombari
 */

package it.fooddelivery.model;

/**
 * Interface to represent a menu.
 *
 */
public interface Menu {

	/**
	 * 
	 * @return the price of the menu.
	 */
	double getPrice();
	
	/**
	 * 
	 * @return the name of the menu.
	 */
	String getName();
	
	/**
	 * 
	 * @return the size of the menu.
	 */
	int getSize();
	
	/**
	 * 
	 * @return all the info of the menu (name, price, size).
	 */
	String showMenuInfo();
	
	/**
	 * 
	 * @return the incremental quantity of menu.
	 */
	int getQuantityPlus();
	
	/**
	 * 
	 * @return the decremental quantity of menu.
	 */
	int getQuantityMinus();
	
	/**
	 * 
	 * @return the quantity of menu.
	 */
	int getQuantity();
	
	/**
	 * 
	 * set quantity of menu.
	 */
	void setQuantity(int quantity);
}
