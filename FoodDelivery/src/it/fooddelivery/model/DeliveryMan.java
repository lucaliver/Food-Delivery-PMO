/**
 * @author Giulia Costa
 */

package it.fooddelivery.model;

import java.util.List;

// Giulia Costa
public interface DeliveryMan {
	
	List<Order> orderInBag();
	
	void addOrder(Order o);
	void deliverOrder(Order o);				// rimuove dallo zaino e aggiunge il guadagno --> libero!!
	
	boolean isFull();

	String getName();
	double getProfit();

}