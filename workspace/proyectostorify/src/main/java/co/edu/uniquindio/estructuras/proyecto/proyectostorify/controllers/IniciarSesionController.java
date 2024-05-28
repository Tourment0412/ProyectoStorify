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
import javafx.scene.shape.SVGPath;
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

	@FXML
	private Button toggleButton;

	@FXML
	private TextField txtContra;
	
	@FXML
    private SVGPath svgIcon;
	
	 private static final String EYE_OPEN = "M12 5C5.63636 5 2 12 2 12C2 12 5.63636 19 12 19C18.3636 19 22 12 22 12C22 12 18.3636 5 12 5Z M12 15C13.6569 15 15 13.6569 15 12C15 10.3431 13.6569 9 12 9C10.3431 9 9 10.3431 9 12C9 13.6569 10.3431 15 12 15Z";
	 private static final String EYE_CLOSED="M9.76404 5.29519C10.4664 5.10724 11.2123 5 12 5C15.7574 5 18.564 7.4404 20.2326 9.43934C21.4848 10.9394 21.4846 13.0609 20.2324 14.5609C20.0406 14.7907 19.8337 15.0264 19.612 15.2635M12.5 9.04148C13.7563 9.25224 14.7478 10.2437 14.9585 11.5M3 3L21 21M11.5 14.9585C10.4158 14.7766 9.52884 14.0132 9.17072 13M4.34914 8.77822C4.14213 9.00124 3.94821 9.22274 3.76762 9.43907C2.51542 10.9391 2.51523 13.0606 3.76739 14.5607C5.43604 16.5596 8.24263 19 12 19C12.8021 19 13.5608 18.8888 14.2744 18.6944";

	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana = mfm.getVentana();
	private App app = mfm.getAplicacion();

	/**
	 * Inicializa el controlador. Configura los eventos de teclado para los campos
	 * de texto para ingresar el nombre de usuario y la contraseña. Al presionar la
	 * tecla Enter en cualquiera de los campos, se ejecuta el método ingresar().
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
		
		txtContra.textProperty().bindBidirectional(txtContrasenia.textProperty());
	}

	@FXML
	void handleToggle(ActionEvent event) {
		if (txtContrasenia.isVisible()) {
			txtContrasenia.setManaged(false);
			txtContrasenia.setVisible(false);
			txtContra.setManaged(true);
			txtContra.setVisible(true);
            svgIcon.setContent(EYE_CLOSED);
        } else {
        	txtContrasenia.setManaged(true);
        	txtContrasenia.setVisible(true);
        	txtContra.setManaged(false);
        	txtContra.setVisible(false);
            svgIcon.setContent(EYE_OPEN);
        }
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
	 * Método que se ejecuta al presionar el botón "Iniciar Sesión". Verifica el
	 * tipo de cuenta e inicia sesión como Administrador o Usuario. Muestra un
	 * mensaje de advertencia si no se encuentra la cuenta.
	 */
	@FXML
	private void ingresar() {
		// Codigo para verificar el tipo de cuenta que va a ingresar (No borrar)
		txtContrasenia.setManaged(true);
    	txtContrasenia.setVisible(true);
    	txtContra.setManaged(false);
    	txtContra.setVisible(false);
        toggleButton.setText("Mostrar");
        
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
