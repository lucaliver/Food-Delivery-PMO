/**
 * @author Giacomo Tombari
 */

package it.fooddelivery.view;

import javax.swing.JFrame;
import it.fooddelivery.controller.Manager;

public class ViewForWorker extends JFrame{
	private final Manager controller;
	private final String TITLE = "Worker Screen";
	
	ViewForWorker (final Manager controller){
		this.controller = controller;
		this.Init();
	}
	
	private void Init(){
		//Inizializza GUI qui
	}
}
