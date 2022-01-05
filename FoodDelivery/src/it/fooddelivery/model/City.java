/**
* @author Giulia Costa
 */

package it.fooddelivery.model;

import org.junit.platform.commons.util.ToStringBuilder;

/**
 * Enum used for Cities.
 *
 */
public enum City {
	FERMIGNANO("Fermignano"),
	CAGLI("Cagli"),
	TAVULLIA("Tavullia"),
	URBANIA("Urbania"),
	FOSSOMBRONE("Fossombrone");	
	
	private final String name;
	
	/**
	 * @param nome città.
	 */
	City(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}