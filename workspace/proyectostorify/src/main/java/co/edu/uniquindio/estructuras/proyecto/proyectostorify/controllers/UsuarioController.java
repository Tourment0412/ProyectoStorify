package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;
import java.io.IOException;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class UsuarioController {
	
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private App app = mfm.getAplicacion();

    @FXML
    private AnchorPane panel;
    
    @FXML
    private Label lblUsuario;
	
	/**
	 * 
	 */
    @FXML
	void initialize() {
    	listaCanciones();
    	lblUsuario.setText(mfm.getUsuarioSesion().getUsername());
	}
	
	/**
	 * 
	 */
    @FXML
    void cancionesGuardadas() {
    	cargarVentana("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/ListaFavoritas.fxml");
    }
	
	/**
	 * 
	 */
    @FXML
    void listaCanciones() {
    	cargarVentana("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/ListaCanciones.fxml");
    }
	
	/**
	 * 
	 */
    @FXML
    void listaArtistas() {
    	cargarVentana("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/Artistas.fxml");
    }
	
	/**
	 * 
	 */
    @FXML
    void cancionesTienda() {
    	cargarVentana("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/CancionesTienda.fxml");
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