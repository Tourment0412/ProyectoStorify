package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.*;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.*;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.InterfazFXUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import lombok.*;

@Setter
@Getter
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
	private App app = mfm.getAplicacion();

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

	/**
	 * Inicia sesión para un usuario administrador.
	 * 
	 * @param admin
	 */
	public void iniciarSesionAdministrador(Administrador admin) {
		ventana.close();
		app.mostrarAdministradorArtistas();
	}

	/**
	 * Inicia sesión para un usuario comun
	 * 
	 * @param usuario
	 */
	public void iniciarSesionUsuario(Usuario usuario) {
		ventana.close();
		app.mostrarListaCanciones();
	}

	/**
	 * Método que se ejecuta al presionar el botón "Iniciar Sesion".
	 */
	@FXML
	private void ingresar() {
		// Codigo para verificar el tipo de cuenta que va a ingresar (No borrar)
		String tipoCuenra;
		if (mfm.existeUsuario(txtNombre.getText(), txtContrasenia.getText())) {
			tipoCuenra = mfm.obtenerTipoCuenta(txtNombre.getText(), txtContrasenia.getText());
			System.out.println("Es " + tipoCuenra);
			switch (tipoCuenra) {
			case "Administrador":
				iniciarSesionAdministrador(
						(Administrador) mfm.obtenerCuentaDatos(txtNombre.getText(), txtContrasenia.getText()));
				break;
			case "Usuario":
				iniciarSesionUsuario((Usuario) mfm.obtenerCuentaDatos(txtNombre.getText(), txtContrasenia.getText()));
				break;

			}
		} else {
			InterfazFXUtil.mostrarMensaje("Cuenta no encontrada", "Cuenta no encontrada",
					"No existe una cuenta con los datos ingresados", AlertType.WARNING);
		}

	}

}
