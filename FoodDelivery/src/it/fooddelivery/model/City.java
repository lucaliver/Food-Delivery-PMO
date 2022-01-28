/**
* @author Giulia Costa
 */

package it.fooddelivery.model;

/**
 * Enum used for Cities.
 */
public enum City {
	FERMIGNANO("Fermignano"),
	CAGLI("Cagli"),
	TAVULLIA("Tavullia"),
	URBANIA("Urbania"),
	FOSSOMBRONE("Fossombrone");	
	
	private final String name;
	
	/**
	 * Constructor, it sets the city's name.
	 * @param name of the city.
	 */
	City(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}