/**
 /* @author Giulia Costa
 */

package it.fooddelivery.model.implementation;

import java.util.ArrayList;
import java.util.List;

import it.fooddelivery.model.Rider;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Order;

/**
 * A class to represent a rider.
 */
public class RiderImpl implements Rider{
	private static final int MAX_CAPACITY = 100;
	private static final double PROFIT_PERCENTAGE = 0.10;
	private double profit;
	private final String name;
	private int capacity;
	private List<Order> orderList;
	private List<City> cities;
	
	/**
	 * Constructor.
	 * 
	 * @param name the rider's name
	 * @param cities a list of all cities where he can deliver
	 */
	public RiderImpl(String name, List<City> cities) {
		this.profit = 0;
		this.capacity = 0;
		this.name = name;
		this.cities = cities;
		this.orderList = new ArrayList<>();
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
	public List<City> getCities() {
		return cities;
	}

	@Override
	public String showRiderInfo() {
		return "Profito: "+String.format("%.2f", getProfit())+
		       "€"+'\n'+"Capacità: "+this.getCapacity()+
		       "/"+RiderImpl.MAX_CAPACITY;
	}
	
	@Override
	public String showBagInfo() {
		StringBuilder sb = new StringBuilder();		
		this.orderList.forEach(o -> sb.append(o.showInfoForRider() +"    Totale: " + String.format("%.2f", o.getPrice()) + "€\n"));
		return sb.toString();
	}

	@Override
	public void addOrder(Order order) {
		orderList.add(order);
		this.capacity += order.getSize();
	}
	
	@Override
	public void deliverOrder(Order order) {
		if(this.orderList.contains(order)) {
			this.profit += this.calculateOrderProfit(order);
			this.capacity -= order.getSize();
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
		return (this.capacity + order.getSize()) <= MAX_CAPACITY;
	}

	@Override
	public int getCapacity() {
		return capacity;
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
	 * @return how much this rider can make out of this order
	 */
	private double calculateOrderProfit(Order order) {
		return order.getPrice() * getPercentage();
	}	
	
	public static double getPercentage() {
		return PROFIT_PERCENTAGE;
	}
	
	public static int getMaxCapacity() {
		return MAX_CAPACITY;
	}
}