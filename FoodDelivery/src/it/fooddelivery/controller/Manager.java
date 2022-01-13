/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.fooddelivery.model.Rider;
import it.fooddelivery.model.implementation.MenuImpl;
import it.fooddelivery.model.implementation.OrderImpl;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.Restaurant;

/**
 * Controller of a food delivery management software.
 *
 */
public class Manager{
	//TODO Forse applicare il Singleton al controller.
	
	private Map<String, Rider> riders;

	private List<Order> waitingOrders;	
	private List<Restaurant> restaurants;
	private Order currentOrder;
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
		this.currentOrder = null;
		this.riderWithLastOrder = Optional.empty();
	}
	
	/**
	 * It tries to assign the order to a delivery man of the correct city.
	 * He needs to have enough bag space. It select the delivery man with the lower profit.
	 * @param order is the order to assign
	 * @return true if assigned to a delivery man, false if put into the waiting list.
	 */
	public boolean assignOrder(Order order) {
		Optional<Rider> selected = this.riders
				.values()
				.stream()
				.filter(x->x.getCities().contains(order.getDestination()))
				.filter(x->x.canFit(order))	
				.sorted((o1, o2)->{return (int)(o1.getProfit()-o2.getProfit());})	
				.findFirst();
		this.riderWithLastOrder = selected;
		if(selected.isPresent()) {
			System.out.println("[DEBUG] Rider selezionato: " + selected.get().getName()); //DEBUG
			selected.get().addOrder(order);
			//TODO mostra come viene stampato il contenuto della bag di un rider, da rimuovere in seguito
			System.out.println(selected.get().showBagInfo());
			return true;
		}else {
			System.out.println("Nessun rider disponibile, ordine in attesa!!!"); //DEBUG
			this.waitingOrders.add(order);
			System.out.println(this.showWaitingOrder());
			return false;
		}
	}
	
	
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
	
	public String showWaitingOrder() {
		StringBuilder sb = new StringBuilder();
		this.waitingOrders.forEach(o -> sb.append(this.waitingOrders.indexOf(o)+1+"# - "+o.showOrderInfo()));
		return sb.toString();
	}
	
	public void addToCurrent(Menu menu) {
		this.currentOrder.addMenu(menu);
	}

	public void removeFromCurrent(Menu menu) {
		this.currentOrder.removeMenu(menu);
	}
}
