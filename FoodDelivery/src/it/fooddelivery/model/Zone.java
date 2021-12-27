/**
 * @author Giulia Costa
 */

package it.fooddelivery.model;

import java.util.List;

/**
 * Interface used to represent cities and their delivery men.
 *
 */
public interface Zone {

	/**
	 * 
	 * @return
	 */
	List<DeliveryMan> deliveryMan();
}
