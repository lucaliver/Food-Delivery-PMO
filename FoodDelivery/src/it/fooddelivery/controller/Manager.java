/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.fooddelivery.model.Rider;
import it.fooddelivery.model.implementation.OrderImpl;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.Restaurant;

/**
 * Controller of a food delivery management software.
 *
 */
public class Manager {
	//TODO Forse applicare il design pattern Singleton al controller.
	
	private Map<String, Rider> riders;

	private List<Order> waitingOrders;	
	private List<Restaurant> restaurants;
	private Order currentOrder; 	//TODO Forse dovrebbe essere optional
	private Optional<Rider> riderWithLastOrder;
	
	/**
	 * Constructor.
	 * @param riders = list of all riders.
	 * @param restaurants = list of all restaurants.
	 */
	public Manager(Map<String, Rider> riders, List <Restaurant> restaurants){
		this.riders = riders;
		this.restaurants = restaurants;
		this.waitingOrders = new ArrayList<>();
		Optional.empty();
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
		Optional<Rider> selected = this.riders
				.values()
				.stream()
				.filter(x->x.getCities().contains(order.getDestination()))
				.filter(x->x.canFit(order))	
				.sorted((o1, o2)->{return (int) (o1.getProfit() - o2.getProfit());})	
				.findFirst();
		riderWithLastOrder = selected;
		if(selected.isPresent()) {
			System.out.println("Rider selezionato: " + selected.get().getName());
			selected.get().addOrder(order);
			return true;
		}else { 
			// 1# - HO CAMBIATO UN PO ALTRIMENTI DAVA ERRORE SE SELECTED ERA NULL: GIACOMO
			System.out.println("Nessun rider disponibile, ordine in attesa!!!");
			this.waitingOrders.add(order);
			return false;
		}
	}
	/* Metodo cge ritorna il possibile nome del fattorino */
	public Optional<Rider> getRiderWithLastOrder(){
		return this.riderWithLastOrder;
	}
	
	/**
	 * It creates a new current order.
	 * @param destination.
	 * @param address.
	 * @param restuarant.
	 */
	public void createOrder(City destination, String address, Restaurant restaurant) {
		//TODO Creare generatore ID
		this.currentOrder = new OrderImpl("01", destination, address, restaurant); 
	}
	
	/**
	 * It assigns the current order (to a rider or to waiting list) 
	 * and empties the current order slot.
	 */
	public void placeCurrentOrder() {
		this.assignOrder(this.currentOrder);
		this.currentOrder = null;
	}
	

	public Order getCurrentOrder() {
		return currentOrder;
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
	
	public Map<String, Rider> getRiders() {
		return riders;
	}

	public void setRiders(Map<String, Rider> riders) {
		this.riders = riders;
	}
	
}
