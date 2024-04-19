package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;

import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.*;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.TiendaUtil;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class AdministradorCancionesController {

	@FXML
	private Button btnAdiministrarCanciones;

	@FXML
	private Button btnGuardar;

	@FXML
	private Button btnSeleccionarCaratula;

	@FXML
	private TableColumn<Cancion, String> columnAlbum;

	@FXML
	private TableColumn<Artista, String> columnCodigo;

	@FXML
	private TableColumn<Cancion, String> columnDuracion;

	@FXML
	private TableColumn<Cancion, String> columnGenero;

	@FXML
	private TableColumn<Artista, String> columnGrupo;

	@FXML
	private TableColumn<Artista, String> columnNacionalidad;

	@FXML
	private TableColumn<Artista, String> columnNombreArtista;

	@FXML
	private TableColumn<Cancion, String> columnNombreCancion;

	@FXML
	private Button btnCerrarSesion;

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
	private ComboBox<String> cmbGenero;

	@NonNull
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
	private TableView<Artista> tableArtistas;

	@FXML
	private TableView<Cancion> tableCanciones;

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

	private CircularList<Cancion> listaCanciones = new CircularList<Cancion>();

	private

	@FXML void initialize() {

		listaCanciones = mfm.obtenerListaCaciones();
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

	@FXML
	void administrarCanciones(ActionEvent event) {
		app.mostrarAdministradorCanciones();
	}

	@FXML
	void cerrarSesion(ActionEvent event) {
		app.mostrarIniciarSesion();

	}

	public void actualizarTablaCanciones() {
		ObservableList<Cancion> listaCancionProperty = FXCollections.observableArrayList();
		for (Cancion cancion : listaCanciones) {
			listaCancionProperty.add(cancion);

		}
		tableCanciones.setItems(listaCancionProperty);
		columnNombreCancion
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombreCancion()));
		columnAlbum
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombreAlbum()));
		columnDuracion
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getDuracion()));
		columnGenero.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getGenero()));

	}

	@FXML
	void guardarCancion(ActionEvent event) {
		Cancion newCancion = new Cancion();
		newCancion.setCodigo(TiendaUtil.generarCadenaAleatoria());
		newCancion.setAnio(txtAnio.getText());
		newCancion.setCaratula(imageCaratula.getImage().getUrl());
		newCancion.setDuracion(txtDuracion.getText());
		newCancion.setGenero(Genero.getEstadoByString(cmbGenero.getSelectionModel().getSelectedItem()));
		newCancion.setNombreCancion(txtCancion.getText());
		newCancion.setNombreAlbum(txtAlbum.getText());
		newCancion.setUrl(txtUrl.getText());
		mfm.agregarCancion(newCancion, tableArtistas.getSelectionModel().getSelectedItem());
		actualizarTablaCanciones();
	}

	@FXML
	void seleccionarCaratula(ActionEvent event) {

	}

}
