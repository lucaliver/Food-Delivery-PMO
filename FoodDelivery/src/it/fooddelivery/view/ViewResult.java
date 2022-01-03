/**
 * @author Luca Procicchiani
 */

package it.fooddelivery.view;

import javax.swing.JFrame;

import it.fooddelivery.controller.Manager;

public class ViewResult extends JFrame {
	private final Manager controller;
	
	ViewResult(final Manager controller){
		this.controller = controller;
		this.Init();
	}
	
	private void Init() {
		// Inizializzare la GUI dentro questo metodo
	}
}
