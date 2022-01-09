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
	private final String address;
	private final Restaurant restaurant;
	private int quantity;
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
		this.size = 0;
		this.price = 0;
		this.menus = new ArrayList<>();	
	}
	
	
	@Override
	public List<Menu> getMenus() {		
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
	public void removeAllMenus() {
		if(!this.getMenus().isEmpty()) {	
			this.getMenus().removeAll(menus);
		}		
	}

	@Override
	public int getOrderSize() {
		this.menus.forEach(m -> size += m.getSize());
		return size;
	}
	
	// TODO cambiare il tipo di price per gestire meglio la valuta
	@Override
	public double getOrderPrice() {
		this.menus.forEach(m -> price += m.getPrice());
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

	public int menuQuantity(String menuName) {
		this.quantity = 0;
		for(Menu m : this.menus) {
			if(menuName.equals(m.getName())) {
				quantity++;
			}
		}
		return quantity;
	}


	@Override
	public String showOrderInfo() {	
		StringBuilder sb = new StringBuilder();
		this.menus.forEach(m -> sb.append('\t'+m.showMenuInfo()));					
		return sb.toString();
	}			
}
