/**
 * @author Giulia Costa
 */

package it.fooddelivery.view;

import java.util.List;

import javax.swing.JFrame;

import it.fooddelivery.controller.Manager;
import it.fooddelivery.model.Menu;

public class ViewPlacing extends JFrame {
	private final Manager controller;
	
	/**
	 * Al costruttore viene passato il controller,
	 * ed i menu offerti dal ristorante selezioanto nella schermata precedente.
	 * @param controller.
	 * @param menuOffer = lista menù offerti dal ristorante (già) selezionato dall'utente.
	 */
	ViewPlacing(final Manager controller, List<Menu> menuOffer){
		this.controller = controller;
		this.Init();
	}
	
	private void Init() {
		// Inizializzare la GUI dentro questo metodo
	}
}
