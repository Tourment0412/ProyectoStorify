package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.util.List;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import javafx.stage.Stage;

public class ModelFactoryController {
	

	private App aplicacion;
	private Stage ventana;
	
	private static class SingletonHolder { 
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// M�todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
	/**
	 * Metodo constructor
	 */
	public ModelFactoryController() {

	}

	public App getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(App aplicacion) {
		this.aplicacion = aplicacion;
	}

	public Stage getVentana() {
		return ventana;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}
	
	

}
