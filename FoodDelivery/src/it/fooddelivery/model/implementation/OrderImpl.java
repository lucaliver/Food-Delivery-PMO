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
	
	private final int idOrder;
	private final City destination;
	private final Map<Menu, Integer> menus;
	private final String address;
	private final Restaurant restaurant;
	private int size;
	private double price;	
	
	/**
	 * Constructor.
	 * @param id = id of the order
	 * @param destination = destination of the order
	 * @param address = address of the order
	 * @param restaurant = resturant that made the order
	 */
	public OrderImpl(int id, City destination, String address, Restaurant restaurant) {		
		this.idOrder = id;
		this.destination = destination;
		this.address = address;
		this.restaurant = restaurant;
		this.menus = new HashMap<>();
		this.price = 0;
		this.size = 0;
	}
	
	@Override
	public void addMenu(Menu m) {
		if (menus.containsKey(m))
			this.menus.put(m, menus.get(m)+1);
		else
			this.menus.put(m, 1);
		this.size += m.getSize();
		this.price += m.getPrice();
	}

	@Override
	public boolean removeMenu(Menu m) {
		if (menus.containsKey(m) && menus.get(m)>0) {
			this.menus.put(m, menus.get(m)-1);
			this.size -= m.getSize();
			this.price -= m.getPrice();
			if (menus.get(m)==0)
				menus.remove(m);
			return true;
		}
		return false;
	}
	
	@Override
	public void removeAllMenus() {
		if(!this.getMenus().isEmpty()) {
			this.menus.clear();
			this.size = 0;
			this.price = 0;
		}	
	}
	
	@Override
	public String showInfoForRider() {
		return new String("[ID: "+String.format("%03d", this.getId())+"] "
		                  +this.getDestination()+", "+this.getAdress()+'\n');
	}
	
	@Override
	public String showOrderContent() {
		StringBuilder sb = new StringBuilder(); 
		this.menus.entrySet()
			.forEach(m -> sb.append(m.getValue() + "x " + m.getKey().showMenuInfo()+'\n'));	
		return sb.toString();
	}
	
	@Override
	public String showOrderInfo() {
		return "Destinazione: " + this.getDestination() + ", "+ this.getAdress()
				+ " \nRistorante: " + this.getRestaurant()
				+ " \nTotale: " + String.format("%.2f",this.getPrice())
				+ "€ \nDimensione: " + this.getSize();
	}

	@Override
	public Map<Menu, Integer> getMenus() {		
		return this.menus;
	}
	
	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public int getId() {
		return this.idOrder;
	}
	@Override
	public City getDestination() {
		return this.destination;		
	}
	
	@Override
	public String getAdress() {
		return address;
	}
	
	@Override
	public Restaurant getRestaurant() {
		return restaurant;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof OrderImpl)) {
			return false;
		}
		OrderImpl other = (OrderImpl) obj;
		if (this.idOrder != other.getId()) {
			return false;
		}
		return true;
	}
}
