
package it.fooddelivery.model.implementation;

import it.fooddelivery.model.Menu;
/**
 * 
 * @author Tombari Giacomo
 *
 */
public class MenuImpl implements Menu{
	
	// Campi
	private final String name;
	private final double price;
	private final int size;
	
	// Costruttore
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
		return("Name: " +getName()+	" Price: " +getPrice()+	" Size: " +getSize());
	}
}
