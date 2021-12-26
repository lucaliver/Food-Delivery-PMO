/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

import java.util.ArrayList;
import java.util.List;

import it.fooddelivery.model.Order;
import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.Zone;

/**
 * Controller of a food delivery management software.
 *
 */
public class DeliveryManager {
	private List<Order> waitingOrders = new ArrayList<>();
	private List<Zone> zones = new ArrayList<>();
	private List<Restaurant> restaurants = new ArrayList<>();
	
	/**
	 * The constructor inizializes all the lists.
	 */
	public DeliveryManager(){
		this.waitingOrders = new ArrayList<>();
		this.zones = new ArrayList<>();
		this.restaurants = new ArrayList<>();
	}
	
	/**
	 * It tries to assign the order to a delivery man of the correct city.
	 * He needs to have enough bag space.
	 * It select the delivery man with the lower profit.
	 * @param order is the order to assign
	 * @return true if assigned to a delivery man,
	 * false if the order was put into the waiting list.
	 */
	public boolean assignOrder(Order order) {
		//TODO implementarla
		// Cercare la zona corrispondente a order.destination()
		// Ottenere la lista di deliveryMen di quella città
		// Filtrare quelli con spazio sufficente all'order.size()
		// Sort per il guadagno crescente
		// Prendo il primo fattorino
		
		// Assegno l'ordine a quel fattorino
		
		// Se non c'è un fattorino, aggiungo l'ordine a waitingOrders
		
		return false;
	}

	public List<Order> getWaitingOrders() {
		return waitingOrders;
	}

	public void setWaitingOrders(List<Order> waitingOrders) {
		this.waitingOrders = waitingOrders;
	}

	public List<Zone> getZones() {
		return zones;
	}

	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	
}
