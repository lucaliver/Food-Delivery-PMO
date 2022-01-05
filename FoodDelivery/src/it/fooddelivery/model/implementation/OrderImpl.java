/**
 * @author Giacomo Tombari 
 */

package it.fooddelivery.model.implementation;

import java.util.ArrayList;
import java.util.List;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.City;

/**
 * A class to represent an order.
 */
public class OrderImpl implements Order{
	private final String idOrder;
	private final City destination;
	private final List<Menu> menus;
	private String address;
	private Restaurant restaurant;
	
	/**
	 * Constructor.
	 * @param id = id of the order.
	 * @param dest = city of destination.
	 */
	public OrderImpl(String id, City destination, String address, Restaurant restaurant) {		
		this.idOrder = id;
		this.destination = destination;
		this.address = address;
		this.restaurant = restaurant;
		this.menus = new ArrayList<>();	
	}
	
	
	@Override
	public List<Menu> getMenusList() {		
		return this.menus;
	}
	
	@Override
	public void addMenu(Menu m) {
		this.menus.add(m);
	}

	@Override
	public void removeMenu(Menu m) {
		this.menus.remove(m);
	}
	
	@Override
	public void removeAllMenu() {
		if(!this.getMenusList().isEmpty()) {	
			this.getMenusList().removeAll(menus);
		}		
	}

	@Override
	public int getSize() {
		int size = 0;
		for(Menu m : menus) {
			size += m.getSize();
		}
		return size;
	}

	@Override
	public double getTotalPrice() {
		double totPrice = 0;
		for(Menu m : menus) {
			totPrice += m.getPrice();
		}
		return totPrice;
	}

	@Override
	public String getIdOrder() {
		return this.idOrder;
	}

	@Override
	public City getDestination() {
		return this.destination;		
	}


	public String getAdress() {
		return address;
	}


	public void setAdress(String adress) {
		this.address = adress;
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	
	

}
