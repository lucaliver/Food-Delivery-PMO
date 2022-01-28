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
	static final int MAX_CAPACITY = 100;
	private static final double PERCENTAGE = 0.20;
	
	private double profit;
	private final String name;
	private int capacity;
	private List<Order> orderList;
	private List<City> cities;
	
	/**
	 * Constructor.
	 * @param name = rider's name.
	 * @param cities = list of all cities where he can deliver.
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
		this.orderList.forEach(o -> sb.append(o.showInfoForRider()+o.showOrderContent()));
		return sb.toString();
	}

	@Override
	public void addOrder(Order o) {
		orderList.add(o);
		this.capacity += o.getOrderSize();
	}
	
	@Override
	public void deliverOrder(Order o) {
		if(this.orderList.contains(o)) {
			this.addProfit(o);
			this.capacity -= o.getOrderSize();
			orderList.remove(o);
		}
		else {
			// TODO Gestione eccezione IllegalArgument?
		}
	}

	@Override
	public void deliverAll() {
		List<Order> orderListCopy = new ArrayList<Order>(this.orderList);
		orderListCopy.forEach(o -> deliverOrder(o));
	}

	@Override
	public boolean isFull() {
		return orderList.size() == MAX_CAPACITY;
	}
			
	@Override
	public boolean canFit(Order o) {
		return (this.capacity + o.getOrderSize()) <= MAX_CAPACITY;
	}
	
	/**
	 * 
	 */
	public double getBagProfit() {
		double res = 0;
		for(Order o : this.getBag()) {
			res += this.calculateOrderProfit(o);
		}
		return res;
	}
	
	/**
	 * Il fattorino incassa una parte del costo dell'ordine.
	 * @param o = ordine di cui incassare il guadagno. 
	 */
	private void addProfit(Order o) {
		this.profit += this.calculateOrderProfit(o);
	}
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	private double calculateOrderProfit(Order o) {
		return o.getOrderPrice() * getPercentage();
	}

	public int getCapacity() {
		return capacity;
	}	
	
	public static double getPercentage() {
		return PERCENTAGE;
	}
	
	public static int getMaxCapacity() {
		return MAX_CAPACITY;
	}
}