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
	
	private final String description;
	
	City(final String description) {
		this.description = description;
	}
}