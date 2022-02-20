/**
* @author Giacomo Tombari
 */

package it.fooddelivery.model.implementation;
import it.fooddelivery.model.City;

/**
 * Enum used for cities.
 */
public enum CityImpl implements City {
	FERMIGNANO("Fermignano"),
	CAGLI("Cagli"),
	TAVULLIA("Tavullia"),
	URBANIA("Urbania"),
	FOSSOMBRONE("Fossombrone");	
	
	private final String name;
	
	/**
	 * Constructor, it sets the city's name.
	 * 
	 * @param name of the city
	 */
	CityImpl(final String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
}