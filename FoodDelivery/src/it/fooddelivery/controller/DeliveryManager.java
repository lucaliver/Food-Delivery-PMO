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
 * Classe per il controller del programma di delivery.
 *
 */
public class DeliveryManager {
	private List<Order> waitingOrders = new ArrayList<>();
	private List<Zone> zones = new ArrayList<>();
	private List<Restaurant> restaurants = new ArrayList<>();
	
	/**
	 * Costruttore, inizializza i campi.
	 */
	public DeliveryManager(){
		this.waitingOrders = new ArrayList<>();
		this.zones = new ArrayList<>();
		this.restaurants = new ArrayList<>();
	}
	
	/**
	 * Assegna l'ordine a un fattorino di quella città,
	 * con abbastanza spazio in zaino e col guadagno minore.
	 * @param order è l'ordine da assegnare
	 * @return vero se assegnata a fattorino,
	 * falso se messa in lista d'attesa
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
