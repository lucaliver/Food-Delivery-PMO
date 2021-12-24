/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.model.implementation;

import java.util.List;

import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Restaurant;

public class RestaurantImpl implements Restaurant {
	private String name;
	private List<Menu> menuOffer;
	
	public RestaurantImpl(String n, List<Menu> o) {
		this.name = n;
		this.setMenuOffer(o);
	}
	
	@Override
	public String name() {
		return this.name;
	}

	@Override
	public List<Menu> menuOffer() {
		return this.getMenuOffer();
	}

	public List<Menu> getMenuOffer() {
		return menuOffer;
	}

	public void setMenuOffer(List<Menu> menuOffer) {
		this.menuOffer = menuOffer;
	}

}
