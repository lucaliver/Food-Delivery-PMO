/**
 * @author Giacomo Tombari 
 */

package it.fooddelivery.model.implementation;

import java.util.HashMap;
import java.util.Map;

import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.Restaurant;

/**
 * A class to represent an order.  Implementation of {@link Order}.
 */
public class OrderImpl implements Order{
	
	private final int id;
	private final CityImpl destination;
	private final Map<Menu, Integer> menus;
	private final String address;
	private final Restaurant restaurant;
	
	/**
	 * Constructs an order.
	 * 
	 * @param id id of this order
	 * @param destination destination of this order
	 * @param address address of this order
	 * @param restaurant resturant that is making this order
	 */
	public OrderImpl(int id, CityImpl destination, String address, Restaurant restaurant) {		
		this.id = id;
		this.destination = destination;
		this.address = address;
		this.restaurant = restaurant;
		this.menus = new HashMap<>();
	}
	
	@Override
	public void increaseMenu(Menu menu) {
		// Se la mappa contiene già tale chiave incrementiamo il suo valore 
		if (menus.containsKey(menu))
			this.menus.put(menu, menus.get(menu)+1);
		// Altrimenti inseriamo una nuova chiave con valore 1
		else
			this.menus.put(menu, 1);
	}

	@Override
	public void decreaseMenu(Menu menu) {
		// Se la mappa contiene tale chiave e il suo valore è > 0 lo decrementiamo 
		if (menus.containsKey(menu) && menus.get(menu)>0) {
			this.menus.put(menu, menus.get(menu)-1);
			// Se dopo il decremento il valore è pari a 0 rimuoviamo la chiave
			if (menus.get(menu)==0)
				menus.remove(menu);
		}
	}
	
	@Override
	public void removeAllMenus() {
		if(!this.getMenus().isEmpty()) {
			this.menus.clear();
		}	
	}
	
	@Override
	public String showBasicInfo() {
		return "[ID: "+String.format("%03d", this.getId())+"] "
				+this.getDestination()+", "+this.getAdress()+", "
		        +String.format("%.2f", this.getPrice()) + "€\n";
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
				+ "€ \nDimensione: " + this.getSize() + "u";
	}

	@Override
	public Map<Menu, Integer> getMenus() {		
		return this.menus;
	}
	
	@Override
	public int getSize() {
		return this.menus
			.entrySet()
			.stream()
			.mapToInt(m -> m.getKey().getSize()*m.getValue())
			.sum();	   
	}
	
	@Override
	public double getPrice() {
		return this.menus
			.entrySet()
			.stream()
			.mapToDouble(m -> m.getKey().getPrice()*m.getValue())
			.sum();
	}

	@Override
	public int getId() {
		return this.id;
	}
	@Override
	public CityImpl getDestination() {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof OrderImpl))
			return false;
		OrderImpl other = (OrderImpl) obj;
		if (id != other.id)
			return false;
		return true;
	}
}