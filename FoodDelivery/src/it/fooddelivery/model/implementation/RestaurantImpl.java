/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.model.implementation;

import java.util.List;

import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Restaurant;

/**
 * A class to represent a restaurant. Implementation of {@link Restaurant}.
 */
public class RestaurantImpl implements Restaurant {
	
	private final String name;
	private final List<Menu> menuOffer;
	
	/**
	 * Constructs a Restaurant with the specified name and menu offer.
	 * 
	 * @param name the name of this restaurant
	 * @param menuOffer the list of menus offered by this restaurant
	 */
	public RestaurantImpl(String name, List<Menu> menuOffer) {
		this.name = name;
		this.menuOffer = menuOffer;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<Menu> getMenuOffer() {
		return this.menuOffer;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RestaurantImpl))
			return false;
		
		RestaurantImpl other = (RestaurantImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
