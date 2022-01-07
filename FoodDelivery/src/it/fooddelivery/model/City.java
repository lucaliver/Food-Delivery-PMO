/**
* @author Giulia Costa
 */

package it.fooddelivery.model;

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