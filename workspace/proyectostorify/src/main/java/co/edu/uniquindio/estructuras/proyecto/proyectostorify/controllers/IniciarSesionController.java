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
	 * @param admin
	 */
	public void iniciarSesionAdministrador(Administrador admin) {
		ventana.close();
		mfm.setUsuarioSesion(admin);
		app.mostrarAdministrador();

	}

	/**
	 * Inicia sesión para un usuario comun
	 * @param usuario
	 */
	public void iniciarSesionUsuario(Usuario usuario) {
		ventana.close();
		mfm.iniciarSesionUsuario(usuario);
		app.mostrarUsuario();

	}

	/**
	 * Metodo que se ejecuta al presionar el botón "Iniciar Sesion".
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
	 * 
	 * @param event
	 */
	@FXML
    void registrarse(ActionEvent event) {
    	app.mostrarResgistrarse();
    }


}
