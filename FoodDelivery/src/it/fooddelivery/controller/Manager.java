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
	 * It tries to assign the order to a delivery man of the correct city.
	 * He needs to have enough bag space. It select the delivery man with the lower profit.
	 * @param order is the order to assign
	 * @return true if assigned to a delivery man, false if put into the waiting list.
	 */
	public boolean assignOrder(Order order) {
		System.out.println("[1-DEBUG manager] L'ordine da assegnare " + order.getIdOrder() + " ha tot menu: " + order.getMenus().size());
		this.riderWithLastOrder = this.riders
				.values()
				.stream()
				.filter(x->x.getCities().contains(order.getDestination()))
				.filter(x->x.canFit(order))	
				.sorted((o1, o2)->{return (int)(o1.getProfit()-o2.getProfit());})	
				.findFirst();
		// TODO penso che optional sia superfluo 
		//Optional<Rider> selected = this.riderWithLastOrder ;
		if(riderWithLastOrder.isPresent()) {
			riderWithLastOrder.get().addOrder(order);
			//TODO mostra come viene stampato il contenuto della bag di un rider, da rimuovere in seguito
			System.out.println("[DEBUG manager] Rider selezionato: " + riderWithLastOrder.get().getName());
			System.out.println("[DEBUG manager] L'ordine " + order.getIdOrder() + " ha tot menu: " + order.getMenus().size());
			System.out.println("[DEBUG manager] Info ultimo ordine: " + riderWithLastOrder.get().showBagInfo());
			
			return true;
		}else {
			this.waitingOrders.add(order);
			System.out.println("[DEBUG Manager] L'ordine " + order.getIdOrder() + " è stato messo in attesa.");
			System.out.println("[DEBUG Manager] In waiting list al momento: " + this.showWaitingOrders()+'\n'+waitingOrders.size());
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
		this.counterID += 1;
		this.currentOrder = new OrderImpl(this.counterID, destination, address, restaurant); 
	}
	
	/**
	 * It assigns the current order (to a rider or to waiting list) 
	 * and empties the current order slot.
	 */
	public boolean assignCurrentOrder() {
		boolean result = this.assignOrder(this.currentOrder);
		//this.currentOrder = null;
		return result;
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
	
	public String showWaitingOrders() {
		StringBuilder sb = new StringBuilder();
		this.waitingOrders.forEach(o -> sb.append("[Ordine " + o.getIdOrder() + "] \n"));
		return sb.toString();
	}
	
	public void addToCurrent(Menu menu) {
		this.currentOrder.addMenu(menu);
	}

	public boolean removeFromCurrent(Menu menu) {
		return this.currentOrder.removeMenu(menu);
	}
	
	public int howManyInCurrent(Menu menu) {
		if (this.currentOrder.getMenus().containsKey(menu))
			return this.currentOrder.getMenus().get(menu);
		else return 0;
	}
}
