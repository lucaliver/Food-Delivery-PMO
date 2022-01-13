/**
 * @author Giacomo Tombari 
 */

package it.fooddelivery.model.implementation;

import java.util.HashMap;
import java.util.Map;

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
	private final Map<Menu, Integer> menus;
	private final String address;
	private final Restaurant restaurant;
	private int size;
	private double price;	
	
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
		this.menus = new HashMap<>();	
	}
	
	@Override
	public Map<Menu, Integer> getMenus() {		
		return this.menus;
	}
	
	@Override
	public void addMenu(Menu m) {
		if (menus.containsKey(m))
			this.menus.put(m, menus.get(m)+1);
		else
			this.menus.put(m, 1);
	}

	@Override
	public boolean removeMenu(Menu m) {
		if (menus.containsKey(m) && menus.get(m)>0) {
			this.menus.put(m, menus.get(m)-1);
			if (menus.get(m)==0)
				menus.remove(m);
			return true;
		}
		// TODO Gestione errore
		return false;
	}
	
	@Override
	public void removeAllMenus() {
		if(!this.getMenus().isEmpty()) {
			this.menus.clear();
		}	
	}

	@Override
	public int getOrderSize() {
		this.size = 0;
		this.menus.entrySet().forEach(m->size += m.getKey().getSize()*m.getValue());
		return size;
	}
	
	// TODO cambiare il tipo di price per gestire meglio la valuta
	@Override
	public double getOrderPrice() {
		this.price = 0;
		this.menus.entrySet().forEach(m->price += m.getKey().getPrice()*m.getValue());
		return price;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}


	/*
	@Override
	public int incrementalMenu(String menuName) {
		return menus.stream()
				.filter(m -> m.getName().equals(menuName))
				.findFirst()
				.get()
				.getQuantityPlus();
	}
	
	@Override
	public int decrementalMenu(String menuName) {
		return menus.stream()
				.filter(m -> m.getName().equals(menuName))
				.filter(m -> m.getQuantity() >= 0)
				.findFirst()
				.get()
				.getQuantityMinus();
	} */

	@Override
	public String showOrderInfo() {
		StringBuilder sb = new StringBuilder(); // TODO forse bastava String
		this.menus.entrySet()
			.forEach(m -> sb.append(m.getValue() + "x " + m.getKey().showMenuInfo()+'\n'));					
		return sb.toString();
	}
}
