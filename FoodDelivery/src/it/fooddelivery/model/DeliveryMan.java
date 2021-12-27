/**
 * @author Giulia Costa
 */

package it.fooddelivery.model;

import java.util.List;

/**
 * An interface to represent a delivery man.
 *
 */
public interface DeliveryMan {
	
	/**
	 * 
	 * @return
	 */
	List<Order> bag();
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	boolean addOrder(Order o);
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	boolean deliverOrder(Order o);	// rimuove dallo zaino e aggiunge il guadagno --> libero!!
	
	/**
	 * 
	 * @return
	 */
	double profit();			// incasso totale, viene usata per controllare il fattorino che ha guadagnato di meno
	
	/**
	 * 
	 * @return
	 */
	int capacity();
	
	/**
	 * 
	 * @return
	 */
	boolean isFull();
}
