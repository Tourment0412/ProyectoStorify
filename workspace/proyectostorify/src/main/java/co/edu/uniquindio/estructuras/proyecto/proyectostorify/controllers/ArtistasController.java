package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ArtistasController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBuscarNombre;

	@FXML
	private Button btnDeshacer;

	@FXML
	private Button btnGuardarFavoritos;

	@FXML
	private Button btnGuardarPlaylist;

	@FXML
	private Button btnRehacer;

	@FXML
	private ImageView imageCaratula;

	@FXML
	private Label lblAlbum;

	@FXML
	private Label lblAnio;

	@FXML
	private Label lblAnioCancion;

	@FXML
	private Label lblCancion;

	@FXML
	private Label lblCanciones;

	@FXML
	private Label lblCodigo;

	@FXML
	private Label lblCodigoCancion;

	@FXML
	private Label lblDuracion;

	@FXML
	private Label lblDuracionCancion;

	@FXML
	private Label lblGenero;

	@FXML
	private Label lblGeneroCancion;

	@FXML
	private Label lblNombreArtista;

	@FXML
	private Label lblNombreCancion;

	@FXML
	private Label lblTitulo;

	@FXML
	private Label lblUrl;

	@FXML
	private Label lblUrlCancion;

	@FXML
	private TableView<?> tableArtistas;

	@FXML
	private TableView<?> tableCanciones;

	@FXML
	private TextField txtBuscarNombre;

	@FXML
	void initialize() {
		assert btnBuscarNombre != null
				: "fx:id=\"btnBuscarNombre\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert btnDeshacer != null : "fx:id=\"btnDeshacer\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert btnGuardarFavoritos != null
				: "fx:id=\"btnGuardarFavoritos\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert btnGuardarPlaylist != null
				: "fx:id=\"btnGuardarPlaylist\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert btnRehacer != null : "fx:id=\"btnRehacer\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert imageCaratula != null
				: "fx:id=\"imageCaratula\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblAlbum != null : "fx:id=\"lblAlbum\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblAnio != null : "fx:id=\"lblAnio\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblAnioCancion != null
				: "fx:id=\"lblAnioCancion\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblCancion != null : "fx:id=\"lblCancion\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblCanciones != null : "fx:id=\"lblCanciones\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblCodigo != null : "fx:id=\"lblCodigo\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblCodigoCancion != null
				: "fx:id=\"lblCodigoCancion\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblDuracion != null : "fx:id=\"lblDuracion\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblDuracionCancion != null
				: "fx:id=\"lblDuracionCancion\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblGenero != null : "fx:id=\"lblGenero\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblGeneroCancion != null
				: "fx:id=\"lblGeneroCancion\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblNombreArtista != null
				: "fx:id=\"lblNombreArtista\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblNombreCancion != null
				: "fx:id=\"lblNombreCancion\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblTitulo != null : "fx:id=\"lblTitulo\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblUrl != null : "fx:id=\"lblUrl\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert lblUrlCancion != null
				: "fx:id=\"lblUrlCancion\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert tableArtistas != null
				: "fx:id=\"tableArtistas\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert tableCanciones != null
				: "fx:id=\"tableCanciones\" was not injected: check your FXML file 'Artistas.fxml'.";
		assert txtBuscarNombre != null
				: "fx:id=\"txtBuscarNombre\" was not injected: check your FXML file 'Artistas.fxml'.";

	}

}
