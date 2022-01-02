/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.model.implementation;

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
	 * @param n = name of the menu.
	 * @param p = price of the menu.
	 * @param s = size of the menu.
	 */
	public MenuImpl(String n, double p, int s) {		
		this.name = n;
		this.price = p;
		this.size = s;
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
	public String show() {
		return("Name: " +getName()+	", Price: " +getPrice()+ "€, Size: " +getSize());
	}
}
