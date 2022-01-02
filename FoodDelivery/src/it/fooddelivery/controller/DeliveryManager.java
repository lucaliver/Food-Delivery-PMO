/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.fooddelivery.model.Rider;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.Restaurant;

/**
 * Controller of a food delivery management software.
 *
 */
public class DeliveryManager {
	//TODO Forse applicare il design pattern Singleton al controller.
	
	private List<Rider> riders;
	private List<Order> waitingOrders;	
	private List<Restaurant> restaurants;
	
	/**
	 * The constructor inizializes all the lists.
	 */
	public DeliveryManager(){
		this.waitingOrders = new ArrayList<>();
		this.riders = new ArrayList<>();
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
		Optional<Rider> selected = this.riders.stream()
		.filter(x->x.getCities().contains(order.getDestination()))
		.filter(x->x.canFit(order))	
		.sorted((o1, o2)->{return (int) (o1.getProfit() - o2.getProfit());})	
		.findFirst();
		
		if (selected.isPresent()) {
			selected.get().addOrder(order);
			return true;
		}
		else {
			this.waitingOrders.add(order);
			return false;
		}
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
