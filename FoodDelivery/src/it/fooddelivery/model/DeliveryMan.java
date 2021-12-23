package it.fooddelivery.model;

import java.util.List;

// Giulia Costa
public interface DeliveryMan {
	List<Order> bag();
	boolean addOrder(Order);
	boolean deliverOrder(Order);			// rimuove dallo zaino e aggiunge il guadagno --> libero!!
	double profit();						// incasso totale, viene usata per controllare il fattorino che ha guadagnato di meno
	int capacity();
	boolean isFull();
}
