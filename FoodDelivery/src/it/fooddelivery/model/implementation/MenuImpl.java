/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.model.implementation;

import it.fooddelivery.model.Menu;

/**
 * A class to represent a menu. Implementation of {@link Menu}.
 */
public class MenuImpl implements Menu{
	
	private final String name;
	private final double price;
	private final int size;	
	
	/**
	 * Constructor, initializes all the fields.
	 * 
	 * @param name name of this menu
	 * @param price price of this menu
	 * @param size size of this menu
	 */
	public MenuImpl(String name, double price, int size) {		
		this.name = name;
		this.price = price;
		this.size = size;
	}
	
	@Override
	public String showMenuInfo() {
		return(this.name+" - "+ String.format("%.2f",this.price)+"€ - "+this.size+"u");
	}

	@Override
	public double getPrice() {		
		return price;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		return size;
	}
}
