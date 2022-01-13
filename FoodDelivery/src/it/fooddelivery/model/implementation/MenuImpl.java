/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.model.implementation;

import java.text.DecimalFormat;

import it.fooddelivery.model.Menu;

/**
 * A class to represent a menu.
 */
public class MenuImpl implements Menu{
	
	private final String name;
	private final double price;
	private final int size;	
	
	/**
	 * Constructor, initializes all the fields.
	 * @param name = name of the menu.
	 * @param price = price of the menu.
	 * @param size = size of the menu.
	 */
	public MenuImpl(String name, double price, int size) {		
		this.name = name;
		this.price = price;
		this.size = size;
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

	@Override
	public String showMenuInfo() {
		return(getName()+" - "+ String.format("%.2f",getPrice())+"€ - "+getSize()+"u");
		
	}

}
