package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.io.IOException;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AdministradorController {
	
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private App app = mfm.getAplicacion();

    @FXML
    private AnchorPane panel;
    
    @FXML
    private Label lblAdministrador;

	
	/**
	 * 
	 */
    @FXML
	void initialize() {
    	gestionArtistas();
    	lblAdministrador.setText(mfm.getUsuarioSesion().getUsername());
	}
	
	/**
	 * 
	 */
    @FXML
    void gestionArtistas() {
    	cargarVentana("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/AdministradorArtistas.fxml");
    }
	
	/**
	 * 
	 */
    @FXML
    void gestionCanciones() {
    	cargarVentana("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/AdministradorCanciones.fxml");
    }
	
	/**
	 * 
	 */
    @FXML
    void cerrarSesion() {
    	app.mostrarIniciarSesion();
    	mfm.setUsuarioSesion(null);
    }
	
	/**
	 * 
	 * @param ruta
	 */
    public void cargarVentana(String ruta) {
    	
    	try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent nuevoContenido = loader.load();

            // Agregar el nuevo contenido al AnchorPane
            panel.getChildren().setAll(nuevoContenido);
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente
        }
    }

}