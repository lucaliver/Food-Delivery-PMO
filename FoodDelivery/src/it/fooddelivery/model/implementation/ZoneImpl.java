/**
 * @author Giulia Costa
 */
package it.fooddelivery.model.implementation;

import java.util.List;

/**
 * Enum used for Cities.
 *
 */
public enum ZoneImpl {
	
	FERMIGNANO("Fermignano"),
	CAGLI("Cagli"),
	TAVULLIA("Tavullia"),
	URBANIA("Urbania"),
	FOSSOMBRONE("Fossombrone");	
	
	private String description;
	
	ZoneImpl(final String description) {
		this.description = description;
	}
	
	public String toString() {
		return description;
	}
}