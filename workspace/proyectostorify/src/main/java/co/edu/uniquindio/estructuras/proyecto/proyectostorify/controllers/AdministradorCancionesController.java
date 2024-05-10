package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.*;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.InterfazFXUtil;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
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
    private Button btnSeleccionarArtista;

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
	private File archivoImagenCaratula;

	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana = mfm.getVentana();
	private App app = mfm.getAplicacion();

	private CircularList<Cancion> listaCanciones = new CircularList<Cancion>();
	private CircularList<Artista> listaArtistasSeleccionados = new CircularList<Artista>();



	@FXML void initialize() {
		listaCanciones = mfm.obtenerListaCaciones();
		ObservableList<String> lista = FXCollections.observableArrayList();
		lista.add("Rock");
		lista.add("Pop");
		lista.add("Punk");
		lista.add("Reggaeton");
		lista.add("Electronica");
		cmbGenero.setItems(lista);
		actualizarTablaArtistas();
		actualizarTablaCanciones();
	}

	@FXML
	void administrarCanciones(ActionEvent event) {
		app.mostrarAdministradorCanciones();
	}

	@FXML
	void cerrarSesion(ActionEvent event) {
		app.mostrarIniciarSesion();

	}

	@FXML
	void actualizar() {
		Cancion cancion = tableCanciones.getSelectionModel().getSelectedItem();
		if(cancion!=null) {
			if (imageCaratula.getImage()!=null) {
				String nombre;
				do {
					nombre=TiendaUtil.obtenerRutaCopiaOrganizada(archivoImagenCaratula.getName());
				} while (TiendaUtil.existeArchivo(nombre));
				 File archivoCopia = new File("src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/caratulasCanciones/"+nombre);
				 try {
	                    Files.copy(archivoImagenCaratula.toPath(), archivoCopia.toPath(), StandardCopyOption.REPLACE_EXISTING);
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
				 cancion.setCaratula(archivoCopia.toURI().toString());
				 cancion.setAnio(txtAnio.getText());
				 cancion.setDuracion(txtDuracion.getText());
				 cancion.setGenero(Genero.getEstadoByString(cmbGenero.getSelectionModel().getSelectedItem()));
				 cancion.setNombreCancion(txtCancion.getText());
				 cancion.setNombreAlbum(txtAlbum.getText());
				 cancion.setUrl(txtUrl.getText());
			} else {
				InterfazFXUtil.mostrarMensaje("Cancion no seleccionada", "Cancion no seleccionada para actualizar");
			}
		}
	}
	
	public void actualizarTablaArtistas() {
		ObservableList<Artista> listaArtistasProperty = FXCollections.observableArrayList();
		for (Artista artista : mfm.obtenerListaArtistas().toCircularList()) {
			listaArtistasProperty.add(artista);
		}
		tableArtistas.setItems(listaArtistasProperty);
		columnNombreArtista.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombre()));
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
		tableCanciones.refresh();
	}
	


	@FXML
	void guardarCancion(ActionEvent event) {
		Cancion newCancion = new Cancion();
		String codigo;
		do {
			codigo=TiendaUtil.generarCadenaAleatoria();
		} while(mfm.existeCodigoCancion(codigo));
		newCancion.setCodigo(codigo);
		if (imageCaratula.getImage()!=null) {
			String nombre;
			do {
				nombre=TiendaUtil.obtenerRutaCopiaOrganizada(archivoImagenCaratula.getName());
			} while (TiendaUtil.existeArchivo(nombre));
			 File archivoCopia = new File("src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/caratulasCanciones/"+nombre);
			 try {
                    Files.copy(archivoImagenCaratula.toPath(), archivoCopia.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
			 newCancion.setCaratula(archivoCopia.toURI().toString());
		}
		newCancion.setAnio(txtAnio.getText());
		newCancion.setDuracion(txtDuracion.getText());
		newCancion.setGenero(Genero.getEstadoByString(cmbGenero.getSelectionModel().getSelectedItem()));
		newCancion.setNombreCancion(txtCancion.getText());
		newCancion.setNombreAlbum(txtAlbum.getText());
		newCancion.setUrl(txtUrl.getText());
		newCancion.setLstArtistas(listaArtistasSeleccionados);
		mfm.agregarCancionesArtistas(listaArtistasSeleccionados, newCancion);
		mfm.agregarCancion(newCancion);
		listaArtistasSeleccionados.clear();
		actualizarTablaCanciones();
	}
	
	@FXML
	void ponerDatosCancion() {
		Cancion cancion = tableCanciones.getSelectionModel().getSelectedItem();
		if(cancion!=null) {
			if (cancion.getCaratula().equals("")) {
				imageCaratula.setImage(null);
			} else {
				imageCaratula.setImage(new Image(cancion.getCaratula()));
			}
			txtAnio.setText(cancion.getAnio());
			txtDuracion.setText(cancion.getDuracion());
			cmbGenero.setValue(cancion.getGenero().toString());
			txtCancion.setText(cancion.getNombreCancion());
			txtAlbum.setText(cancion.getNombreAlbum());
		}
	}

	@FXML
	void seleccionarCaratula(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.jpg", "*.png", "*.jpeg"));
        archivoImagenCaratula = fileChooser.showOpenDialog(null);
        if (archivoImagenCaratula != null) {
        	Image image = new Image(archivoImagenCaratula.toURI().toString());
        	imageCaratula.setImage(image);
        } else {
        	imageCaratula.setImage(null);
        }
	}
	
    @FXML
    void seleccionarArtista(ActionEvent event) {
    	Artista artistaSeleccionado = tableArtistas.getSelectionModel().getSelectedItem();
    	if (!listaArtistasSeleccionados.contains(artistaSeleccionado)) {
    		listaArtistasSeleccionados.add(artistaSeleccionado);
    		
    		
    	}else {
    		JOptionPane.showMessageDialog(null, "El artista ya fue seleccionado");
    	}
    }

}
