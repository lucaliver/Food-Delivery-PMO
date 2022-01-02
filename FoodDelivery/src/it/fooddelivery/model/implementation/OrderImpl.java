package it.fooddelivery.model.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.ZoneType;

public class OrderImpl implements Order{

	// Campi
	private final String idOrder;
	private final ZoneType destination;
	private final List<Menu> menus;
	
	// Costruttore
	public OrderImpl(String id, ZoneType dest) {		
		this.idOrder = id;
		this.destination = dest;
		this.menus = new ArrayList<>();	
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
	public void removeAllMenu() {
		if(!this.menusList().isEmpty()) {	
			this.menusList().removeAll(menus);
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
	public double totalPrice() {
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
	public ZoneType getDestination() {
		return this.destination;		
	}


	

}
