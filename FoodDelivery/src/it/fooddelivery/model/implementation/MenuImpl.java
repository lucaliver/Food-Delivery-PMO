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
	private static final DecimalFormat df = new DecimalFormat("0.00"); //Per i centesimi
	
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
	public String showMenuInfo() {
		return(getName()+" - "+ df.format(getPrice())+"€ - "+getSize()+"u");
	}
}
