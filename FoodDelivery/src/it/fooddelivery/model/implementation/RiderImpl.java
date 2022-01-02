/**
 /* @author Giulia Costa
 */

package it.fooddelivery.model.implementation;

import java.util.ArrayList;
import java.util.List;

import it.fooddelivery.model.Rider;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Order;

public class RiderImpl implements Rider{
	
	static final int MAX_CAPACITY = 100;
	static final double PERCENTAGE = 0.20;
	
	private double profit;
	private final String name;
	private int capacity;
	private List<Order> orderList;
	private List<City> cities;
		
	public RiderImpl(String n, List<City> c) {
		this.profit = 0;
		this.capacity = 0;
		this.name = n;
		this.cities = c;
		this.orderList = new ArrayList<>();
	}
	
	@Override
	public List<Order> getBag() {
		return orderList;
	}

	@Override
	public void addOrder(Order o) {
		orderList.add(o);
	}
	
	@Override
	public void deliverOrder(Order o) {
		orderList.remove(o);
		addProfit(o);
	}

	@Override
	public boolean isFull() {
		return orderList.size() == MAX_CAPACITY;
	}
	
	// restituisce il profitto totale di ogni fatttorino
	@Override
	public double getProfit() {
		return profit;
	}
	
	/*public void printProfit(double p) {
		if(p > 0)
			System.out.println(getProfit());
		else
			System.out.println("ERRORE! Valore negativo!");
	}*/
	
	@Override
	public String getName() {
		return name;
	}
		
	@Override
	public boolean canFit(Order o) {		// ci entra nello zaino?
		return (this.capacity + o.getSize()) <= MAX_CAPACITY;
	}
	
	@Override
	public List<City> getCities() {
		return cities;
	}
	
	public static int getMaxCapacity() {
		return MAX_CAPACITY;
	}

	/**
	 *  
	 * @param o viene aggiunto il profitto al fattorino per ogni ordine 
	 */
	private void addProfit(Order o) {
		this.profit += o.totalPrice() * PERCENTAGE;
	}
}