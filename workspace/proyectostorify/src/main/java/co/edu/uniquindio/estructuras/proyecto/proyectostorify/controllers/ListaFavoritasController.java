package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Cancion;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.Usuario;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.InterfazFXUtil;
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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class ListaFavoritasController {
	
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

	@FXML
	void initialize() {
		ObservableList<String> generos = FXCollections.observableArrayList();
		generos.add("Pop");
		generos.add("Rock");
		generos.add("Punk");
		generos.add("Reggaeton");
		generos.add("Electronica");
		cmbGenero.setItems(generos);
		listaCanciones = ((Usuario) mfm.getUsuarioSesion()).getLstCancionesFavoritas();
		System.out.println("Tam: " + listaCanciones.size());
		;
		actualizarTablaCanciones(listaCanciones);
	}

	@FXML
	void refrescarTabla(ActionEvent event) {
		listaCanciones = ((Usuario) mfm.getUsuarioSesion()).getLstCancionesGuardadas();
		actualizarTablaCanciones(listaCanciones);
	}

	@FXML
	void eliminarDeLista(ActionEvent event) {
		Cancion c = tableCanciones.getSelectionModel().getSelectedItem();
		if (c != null) {
			if (listaCanciones != null) {
				if (listaCanciones.remove(c)) {
					tableCanciones.getItems().remove(c);
					listaCanciones.remove(c);
					InterfazFXUtil.mostrarMensaje("Cancion removida","Canción removida exitosamente.");
				} else {
					InterfazFXUtil.mostrarMensaje("No esta en la lista","La canción seleccionada no se encontraba en la lista.");
				}
			} else {
				InterfazFXUtil.mostrarMensaje("Lista vacia","La lista de canciones guardadas del usuario esta vacia.");
			}
		} else {
			InterfazFXUtil.mostrarMensaje("Cancion no seleccionada", "Por favor, seleccione una canción de la lista.");
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
			if (c.getNombreCancion().equalsIgnoreCase(nombreC) && c.getNombreAlbum().equalsIgnoreCase(albumC)
					&& c.getAnio().equalsIgnoreCase(anioC) && c.getDuracion().equalsIgnoreCase(duracionC)
					&& c.getGenero().toString().equals(generoC)) {
				listaTemp.add(c);
			}
		}
		actualizarTablaCanciones(listaTemp);

	}

	@FXML
	void mostrarDetallesCancion(ActionEvent event) {
		Cancion c = tableCanciones.getSelectionModel().getSelectedItem();
		if(c!=null) {
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
			
		}else {
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

}