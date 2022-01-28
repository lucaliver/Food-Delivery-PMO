/**
 * @author Giulia Costa
 */

package it.fooddelivery.model;

import java.util.List;

/**
 * Interface to represent a rider.
 */
public interface Rider {
	
	/** 
	 * @return list of all the orders in the rider's bag.
	 */
	List<Order> getBag();
	
	/**
	 * It adds the order to the rider's bag.
	 * @param o = order to add.
	 */
	void addOrder(Order o);
	
	/**
	 * Consegna l'ordine rimuovendolo dallo zaino e aggiungendo il guadagno al fattorino.
	 * @param o = ordine da consegnare.
	 */
	void deliverOrder(Order o);	
	
	/**
	 * It delivers every order of this rider.
	 */
	void deliverAll();
	
	/**
	 * @return true se la bag è piena, altrimenti false.
	 */
	boolean isFull();

	/**
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
	
	/**
	 * 
	 * @return the capacity in that moment
	 */
	int getCapacity();
	
	/**
	 * 
	 * @return all the of this rider info in that moment
	 */
	String showRiderInfo();
	
	/**
	 * 
	 * @return the content of the rider's bag
	 */
	String showBagInfo();
	
	/**
	 * 
	 * @return the bag profit of the rider.
	 */
	double getBagProfit();
	
}