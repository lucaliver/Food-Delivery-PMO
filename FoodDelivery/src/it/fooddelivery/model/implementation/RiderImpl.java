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
	
	
	
	@Override
	public List<Order> getBag() {
		return orderList;
	}

	@Override
	public void addOrder(Order o) {
		orderList.add(o);
		this.setCapacity(o.getOrderSize());
	}
	
	@Override
	public void deliverOrder(Order o) {
		if(this.orderList.contains(o)) {
			this.addProfit(o);
			this.setCapacity(-o.getOrderSize());
			//orderList.remove(o);
		}
	}
	// TODO #1# Problema con deliveryAll: non possiamo iterare una collection di cui andiamo a togliere i suoi elementi,
	// una soluzione sarebbe commentare l'istruzione remove sopra e quando facciamo deliveryAll fare una clear
	// non so quanto questoconcettualmente sia corretto
	@Override
	public void deliverAll() {
		this.orderList.forEach(o -> deliverOrder(o));
		this.orderList.clear();
	}

	@Override
	public boolean isFull() {
		return orderList.size() == MAX_CAPACITY;
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
	public boolean canFit(Order o) {		// ci entra nello zaino?
		return (this.capacity + o.getOrderSize()) <= MAX_CAPACITY;
	}
	
	@Override
	public List<City> getCities() {
		return cities;
	}
	
	/**
	 * Il fattorino incassa una parte del costo dell'ordine.
	 * @param o = ordine di cui incassare il guadagno. 
	 */
	private void addProfit(Order o) {
		this.profit += o.getOrderPrice() * getPercentage();
	}
	
	private void setCapacity(int size) {
		this.capacity += size;
	}
	
	public int getCapacity() {
		return capacity;
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

		//STAMPE DI DEBUG
		/*System.out.println("[DEBUG RiderImpl.showBagInfo()] " 
				+ this.name + " ha " + this.orderList.size() + " ordini. ");
		this.orderList.forEach(o -> System.out.println("[DEBUG RiderImpl.showBagInfo()] L'ordine "
				+ o.getIdOrder() + " ha tot menu: " + o.getMenus().size()));*/
		
		this.orderList.forEach(o -> sb.append(o.printIdOrder()+o.showOrderInfo()));
		return sb.toString();
	}

	public static double getPercentage() {
		return PERCENTAGE;
	}
	
	
}