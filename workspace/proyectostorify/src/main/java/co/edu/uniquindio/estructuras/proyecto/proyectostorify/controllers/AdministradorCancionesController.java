package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

	private CircularList<Artista> listaArtista = mfm.obtenerArtistas();

	/**
	 * Método llamado al inicializar el controlador. Configura la lista de géneros
	 * musicales y actualiza las tablas de artistas y canciones.
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
	 * Abre la interfaz para administrar canciones.
	 * 
	 * @param event el evento que dispara la acción
	 */
	@FXML
	void administrarCanciones(ActionEvent event) {
		app.mostrarAdministradorCanciones();
	}

	/**
	 * Cierra la sesión actual y muestra la pantalla de inicio de sesión.
	 * 
	 * @param event el evento que dispara la acción
	 */
	@FXML
	void cerrarSesion(ActionEvent event) {
		app.mostrarIniciarSesion();

	}

	/**
	 * Actualiza la información de la canción seleccionada en la tabla. Copia los
	 * archivos de carátula y audio a sus respectivas ubicaciones si están
	 * disponibles.
	 */
	@FXML
	void actualizar() {
		Cancion cancion = tableCanciones.getSelectionModel().getSelectedItem();
		if (sonDatosValidos()) {
			if (cancion != null) {
				File archivoCopia;
				if (imageCaratula.getImage() != null) {
					String nombre;
					do {
						nombre = TiendaUtil.obtenerRutaCopiaOrganizada(archivoImagenCaratula.getName());
					} while (TiendaUtil.existeArchivoCaratula(nombre));
					archivoCopia = new File(
							"src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/caratulasCanciones/"
									+ nombre);
					try {
						Files.copy(archivoImagenCaratula.toPath(), archivoCopia.toPath(),
								StandardCopyOption.REPLACE_EXISTING);
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
					audio = TiendaUtil.obtenerRutaCopiaOrganizada(archivoAudioCancion.getName());
				} while (TiendaUtil.existeArchivoAudio(audio));
				archivoCopia = new File(
						"src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/cancionesAudios/"
								+ audio);
				try {
					Files.copy(archivoAudioCancion.toPath(), archivoCopia.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				cancion.setUrl(audio);
				actualizarTablaCanciones();
				mfm.guardarDatos();
			} else {
				InterfazFXUtil.mostrarMensaje("Cancion no seleccionada", "Cancion no seleccionada para actualizar");
			}

		}
	}

	/**
	 * Actualiza la tabla de artistas con la lista actualizada de artistas.
	 */
	public void actualizarTablaArtistas() {
		ObservableList<Artista> listaArtistasProperty = FXCollections.observableArrayList();
		for (Artista artista : mfm.obtenerListaArtistas().toCircularList()) {
			listaArtistasProperty.add(artista);
		}
		tableArtistas.setItems(listaArtistasProperty);
		columnNombreArtista
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombre()));
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
	 * Actualiza la tabla de canciones con la lista actualizada de canciones.
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
	 * Verifica si los datos de entrada para una canción son válidos.
	 * 
	 * @return true si los datos son válidos, false en caso contrario
	 */
	public boolean sonDatosValidos() {
		boolean sonDatosValidos = false;
		String msj = "";
		if (InterfazFXUtil.estaCampoVacio(txtCancion)) {
			msj += "Debe indicar el nombre de la cancion\n";
		}
		if (InterfazFXUtil.estaCampoVacio(txtAlbum)) {
			msj += "Debe indicar el album al que pertenece la cancion\n";
		}
		try {
			int valor = Integer.parseInt(txtAnio.getText());
			if (valor < 0) {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			if (txtAnio.getText().trim().equals("")) {
				msj += "El a\u00F1o no debe estar vacio\n";
			} else {
				msj += "A\u00F1o indicado no valido\n";
			}
		}
		if (InterfazFXUtil.estaCampoVacio(txtDuracion)) {
			msj += "Debe indicar la duracion\n";
		} else if (!txtDuracion.getText().trim().matches("^[0-9:]+$")) {
			msj += "Duracion no valida\n";
		}
		if (archivoAudioCancion == null) {
			msj += "Debe seleccionar el audio de la cancion\n";
		}
		if (InterfazFXUtil.estaCampoVacio(cmbGenero)) {
			msj += "Debe indicar el genero de la cancion\n";
		}
		if (msj.equals("")) {
			sonDatosValidos = true;
		} else {
			InterfazFXUtil.mostrarMensaje("Entradas no validas", "Entradas no validas", msj, AlertType.ERROR);
		}
		return sonDatosValidos;
	}

	/**
	 * Guarda una nueva canción con los datos ingresados.
	 * 
	 * @param event el evento que dispara la acción
	 */
	@FXML
	void guardarCancion(ActionEvent event) {
		if (sonDatosValidos()) {
			Cancion newCancion = new Cancion();
			String codigo;
			do {
				codigo = TiendaUtil.generarCadenaAleatoria();
			} while (mfm.existeCodigoCancion(codigo));
			newCancion.setCodigo(codigo);
			if (imageCaratula.getImage() != null) {
				String nombre;
				do {
					nombre = TiendaUtil.obtenerRutaCopiaOrganizada(archivoImagenCaratula.getName());
				} while (TiendaUtil.existeArchivoCaratula(nombre));
				File archivoCopia = new File(
						"src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/caratulasCanciones/"
								+ nombre);
				try {
					Files.copy(archivoImagenCaratula.toPath(), archivoCopia.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
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
				audio = TiendaUtil.obtenerRutaCopiaOrganizada(archivoAudioCancion.getName());
			} while (TiendaUtil.existeArchivoAudio(audio));
			File archivoCopia = new File(
					"src/main/resources/co/edu/uniquindio/estructuras/proyecto/proyectostorify/cancionesAudios/"
							+ audio);
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
			mfm.guardarDatos();
		}
	}

	/**
	 * Coloca los datos de la canción seleccionada en los campos correspondientes.
	 */
	@FXML
	void ponerDatosCancion() {

		Cancion cancion = tableCanciones.getSelectionModel().getSelectedItem();
		if (cancion != null) {
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
			archivoAudioCancion = TiendaUtil.obtenerArchivoAudio(cancion);
		}
	}

	/**
	 * Selecciona una imagen de carátula para la canción.
	 * 
	 * @param event el evento que dispara la acción
	 */
	@FXML
	void seleccionarCaratula(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter("Archivos de Imagen", "*.jpg", "*.png", "*.jpeg"));
		archivoImagenCaratula = fileChooser.showOpenDialog(null);
		if (archivoImagenCaratula != null) {
			Image image = new Image(archivoImagenCaratula.toURI().toString());
			imageCaratula.setImage(image);
		} else {
			imageCaratula.setImage(null);
		}
	}

	/**
	 * Selecciona un artista de la tabla de artistas para agregarlo a la lista de
	 * artistas seleccionados.
	 * 
	 * @param event el evento que dispara la acción
	 */
	@FXML
	void seleccionarArtista(ActionEvent event) {
		Artista artistaSeleccionado = tableArtistas.getSelectionModel().getSelectedItem();
		if (!listaArtistasSeleccionados.contains(artistaSeleccionado)) {
			listaArtistasSeleccionados.add(artistaSeleccionado);

		} else {
			JOptionPane.showMessageDialog(null, "El artista ya fue seleccionado");
		}
	}

	/**
	 * Selecciona un archivo de audio para la canción.
	 */
	@FXML
	void seleccionarAudioCancion() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos MP3", "*.mp3"));
		archivoAudioCancion = fileChooser.showOpenDialog(null);
	}

	/**
	 * Reproduce el archivo de audio seleccionado para la canción.
	 */
	@FXML
	void reproducirAudioCancion() {
		if (archivoAudioCancion != null) {
			CircularList<File> cancion = new CircularList<File>();
			cancion.add(archivoAudioCancion);
			mfm.mostrarReproductorAudio(cancion);
		} else {
			InterfazFXUtil.mostrarMensaje("No se puede reproducir cancion", "No hay audio para reproducir");
		}
	}

	/**
	 * Muestra un mensaje con los géneros musicales más populares.
	 */
//    @FXML
//    void generosPopulares() {
//        CircularList<Genero> generosPopulares = mfm.obtenerGenerosPopulares();
//        String msj = "";
//        
//        if (generosPopulares.size() > 1) {
//            msj += "Los géneros más populares son:\n";
//            for (Genero genero : generosPopulares) {
//                msj += "- " + genero.toString() + "\n";
//            }
//        } else {
//            msj += "El género más popular es: " + generosPopulares.get(0).toString();
//        }
//
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Popularidad de los Géneros");
//        alert.setHeaderText("Información sobre los géneros musicales más populares");
//        alert.setContentText(msj);
//
//        Image image = new Image(getClass().getResourceAsStream("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/images/logo.png"));
//        ImageView imageView = new ImageView(image);
//        imageView.setFitWidth(100);
//        imageView.setFitHeight(100);
//        alert.setGraphic(imageView);
//        
//
//        alert.getDialogPane().getStylesheets().add(getClass().getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/styles/tryalert.css").toExternalForm());
//        
//        alert.showAndWait();
//    }
	/**
	 * Muestra una ventana de diálogo con información sobre los géneros musicales
	 * más populares.
	 */
	@FXML
	void generosPopulares() {
		CircularList<Genero> generosPopulares = mfm.obtenerGenerosPopulares();
		StringBuilder msj = new StringBuilder();

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Popularidad de los Géneros");
		alert.setHeaderText("Información sobre los géneros musicales más populares");

		if (generosPopulares.isEmpty()) {
			msj.append("No hay géneros populares disponibles.");
		} else {
			msj.append("Los géneros más populares son:\n");
			VBox genreBox = new VBox(); // Contenedor vertical para los géneros
			genreBox.setAlignment(Pos.CENTER_LEFT);
			for (Genero genero : generosPopulares) {
				HBox genreRow = new HBox(); // Contenedor horizontal para cada género
				genreRow.setAlignment(Pos.CENTER_LEFT);
				// Crear un ícono o color para representar el nivel de popularidad del género
				ImageView popularityIcon = new ImageView(getPopularityIcon(genero, generosPopulares));
				popularityIcon.setFitWidth(110);
				popularityIcon.setFitHeight(40);
				// Crear una etiqueta para mostrar el nombre del género
				Label genreLabel = new Label("  " + genero.toString());
				genreLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
				// Estilo de la etiqueta
				// Agregar el ícono y la etiqueta al contenedor horizontal
				genreRow.getChildren().addAll(popularityIcon, genreLabel);
				// Agregar el contenedor horizontal al contenedor vertical
				genreBox.getChildren().add(genreRow);
			}
			// Establecer el contenido de la alerta como el contenedor vertical con los
			// géneros
			alert.getDialogPane().setContent(genreBox);
		}

		alert.setContentText(msj.toString());

		Image image = new Image(getClass()
				.getResourceAsStream("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/images/logo.png"));
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);
		alert.setGraphic(imageView);

		alert.getDialogPane().getStylesheets()
				.add(getClass()
						.getResource("/co/edu/uniquindio/estructuras/proyecto/proyectostorify/styles/tryalert.css")
						.toExternalForm());

		alert.showAndWait();
	}

	/**
	 * Obtiene el ícono que representa el nivel de popularidad del género.
	 *
	 * @param genero           El género del cual se obtiene la popularidad.
	 * @param generosPopulares La lista de géneros populares.
	 * @return El ícono que representa el nivel de popularidad del género.
	 */
	private Image getPopularityIcon(Genero genero, CircularList<Genero> generosPopulares) {
		// Obtener el nivel de popularidad del género
		int popularityLevel = getPopularityLevel(genero, generosPopulares);

		// Asignar el ícono en función del nivel de popularidad
		switch (popularityLevel) {
		case 0:
			return new Image(getClass().getResourceAsStream(
					"/co/edu/uniquindio/estructuras/proyecto/proyectostorify/images/high_popularity.png"));
		case 1:
			return new Image(getClass().getResourceAsStream(
					"/co/edu/uniquindio/estructuras/proyecto/proyectostorify/images/medium_popularity.png"));
		default:
			return new Image(getClass().getResourceAsStream(
					"/co/edu/uniquindio/estructuras/proyecto/proyectostorify/images/low_popularity.png"));
		}
	}

	/**
	 * Obtiene el nivel de popularidad de un género.
	 *
	 * @param genero           El género del cual se obtiene la popularidad.
	 * @param generosPopulares La lista de géneros populares.
	 * @return El nivel de popularidad del género.
	 */
	private int getPopularityLevel(Genero genero, CircularList<Genero> generosPopulares) {
		// Obtener la popularidad del género
		int popularity = mfm.obtenerPopularidadGenero(genero);

		// Contar cuántos géneros tienen la misma popularidad
		int count = 0;
		for (Genero g : generosPopulares) {
			if (mfm.obtenerPopularidadGenero(g) == popularity) {
				count++;
			}
		}

		// Devolver el nivel de popularidad basado en la cantidad de géneros con la
		// misma popularidad
		return count <= 3 ? count - 1 : 2;
	}

	@FXML
	void buscarNombreArtista(ActionEvent event) {
		if (!(txtBuscarNombre.getText().trim().isEmpty())) {
			CircularList<Artista> listTemp = new CircularList<Artista>();
			for (Artista artista : listaArtista) {
				if (artista.getNombre().equalsIgnoreCase(txtBuscarNombre.getText().trim())) {
					listTemp.add(artista);
				}
			}
			actualizarTablaBusqueda(listTemp);
			listTemp.clear();
		} else {
			actualizarTablaArtistas();
		}
	}

	public void actualizarTablaBusqueda(CircularList<Artista> listaArtista) {
		ObservableList<Artista> listaArtistasProperty = FXCollections.observableArrayList();
		for (Artista artista : listaArtista) {
			listaArtistasProperty.add(artista);
		}

		tableArtistas.setItems(listaArtistasProperty);
		columnNombreArtista
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombre()));
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

	@FXML
	void eliminarEvent(ActionEvent event) {
		Cancion cancion = tableCanciones.getSelectionModel().getSelectedItem();
		if (cancion != null) {
			CircularList<Artista> lstArtistas = mfm.obtenerArtistas();
			for (Artista artistaRef : lstArtistas) {
				if (artistaRef.getLstCanciones().toCircularList().contains(cancion)) {
					mfm.getTiendaMusica().eliminarCancion(cancion);
					//artistaRef.getLstCanciones().eliminar(cancion);
					System.out.println("Se ha eliminado la cancion");
					actualizarTablaCanciones();
				}
			}
		}
	}

}
