/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.fooddelivery.model.DeliveryMan;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.ZoneType;
import it.fooddelivery.model.implementation.DeliveryManImpl;

/**
 * Controller of a food delivery management software.
 *
 */
public class DeliveryManager {
	//TODO Forse applicare il design pattern Singleton al controller, tanto ce ne deve essere uno solo.

	//private List<Zone> zones;
	private Map<ZoneType, List<DeliveryMan>> deliveryMap;

	private List<Order> waitingOrders = new ArrayList<>();	
	private List<Restaurant> restaurants;
	
	/**
	 * The constructor inizializes all the lists.
	 */
	public DeliveryManager(){
		this.waitingOrders = new ArrayList<>();
		//this.zones = new ArrayList<>();
		this.deliveryMap = new HashMap<>();
		this.restaurants = new ArrayList<>();
		
		//DA RIMUOVERE, STO SOLO PROVANDO UNA COSA
		List<DeliveryMan> listaProva = new ArrayList<>();
		listaProva.add(new DeliveryManImpl(0, "Bogliolo"));
		this.deliveryMap.put(ZoneType.URBANIA, listaProva);
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
		/*
		Optional<DeliveryMan> selected = this.deliveryMap.get(order.getDestination()).stream() 
		.filter(x->x.capacity()>=order.getSize())	
		.sorted((o1, o2)->{return (int) (o1.profit() - o2.profit());})	
		.findFirst();
		
		
		if (selected.isPresent()) {
			selected.get().addOrder(order);
			return true;
		}
		else {
			this.waitingOrders.add(order);
			return false;
		}
		*/
		return true;
	}

	public List<Order> getWaitingOrders() {
		return waitingOrders;
	}

	public void setWaitingOrders(List<Order> waitingOrders) {
		this.waitingOrders = waitingOrders;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	
}
