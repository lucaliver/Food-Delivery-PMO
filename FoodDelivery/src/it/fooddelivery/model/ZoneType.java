/**
* @author Giulia Costa
 */
package it.fooddelivery.model;

/**
 * Enum used for Cities.
 *
 */
public enum ZoneType {
	
	FERMIGNANO("Fermignano"),
	CAGLI("Cagli"),
	TAVULLIA("Tavullia"),
	URBANIA("Urbania"),
	FOSSOMBRONE("Fossombrone");	
	
	private final String description;
	
	ZoneType(final String description) {
		this.description = description;
	}
}