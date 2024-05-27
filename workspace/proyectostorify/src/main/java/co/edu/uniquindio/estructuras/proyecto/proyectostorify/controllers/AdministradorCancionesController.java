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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	private TextField txtDuracion;

	private File archivoImagenCaratula;
	private File archivoAudioCancion;

	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana = mfm.getVentana();
	private App app = mfm.getAplicacion();

	private CircularList<Cancion> listaCanciones = new CircularList<Cancion>();
	private CircularList<Artista> listaArtistasSeleccionados = new CircularList<Artista>();

	/**
	 * 
	 */
	@FXML
	void initialize() {
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
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	void administrarCanciones(ActionEvent event) {
		app.mostrarAdministradorCanciones();
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
	 */
	@FXML
	void actualizar() {
		Cancion cancion = tableCanciones.getSelectionModel().getSelectedItem();
		if (sonDatosValidos()) {
			if(cancion!=null) {
				File archivoCopia;
				if (imageCaratula.getImage()!=null) {
					String nombre;
					do {
						nombre=TiendaUtil.obtenerRutaCopiaOrganizada(archivoImagenCaratula.getName());
					} while (TiendaUtil.existeArchivoCaratula(nombre));
					archivoCopia = new File("src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/caratulasCanciones/"+nombre);
					try {
						Files.copy(archivoImagenCaratula.toPath(), archivoCopia.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					cancion.setCaratula(archivoCopia.toURI().toString());

				} else {
					cancion.setCaratula("");
				}
				cancion.setAnio(txtAnio.getText());
				cancion.setDuracion(txtDuracion.getText());
				cancion.setGenero(Genero.getEstadoByString(cmbGenero.getSelectionModel().getSelectedItem()));
				cancion.setNombreCancion(txtCancion.getText());
				cancion.setNombreAlbum(txtAlbum.getText());

				String audio;
				do {
					audio=TiendaUtil.obtenerRutaCopiaOrganizada(archivoAudioCancion.getName());
				} while (TiendaUtil.existeArchivoAudio(audio));
				archivoCopia = new File("src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/cancionesAudios/"
						+audio);
				try {
					Files.copy(archivoAudioCancion.toPath(), archivoCopia.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				cancion.setUrl(audio);
			} else {
				InterfazFXUtil.mostrarMensaje("Cancion no seleccionada", "Cancion no seleccionada para actualizar");
			}
		}
	}
	
	/**
	 * 
	 */
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
	
	/**
	 * 
	 */
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
	
	/**
	 * 
	 * @return
	 */
	public boolean sonDatosValidos() {
		boolean sonDatosValidos = false;
		String msj = "";
		if (InterfazFXUtil.estaCampoVacio(txtCancion)) {
			msj+="Debe indicar el nombre de la cancion\n";
		}
		if (InterfazFXUtil.estaCampoVacio(txtAlbum)) {
			msj+="Debe indicar el album al que pertenece la cancion\n";
		}
		try {
    		int valor=Integer.parseInt(txtAnio.getText());
			if (valor<0) {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			if (txtAnio.getText().trim().equals("")) {
				msj+="El a\u00F1o no debe estar vacio\n";
			} else {
				msj+="A\u00F1o indicado no valido\n";
			}
		}
		if (InterfazFXUtil.estaCampoVacio(txtDuracion)) {
			msj+="Debe indicar la duracion\n";
		} else if (!txtDuracion.getText().trim().matches("^[0-9:]+$")) {
			msj+="Duracion no valida\n";
		}
		if (archivoAudioCancion==null) {
			msj += "Debe seleccionar el audio de la cancion\n";
		}
		if (InterfazFXUtil.estaCampoVacio(cmbGenero)) {
			msj+="Debe indicar el genero de la cancion\n";
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
	 * @param event
	 */
	@FXML
	void guardarCancion(ActionEvent event) {
		if (sonDatosValidos()) {
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
				} while (TiendaUtil.existeArchivoCaratula(nombre));
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

			String audio;
			do {
				audio=TiendaUtil.obtenerRutaCopiaOrganizada(archivoAudioCancion.getName());
			} while (TiendaUtil.existeArchivoAudio(audio));
			File archivoCopia = new File("src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/cancionesAudios/"
					+audio);
			try {
				Files.copy(archivoAudioCancion.toPath(), archivoCopia.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			newCancion.setUrl(audio);

			newCancion.setLstArtistas(listaArtistasSeleccionados);
			mfm.agregarCancionesArtistas(listaArtistasSeleccionados, newCancion);
			mfm.agregarCancion(newCancion);
			listaArtistasSeleccionados.clear();
			actualizarTablaCanciones();
		}
	}
	
	/**
	 * 
	 */
	@FXML
	void ponerDatosCancion() {
		
		Cancion cancion= tableCanciones.getSelectionModel().getSelectedItem();
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
			archivoAudioCancion=TiendaUtil.obtenerArchivoAudio(cancion);
		}
	}
	
	/**
	 * 
	 * @param event
	 */
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
	
	/**
	 * 
	 * @param event
	 */
    @FXML
    void seleccionarArtista(ActionEvent event) {
    	Artista artistaSeleccionado = tableArtistas.getSelectionModel().getSelectedItem();
    	if (!listaArtistasSeleccionados.contains(artistaSeleccionado)) {
    		listaArtistasSeleccionados.add(artistaSeleccionado);
    		
    		
    	}else {
    		JOptionPane.showMessageDialog(null, "El artista ya fue seleccionado");
    	}
    }
	
	/**
	 * 
	 */
    @FXML
    void seleccionarAudioCancion() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().add(
    			new FileChooser.ExtensionFilter("Archivos MP3", "*.mp3"));
    	archivoAudioCancion = fileChooser.showOpenDialog(null);
    }
	
	/**
	 * 
	 */
    @FXML
    void reproducirAudioCancion() {
    	if (archivoAudioCancion!=null) {
    		CircularList<File> cancion=new CircularList<File>();
        	cancion.add(archivoAudioCancion);
        	mfm.mostrarReproductorAudio(cancion);
    	} else {
    		InterfazFXUtil.mostrarMensaje("No se puede reproducir cancion", "No hay audio para reproducir");
    	}
    }
	
//	/**
//	 * 
//	 */
//    @FXML
//    void generosPopulares() {
//    	CircularList<Genero> generosPopulares=mfm.obtenerGenerosPopulares();
//    	String msj="";
//    	if (generosPopulares.size()>1) {
//    		msj+="Los generos mas populares son :\n";
//    		for (Genero genero : generosPopulares) {
//    			msj+=genero.toString()+"\n";
//			}
//    	} else {
//    		msj+="El genero mas popular es :"+generosPopulares.get(0).toString();
//    	}
//    	InterfazFXUtil.mostrarMensaje("Popularidad de los generos", msj);
//    }
    @FXML
    void generosPopulares() {
        CircularList<Genero> generosPopulares = mfm.obtenerGenerosPopulares();
        String msj = "";
        
        if (generosPopulares.size() > 1) {
            msj += "Los géneros más populares son:\n";
            for (Genero genero : generosPopulares) {
                msj += "- " + genero.toString() + "\n";
            }
        } else {
            msj += "El género más popular es: " + generosPopulares.get(0).toString();
        }

        // Crear una alerta personalizada
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Popularidad de los Géneros");
        alert.setHeaderText("Información sobre los géneros musicales más populares");
        alert.setContentText(msj);

        // Añadir un icono a la alerta (opcional)
        Image icon = new Image(getClass().getResourceAsStream("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/images/logo.png"));
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        alert.setGraphic(imageView);

        // Personalizar el estilo de la alerta
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/styles/tryalert.css").toExternalForm());
        
        // Mostrar la alerta y esperar a que el usuario cierre la ventana
        alert.showAndWait();
    }


}
