/**
 /* @author Luca Procicchiani
 */

package it.fooddelivery.model.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.fooddelivery.model.Rider;
import it.fooddelivery.model.Order;

/**
 * A class to represent a rider.  Implementation of {@link Rider}.
 */
public class RiderImpl implements Rider{
	
	private static final int MAX_CAPACITY = 100;
	private static final double PROFIT_PERCENTAGE = 0.10;
	private double profit;
	private final String name;
	private List<Order> orderList;
	private Set<CityImpl> cities;
	
	/**
	 * Constructs a Rider with the specified name and cities.
	 * 
	 * @param name the rider's name
	 * @param cities a list of all cities where he can deliver
	 */
	public RiderImpl(String name, Set<CityImpl> cities) {
		this.name = name;
		this.cities = cities;
		this.profit = 0;
		this.orderList = new ArrayList<>();
	}

	@Override
	public String showRiderInfo() {
		StringBuilder riderCities = new StringBuilder("Destinazioni:");
		cities.forEach(c -> riderCities.append(c.getName()+"\n                     "));
		return "Profito: "+String.format("%.2f", getProfit())+
		       "€"+'\n'+"Capacità: "+this.getUsedSpace()+
		       "/"+RiderImpl.MAX_CAPACITY + "u\n"+
		       riderCities;
	}
	
	@Override
	public String showBagInfo() {
		StringBuilder sb = new StringBuilder();		
		this.orderList.forEach(o -> sb.append(o.showBasicInfo()));
		return sb.toString();
	}

	@Override
	public void addOrder(Order order) {
		orderList.add(order);
	}
	
	@Override
	public void deliverOrder(Order order) {
		if(this.orderList.contains(order)) {
			this.profit += this.calculateOrderProfit(order);
			orderList.remove(order);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void deliverAll() {
		List<Order> orderListCopy = new ArrayList<Order>(this.orderList);
		orderListCopy.forEach(o -> deliverOrder(o));
	}
			
	@Override
	public boolean canFit(Order order) {
		return (this.getUsedSpace() + order.getSize()) <= MAX_CAPACITY;
	}
	
	public static double getPercentage() {
		return PROFIT_PERCENTAGE;
	}
	
	public static int getMaxCapacity() {
		return MAX_CAPACITY;
	}
	
	@Override
	public List<Order> getBag() {
		return orderList;
	}
	
	@Override
	public double getProfit() {
		return profit;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Set<CityImpl> getCities() {
		return cities;
	}
	
	@Override
	public int getUsedSpace() {
		return this.orderList.stream().mapToInt(o -> o.getSize()).sum();
	}
	
	@Override
	public double getBagProfit() {
		double res = 0;
		for(Order o : this.getBag()) {
			res += this.calculateOrderProfit(o);
		}
		return res;
	}
	
	/**
	 * Calculates the profit of the order.
	 * 
	 * @param order the order
	 * @return how much this rider could make out of this order
	 */
	private double calculateOrderProfit(Order order) {
		return order.getPrice() * RiderImpl.PROFIT_PERCENTAGE;
	}
}