package it.fooddelivery.model;

import java.util.List;

// Giulia Costa
public enum Zone {
	FERMIGNANO("Fermignano"),
	CAGLI("Cagli"),
	TAVULLIA("Tavullia"),
	URBANIA("Urbania"),
	FOSSOMBRONE("Fossombrone");
	
	
	private String description;
	
	List<DeliveryMan> deliveryMan();
	
	public String toString() {
		return description;
	}
}