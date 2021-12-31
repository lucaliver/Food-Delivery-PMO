/**
 /* @author Giulia Costa
 */

package it.fooddelivery.model.implementation;

import java.util.ArrayList;
import java.util.List;

import it.fooddelivery.model.DeliveryMan;
import it.fooddelivery.model.Order;

public class DeliveryManImpl implements DeliveryMan{
	
	static final int MAX_CAPACITY = 100;
	
	private double profit;
	private final String name;
	private List<Order> orderList;
		
	public DeliveryManImpl(double p, String n) {
		this.profit = p;
		this.name = n;
		this.orderList = new ArrayList<>();
	}

	@Override
	public List<Order> orderInBag() {
		return orderList;
	}

	@Override
	public void addOrder(Order o) {
		orderList.add(o);
	}

	//aggiunto il prezzo dell'ordine consegnato
	public double totalProfit(Order o) {
		return profit += o.totalPrice();
	}
	
	// eliminato l'ordine consegnato dalla lista precedente
	public List<Order> removeToBag(Order o){
		orderList.remove(o);
		return orderList;
	}
	
	@Override
	public void deliverOrder(Order o) {
		removeToBag(o);
		totalProfit(o);
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
	
	public void printProfit(double p) {
		if(p > 0)
			System.out.println(getProfit());
		else
			System.out.println("ERRORE! Valore negativo!");
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public static int getMaxCapacity() {
		return MAX_CAPACITY;
	}
}