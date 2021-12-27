/**
 * @author Giulia Costa
 */

package it.fooddelivery.model.implementation;

import java.util.ArrayList;
import java.util.List;

import it.fooddelivery.model.DeliveryMan;
import it.fooddelivery.model.Order;

/**
 * A class that represents a delivery man.
 *
 */
public class DeliveryManImpl implements DeliveryMan {
	private List<Order> ordersInBag;
	private double money;
	private int bagCapacity;
	
	private static final int MAXCAPACITY = 100;
	
	/**
	 * Constructor.
	 */
	public DeliveryManImpl() {
		this.ordersInBag = new ArrayList<>();
		this.money = 0;
	}


	@Override
	public List<Order> bag() {
		return this.getOrdersInBag();
	}

	@Override
	public boolean addOrder(Order o) {
		this.ordersInBag.add(o);
		
		//TODO Gestione errori e ritorno dell'esito dell'operazione.
		return true;
	}

	@Override
	public boolean deliverOrder(Order o) {
		this.ordersInBag.remove(o);
		this.money += o.totalPrice()/10;
		
		//TODO Gestione errori e ritorno dell'esito dell'operazione.
		return true;
	}

	@Override
	public double profit() {
		return this.money;
	}

	@Override
	public int capacity() {
		return this.bagCapacity;
	}

	@Override
	public boolean isFull() {
		if (this.bagCapacity == this.MAXCAPACITY)
			return true;
		else 
			return false;
	}


	public List<Order> getOrdersInBag() {
		return ordersInBag;
	}


	public void setOrdersInBag(List<Order> ordersInBag) {
		this.ordersInBag = ordersInBag;
	}
}
