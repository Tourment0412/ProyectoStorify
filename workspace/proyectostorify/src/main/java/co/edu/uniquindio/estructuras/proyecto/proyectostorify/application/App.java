package co.edu.uniquindio.estructuras.proyecto.proyectostorify.application;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers.*;


/**
 * JavaFX App
 */
public class App extends Application {

	
	
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	/**
	 * Metodo que inica la ejecucion de la aplicacion
	 */
	@Override
	public void start(Stage primaryStage) throws IOException{
		ModelFactoryController mfm = ModelFactoryController.getInstance();
		mfm.setAplicacion(this);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Storify");
		mostrarIniciarSesion();
	}
	
	public void mostrarAdministrador() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/Administrador.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			AdministradorController controlador = loader.getController();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarUsuario() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/Usuario.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			UsuarioController controlador = loader.getController();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inicia la ventana principal
	 */
	public void mostrarIniciarSesion() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/IniciarSesion.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			IniciarSesionController controlador = loader.getController();
			controlador.setVentana(primaryStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void mostrarAdministradorArtistas() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/AdministradorArtistas.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			AdministradorArtistasController controlador = loader.getController();
			controlador.setVentana(primaryStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 */
	public void mostrarAdministradorCanciones() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/AdministradorCanciones.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			AdministradorCancionesController controlador = loader.getController();
			controlador.setVentana(primaryStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 */
	public void mostrarArtistas() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/Artistas.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			ArtistasController controlador = loader.getController();
			controlador.setVentana(primaryStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 */
	public void mostrarListaCanciones() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/ListaCanciones.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			ListaCancionesController controlador = loader.getController();
			controlador.setVentana(primaryStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 */
	public void mostrarResgistrarse() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/Registrarse.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			RegistrarseController controlador = loader.getController();
			controlador.setVentana(primaryStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}