package it.fooddelivery.model.implementation;

import java.util.List;

// Giulia Costa
public enum ZoneImpl {
	
	FERMIGNANO("Fermignano"),
	CAGLI("Cagli"),
	TAVULLIA("Tavullia"),
	URBANIA("Urbania"),
	FOSSOMBRONE("Fossombrone");	
	
	private String description;
	
	ZoneImpl(final String description) {
		this.description = description;
	}
	
	public String toString() {
		return description;
	}
}