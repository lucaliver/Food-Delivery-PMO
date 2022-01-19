/**
 * @author Giacomo Tombari 
 */

package it.fooddelivery.model.implementation;

import java.text.DecimalFormat;
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
	private static final DecimalFormat df = new DecimalFormat("0.00"); //Per i centesimi
	
	private final int idOrder;
	private final City destination;
	private Map<Menu, Integer> menus;
	private final String address;
	private final Restaurant restaurant;
	private int size;
	private double price;	
	
	/**
	 * Constructor.
	 * @param id
	 * @param destination
	 * @param address
	 * @param restaurant
	 */
	public OrderImpl(int id, City destination, String address, Restaurant restaurant) {		
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
		this.menus.entrySet().forEach(m -> size += m.getKey().getSize()*m.getValue());
		return size;
	}
	
	@Override
	public double getOrderPrice() {
		this.price = 0;
		this.menus.entrySet().forEach(m -> price += m.getKey().getPrice()*m.getValue());
		return price;
	}

	@Override
	public int getIdOrder() {
		return this.idOrder;
	}
	
	@Override
	public String printIdOrder() {
		return new String("[Ordine " + String.format("%03d", this.getIdOrder()) + "]: \n");
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
	public String showOrderContent() {
		StringBuilder sb = new StringBuilder(); 
		this.menus.entrySet()
			.forEach(m -> sb.append(m.getValue() + "x " + m.getKey().showMenuInfo()+'\n'));	
		return sb.toString();
	}
	

	@Override
	public String showOrderInfo() {
		return "Destinazione: " + this.getDestination() + ", "
				+ this.getAdress() + "  " + '\n'
				+ "Ristorante: " + this.getRestaurant() + '\n'
				+ "Totale: " + df.format(this.getOrderPrice()) + "€" + '\n'
				//+ "Dimensione: " + this.controller.getCurrentOrder().getSize()
				;
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
		if (this.idOrder != other.getIdOrder()) {
			return false;
		}
		return true;
	}
}
