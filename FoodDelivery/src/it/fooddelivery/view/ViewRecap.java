/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.view;

import javax.swing.JFrame;

import it.fooddelivery.controller.Manager;

public class ViewRecap extends JFrame {
	private final Manager controller;
	
	ViewRecap(final Manager controller){
		this.controller = controller;
		this.Init();
	}
	
	private void Init() {
		// Inizializzare la GUI dentro questo metodo
	}
}
