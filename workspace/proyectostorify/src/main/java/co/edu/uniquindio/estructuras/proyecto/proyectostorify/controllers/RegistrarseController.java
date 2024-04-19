package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class RegistrarseController {
	
    @FXML
    private Button btnRegistrarse;
    
    @FXML
    private Button btnIniciarSesion;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblContrasenia;

	@FXML
	private Label lblEmail;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblTitulo;

	@FXML
	private TextField txtContrasenia;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtNombre;
	
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana = mfm.getVentana();
	private App app = mfm.getAplicacion();

	@FXML
	void initialize() {
		assert btnIniciarSesion != null
				: "fx:id=\"btnInicioSesion\" was not injected: check your FXML file 'Registrarse.fxml'.";
		assert lblContrasenia != null
				: "fx:id=\"lblContrasenia\" was not injected: check your FXML file 'Registrarse.fxml'.";
		assert lblEmail != null : "fx:id=\"lblEmail\" was not injected: check your FXML file 'Registrarse.fxml'.";
		assert lblNombre != null : "fx:id=\"lblNombre\" was not injected: check your FXML file 'Registrarse.fxml'.";
		assert lblTitulo != null : "fx:id=\"lblTitulo\" was not injected: check your FXML file 'Registrarse.fxml'.";
		assert txtContrasenia != null
				: "fx:id=\"txtContrasenia\" was not injected: check your FXML file 'Registrarse.fxml'.";
		assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'Registrarse.fxml'.";
		assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'Registrarse.fxml'.";

	}
	
	@FXML
    void iniciarSesion(ActionEvent event) {
    	app.mostrarIniciarSesion();

    }
	
    @FXML
    void registrarse(ActionEvent event) {
    	mfm.agregarUsuario(txtNombre.getText(), txtEmail.getText(), txtContrasenia.getText());
    	app.mostrarIniciarSesion();
    }


}
