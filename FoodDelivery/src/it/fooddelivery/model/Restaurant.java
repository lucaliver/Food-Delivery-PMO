/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.model;

import java.util.List;

/**
 * Un'interfaccia per rappresentare dei ristoranti.
 */
public interface Restaurant {
	
	/**
	 * @return il nome del ristorante.
	 */
	public String name();
	
	/**
	 * @return tutti i menu offerti dal ristorante.
	 */
	public List<Menu> menuOffer();
}
