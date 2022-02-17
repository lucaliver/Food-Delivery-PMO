/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.fooddelivery.model.Rider;
import it.fooddelivery.model.implementation.OrderImpl;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Menu;
import it.fooddelivery.model.Order;
import it.fooddelivery.model.Restaurant;

/**
 * Controller component in the MVC pattern for the Food Delivery Application.
 */
public class ManagerImpl implements Manager{
	private Map<String, Rider> riders;
	private List<Order> waitingOrders;
	private Set<Restaurant> restaurants;
	private Optional<Order> currentOrder;
	private Optional<Rider> riderWithLastOrder;
	private int sequentialIdCounter;
	
	/**
	 * Constructs a Manager with the specified riders and restaurants.
	 * 
	 * @param riders list of all riders
	 * @param restaurants list of all restaurants
	 */
	public ManagerImpl(Map<String, Rider> riders, Set<Restaurant> restaurants){
		this.riders = riders;
		this.restaurants = restaurants;
		this.waitingOrders = new ArrayList<>();
		this.currentOrder = Optional.empty();
		this.riderWithLastOrder = Optional.empty();
		this.sequentialIdCounter = 0;
	}
	
	@Override
	public Optional<Rider> canBeAssigned(Order order){
		return this.riderWithLastOrder = 
			   this.riders
					.values()
					.stream()
					.filter(x->x.getCities().contains(order.getDestination()))
					.filter(x->x.canFit(order))	
					.sorted(Comparator.comparingDouble(Rider::getProfit))	
					.findFirst();		
	}
	
	@Override
	public void createCurrentOrder(City destination, String address, Restaurant restaurant) {
		this.sequentialIdCounter += 1;
		this.currentOrder = Optional.of(new OrderImpl(this.sequentialIdCounter, destination, address, restaurant)); 
	}
	
	@Override
	public boolean assignCurrentOrder() {
		boolean result = this.assignOrder(this.getCurrentOrderPresent());
		this.currentOrder = Optional.empty();
		return result;
	}
	
	@Override
	public String showWaitingOrders() {
		StringBuilder sb = new StringBuilder();
		this.waitingOrders.forEach(o -> sb.append(o.showInfoForRider() + "    Totale: " + String.format("%.2f", o.getPrice()) + "€\n"));
		return sb.toString();
	}
	
	@Override
	public int howManyInCurrent(Menu menu) {
		if (this.currentOrder.get().getMenus().containsKey(menu))
			return this.currentOrder.get().getMenus().get(menu);
		else return 0;
	}
	
	@Override
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
	
	@Override
	public void addToCurrent(Menu menu) {
		this.getCurrentOrderPresent().addMenu(menu);
	}
	
	@Override
	public boolean removeFromCurrent(Menu menu) {
		return this.getCurrentOrderPresent().removeMenu(menu);
	}
	
	@Override
	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}
	
	@Override
	public Map<String, Rider> getRiders() {
		return riders;
	}
	
	@Override
	public Optional<Rider> getRiderWithLastOrder(){
		return this.riderWithLastOrder;
	}
	
	@Override
	public Order getCurrentOrderPresent() {
		return this.currentOrder.orElseThrow();
	}
	
	@Override
	public List<Order> getWaitingOrders() {
		return waitingOrders;
	}
	
	@Override
	public int getSequentialIdCounter() {
		return this.sequentialIdCounter;
	}
	
	/**
	* Tries to assign the order to a rider that delivers in the order's city.
	* He must have enough bag space. The rider with the lowest profit has the priority.
	* If no Rider can receive the Order, it will be put into the waiting list.
	* 
	* @param order the order to assign
	* @return {@code true} if assigned to a Rider, {@code false} if put into the waiting list
	*/
	private boolean assignOrder(Order order) {
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
}
