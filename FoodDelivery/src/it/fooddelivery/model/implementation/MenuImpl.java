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
	
	private int quantity;
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
		this.quantity = 0;
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

	@Override
	public int getQuantityPlus() {
		++quantity;
		System.out.println(quantity);
		return quantity;
	}

	@Override
	public int getQuantityMinus() {
		quantity--;
		System.out.println(quantity);
		return quantity;
	}

	@Override
	public int getQuantity() {
		if(quantity == 0) {
			System.out.println("0");
		}
		return quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity;		
	}
}
