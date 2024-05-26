package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.io.File;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.*;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CancionesTiendaController {
	
    @FXML
    private TableColumn<Cancion, String> columNombreCancion;

    @FXML
    private Button btnBusquedaConjuntiva;

    @FXML
    private Label lblAnio;

    @FXML
    private Label lblTitulo;

    @FXML
    private Button btnRehacer;

    @FXML
    private TextField txtNombreCancion;

    @FXML
    private Label lblCancion;

    @FXML
    private ComboBox<String> cmbGenero;

    @FXML
    private Label lblDuracion;

    @FXML
    private Label lblDuracionCancion;

    @FXML
    private TableColumn<Cancion, String> columDuracionCancion;

    @FXML
    private TableColumn<Cancion, String> columAlbumCancion;

    @FXML
    private Label lblUrlCancion;

    @FXML
    private Label lblCanciones;

    @FXML
    private Button btnDeshacer;

    @FXML
    private ImageView imageCaratula;

    @FXML
    private Label lblCodigoCancion;

    @FXML
    private Label lblGeneroCancion;

    @FXML
    private Label lblAlbum;

    @FXML
    private Button btnGuardarFavoritos;

    @FXML
    private Label lblDuracionDeLaCancion;

    @FXML
    private Label lblNombreCancion;

    @FXML
    private Label lblGeneroDeLaCancion;

    @FXML
    private Label lblNombreDelAlbum;

    @FXML
    private Label lblAnioCancion;

    @FXML
    private Label lblNombreDeLaCancion;

    @FXML
    private TextField txtDuracion;

    @FXML
    private Label lblGenero;

    @FXML
    private Button btnBusquedaDisyuntiva;

    @FXML
    private Label lblAnioDeLaCancion;

    @FXML
    private TableColumn<Cancion, String> columGeneroCancion;

    @FXML
    private Button btnGuardarPlaylist;

    @FXML
    private TextField txtAnio;

    @FXML
    private TableView<Cancion> tableCanciones;

    @FXML
    private TextField txtNombreAlbum;

    @FXML
    private Label lblUrl;

    @FXML
    private Label lblCodigo;
    
    private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana = mfm.getVentana();
	private App app = mfm.getAplicacion();
	CircularList<Cancion> listaCanciones=mfm.obtenerCancionesArtistas();
	
	/**
	 * 
	 */
    @FXML
	void initialize() {
    	ObservableList<String> generos = FXCollections.observableArrayList();
		generos.add("Pop");
		generos.add("Rock");
		generos.add("Punk");
		generos.add("Reggaeton");
		generos.add("Electronica");
		cmbGenero.setItems(generos);
    	actualizarTablaCanciones(listaCanciones);
	}
	
	/**
	 * 
	 * @param listaCanciones
	 */
	public void actualizarTablaCanciones(CircularList<Cancion> listaCanciones) {
		tableCanciones.getItems().clear();
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
		columGeneroCancion
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getGenero()));

		tableCanciones.refresh();
	}
	
	/**
	 * 
	 */
    @FXML
    void mostrarDetallesCanciones() {
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
	
	/**
	 * 
	 * @param event
	 */
    @FXML
    void deshacerSeleccion(ActionEvent event) {

    }
	
	/**
	 * 
	 * @param event
	 */
    @FXML
    void rehacerSeleccion(ActionEvent event) {

    }
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	void guardarFavoritos(ActionEvent event) {
		Cancion c = tableCanciones.getSelectionModel().getSelectedItem();
		if (c!=null) {
			CircularList<Cancion> favoritas = ((Usuario)mfm.getUsuarioSesion()).getLstCancionesFavoritas();
			if(!favoritas.contains(c)) {
				favoritas.add(c);
				mfm.guardarAccion(c, "ADDfavorita");
				InterfazFXUtil.mostrarMensaje("Cancion ya guardada", "Cancion Guardada En Favoritos");
			}else {
				InterfazFXUtil.mostrarMensaje("Cancion ya guardada", "La cancion ya se encuentra en su lista de Favoritos");
			}
		} else {
			InterfazFXUtil.mostrarMensaje("Cancion no seleccionada", "No hay una cancion seleccionada para agregar a favoritos");
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	void guardarPlaylist(ActionEvent event) {
		Cancion c = tableCanciones.getSelectionModel().getSelectedItem();
		if (c!=null) {
			CircularList<Cancion> guardadas = ((Usuario)mfm.getUsuarioSesion()).getLstCancionesGuardadas();
			if(!guardadas.contains(c)) {
				guardadas.add(c);
				mfm.guardarAccion(c, "ADDplaylist");
				InterfazFXUtil.mostrarMensaje("Cancion ya guardada", "Cancion Guardada En playlist");
			}else {
				InterfazFXUtil.mostrarMensaje("Cancion ya guardada", "La cancion ya se encuentra en su lista de playlist");
			}
		} else {
			InterfazFXUtil.mostrarMensaje("Cancion no seleccionada", "No hay una cancion seleccionada para agregar a playlist");
		}
	}
	
	/**
	 * 
	 * @param event
	 */
    @FXML
    void reproducirCancion(ActionEvent event) {
		Cancion cancion = tableCanciones.getSelectionModel().getSelectedItem();
		if(cancion!=null) {
			CircularList<File> archivo=new CircularList<File>();
			archivo.add(TiendaUtil.obtenerArchivoAudio(cancion));
			mfm.mostrarReproductorAudio(archivo);
		} else {
			InterfazFXUtil.mostrarMensaje("Cancion no seleecionada", "No ha seleccionado una cancion para reproducir");
		}
    }
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	void busquedaO(ActionEvent event) {
		CircularList<Cancion> listaTemp = new CircularList<Cancion>();
		String nombreC = txtNombreCancion.getText();
		String albumC = txtNombreAlbum.getText();
		String anioC = txtAnio.getText();
		String duracionC = txtDuracion.getText();
		String generoC = cmbGenero.getSelectionModel().getSelectedItem();
		for (Cancion c : listaCanciones) {
			if (c.getNombreCancion().equalsIgnoreCase(nombreC) || c.getNombreAlbum().equalsIgnoreCase(albumC)
					|| c.getAnio().equalsIgnoreCase(anioC) || c.getDuracion().equalsIgnoreCase(duracionC)
					|| c.getGenero().toString().equals(generoC)) {
				listaTemp.add(c);
			}
		}
		actualizarTablaCanciones(listaTemp);

	}
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	void busquedaY(ActionEvent event) {
		CircularList<Cancion> listaTemp = new CircularList<Cancion>();
		String nombreC = txtNombreCancion.getText();
		String albumC = txtNombreAlbum.getText();
		String anioC = txtAnio.getText();
		String duracionC = txtDuracion.getText();
		String generoC = cmbGenero.getSelectionModel().getSelectedItem();

		for (Cancion c : listaCanciones) {
			boolean cumpleParametros = true;

			if (!nombreC.isEmpty() && !c.getNombreCancion().equalsIgnoreCase(nombreC))
				cumpleParametros = false;
			if (!albumC.isEmpty() && !c.getNombreAlbum().equalsIgnoreCase(albumC))
				cumpleParametros = false;
			if (!anioC.isEmpty() && !c.getAnio().equalsIgnoreCase(anioC))
				cumpleParametros = false;
			if (!duracionC.isEmpty() && !c.getDuracion().equalsIgnoreCase(duracionC))
				cumpleParametros = false;
			if (generoC != null && !c.getGenero().toString().equalsIgnoreCase(generoC))
				cumpleParametros = false;

			if (cumpleParametros) {
				listaTemp.add(c);
			}
		}
		actualizarTablaCanciones(listaTemp);
	}

}
