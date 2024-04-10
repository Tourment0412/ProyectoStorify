package co.edu.uniquindio.estructuras.proyecto.proyectostorify.application;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;



/**
 * JavaFX App
 */
public class App extends Application {

//    private static Scene scene;
//    
//    
//    //Holaa
//    //Sandia
//    //Intro miguel
//    //hola
//    @Override
//    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("primary"), 640, 480);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/"+fxml + ".fxml"));
//        return fxmlLoader.load();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
	
	
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	/**
	 * Metodo que inica la ejecucion de la aplicacion
	 */
	@Override
	public void start(Stage primaryStage) throws IOException{
		co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers.ModelFactoryController mfm = co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers.ModelFactoryController.getInstance();
		mfm.setAplicacion(this);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Storify");
		mostrarLogin();
	}
	
	/**
	 * Inicia la ventana principal
	 */
	public void mostrarLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/fxml/IniciarSesion.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers.IniciarSesionController controlador = loader.getController();
			controlador.setVentana(primaryStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}