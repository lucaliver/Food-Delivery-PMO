package it.fooddelivery.model.implementation;

import java.util.List;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;

public class OrderImpl implements Order{

	// Campi
	private final String idOrder;
	private final String destination;
	private final List<Menu> menus;
	
	// Costruttore
	public OrderImpl(String id, String dest) {		
		this.idOrder = id;
		this.destination = dest;
		this.menus = null;
	}
	
	
	@Override
	public List<Menu> menusList() {		
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
	public int getSize() {
		int s = for(Menu m :)
	}

	@Override
	public double totalPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getIdOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getDestination() {
		// TODO Auto-generated method stub
		
	}

}
