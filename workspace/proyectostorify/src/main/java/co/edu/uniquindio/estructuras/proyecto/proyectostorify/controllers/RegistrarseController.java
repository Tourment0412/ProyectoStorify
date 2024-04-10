package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistrarseController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnInicioSesion;

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

	@FXML
	void initialize() {
		assert btnInicioSesion != null
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

}
