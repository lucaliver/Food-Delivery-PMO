/**
 * @author Giulia Costa
 */

package it.fooddelivery.model;

import java.util.List;

// Giulia Costa
public interface DeliveryMan {
	/** 
	 * 
	 * @return lista degli ordini nello zaino del fattorino
	 */
	List<Order> getBag();
	
	/**
	 * 
	 * @param o = ordine da aggiungere alla bag
	 */
	void addOrder(Order o);
	
	/**
	 * Consegna l'ordine rimuovendolo dallo zaino e aggiungendo il guadagno al profitto personale
	 * @param o = ordine da consegnare
	 */
	void deliverOrder(Order o);				// rimuove dallo zaino e aggiunge il guadagno --> libero!!
	
	/**
	 * 
	 * @return true se la bag è piena
	 */
	boolean isFull();

	/**
	 * 
	 * @return il nome del fattorino
	 */
	String getName();
	
	/** 
	 * 
	 * @return l'attuale guadagno
	 */
	double getProfit();
	
	/**
	 * 
	 * @param o = ordine da controllare se entra in bag
	 * @return true se entra l'ordine 
	 */
	boolean canFit(Order o);

}