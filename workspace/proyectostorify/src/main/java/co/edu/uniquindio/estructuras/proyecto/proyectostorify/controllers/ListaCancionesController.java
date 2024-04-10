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
public class ListaCancionesController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBusquedaConjuntiva;

	@FXML
	private Button btnBusquedaDisyuntiva;

	@FXML
	private Button btnDeshacer;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnRehacer;

	@FXML
	private ComboBox<?> cmbGenero;

	@FXML
	private ImageView imageCaratula;

	@FXML
	private Label lblAlbum;

	@FXML
	private Label lblAlbumCancion;

	@FXML
	private Label lblAnio;

	@FXML
	private Label lblAnioCancion;

	@FXML
	private Label lblAnioDeLaCancion;

	@FXML
	private Label lblCancion;

	@FXML
	private Label lblCodigo;

	@FXML
	private Label lblCodigoCancion;

	@FXML
	private Label lblDuracion;

	@FXML
	private Label lblDuracionCancion;

	@FXML
	private Label lblDuracionDeLaCancion;

	@FXML
	private Label lblGenero;

	@FXML
	private Label lblGeneroCancion;

	@FXML
	private Label lblGeneroDeLaCancion;

	@FXML
	private Label lblNombreCancion;

	@FXML
	private Label lblNombreDeLaCancion;

	@FXML
	private Label lblNombreDelAlbum;

	@FXML
	private Label lblTitulo;

	@FXML
	private Label lblUrl;

	@FXML
	private Label lblUrlCancion;

	@FXML
	private TableView<?> tableCanciones;

	@FXML
	private TextField txtAnio;

	@FXML
	private TextField txtDuracion;

	@FXML
	private TextField txtNombreAlbum;

	@FXML
	private TextField txtNombreCancion;
	
	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana = mfm.getVentana();
	private App app = mfm.getAplicacion();

	@FXML
	void initialize() {
		assert btnBusquedaConjuntiva != null
				: "fx:id=\"btnBusquedaConjuntiva\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert btnBusquedaDisyuntiva != null
				: "fx:id=\"btnBusquedaDisyuntiva\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert btnDeshacer != null
				: "fx:id=\"btnDeshacer\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert btnEliminar != null
				: "fx:id=\"btnEliminar\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert btnRehacer != null
				: "fx:id=\"btnRehacer\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert cmbGenero != null : "fx:id=\"cmbGenero\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert imageCaratula != null
				: "fx:id=\"imageCaratula\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblAlbum != null : "fx:id=\"lblAlbum\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblAlbumCancion != null
				: "fx:id=\"lblAlbumCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblAnio != null : "fx:id=\"lblAnio\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblAnioCancion != null
				: "fx:id=\"lblAnioCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblAnioDeLaCancion != null
				: "fx:id=\"lblAnioDeLaCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblCancion != null
				: "fx:id=\"lblCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblCodigo != null : "fx:id=\"lblCodigo\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblCodigoCancion != null
				: "fx:id=\"lblCodigoCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblDuracion != null
				: "fx:id=\"lblDuracion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblDuracionCancion != null
				: "fx:id=\"lblDuracionCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblDuracionDeLaCancion != null
				: "fx:id=\"lblDuracionDeLaCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblGenero != null : "fx:id=\"lblGenero\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblGeneroCancion != null
				: "fx:id=\"lblGeneroCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblGeneroDeLaCancion != null
				: "fx:id=\"lblGeneroDeLaCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblNombreCancion != null
				: "fx:id=\"lblNombreCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblNombreDeLaCancion != null
				: "fx:id=\"lblNombreDeLaCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblNombreDelAlbum != null
				: "fx:id=\"lblNombreDelAlbum\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblTitulo != null : "fx:id=\"lblTitulo\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblUrl != null : "fx:id=\"lblUrl\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert lblUrlCancion != null
				: "fx:id=\"lblUrlCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert tableCanciones != null
				: "fx:id=\"tableCanciones\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert txtAnio != null : "fx:id=\"txtAnio\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert txtDuracion != null
				: "fx:id=\"txtDuracion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert txtNombreAlbum != null
				: "fx:id=\"txtNombreAlbum\" was not injected: check your FXML file 'ListaCanciones.fxml'.";
		assert txtNombreCancion != null
				: "fx:id=\"txtNombreCancion\" was not injected: check your FXML file 'ListaCanciones.fxml'.";

	}

}
