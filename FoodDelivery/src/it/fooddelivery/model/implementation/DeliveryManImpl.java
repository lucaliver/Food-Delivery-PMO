/**
 /* @author Giulia Costa
 */

package it.fooddelivery.model.implementation;

import java.util.ArrayList;
import java.util.List;

import it.fooddelivery.model.DeliveryMan;
import it.fooddelivery.model.Order;

public class DeliveryManImpl implements DeliveryMan{
	private int capacity;
	private double profit;
	private List<Order> orderList;
	
	public DeliveryManImpl(final int capacity) {
		this.profit = 0;
		this.capacity = capacity;
		orderList = new ArrayList<>();
	}

	@Override
	public double profit() {
		return profit;
	}

	@Override
	public List<Order> orderInBag() {
		return orderList;
	}

	@Override
	public void addOrder(Order o) {
		orderList.add(o);
	}

	@Override
	public void deliverOrder(Order o) {
		// eliminato l'ordine consegnato dalla lista precedente
		orderList.remove(o);	
		// aggiunto il guadagno al fattorino
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@Override
	public boolean isFull() {
		return orderList.size() == capacity;
	}

}
