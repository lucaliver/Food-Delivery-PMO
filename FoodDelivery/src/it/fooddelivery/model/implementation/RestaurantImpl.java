/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.model.implementation;

import java.util.List;

import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Restaurant;

/**
 * A class to represent a restaurant.
 */
public class RestaurantImpl implements Restaurant {
	private final String name;
	private List<Menu> menuOffer;
	
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

}
