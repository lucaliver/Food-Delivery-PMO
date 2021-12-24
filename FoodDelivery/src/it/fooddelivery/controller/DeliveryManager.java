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
 * Classe per il controller del programma di gestione del delivery.
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
	 * Assegna l'ordine secondo a un fattorino di quella città,
	 * col guadagno minore e con abbastanza spazio in zaino.
	 * @param order è l'ordine da assegnare
	 * @return vero se assegnata a fattorino,
	 * falso se messa in lista d'attesa
	 */
	public boolean assignOrder(Order order) {
		//TODO implementarla
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
