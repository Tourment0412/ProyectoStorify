package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Cancion;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Genero;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Usuario;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.stack.Stack;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class ListaCancionesController {

	@FXML
	private Button btnDetalles;

	@FXML
	private Button btnRefrescar;

	@FXML
	private TableColumn<Cancion, String> columnAlbumCancion;

	@FXML
	private TableColumn<Cancion, String> columnDuracionCancion;

	@FXML
	private TableColumn<Cancion, String> columnGeneroCancion;

	@FXML
	private TableColumn<Cancion, String> columnNombreCancion;

	@FXML
	private Button btnRegistrarse;

	@FXML
	private Button btnCerrarSesion;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBusquedaConjuntiva;

	@FXML
	private Button btnBusquedaDisyuntiva;

	@FXML
	private Button btnDeshacer;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnRehacer;

	@FXML
	private ComboBox<String> cmbGenero;

	@FXML
	private ImageView imageCaratula;

	@FXML
	private Label lblAlbum;

	@FXML
	private Label lblAlbumCancion;

	@FXML
	private Label lblAnio;

	@FXML
	private Label lblAnioCancion;

	@FXML
	private Label lblAnioDeLaCancion;

	@FXML
	private Label lblCancion;

	@FXML
	private Label lblCodigo;

	@FXML
	private Label lblCodigoCancion;

	@FXML
	private Label lblDuracion;

	@FXML
	private Label lblDuracionCancion;

	@FXML
	private Label lblDuracionDeLaCancion;

	@FXML
	private Label lblGenero;

	@FXML
	private Label lblGeneroCancion;

	@FXML
	private Label lblGeneroDeLaCancion;

	@FXML
	private Label lblNombreCancion;

	@FXML
	private Label lblNombreDeLaCancion;

	@FXML
	private Label lblNombreDelAlbum;

	@FXML
	private Label lblTitulo;

	@FXML
	private Label lblUrl;

	@FXML
	private Label lblUrlCancion;

	@FXML
	private TableView<Cancion> tableCanciones;

	@FXML
	private TextField txtAnio;

	@FXML
	private TextField txtDuracion;

	@FXML
	private TextField txtNombreAlbum;

	@FXML
	private TextField txtNombreCancion;

	private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Stage ventana = mfm.getVentana();
	private App app = mfm.getAplicacion();

	private CircularList<Cancion> listaCanciones;

	private Stack<Cancion> undoStack = new Stack<>();
	private Stack<Cancion> redoStack = new Stack<>();

	@FXML
	void initialize() {
		ObservableList<String> generos = FXCollections.observableArrayList();
		generos.add("Pop");
		generos.add("Rock");
		generos.add("Punk");
		generos.add("Reggaeton");
		generos.add("Electronica");
		cmbGenero.setItems(generos);
		listaCanciones = ((Usuario) mfm.getUsuarioSesion()).getLstCancionesGuardadas();
		actualizarTablaCanciones(listaCanciones);
	}

	@FXML
	void refrescarTabla(ActionEvent event) {
		listaCanciones = ((Usuario) mfm.getUsuarioSesion()).getLstCancionesGuardadas();
		actualizarTablaCanciones(listaCanciones);

	}

//	@FXML
//	void eliminarDeLista(ActionEvent event) {
//		Cancion c = tableCanciones.getSelectionModel().getSelectedItem();
//		if (c != null) {
//			if (listaCanciones != null) {
//				if (listaCanciones.remove(c)) {
//                    undoStack.push(c, "deshacer");
//                    redoStack.clear(); 
//					tableCanciones.getItems().remove(c);
//					((Usuario) mfm.getUsuarioSesion()).getLstCancionesGuardadas().remove(c);
//					System.out.println(((Usuario) mfm.getUsuarioSesion()).getLstCancionesGuardadas().size());
//					InterfazFXUtil.mostrarMensaje("Cancion removida","Canción removida exitosamente.");
//
//				} else {
//					InterfazFXUtil.mostrarMensaje("No esta en la lista","La canción seleccionada no se encontraba en la lista.");
//				}
//			} else {
//				InterfazFXUtil.mostrarMensaje("Lista vacia","La lista de canciones guardadas del usuario esta vacia.");
//			}
//		} else {
//			InterfazFXUtil.mostrarMensaje("Cancion no seleccionada", "Por favor, seleccione una canción de la lista.");
//		}
//	}

	@FXML
	void eliminarDeLista(ActionEvent event) {
		Cancion c = tableCanciones.getSelectionModel().getSelectedItem();
		if (c != null) {
			if (listaCanciones != null) {
				boolean confirmacion = InterfazFXUtil.mostrarConfirmacion("Eliminar canción",
						"¿Estás seguro de que quieres eliminar esta canción?");
				if (confirmacion) {
					if (listaCanciones.remove(c)) {
						undoStack.push(c, "deshacer");
						redoStack.clear();
						tableCanciones.getItems().remove(c);
						((Usuario) mfm.getUsuarioSesion()).getLstCancionesGuardadas().remove(c);
						System.out.println(((Usuario) mfm.getUsuarioSesion()).getLstCancionesGuardadas().size());
						actualizarTablaCanciones(listaCanciones);
						InterfazFXUtil.mostrarMensaje("Canción removida", "Canción removida exitosamente.");

					} else {
						InterfazFXUtil.mostrarMensaje("No está en la lista",
								"La canción seleccionada no se encontraba en la lista.");
					}
				}
			} else {
				InterfazFXUtil.mostrarMensaje("Lista vacía", "La lista de canciones guardadas del usuario está vacía.");
			}
		} else {
			InterfazFXUtil.mostrarMensaje("Canción no seleccionada", "Por favor, seleccione una canción de la lista.");
		}
	}

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
		System.out.println(listaTemp.size());
		actualizarTablaCanciones(listaTemp);

	}

	@FXML
	void mostrarDetallesCancion(MouseEvent event) {
		Cancion c = tableCanciones.getSelectionModel().getSelectedItem();

		if (c != null) {
			if (c.getCaratula().equals("")) {
				imageCaratula.setImage(null);
			} else {
				imageCaratula.setImage(new Image(c.getCaratula()));
			}

			lblCodigo.setText(c.getCodigo());
			lblCancion.setText(c.getNombreCancion());
			lblAlbum.setText(c.getNombreAlbum());
			lblAnio.setText(c.getAnio());
			lblDuracion.setText(c.getDuracion());
			lblUrl.setText(c.getUrl());
			lblGenero.setText(c.getGenero().toString());

		} else {

			JOptionPane.showMessageDialog(null, "Por favor seleccione una cancion");
		}
	}

	public void actualizarTablaCanciones(CircularList<Cancion> listaCanciones) {
		ObservableList<Cancion> listaCancionProperty = FXCollections.observableArrayList();
		for (Cancion cancion : listaCanciones) {
			listaCancionProperty.add(cancion);

		}

		tableCanciones.setItems(listaCancionProperty);
		columnNombreCancion
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombreCancion()));
		columnAlbumCancion
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getNombreAlbum()));
		columnDuracionCancion
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getDuracion()));
		columnGeneroCancion
				.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getGenero()));
		tableCanciones.refresh();
	}

	@FXML
	void deshacer(ActionEvent event) {
		if (!undoStack.isEmpty()) {
			Cancion operacionDeshacer = undoStack.pop();
			listaCanciones.add(operacionDeshacer);
			actualizarTablaCanciones(listaCanciones);
			redoStack.push(operacionDeshacer, "deshacer");
		} else {
			InterfazFXUtil.mostrarMensaje("Nada que deshacer", "No hay operaciones para deshacer.");
		}
	}

	@FXML
	void rehacer(ActionEvent event) {
		if (!redoStack.isEmpty()) {
			Cancion operacionRehacer = redoStack.pop();
			listaCanciones.add(operacionRehacer);
			actualizarTablaCanciones(listaCanciones);
			undoStack.push(operacionRehacer, "rehacer");
		} else {
			InterfazFXUtil.mostrarMensaje("Nada que rehacer", "No hay operaciones para rehacer.");
		}
	}
	
	@FXML
	void reproducirCancion() {
		CircularList<File> archivosCaciones=obtenerArchivosCanciones();
		mfm.mostrarReproductorAudio(archivosCaciones);
	}
	
	public CircularList<File> obtenerArchivosCanciones() {
		CircularList<File> archivosCaciones=new CircularList<File>();
		File archivo;
		for (Cancion cancion : listaCanciones) {
			archivo=TiendaUtil.obtenerArchivoAudio(cancion);
			if (archivo!=null) {
				archivosCaciones.add(archivo);
			}
		}
		return archivosCaciones;
	}

}
