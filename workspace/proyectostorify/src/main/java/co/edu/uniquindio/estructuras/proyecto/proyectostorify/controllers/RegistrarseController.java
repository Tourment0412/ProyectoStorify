package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.InterfazFXUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	
	/**
	 * Método de inicialización para la interfaz de registro.
	 * Se asegura de que los elementos de la interfaz estén correctamente inicializados.
	 */
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
	
	/**
	 * Verifica si los datos ingresados en la interfaz de registro son válidos.
	 * 
	 * @return true si los datos son válidos, false de lo contrario
	 */
	public boolean sonDatosValidos() {
		boolean sonDatosValidos = false;
		String msj = "";
		if (InterfazFXUtil.estaCampoVacio(txtNombre)) {
			msj+="Debe indicar el nombre de usuario\n";
		}
		if (InterfazFXUtil.estaCampoVacio(txtEmail)) {
			msj+="Debe indicar el email del usuario\n";
		}
		if (InterfazFXUtil.estaCampoVacio(txtContrasenia)) {
			msj+="Debe indicar su contrase\u00F1a\n";
		}
		if (msj.equals("")) {
			sonDatosValidos = true;
		} else {
			InterfazFXUtil.mostrarMensaje("Entradas no validas", "Entradas no validas", msj, AlertType.ERROR);
		}
		return sonDatosValidos;
	}
	
	/**
	 * Maneja el evento de iniciar sesión.
	 * 
	 * @param event el evento de acción
	 */
	@FXML
    void iniciarSesion(ActionEvent event) {
    	app.mostrarIniciarSesion();

    }
	
	/**
	 * Maneja el evento de registrarse.
	 * 
	 * @param event el evento de acción
	 */
    @FXML
    void registrarse(ActionEvent event) {
    	if (sonDatosValidos()) {
        	mfm.agregarUsuario(txtNombre.getText(), txtEmail.getText(), txtContrasenia.getText());
        	mfm.guardarDatos();
        	app.mostrarIniciarSesion();
    	}
    }


}
