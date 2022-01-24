/**
 /* @author Giulia Costa
 */

package it.fooddelivery.model.implementation;

import java.util.ArrayList;
import java.util.List;

import it.fooddelivery.model.Rider;
import it.fooddelivery.model.City;
import it.fooddelivery.model.Order;

public class RiderImpl implements Rider{
	static final int MAX_CAPACITY = 100;
	private static final double PERCENTAGE = 0.20;
	
	private double profit;
	private final String name;
	private int capacity;
	private List<Order> orderList;
	private List<City> cities;
	
	/**
	 * Costruttore.
	 * @param name = nome del rider.
	 * @param cities = lista delle città in cui egli consegna.
	 */
	public RiderImpl(String name, List<City> cities) {
		this.profit = 0;
		this.capacity = 0;
		this.name = name;
		this.cities = cities;
		this.orderList = new ArrayList<>();
	}
	
	public static double getPercentage() {
		return PERCENTAGE;
	}
	
	public static int getMaxCapacity() {
		return MAX_CAPACITY;
	}
		
	@Override
	public List<Order> getBag() {
		return orderList;
	}
	
	public int getCapacity() {
		return capacity;
	}	
	
	// restituisce il profitto totale di ogni fatttorino
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
		this.orderList.forEach(o -> sb.append(o.printInfoForRider()+o.showOrderContent()));
		return sb.toString();
	}

	@Override
	public void addOrder(Order o) {
		orderList.add(o);
		this.addCapacity(o.getOrderSize());
	}
	
	@Override
	public void deliverOrder(Order o) {
		if(this.orderList.contains(o)) {
			this.addProfit(o);
			this.addCapacity(-o.getOrderSize());
			orderList.remove(o);
		}
		else {
			// TODO Gestione eccezione
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
	public boolean canFit(Order o) {		// ci entra nello zaino?
		return (this.capacity + o.getOrderSize()) <= MAX_CAPACITY;
	}
	
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
	
	private double calculateOrderProfit(Order o) {
		return o.getOrderPrice() * getPercentage();
	}
		
	private void addCapacity(int size) {
		this.capacity += size;
	}

	
}