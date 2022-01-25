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
 */
public class Manager{
	//TODO Forse applicare il Singleton al controller.
	private Map<String, Rider> riders;

	private List<Order> waitingOrders;	
	private List<Restaurant> restaurants;
	private Order currentOrder;
	private Optional<Rider> riderWithLastOrder;
	private int counterID;
	
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
		this.counterID = 0;
	}
	
	/**
	 * Check if a order can be assigned.
	 * @param order to check.
	 * @return an optional rider if there is one that can take the order, empty if not.
	 */
	public Optional<Rider> canBeAssign(Order order){
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
	* It tries to assign the order to a delivery man of the correct city.
	* He needs to have enough bag space. It select the delivery man with the lower profit.
	* @param order is the order to assign
	* @return true if assigned to a rider, false if put into the waiting list.
	*/
	public boolean assignOrder(Order order) {
		//System.out.println("[1-DEBUG manager] L'ordine da assegnare " + order.getIdOrder() + " ha tot menu: " + order.getMenus().size());
		if(this.canBeAssign(order).isPresent()) { 
			riderWithLastOrder.get().addOrder(order);
			return true;
		}else {
			if(!this.waitingOrders.contains(order)) {
				this.waitingOrders.add(order);
				System.out.println("[DEBUG Manager] L'ordine " + order.getIdOrder() + " è stato messo in attesa.");
				System.out.println("[DEBUG Manager] In waiting list al momento: " + this.showWaitingOrders()+'\n'+waitingOrders.size());
			}
			return false;
		}
	}
	
	/**
	 * It creates a new current order.
	 * @param destination.
	 * @param address.
	 * @param restuarant.
	 */
	public void createOrder(City destination, String address, Restaurant restaurant) {
		this.counterID += 1;
		this.currentOrder = new OrderImpl(this.counterID, destination, address, restaurant); 
	}
	
	/**
	 * It assigns the current order (to a rider or to waiting list). 
	 * and empties the current order slot.
	 * @return true if assigned to a rider, false if put into the waiting list.
	 */
	public boolean assignCurrentOrder() {
		boolean result = this.assignOrder(this.currentOrder);
		this.currentOrder = null;
		return result;
	}
	
	/**
	 * Show info about orders in waiting list.
	 * @return string with info about all orders in waiting list.
	 */
	public String showWaitingOrders() {
		StringBuilder sb = new StringBuilder();
		this.waitingOrders.forEach(o -> sb.append(o.printInfoForRider()+o.showOrderContent()));
		return sb.toString();
	}
	
	/**
	 * Returns how many menu of a specific type are in the current order.
	 * @param menu.
	 * @return number of quantity of that menu.
	 */
	public int howManyInCurrent(Menu menu) {
		if (this.currentOrder.getMenus().containsKey(menu))
			return this.currentOrder.getMenus().get(menu);
		else return 0;
	}
	
	/**
	 * It check if any orders in waiting can be assign to the given rider
	 * @param freeRider = rider who's just finish a delivery
	 * @return true if at least one new order was given to freeRider
	 */
	public boolean refreshWaitingOrder(Rider freeRider) {
		List<Order> noMoreWaitingOrders = new ArrayList<Order>(); 
		this.getWaitingOrders().forEach(o ->{	
			if(this.canBeAssign(o).isPresent())
				if(this.canBeAssign(o).get().equals(freeRider)) {
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

	public Order getCurrentOrder() {
		return currentOrder;
	}
	
	public List<Order> getWaitingOrders() {
		return waitingOrders;
	}
	
	public void addToCurrent(Menu menu) {
		this.currentOrder.addMenu(menu);
	}

	public boolean removeFromCurrent(Menu menu) {
		return this.currentOrder.removeMenu(menu);
	}
}
