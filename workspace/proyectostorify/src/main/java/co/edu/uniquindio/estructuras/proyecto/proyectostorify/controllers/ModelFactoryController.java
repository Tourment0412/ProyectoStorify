package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import java.util.List;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree.BinaryTree;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.*;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.services.*;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.stack.Stack;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.PersistenciaTexto;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.TiendaUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class ModelFactoryController {

	@NonNull
	private App aplicacion;
	@NonNull
	private Stage ventana;
	@NonNull
	Storify tiendaMusica = new Storify("Stori");
	Stack<Cancion> pilaDeshacer;
	Stack<Cancion> pilaRehacer;
	Cuenta usuarioSesion;

	/**
	 * Clase que implementa el patrón Singleton para controlar la creación de
	 * instancias de ModelFactoryController.
	 */
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	/**
	 * Método estático para obtener la única instancia de ModelFactoryController.
	 * 
	 * @return La instancia única de ModelFactoryController.
	 */
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	/**
	 * Constructor de la clase ModelFactoryController que inicializa los datos de la
	 * cuenta y los datos.
	 */
	public ModelFactoryController() {
		cargarDatos();
		if (tiendaMusica.getLstCanciones().size()==0 && tiendaMusica.getLstCuentas().size()==0) {
			inicializarDatosCuenta();
			inicializarDatos();
		}
	}

	/**
	 * Inicializa los datos de la cuenta, creando cuentas de administrador y usuario.
	 */
	public void inicializarDatosCuenta() {
		Administrador admin = new Administrador("admin", "$aDmiN", "code1");
		Usuario usuario = new Usuario("juan", "456", "@789");
		tiendaMusica.getLstCuentas().put(usuario.getUsername(), usuario);
		tiendaMusica.getLstCuentas().put(admin.getUsername(), admin);
	}

	/**
	 * Inicializa los datos de la aplicación, creando artistas, canciones y estableciendo relaciones entre ellos.
	 */
	public void inicializarDatos() {

		// Creación de cuentas de administrador y usuario

		// Creación de artistas y canciones
		Artista artista1 = new Artista("aaaaa", "Evanescence", "Estadounidense", true, new ListaDoble<Cancion>());
		Artista artista2 = new Artista("aaaab", "Michael Jackson", "Estadounidense", false, new ListaDoble<Cancion>());
		Artista artista3 = new Artista("aaaac", "Maluma", "Colombiana", false, new ListaDoble<Cancion>());
		tiendaMusica.getLstArtistas().add(artista1);
		tiendaMusica.getLstArtistas().add(artista2);
		tiendaMusica.getLstArtistas().add(artista3);

		// Creación y adición de canciones para los artistas
		Cancion cancion1 = new Cancion("aaaaa", "Bring Me To Life", "Fallen", "", "2003", "3:55", "evan1.mp3",
				Genero.ROCK, new CircularList<Artista>());
		Cancion cancion2 = new Cancion("aaaab", "Call Me When You're Sober", "The Open Door", "", "2006", "3:32",
				"evan2.mp3", Genero.ROCK, new CircularList<Artista>());
		Cancion cancion3 = new Cancion("aaaac", "Billie Jean", "Thriller", "", "1982", "4:45", "Mich1.mp3", Genero.POP,
				new CircularList<Artista>());
		Cancion cancion4 = new Cancion("aaaad", "Beat It", "Thriller", "", "1982", "4:48", "Mich2.mp3", Genero.POP,
				new CircularList<Artista>());
		Cancion cancion5 = new Cancion("aaaae", "Felices los 4", "F.A.M.E.", "", "2018", "4:50", "Malu1.mp3",
				Genero.REGGAETON, new CircularList<Artista>());
		Cancion cancion6 = new Cancion("aaaaf", "Hawai", "Papi Juancho", "", "2020", "4:18", "Malu2.mp3",
				Genero.REGGAETON, new CircularList<Artista>());
		artista1.agregarCancion(cancion1);
		artista1.agregarCancion(cancion2);
		artista2.agregarCancion(cancion3);
		artista2.agregarCancion(cancion4);
		artista3.agregarCancion(cancion5);
		artista3.agregarCancion(cancion6);
		tiendaMusica.agregarCancion(cancion1);
		tiendaMusica.agregarCancion(cancion2);
		tiendaMusica.agregarCancion(cancion3);
		tiendaMusica.agregarCancion(cancion4);
		tiendaMusica.agregarCancion(cancion5);
		tiendaMusica.agregarCancion(cancion6);

		// Establecimiento de canciones para cada artista
		establecerCancionesArtista(artista1);
		establecerCancionesArtista(artista2);
		establecerCancionesArtista(artista3);

	}

	/**
	 * Inicia la sesión de un usuario.
	 * @param usuario El usuario que inicia sesión.
	 */
	public void iniciarSesionUsuario(Usuario usuario) {
		usuarioSesion = usuario;
		pilaDeshacer = new Stack<Cancion>();
		pilaRehacer = new Stack<Cancion>();
	}

	/**
	 * Obtiene la lista de canciones.
	 * 
	 * @return la lista de canciones
	 */
	public CircularList<Cancion> obtenerListaCaciones() {
		return tiendaMusica.getLstCanciones();
	}

	/**
	 * Obtiene la lista de artistas.
	 * 
	 * @return la lista de artistas
	 */
	public BinaryTree<Artista> obtenerListaArtistas() {
		return tiendaMusica.getLstArtistas();
	}

	/**
	 * Obtiene una cuenta a partir de su nombre de usuario.
	 * 
	 * @param nombre el nombre de usuario
	 * @return la cuenta correspondiente al nombre de usuario dado
	 */
	public Cuenta obtenerCuenta(String nombre) {
		return tiendaMusica.obtenerCuenta(null);
	}

	/**
	 * Obtiene una canción a partir de su código.
	 * 
	 * @param codigo el código de la canción
	 * @return la canción correspondiente al código dado
	 */
	public Cancion obtenerCancion(String codigo) {
		return tiendaMusica.obtenerCancion(codigo);
	}

	/**
	 * Agrega un usuario.
	 * 
	 * @param usuario el usuario a agregar
	 */
	public void agregarUsuario(Usuario usuario) {
		tiendaMusica.agregarUsuario(usuario);
	}

	/**
	 * Elimina un usuario a partir de su nombre de usuario.
	 * 
	 * @param nombreUsuario el nombre de usuario del usuario a eliminar
	 */
	public void eliminarUsuario(String nombreUsuario) {
		tiendaMusica.eliminarUsuario(nombreUsuario);
	}

	/**
	 * Agrega una canción.
	 * 
	 * @param cancion la canción a agregar
	 */
	public void agregarCancion(Cancion cancion) {
		tiendaMusica.agregarCancion(cancion);
	}

	/**
	 * Elimina una canción.
	 * 
	 * @param cancion la canción a eliminar
	 */
	public void eliminarCancion(Cancion cancion) {
		tiendaMusica.eliminarCancion(cancion);
	}

	/**
	 * Agrega un artista.
	 * 
	 * @param artista el artista a agregar
	 */
	public void agregarArtista(Artista artista) {
		tiendaMusica.agregarArtista(artista);
	}

	/**
	 * Elimina un artista.
	 * 
	 * @param artista el artista a eliminar
	 */
	public void eliminarArtista(Artista artista) {
		tiendaMusica.eliminarArtista(artista);
	}

	/**
	 * Obtiene un artista a partir de su nombre.
	 * 
	 * @param nombre el nombre del artista a obtener
	 * @return el artista correspondiente al nombre dado
	 */
	public Artista obtenerArtistaNombre(String nombre) {
		return tiendaMusica.obtenerArtistaNombre(nombre);
	}

	/**
	 * Comprueba si existe un usuario con el nombre de usuario y contraseña dados.
	 * 
	 * @param nombre el nombre de usuario
	 * @param contrasenia la contraseña
	 * @return true si existe un usuario con el nombre de usuario y contraseña dados, false en caso contrario
	 */
	public boolean existeUsuario(String nombre, String contrasenia) {
		// TODO Auto-generated method stub
		return tiendaMusica.existeUsuario(nombre, contrasenia);
	}

	/**
	 * Obtiene el tipo de cuenta a partir del nombre de usuario y contraseña.
	 * 
	 * @param nombre el nombre de usuario
	 * @param contrasenia la contraseña
	 * @return el tipo de cuenta correspondiente al nombre de usuario y contraseña dados
	 */
	public String obtenerTipoCuenta(String nombre, String contrasenia) {
		// TODO Auto-generated method stub
		return tiendaMusica.obtenerTipoCuenta(nombre, contrasenia);
	}

	/**
	 * Obtiene una cuenta a partir del nombre de usuario y contraseña.
	 * 
	 * @param nombre el nombre de usuario
	 * @param contrasenia la contraseña
	 * @return la cuenta correspondiente al nombre de usuario y contraseña dados
	 */
	public Cuenta obtenerCuentaDatos(String nombre, String contrasenia) {
		// TODO Auto-generated method stub
		return tiendaMusica.obtenerCuentaDatos(nombre, contrasenia);
	}

	/**
	 * Agrega un usuario con los datos proporcionados.
	 * 
	 * @param nombre el nombre del usuario
	 * @param email el correo electrónico del usuario
	 * @param contrasenia la contraseña del usuario
	 */
	public void agregarUsuario(String nombre, String email, String contrasenia) {
		// TODO Auto-generated method stub
		tiendaMusica.registrarUsuario(nombre, email, contrasenia);
	}

	/**
	 * Obtiene la lista de artistas.
	 * 
	 * @return la lista de artistas
	 */
	public CircularList<Artista> obtenerArtistas() {
		return tiendaMusica.obtenerArtistas();
	}

	/**
	 * Establece las canciones para un artista.
	 * 
	 * @param newArtista el artista al que se le establecerán las canciones
	 */
	public void establecerCancionesArtista(Artista newArtista) {
		// TODO Auto-generated method stub
		tiendaMusica.establecerCancionesArtista(newArtista);
	}

	/**
	 * Comprueba si existe una canción con el código dado.
	 * 
	 * @param codigo el código de la canción a comprobar
	 * @return true si existe una canción con el código dado, false en caso contrario
	 */
	public boolean existeCodigoCancion(String codigo) {
		return tiendaMusica.existeCodigoCancion(codigo);
	}

	/**
	 * 
	 * @param lstArtistas
	 * @param cancion
	 */
	public void agregarCancionesArtistas(CircularList<Artista> lstArtistas, Cancion cancion) {
		tiendaMusica.agregarCancionesArtistas(lstArtistas, cancion);
	}

	/**
	 * Agrega canciones a la lista de canciones de los artistas dados.
	 * 
	 * @param lstArtistas la lista de artistas a los que se les agregarán las canciones
	 * @param cancion la canción a agregar a los artistas
	 */
	public CircularList<File> obtenerAudiosCancionesUsuario(CircularList<Cancion> listaCanciones) {
		CircularList<File> audios = new CircularList<File>();
		String audio;
		for (Cancion cancion : listaCanciones) {
			audio = cancion.getUrl();
		}
		return audios;
	}

	/**
	 * Muestra el reproductor de audio con la lista de archivos proporcionada.
	 * 
	 * @param archivos la lista de archivos de audio a reproducir
	 */
	public void mostrarReproductorAudio(CircularList<File> archivos) {
		this.getAplicacion().reproducirCancion(archivos);
	}

	/**
	 * Guarda una canción en la lista de reproducción del usuario.
	 * 
	 * @param c la canción a guardar en la lista de reproducción
	 */
	public void guardarPlayListUsuario(Cancion c) {
		// TODO Auto-generated method stub
		tiendaMusica.guardarPlayListUsuario(c, (Usuario) usuarioSesion);
	}

	/**
	 * Guarda una canción en la lista de favoritos del usuario.
	 * 
	 * @param c la canción a guardar en la lista de favoritos
	 */
	public void guardarFavoritatUsuario(Cancion c) {
		// TODO Auto-generated method stub
		tiendaMusica.guardarCancionFavoritaUsuario(c, (Usuario) usuarioSesion);
	}

	/**
	 * Elimina una canción de la lista de reproducción del usuario.
	 * 
	 * @param c la canción a eliminar de la lista de reproducción
	 */
	public void eliminarCancionPlayListUsuario(Cancion c) {
		tiendaMusica.eliminarCancionGuardada(c, (Usuario) usuarioSesion);
	}

	/**
	 * Elimina una canción de la lista de favoritos del usuario.
	 * 
	 * @param c la canción a eliminar de la lista de favoritos
	 */
	public void eliminarCancionFavoritatUsuario(Cancion c) {
		tiendaMusica.eliminarCancionFavorita(c, (Usuario) usuarioSesion);
	}

	/**
	 * Obtiene las canciones de los artistas.
	 * 
	 * @return la lista de canciones de los artistas
	 */
	public CircularList<Cancion> obtenerCancionesArtistas() {
		return tiendaMusica.obtenerCancionesArtistas();
	}

	/**
	 * Obtiene los géneros musicales más populares.
	 * 
	 * @return la lista de géneros musicales más populares
	 */
	public CircularList<Genero> obtenerGenerosPopulares() {
		return tiendaMusica.obtenerGenerosPopulares();
	}
	
	/**
	 * Cuenta cuantas canciones han sido guardadas y seleccionadas como favoritas de cierto genero 
	 * @param genero Genero indicado
	 * @return Cantidad de canciones guardades del genero indicado
	 */
	public int obtenerPopularidadGenero(Genero genero) {
		return tiendaMusica.contarGeneroCancionesUsuarios(genero);
	}

	/**
	 * Importa artistas desde un archivo.
	 * 
	 * @param archivoArtistas el archivo que contiene los datos de los artistas
	 */
	public void importarArtistas(File archivoArtistas) {
		try {
			PersistenciaTexto.cargarArtistas(tiendaMusica, archivoArtistas.getAbsolutePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Exporta artistas a un archivo de texto.
	 * 
	 * @param txtFile el archivo de texto donde se exportarán los datos de los artistas
	 */
	public void exportarArtistas(File txtFile) {
		try {
			PersistenciaTexto.guardarArtistas(tiendaMusica, txtFile.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Guarda una acción realizada por el usuario para permitir deshacerla más tarde.
	 * 
	 * @param cancion la canción sobre la que se realizó la acción
	 * @param accion la acción realizada (por ejemplo, "ADDplaylist" o "RMfavorita")
	 */
	public void guardarAccion(Cancion cancion, String accion) {
		pilaDeshacer.push(cancion, accion);
	}

	/**
	 * Deshace la última acción realizada por el usuario.
	 */
	public void deshacer() {
		if (!pilaDeshacer.isEmpty()) {
			String accion = pilaDeshacer.headAction();
			Cancion cancion = pilaDeshacer.pop();
			pilaRehacer.push(cancion, accion);
			Usuario usuario = (Usuario) usuarioSesion;
			switch (accion) {
			case "ADDplaylist":
				usuario.getLstCancionesGuardadas().remove(cancion);
				break;
			case "RMplaylist":
				usuario.getLstCancionesGuardadas().add(cancion);
				break;
			case "ADDfavorita":
				usuario.getLstCancionesFavoritas().remove(cancion);
				break;
			case "RMfavorita":
				usuario.getLstCancionesFavoritas().add(cancion);
				break;
			default:
				System.out.println("Error en deshacer");
			}
		}
	}

	/**
	 * Rehace la última acción deshecha por el usuario.
	 */
	public void rehacer() {
		if (!pilaRehacer.isEmpty()) {
			String accion = pilaRehacer.headAction();
			Cancion cancion = pilaRehacer.pop();
			pilaDeshacer.push(cancion, accion);
			Usuario usuario = (Usuario) usuarioSesion;
			switch (accion) {
			case "ADDplaylist":
				usuario.getLstCancionesGuardadas().add(cancion);
				break;
			case "RMplaylist":
				usuario.getLstCancionesGuardadas().remove(cancion);
				break;
			case "ADDfavorita":
				usuario.getLstCancionesFavoritas().add(cancion);
				break;
			case "RMfavorita":
				usuario.getLstCancionesFavoritas().remove(cancion);
				break;
			default:
				System.out.println("Error en rehacer");
			}
		}
	}

	/**
	 * Carga los datos desde el almacenamiento.
	 */
	public void cargarDatos() {
		
	}

	/**
	 * Guarda los datos en el almacenamiento.
	 */
	public void guardarDatos() {
		ArtistasDao artistasGuardar=new ArtistasDao();
		CancionesDao cancionesGuardar=new CancionesDao();
		CuentasDao cuentasGuardar=new CuentasDao();
		artistasGuardar.saveData(tiendaMusica.getLstArtistas());
		cancionesGuardar.saveData(tiendaMusica.getLstCanciones());
		cuentasGuardar.saveData(tiendaMusica.getLstCuentas());
	}

}
