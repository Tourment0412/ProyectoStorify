package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IniciarSesionController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnInicioSesion;

	@FXML
	private Label lblContrasenia;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblTitulo;

	@FXML
	private TextField txtContrasenia;

	@FXML
	private TextField txtNombre;

	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana = mfm.getVentana();
	private co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App App = mfm.getAplicacion();

	@FXML
	void initialize() {
		assert btnInicioSesion != null
				: "fx:id=\"btnInicioSesion\" was not injected: check your FXML file 'IniciarSesion.fxml'.";
		assert lblContrasenia != null
				: "fx:id=\"lblContrasenia\" was not injected: check your FXML file 'IniciarSesion.fxml'.";
		assert lblNombre != null : "fx:id=\"lblNombre\" was not injected: check your FXML file 'IniciarSesion.fxml'.";
		assert lblTitulo != null : "fx:id=\"lblTitulo\" was not injected: check your FXML file 'IniciarSesion.fxml'.";
		assert txtContrasenia != null
				: "fx:id=\"txtContrasenia\" was not injected: check your FXML file 'IniciarSesion.fxml'.";
		assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'IniciarSesion.fxml'.";

	}

	public ResourceBundle getResources() {
		return resources;
	}

	public void setResources(ResourceBundle resources) {
		this.resources = resources;
	}

	public URL getLocation() {
		return location;
	}

	public void setLocation(URL location) {
		this.location = location;
	}

	public Button getBtnInicioSesion() {
		return btnInicioSesion;
	}

	public void setBtnInicioSesion(Button btnInicioSesion) {
		this.btnInicioSesion = btnInicioSesion;
	}

	public Label getLblContrasenia() {
		return lblContrasenia;
	}

	public void setLblContrasenia(Label lblContrasenia) {
		this.lblContrasenia = lblContrasenia;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public Label getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(Label lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public TextField getTxtContrasenia() {
		return txtContrasenia;
	}

	public void setTxtContrasenia(TextField txtContrasenia) {
		this.txtContrasenia = txtContrasenia;
	}

	public TextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(TextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public ModelFactoryController getMfm() {
		return mfm;
	}

	public void setMfm(ModelFactoryController mfm) {
		this.mfm = mfm;
	}

	public Stage getVentana() {
		return ventana;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}

	public co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App getAplicacion() {
		return App;
	}

	public void setAplicacion(co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App aplicacion) {
		this.App = aplicacion;
	}
	

}
