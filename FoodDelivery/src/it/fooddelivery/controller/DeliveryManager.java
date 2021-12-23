// LUCA PROCICCHIANI

package it.fooddelivery.controller;

import java.util.ArrayList;
import java.util.List;

import it.fooddelivery.model.Order;
import it.fooddelivery.model.Restaurant;
import it.fooddelivery.model.Zone;

public class DeliveryManager {
	private List<Order> waitingOrders = new ArrayList<>();
	private List<Zone> zones = new ArrayList<>();
	private List<Restaurant> restaurants = new ArrayList<>();
	
	//Costruttore
	DeliveryManager(){
		this.waitingOrders = new ArrayList<>();
		this.zones = new ArrayList<>();
		this.restaurants = new ArrayList<>();
	}
	
	
}
