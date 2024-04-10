package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class AdministradorCancionesController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBuscarNombre;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnVolver;

	@FXML
	private ComboBox<?> cmbGenero;

	@FXML
	private ImageView imageCaratula;

	@FXML
	private Label lblAlbum;

	@FXML
	private Label lblAnioCancion;

	@FXML
	private Label lblCanciones;

	@FXML
	private Label lblCodigoCancion;

	@FXML
	private Label lblDuracionCancion;

	@FXML
	private Label lblGeneroCancion;

	@FXML
	private Label lblNombreArtista;

	@FXML
	private Label lblNombreCancion;

	@FXML
	private Label lblTitulo;

	@FXML
	private Label lblUrlCancion;

	@FXML
	private TableView<?> tableArtistas;

	@FXML
	private TableView<?> tableCanciones;

	@FXML
	private TextField txtAlbum;

	@FXML
	private TextField txtAnio;

	@FXML
	private TextField txtBuscarNombre;

	@FXML
	private TextField txtCancion;

	@FXML
	private TextField txtCodigo;

	@FXML
	private TextField txtDuracion;

	@FXML
	private TextField txtUrl;
	
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana = mfm.getVentana();
	private App app = mfm.getAplicacion();

	@FXML
	void initialize() {
		assert btnBuscarNombre != null
				: "fx:id=\"btnBuscarNombre\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert btnEliminar != null
				: "fx:id=\"btnEliminar\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert btnVolver != null
				: "fx:id=\"btnVolver\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert cmbGenero != null
				: "fx:id=\"cmbGenero\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert imageCaratula != null
				: "fx:id=\"imageCaratula\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert lblAlbum != null
				: "fx:id=\"lblAlbum\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert lblAnioCancion != null
				: "fx:id=\"lblAnioCancion\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert lblCanciones != null
				: "fx:id=\"lblCanciones\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert lblCodigoCancion != null
				: "fx:id=\"lblCodigoCancion\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert lblDuracionCancion != null
				: "fx:id=\"lblDuracionCancion\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert lblGeneroCancion != null
				: "fx:id=\"lblGeneroCancion\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert lblNombreArtista != null
				: "fx:id=\"lblNombreArtista\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert lblNombreCancion != null
				: "fx:id=\"lblNombreCancion\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert lblTitulo != null
				: "fx:id=\"lblTitulo\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert lblUrlCancion != null
				: "fx:id=\"lblUrlCancion\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert tableArtistas != null
				: "fx:id=\"tableArtistas\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert tableCanciones != null
				: "fx:id=\"tableCanciones\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert txtAlbum != null
				: "fx:id=\"txtAlbum\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert txtAnio != null
				: "fx:id=\"txtAnio\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert txtBuscarNombre != null
				: "fx:id=\"txtBuscarNombre\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert txtCancion != null
				: "fx:id=\"txtCancion\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert txtCodigo != null
				: "fx:id=\"txtCodigo\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert txtDuracion != null
				: "fx:id=\"txtDuracion\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";
		assert txtUrl != null
				: "fx:id=\"txtUrl\" was not injected: check your FXML file 'AdministradorCanciones.fxml'.";

	}

}
