package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;

import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree.BinaryTree;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Artista;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Cancion;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.TiendaUtil;
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
import javafx.stage.Stage;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class AdministradorArtistasController {


	@FXML
	private Button btnAdiministrarCanciones;


	@FXML
	private TableColumn<Artista, String> columnCodigo;

	@FXML
	private TableColumn<Artista, String> columnGrupo;

	@FXML
	private TableColumn<Artista, String> columnNacionalidad;

	@FXML
	private TableColumn<Artista, String> columnNombre;

	@FXML
	private Button btnCerrarSesion;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnActualizar;

	@FXML
	private Button btnCrear;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnVolver;

	@FXML
	private ComboBox<String> cmbGrupo;

	@FXML
	private Label lblGrupo;

	@FXML
	private Label lblNacionalidad;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblTitulo;

	@FXML
	private TableView<Artista> tableArtistas;

	@FXML
	private TextField txtNacionalidad;

	@FXML
	private TextField txtNombre;

	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana = mfm.getVentana();
	private App app = mfm.getAplicacion();

	private CircularList<Artista> listaArtista;;

	@FXML
	void initialize() {
		
		tableArtistas.getItems().clear();
		ObservableList<Artista> listaArtistasProperty=FXCollections.observableArrayList();
		for (Artista artista : mfm.obtenerArtistas()) {
			listaArtistasProperty.add(artista);
		}
		tableArtistas.setItems(listaArtistasProperty);
		ObservableList<String> lista = FXCollections.observableArrayList();
		lista.add("Si");
		lista.add("No");
		cmbGrupo.setItems(lista);
	}

	private void actualizarTablaArtistas() {
		listaArtista = mfm.obtenerArtistas();
		System.out.println(listaArtista.toString());
		ObservableList<Artista> listaArtistasProperty = FXCollections.observableArrayList();
		for (Artista artista : listaArtista) {
			listaArtistasProperty.add(artista);
		}
		System.out.println(listaArtistasProperty.toString());
		
		tableArtistas.setItems(listaArtistasProperty);
		columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombre()));
		columnCodigo.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getCodigo()));
		columnGrupo.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().isEsGrupo()));
		columnNacionalidad
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNacionalidad()));
		tableArtistas.refresh();
	}

	@FXML
	void cerrarSesion(ActionEvent event) {
		app.mostrarIniciarSesion();

	}

	@FXML
	void actualizarArtista(ActionEvent event) {

	}

	@FXML
	void administrarCanciones(ActionEvent event) {

	}

	@FXML
	void crearArtista(ActionEvent event) {
		Artista newArtista = new Artista();
		newArtista.setCodigo(TiendaUtil.generarCadenaAleatoria());
		if (cmbGrupo.getSelectionModel().getSelectedItem() == "No") {
			newArtista.setEsGrupo(false);
		} else {
			newArtista.setEsGrupo(true);
		}
		newArtista.setNacionalidad(txtNacionalidad.getText());
		newArtista.setNombre(txtNombre.getText());
		mfm.agregarArtista(newArtista);
		System.out.println(mfm.obtenerArtistas().size());
		actualizarTablaArtistas();
	}

	@FXML
	void eliminarArtista(ActionEvent event) {

	}

}
