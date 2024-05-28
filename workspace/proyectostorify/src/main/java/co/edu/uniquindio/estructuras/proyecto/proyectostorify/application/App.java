package co.edu.uniquindio.estructuras.proyecto.proyectostorify.application;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
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
	public void start(Stage primaryStage) throws IOException {
		// Cargar la imagen del icono desde el classpath
		String iconPath = "/co/edu/uniquindio/estructuras/proyecto/proyectostorify/images/logo2.png";
		Image icon = new Image(getClass().getResourceAsStream(iconPath));

		// Establecer la imagen como el icono del Stage principal
		primaryStage.getIcons().add(icon);

		ModelFactoryController mfm = ModelFactoryController.getInstance();
		mfm.setAplicacion(this);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Storify");
		mostrarIniciarSesion();
	}

	/**
	 * Muestra la ventana del administrador
	 */
	public void mostrarAdministrador() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class
					.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/Administrador.fxml"));
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

	/**
	 * Muestra la ventana del usuario
	 */
	public void mostrarUsuario() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
					App.class.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/Usuario.fxml"));
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
			loader.setLocation(App.class
					.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/IniciarSesion.fxml"));
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
	 * Muestra la ventana de administrador de artistas
	 */
	public void mostrarAdministradorArtistas() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource(
					"/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/AdministradorArtistas.fxml"));
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
	 * Muestra la ventana de administracion de canciones
	 */
	public void mostrarAdministradorCanciones() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource(
					"/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/AdministradorCanciones.fxml"));
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
	 * Muestra la ventana de los artistas
	 */
	public void mostrarArtistas() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class
					.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/Artistas.fxml"));
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
	 * Muestra la ventana de lista de canciones
	 */
	public void mostrarListaCanciones() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class
					.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/ListaCanciones.fxml"));
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
	 * Muestra la ventana para el registro de usuarios
	 */
	public void mostrarResgistrarse() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class
					.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/Registrarse.fxml"));
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

	/**
	 * Muestra la ventana para reproducir audios
	 * 
	 * @param archivosReproducir Archivos de audio a reproducir
	 */
	public void reproducirCancion(CircularList<File> archivosReproducir) {
		try {
			String iconPath = "/co/edu/uniquindio/estructuras/proyecto/proyectostorify/images/logo2.png";
			Image icon = new Image(getClass().getResourceAsStream(iconPath));
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class
					.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/videoplayer.fxml"));
			BorderPane page = (BorderPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(icon);
			dialogStage.setTitle("Reproductor");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			VideoPlayerController controller = loader.getController();
			controller.organizarArchivos(archivosReproducir);
			dialogStage.setOnCloseRequest(event -> {
				controller.stop();
			});
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}