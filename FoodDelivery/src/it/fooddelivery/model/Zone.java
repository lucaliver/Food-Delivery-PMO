/**
 * @author Giulia Costa
 */

package it.fooddelivery.model;

import java.util.List;

public interface Zone {
	
		List<DeliveryMan> deliveryMan();
		
		String description();
}