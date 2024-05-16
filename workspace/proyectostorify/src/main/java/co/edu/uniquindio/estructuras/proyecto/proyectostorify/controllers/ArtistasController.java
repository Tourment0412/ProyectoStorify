package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.io.File;
import java.net.URL;


import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Artista;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Cancion;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Usuario;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.InterfazFXUtil;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.TiendaUtil;
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
import javafx.scene.image.Image;
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
    	CircularList<Artista> listTemp = new CircularList<Artista>();
    	for (Artista artista : listaArtista) {
    		if (artista.getNombre().equalsIgnoreCase(txtBuscarNombre.getText().trim())){
    			listTemp.add(artista);
    		}
    	}
    	actualizarTablaArtistas(listTemp);
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
		Cancion c = tableCanciones.getSelectionModel().getSelectedItem();
		CircularList<Cancion> favoritas = ((Usuario)mfm.getUsuarioSesion()).getLstCancionesFavoritas();
		if(!favoritas.contains(c)) {
			favoritas.add(c);
			InterfazFXUtil.mostrarMensaje("Cancion ya guardada", "Cancion Guardada En Favoritos");
		}else {
			InterfazFXUtil.mostrarMensaje("Cancion ya guardada", "La cancion ya se encuentra en su lista de Favotitos");
		}
		
		
	}

	@FXML
	void guardarPlaylist(ActionEvent event) {
		Cancion c = tableCanciones.getSelectionModel().getSelectedItem();
		mfm.guardarPlayListUsuario(c);
		
		
	}

	@FXML
	void rehacerSeleccion(ActionEvent event) {

	}	
	
	@FXML
    void mostrarCancionesArtista(MouseEvent event) {
    	Artista artista = tableArtistas.getSelectionModel().getSelectedItem();
    	ListaDoble<Cancion> cancionesArtista = artista.getLstCanciones();
    	actualizarTablaCanciones(cancionesArtista);
    	
    	
    }

    @FXML
    void mostrarDetallesCanciones(MouseEvent event) {
    	Cancion cancion = tableCanciones.getSelectionModel().getSelectedItem();
		if(cancion!=null) {
			if (cancion.getCaratula().equals("")) {
				imageCaratula.setImage(null);
			} else {
				imageCaratula.setImage(new Image(cancion.getCaratula()));
			}
			lblCodigo.setText(cancion.getUrl());
			lblAnio.setText(cancion.getAnio());
			lblDuracion.setText(cancion.getDuracion());
			lblGenero.setText(cancion.getGenero().toString());
			lblCancion.setText(cancion.getNombreCancion());
			lblAlbum.setText(cancion.getNombreAlbum());
			lblUrl.setText(cancion.getUrl());
		}
    }

    @FXML
    void refrescarTabla(ActionEvent event) {
    	actualizarTablaArtistas(listaArtista);
    	actualizarTablaCanciones(new ListaDoble<Cancion>());
    }

	private void actualizarTablaArtistas(CircularList<Artista> listaArtista) {
		ObservableList<Artista> listaArtistasProperty = FXCollections.observableArrayList();
		for (Artista artista : listaArtista) {
			listaArtistasProperty.add(artista);
		}

		tableArtistas.setItems(listaArtistasProperty);
		columNombreArtista.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombre()));
		columCodigoArtista.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getCodigo()));
		columGrupoArtista.setCellValueFactory(cellData -> {
			if (cellData.getValue().isEsGrupo()) {
				return new SimpleStringProperty("Grupo");
			} else {
				return new SimpleStringProperty("No grupo");
			}
		});
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
	
	@FXML
	void reproducirCancion() {
		Cancion cancion = tableCanciones.getSelectionModel().getSelectedItem();
		if(cancion!=null) {
			CircularList<File> archivo=new CircularList<File>();
			archivo.add(TiendaUtil.obtenerArchivoAudio(cancion));
			mfm.mostrarReproductorAudio(archivo);
		} else {
			InterfazFXUtil.mostrarMensaje("Cancion no seleecionada", "No ha seleccionado una cancion para reproducir");
		}
	}

}
