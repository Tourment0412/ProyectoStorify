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
     * Inicializa el controlador.
     * Configura la interfaz del administrador y establece el nombre de usuario en la etiqueta correspondiente.
     */
    @FXML
	void initialize() {
    	gestionArtistas();
    	lblAdministrador.setText(mfm.getUsuarioSesion().getUsername());
	}
	
    /**
     * Gestiona la ventana de administración de artistas.
     * Carga la interfaz de administración de artistas.
     */
    @FXML
    void gestionArtistas() {
    	cargarVentana("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/AdministradorArtistas.fxml");
    }
	
    /**
     * Gestiona la ventana de administración de canciones.
     * Carga la interfaz de administración de canciones.
     */
    @FXML
    void gestionCanciones() {
    	cargarVentana("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/AdministradorCanciones.fxml");
    }
	
    /**
     * Cierra la sesión del usuario actual y muestra la pantalla de inicio de sesión.
     */
    @FXML
    void cerrarSesion() {
    	app.mostrarIniciarSesion();
    	mfm.setUsuarioSesion(null);
    }
	
    /**
     * Carga una nueva ventana en el panel principal.
     * 
     * @param ruta la ruta del archivo FXML que se va a cargar
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