package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.io.File;
import java.net.URL;

import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree.BinaryTree;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Artista;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Cancion;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.InterfazFXUtil;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
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
	
	/**
	 * 
	 * @return
	 */
	public boolean sonDatosValidos() {
		boolean sonDatosValidos = false;
		String msj = "";
		if (InterfazFXUtil.estaCampoVacio(txtNombre)) {
			msj+="Debe indicar el nombre del artista\n";
		}
		if (InterfazFXUtil.estaCampoVacio(txtNacionalidad)) {
			msj+="Debe indicar la nacionalidad del artista\n";
		}
		if (InterfazFXUtil.estaCampoVacio(cmbGrupo)) {
			msj+="Debe indicar si es un grupo o no\n";
		}
		if (msj.equals("")) {
			sonDatosValidos = true;
		} else {
			InterfazFXUtil.mostrarMensaje("Entradas no validas", "Entradas no validas", msj, AlertType.ERROR);
		}
		return sonDatosValidos;
	}

	
	/**
	 * 
	 */
	@FXML
	void initialize() {
		actualizarTablaArtistas();
		ObservableList<String> lista = FXCollections.observableArrayList();
		lista.add("Si");
		lista.add("No");
		cmbGrupo.setItems(lista);
	}

	
	/**
	 * 
	 */
	private void actualizarTablaArtistas() {

		ObservableList<Artista> listaArtistasProperty = FXCollections.observableArrayList();
		for (Artista artista : mfm.obtenerListaArtistas().toCircularList()) {
			listaArtistasProperty.add(artista);
		}
		tableArtistas.setItems(listaArtistasProperty);
		columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombre()));
		columnCodigo.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getCodigo()));
		columnGrupo.setCellValueFactory(cellData -> {
			if (cellData.getValue().isEsGrupo()) {
				return new SimpleStringProperty("Grupo");
			} else {
				return new SimpleStringProperty("No grupo");
			}
		});
		columnNacionalidad
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNacionalidad()));
		tableArtistas.refresh();
	}
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	void cerrarSesion(ActionEvent event) {
		app.mostrarIniciarSesion();

	}
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	void actualizarArtista(ActionEvent event) {
		int selectedIndex = tableArtistas.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			if (sonDatosValidos()) {
				Artista artistaElejido = tableArtistas.getSelectionModel().getSelectedItem();
				artistaElejido.setNombre(txtNombre.getText());
				artistaElejido.setNacionalidad(txtNacionalidad.getText());
				if (cmbGrupo.getSelectionModel().getSelectedItem() == "No") {
					artistaElejido.setEsGrupo(false);
				} else {
					artistaElejido.setEsGrupo(true);
				}
			}
		} else {
			InterfazFXUtil.mostrarMensaje("Artista no seleccionado", "No ha seleccionado nigun artista a actualizar");
		}
		actualizarTablaArtistas();
	}
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	void administrarCanciones(ActionEvent event) {

	}
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	void crearArtista(ActionEvent event) {
		Artista newArtista = new Artista();
		if (sonDatosValidos()) {
			newArtista.setCodigo(TiendaUtil.generarCadenaAleatoria());
			if (cmbGrupo.getSelectionModel().getSelectedItem() == "No") {
				newArtista.setEsGrupo(false);
			} else {
				newArtista.setEsGrupo(true);
			}
			newArtista.setNombre(txtNombre.getText());
			newArtista.setNacionalidad(txtNacionalidad.getText());
			mfm.agregarArtista(newArtista);
			actualizarTablaArtistas();
		}
	}
	
	/**
	 * 
	 */
	@FXML
	void ponerDatos() {
		int selectedIndex = tableArtistas.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Artista artistaElejido = tableArtistas.getSelectionModel().getSelectedItem();
			txtNombre.setText(artistaElejido.getNombre());
			txtNacionalidad.setText(artistaElejido.getNacionalidad());
			if (artistaElejido.isEsGrupo()) {
				cmbGrupo.setValue("Si");
			} else {
				cmbGrupo.setValue("No");
			}
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	void eliminarArtista(ActionEvent event) {
		int selectedIndex = tableArtistas.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Artista artistaElejido = tableArtistas.getSelectionModel().getSelectedItem();
			tableArtistas.getItems().remove(artistaElejido);
			mfm.eliminarArtista(artistaElejido);
		} else {
			InterfazFXUtil.mostrarMensaje("Artista no seleccionado", "No ha seleccionado nigun artista a eliminar");
		}
	}
	
	/**
	 * 
	 */
	@FXML
	void importar() {
		File archivoArtistas;
		FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de Texto", "*.txt"));
        archivoArtistas = fileChooser.showOpenDialog(null);
        if (archivoArtistas != null) {
        	mfm.importarArtistas(archivoArtistas);
        	actualizarTablaArtistas();
            InterfazFXUtil.mostrarMensaje("Importacion existosa", "Las canciones y artistas fueron cargados correctamente");
        }
	}
	
	/**
	 * 
	 */
	@FXML
	void exportar() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccionar Carpeta");
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            File txtFile = new File(selectedDirectory, "archivoArtistas.txt");
            mfm.exportarArtistas(txtFile);
        }
	}

}
