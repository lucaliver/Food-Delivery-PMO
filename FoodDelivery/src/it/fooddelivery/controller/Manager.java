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
 * Controller of the Food Delivery application. Controller component in the MVC pattern.
 */
public class Manager{
	//TODO Forse applicare il Singleton al controller?
	private Map<String, Rider> riders;

	private List<Order> waitingOrders;	
	private List<Restaurant> restaurants;
	private Optional<Order> currentOrder;
	private Optional<Rider> riderWithLastOrder;
	private int sequentialIdCounter;
	
	/**
	 * Constructs a Manager with the specified riders and restaurants.
	 * 
	 * @param riders list of all riders
	 * @param restaurants list of all restaurants
	 */
	public Manager(Map<String, Rider> riders, List <Restaurant> restaurants){
		this.riders = riders;
		this.restaurants = restaurants;
		this.waitingOrders = new ArrayList<>();
		this.currentOrder = null;
		this.riderWithLastOrder = Optional.empty();
		this.sequentialIdCounter = 0;
	}
	
	/**
	 * Checks if the order can be assigned.
	 * 
	 * @param order the order to check
	 * @return an Optional Rider if there is one that can take the order, empty if not
	 */
	public Optional<Rider> canBeAssigned(Order order){
		this.riderWithLastOrder = this.riders
					.values()
					.stream()
					.filter(x->x.getCities().contains(order.getDestination()))
					.filter(x->x.canFit(order))	
					.sorted((o1, o2)->{return (int)(o1.getProfit()-o2.getProfit());})	
					.findFirst();
		return riderWithLastOrder;
	}
			
	/**
	* Tries to assign the order to a rider that delivers the order's city.
	* He must have enough bag space, and the rider with the lowest profit has the priority.
	* If no Rider can receive the Order, it will be put into the waiting list.
	* 
	* @param order the order to assign
	* @return {@code true} if assigned to a Rider, {@code false} if put into the waiting list
	*/
	public boolean assignOrder(Order order) {
		if(this.canBeAssigned(order).isPresent()) { 
			riderWithLastOrder.get().addOrder(order);
			return true;
		}else {
			if(!this.waitingOrders.contains(order)) {
				this.waitingOrders.add(order);
			}
			return false;
		}
	}
	
	/**
	 * Creates a new current order.
	 * 
	 * @param destination destination of the order
	 * @param address address of the order
	 * @param restuarant restaurant of the order
	 */
	public void createOrder(City destination, String address, Restaurant restaurant) {
		this.sequentialIdCounter += 1;
		this.currentOrder = Optional.of(new OrderImpl(this.sequentialIdCounter, destination, address, restaurant)); 
	}
	
	/**
	 * Assigns the current order (to a rider or to waiting list) and empties the current order slot.
	 * 
	 * @return {@code true} if assigned to a Rider, {@code false} if put into the waiting list
	 */
	public boolean assignCurrentOrder() {
		boolean result = this.assignOrder(this.currentOrder.get());
		this.currentOrder = null;
		return result;
	}
	
	/**
	 * Returns a String with info about the orders in the waiting list.
	 * 
	 * @return String with info about the orders in the waiting list
	 */
	public String showWaitingOrders() {
		StringBuilder sb = new StringBuilder();
		this.waitingOrders.forEach(o -> sb.append(o.showInfoForRider()+o.showOrderContent()));
		return sb.toString();
	}
	
	/**
	 * Returns the quantity of a specific menu in the current order.
	 * 
	 * @param menu menu to look for
	 * @return quantity of that menu
	 */
	public int howManyInCurrent(Menu menu) {
		if (this.currentOrder.get().getMenus().containsKey(menu))
			return this.currentOrder.get().getMenus().get(menu);
		else return 0;
	}
	
	/**
	 * Tries to assign the orders in the waiting list to the Rider.
	 * 
	 * @param freeRider Rider that we are checking
	 * @return {@code true} if at least one new order was given to the Rider
	 */
	public boolean refreshWaitingOrder(Rider freeRider) {
		List<Order> noMoreWaitingOrders = new ArrayList<Order>(); 
		this.getWaitingOrders().forEach(o ->{	
			if(this.canBeAssigned(o).isPresent())
				if(this.canBeAssigned(o).get().equals(freeRider)) {
				noMoreWaitingOrders.add(o);
				freeRider.addOrder(o);
			}
		});
		this.waitingOrders.removeAll(noMoreWaitingOrders);
		return(noMoreWaitingOrders.size()>=1);
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

	public Optional<Rider> getRiderWithLastOrder(){
		return this.riderWithLastOrder;
	}

	public void setRiders(Map<String, Rider> riders) {
		this.riders = riders;
	}

	public Optional<Order> getCurrentOrder() {
		return currentOrder;
		// TODO se facciamo return currentOrder.get()????
		// In teoria prendiamo l'ordine corrente sempre quando è già stato creato
		// Metterci un lancio di eccezione forse?
	}
	
	public List<Order> getWaitingOrders() {
		return waitingOrders;
	}
	
	public void addToCurrent(Menu menu) {
		this.currentOrder.get().addMenu(menu);
	}

	public boolean removeFromCurrent(Menu menu) {
		return this.currentOrder.get().removeMenu(menu);
	}
}
