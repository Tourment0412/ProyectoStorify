package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Artista;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Cancion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
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
public class ArtistasController {
	
    @FXML
    private Button btnCanciones;
    
    @FXML
    private Button btnDetalles;

	@FXML
	private Button btnBuscarNombre;

	@FXML
	private Button btnCerrarSesion;

	@FXML
	private Button btnDeshacer;

	@FXML
	private Button btnGuardarFavoritos;

	@FXML
	private Button btnGuardarPlaylist;

	@FXML
	private Button btnRehacer;

	@FXML
	private TableColumn<Cancion, String> columAlbumCancion;

	@FXML
	private TableColumn<Artista, String> columCodigoArtista;

	@FXML
	private TableColumn<Cancion, String> columDuracionCancion;

	@FXML
	private TableColumn<Cancion, String> columGeneroCancion;

	@FXML
	private TableColumn<Artista, String> columGrupoArtista;

	@FXML
	private TableColumn<Artista, String> columNacionalidadArtista;

	@FXML
	private TableColumn<Artista, String> columNombreArtista;

	@FXML
	private TableColumn<Cancion, String> columNombreCancion;

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
    private Button btnRefrescar;

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
	private TableView<Artista> tableArtistas;

	@FXML
	private TableView<Cancion> tableCanciones;

	@FXML
	private TextField txtBuscarNombre;

	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana = mfm.getVentana();
	private App app = mfm.getAplicacion();

	private CircularList<Artista> listaArtista = mfm.obtenerArtistas();
	private CircularList<Cancion> listaCanciones = new CircularList<Cancion>();

	@FXML
	void initialize() {
		actualizarTablaArtistas(listaArtista);
	}
	
    @FXML
    void buscarNombreArtista(ActionEvent event) {
    	Artista a = mfm.obtenerArtista(txtBuscarNombre.getText());
    	CircularList<Artista> listTemp = new CircularList<Artista>();
    	if(a!=null) {
    		listTemp.add(a);
    		actualizarTablaArtistas(listTemp);
    	}else {
    		JOptionPane.showMessageDialog(null, "Artista No Encontrado");
    	}
    	listTemp.clear();
    }

	@FXML
	void cerrarSesion(ActionEvent event) {
		app.mostrarIniciarSesion();

	}

	public CircularList<Artista> obtenerArtistasNombre() {
		CircularList<Artista> listaArtistas = mfm.obtenerArtistas();
		return listaArtistas;
	}

	@FXML
	void deshacerSeleccion(ActionEvent event) {

	}

	@FXML
	void guardarFavoritos(ActionEvent event) {

	}

	@FXML
	void guardarPlaylist(ActionEvent event) {

	}

	@FXML
	void rehacerSeleccion(ActionEvent event) {

	}	
	
    @FXML
    void mostrarCancionesArtista(ActionEvent event) {
    	Artista artista = tableArtistas.getSelectionModel().getSelectedItem();
    	ListaDoble<Cancion> cancionesArtista = artista.getLstCanciones();
    	actualizarTablaCanciones(cancionesArtista);
    }

    @FXML
    void mostrarDetallesCanciones(ActionEvent event) {

    }

    @FXML
    void refrescarTabla(ActionEvent event) {
    	actualizarTablaArtistas(listaArtista);
    	actualizarTablaCanciones(new ListaDoble<Cancion>());
    }

	private void actualizarTablaArtistas(CircularList<Artista> listaArtista) {
		System.out.println(listaArtista.toString());
		ObservableList<Artista> listaArtistasProperty = FXCollections.observableArrayList();
		for (Artista artista : listaArtista) {
			listaArtistasProperty.add(artista);
		}
		System.out.println(listaArtistasProperty.toString());

		tableArtistas.setItems(listaArtistasProperty);
		columNombreArtista.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombre()));
		columCodigoArtista.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getCodigo()));
		columGrupoArtista.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().isEsGrupo()));
		columNacionalidadArtista
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNacionalidad()));
		tableArtistas.refresh();
	}
	
	public void actualizarTablaCanciones(ListaDoble<Cancion> listaCanciones) {
		ObservableList<Cancion> listaCancionProperty = FXCollections.observableArrayList();
		for (Cancion cancion : listaCanciones) {
			listaCancionProperty.add(cancion);

		}
		tableCanciones.setItems(listaCancionProperty);
		columNombreCancion
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombreCancion()));
		columAlbumCancion
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombreAlbum()));
		columDuracionCancion
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getDuracion()));
		columGeneroCancion.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getGenero()));
		tableCanciones.refresh();
	}

}
