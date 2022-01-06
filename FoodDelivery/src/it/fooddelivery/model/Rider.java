/**
 * @author Giulia Costa
 */

package it.fooddelivery.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface to represent a rider.
 */
public interface Rider {
	
	/** 
	 * @return lista di tutti gli ordini nella bag del fattorino.
	 */
	List<Order> getBag();
	
	/**
	 * @param o = ordine da aggiungere alla bag.
	 */
	void addOrder(Order o);
	
	/**
	 * Consegna l'ordine rimuovendolo dallo zaino e aggiungendo il guadagno al fattorino.
	 * @param o = ordine da consegnare.
	 */
	void deliverOrder(Order o);				// rimuove dallo zaino e aggiunge il guadagno --> libero!!
	
	/**
	 * It delivers every order of this rider.
	 */
	void deliverAll();
	
	/**
	 * @return true se la bag è piena, altrimenti false.
	 */
	boolean isFull();

	/**
	 * 
	 * @return il nome del fattorino.
	 */
	String getName();
	
	/** 
	 * @return l'attuale guadagno del fattorino.
	 */
	double getProfit();
	
	/**
	 * @param o = ordine da controllare se entra in bag.
	 * @return true se ci entra, altrimenti false.
	 */
	boolean canFit(Order o);
	
	/**
	 * @return lista delle città in cui il fattorino consegna.
	 */
	List<City> getCities();
	
	int getCapacity();

	 

}