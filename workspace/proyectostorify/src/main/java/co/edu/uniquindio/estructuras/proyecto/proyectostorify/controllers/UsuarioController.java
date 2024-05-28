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
	 * Inicializa la interfaz. Carga la lista de canciones y muestra el nombre de
	 * usuario en un campo de texto.
	 */
	@FXML
	void initialize() {
		listaCanciones();
		lblUsuario.setText(mfm.getUsuarioSesion().getUsername());
	}

	/**
	 * Carga la ventana de las canciones guardadas. Invoca el método cargarVentana
	 * con la ruta correspondiente al archivo FXML de la lista de canciones
	 * guardadas.
	 */
	@FXML
	void cancionesGuardadas() {
		cargarVentana("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/ListaFavoritas.fxml");
	}

	/**
	 * Carga la ventana de la lista de canciones. Invoca el método cargarVentana con
	 * la ruta correspondiente al archivo FXML de la lista de canciones.
	 */
	@FXML
	void listaCanciones() {
		cargarVentana("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/ListaCanciones.fxml");
	}

	/**
	 * Carga la ventana de la lista de artistas. Invoca el método cargarVentana con
	 * la ruta correspondiente al archivo FXML de la lista de artistas.
	 */
	@FXML
	void listaArtistas() {
		cargarVentana("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/Artistas.fxml");
	}

	/**
	 * Carga la ventana de las canciones disponibles en la tienda. Invoca el método
	 * cargarVentana con la ruta correspondiente al archivo FXML de las canciones
	 * disponibles en la tienda.
	 */
	@FXML
	void cancionesTienda() {
		cargarVentana("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/CancionesTienda.fxml");
	}

	/**
	 * Cierra la sesión actual y muestra la ventana de inicio de sesión.
	 * Establece el usuario de la sesión en null.
	 */
	@FXML
	void cerrarSesion() {
		app.mostrarIniciarSesion();
		mfm.setUsuarioSesion(null);
	}

	/**
	 * Carga una nueva ventana FXML en el panel principal.
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