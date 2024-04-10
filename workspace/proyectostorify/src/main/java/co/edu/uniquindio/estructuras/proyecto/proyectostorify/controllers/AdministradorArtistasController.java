package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdministradorArtistasController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnActualizar;

	@FXML
	private Button btnCrear;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnVolver;

	@FXML
	private ComboBox<?> cmbGrupo;

	@FXML
	private Label lblGrupo;

	@FXML
	private Label lblNacionalidad;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblTitulo;

	@FXML
	private TableView<?> tableArtistas;

	@FXML
	private TextField txtNacionalidad;

	@FXML
	private TextField txtNombre;

	@FXML
	void initialize() {
		assert btnActualizar != null
				: "fx:id=\"btnActualizar\" was not injected: check your FXML file 'AdministradorArtistas.fxml'.";
		assert btnCrear != null
				: "fx:id=\"btnCrear\" was not injected: check your FXML file 'AdministradorArtistas.fxml'.";
		assert btnEliminar != null
				: "fx:id=\"btnEliminar\" was not injected: check your FXML file 'AdministradorArtistas.fxml'.";
		assert btnVolver != null
				: "fx:id=\"btnVolver\" was not injected: check your FXML file 'AdministradorArtistas.fxml'.";
		assert cmbGrupo != null
				: "fx:id=\"cmbGrupo\" was not injected: check your FXML file 'AdministradorArtistas.fxml'.";
		assert lblGrupo != null
				: "fx:id=\"lblGrupo\" was not injected: check your FXML file 'AdministradorArtistas.fxml'.";
		assert lblNacionalidad != null
				: "fx:id=\"lblNacionalidad\" was not injected: check your FXML file 'AdministradorArtistas.fxml'.";
		assert lblNombre != null
				: "fx:id=\"lblNombre\" was not injected: check your FXML file 'AdministradorArtistas.fxml'.";
		assert lblTitulo != null
				: "fx:id=\"lblTitulo\" was not injected: check your FXML file 'AdministradorArtistas.fxml'.";
		assert tableArtistas != null
				: "fx:id=\"tableArtistas\" was not injected: check your FXML file 'AdministradorArtistas.fxml'.";
		assert txtNacionalidad != null
				: "fx:id=\"txtNacionalidad\" was not injected: check your FXML file 'AdministradorArtistas.fxml'.";
		assert txtNombre != null
				: "fx:id=\"txtNombre\" was not injected: check your FXML file 'AdministradorArtistas.fxml'.";

	}

}
