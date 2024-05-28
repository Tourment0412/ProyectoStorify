package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Administrador;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Usuario;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.InterfazFXUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IniciarSesionController {
	

    @FXML
    private Button btnCerrarSesion;
    
    @FXML
    private Button btnRegistrarse;

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
    private PasswordField txtContrasenia;

	@FXML
	private TextField txtNombre;

	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana = mfm.getVentana();
	private App app = mfm.getAplicacion();

	/**
	 * Inicializa el controlador.
	 * Configura los eventos de teclado para los campos de texto para ingresar el nombre de usuario y la contraseña.
	 * Al presionar la tecla Enter en cualquiera de los campos, se ejecuta el método ingresar().
	 */
	@FXML
	void initialize() {
		txtNombre.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                ingresar();
                event.consume();
            }
        });

        txtContrasenia.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                ingresar();
                event.consume();
            }
        });

	}

	/**
	 * Inicia sesión para un usuario administrador.
	 *
	 * @param admin el objeto Administrador que inicia sesión
	 */
	public void iniciarSesionAdministrador(Administrador admin) {
		ventana.close();
		mfm.setUsuarioSesion(admin);
		app.mostrarAdministrador();

	}

	/**
	 * Inicia sesión para un usuario común.
	 *
	 * @param usuario el objeto Usuario que inicia sesión
	 */
	public void iniciarSesionUsuario(Usuario usuario) {
		ventana.close();
		mfm.iniciarSesionUsuario(usuario);
		app.mostrarUsuario();

	}

	/**
	 * Método que se ejecuta al presionar el botón "Iniciar Sesión".
	 * Verifica el tipo de cuenta e inicia sesión como Administrador o Usuario.
	 * Muestra un mensaje de advertencia si no se encuentra la cuenta.
	 */
	@FXML
	private void ingresar() {
		// Codigo para verificar el tipo de cuenta que va a ingresar (No borrar)
		String tipoCuenra;
		if (mfm.existeUsuario(txtNombre.getText(), txtContrasenia.getText())) {
			tipoCuenra = mfm.obtenerTipoCuenta(txtNombre.getText(), txtContrasenia.getText());
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
	
	/**
	 * Muestra la ventana de registro de usuario.
	 *
	 * @param event el evento de acción que dispara la acción de registrarse
	 */
	@FXML
    void registrarse(ActionEvent event) {
    	app.mostrarResgistrarse();
    }


}
