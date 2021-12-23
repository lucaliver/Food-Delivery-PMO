package it.fooddelivery.model;

import java.util.List;

// Interfaccia implementa da G.T.

public interface Order {
	
	public List<Menu> menus();
	public void addMenu();
	public void removeMenu();
	public int size();
	public double totalPrice();
	public void destination();
	
	
}
