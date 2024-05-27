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
	Storify tiendaMusica=new Storify("Stori");
	Stack<Cancion> pilaDeshacer;
	Stack<Cancion> pilaRehacer;
	Cuenta usuarioSesion;
	
	private static class SingletonHolder { 
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// M�todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
	/**
	 * Metodo constructor
	 */
	public ModelFactoryController() {
		inicializarDatosCuenta();
		inicializarDatos();
	}
	
	/**
	 * 
	 */
	public void inicializarDatosCuenta() {
		Administrador admin = new Administrador("pepe", "123", "code1");
	    Usuario usuario = new Usuario("juan", "456", "@789");
	    tiendaMusica.getLstCuentas().put(usuario.getUsername(), usuario);
	    tiendaMusica.getLstCuentas().put(admin.getUsername(), admin);
	}
	
	/**
	 * 
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
	    Cancion cancion1 = new Cancion("aaaaa", "Bring Me To Life", "Fallen", "", "2003", "3:55", "evan1.mp3", Genero.ROCK, new CircularList<Artista>());
	    Cancion cancion2 = new Cancion("aaaab", "Call Me When You're Sober", "The Open Door", "", "2006", "3:32", "evan2.mp3", Genero.ROCK, new CircularList<Artista>());
	    Cancion cancion3 = new Cancion("aaaac", "Billie Jean", "Thriller", "", "1982", "4:45", "Mich1.mp3", Genero.POP, new CircularList<Artista>());
	    Cancion cancion4 = new Cancion("aaaad", "Beat It", "Thriller", "", "1982", "4:48", "Mich2.mp3", Genero.POP, new CircularList<Artista>());
	    Cancion cancion5 = new Cancion("aaaae", "Felices los 4", "F.A.M.E.", "", "2018", "4:50", "Malu1.mp3", Genero.REGGAETON, new CircularList<Artista>());
	    Cancion cancion6 = new Cancion("aaaaf", "Hawai", "Papi Juancho", "", "2020", "4:18", "Malu2.mp3", Genero.REGGAETON, new CircularList<Artista>());
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
	 * 
	 * @param usuario
	 */
	public void iniciarSesionUsuario(Usuario usuario) {
		usuarioSesion=usuario;
		pilaDeshacer=new Stack<Cancion>();
		pilaRehacer=new Stack<Cancion>();
	}
	
	/**
	 * 
	 * @return
	 */
	public CircularList<Cancion> obtenerListaCaciones (){
		return tiendaMusica.getLstCanciones();
	}
	
	/**
	 * 
	 * @return
	 */
	public BinaryTree<Artista> obtenerListaArtistas(){
		return tiendaMusica.getLstArtistas();
	}
	
	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public Cuenta obtenerCuenta(String nombre) {
		return tiendaMusica.obtenerCuenta(null);
	}
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public Cancion obtenerCancion(String codigo) {
		return tiendaMusica.obtenerCancion(codigo);
	}
	
	
	/**
	 * 
	 * @param usuario
	 */
	public void agregarUsuario(Usuario usuario) {
		tiendaMusica.agregarUsuario(usuario);
	}
	
	/**
	 * 
	 * @param nombreUsuario
	 */
	public void eliminarUsuario(String nombreUsuario) {
		tiendaMusica.eliminarUsuario(nombreUsuario);
	}
	
	/**
	 * 
	 * @param cancion
	 */
	public void agregarCancion(Cancion cancion) {
		tiendaMusica.agregarCancion(cancion);
	}
	
	/**
	 * 
	 * @param cancion
	 */
	public void eliminarCancion(Cancion cancion) {
		tiendaMusica.eliminarCancion(cancion);
	}
	
	/**
	 * 
	 * @param artista
	 */
	public void agregarArtista(Artista artista) {
		tiendaMusica.agregarArtista(artista);
	}
	
	/**
	 * 
	 * @param artista
	 */
	public void eliminarArtista(Artista artista) {
		tiendaMusica.eliminarArtista(artista);
	}
	
	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public Artista obtenerArtistaNombre(String nombre) {
		return tiendaMusica.obtenerArtistaNombre(nombre);
	}
	
	
	/**
	 * 
	 * @param nombre
	 * @param contrasenia
	 * @return
	 */
	public boolean existeUsuario(String nombre, String contrasenia) {
		// TODO Auto-generated method stub
		return tiendaMusica.existeUsuario(nombre, contrasenia);
	}
	
	/**
	 * 
	 * @param nombre
	 * @param contrasenia
	 * @return
	 */
	public String obtenerTipoCuenta(String nombre, String contrasenia) {
		// TODO Auto-generated method stub
		return tiendaMusica.obtenerTipoCuenta(nombre, contrasenia);
	}

	/**
	 * 
	 * @param nombre
	 * @param contrasenia
	 * @return
	 */
	public Cuenta obtenerCuentaDatos(String nombre, String contrasenia) {
		// TODO Auto-generated method stub
		return tiendaMusica.obtenerCuentaDatos(nombre, contrasenia);
	}
	
	/**
	 * 
	 * @param nombre
	 * @param email
	 * @param contrasenia
	 */
	public void agregarUsuario(String nombre, String email, String contrasenia) {
		// TODO Auto-generated method stub
		tiendaMusica.registrarUsuario(nombre, email, contrasenia);
	}
	
	/**
	 * 
	 * @return
	 */
	public CircularList<Artista> obtenerArtistas() {
		return tiendaMusica.obtenerArtistas();
	}
	
	/**
	 * 
	 * @param newArtista
	 */
	public void establecerCancionesArtista(Artista newArtista) {
		// TODO Auto-generated method stub
		tiendaMusica.establecerCancionesArtista(newArtista);
	}
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existeCodigoCancion(String codigo) {
		return tiendaMusica.existeCodigoCancion(codigo);
	}
	
	/**
	 * 
	 * @param lstArtistas
	 * @param cancion
	 */
	public void agregarCancionesArtistas (CircularList<Artista> lstArtistas, Cancion cancion) {
		tiendaMusica.agregarCancionesArtistas(lstArtistas, cancion);
	}
	
	/**
	 * 
	 * @param listaCanciones
	 * @return
	 */
	public CircularList<File> obtenerAudiosCancionesUsuario(CircularList<Cancion> listaCanciones) {
		CircularList<File> audios=new CircularList<File>();
		String audio;
		for (Cancion cancion : listaCanciones) {
			audio=cancion.getUrl();
		}
		return audios;
	}
	
	/**
	 * 
	 * @param archivos
	 */
	public void mostrarReproductorAudio(CircularList<File> archivos) {
		this.getAplicacion().reproducirCancion(archivos);
	}
	
	/**
	 * 
	 * @param c
	 */
	public void guardarPlayListUsuario(Cancion c) {
		// TODO Auto-generated method stub
		tiendaMusica.guardarPlayListUsuario(c, (Usuario)usuarioSesion);
	}
	
	/**
	 * 
	 * @param c
	 */
	public void guardarFavoritatUsuario(Cancion c) {
		// TODO Auto-generated method stub
		tiendaMusica.guardarCancionFavoritaUsuario(c, (Usuario)usuarioSesion);
	}
	
	/**
	 * 
	 * @param c
	 */
	public void eliminarCancionPlayListUsuario(Cancion c) {
		tiendaMusica.eliminarCancionGuardada(c, (Usuario)usuarioSesion);
	}
	
	/**
	 * 
	 * @param c
	 */
	public void eliminarCancionFavoritatUsuario(Cancion c) {
		tiendaMusica.eliminarCancionFavorita(c, (Usuario)usuarioSesion);
	}
	
	/**
	 * 
	 * @return
	 */
	public CircularList<Cancion> obtenerCancionesArtistas() {
		return tiendaMusica.obtenerCancionesArtistas();
	}
	
	/**
	 * 
	 * @return
	 */
	public CircularList<Genero> obtenerGenerosPopulares() {
		return tiendaMusica.obtenerGenerosPopulares();
	}
	
	/**
	 * 
	 * @param archivoArtistas
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
	 * 
	 * @param txtFile
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
	 * 
	 * @param cancion
	 * @param accion
	 */
	public void guardarAccion(Cancion cancion, String accion) {
		pilaDeshacer.push(cancion, accion);
	}
	
	/**
	 * 
	 */
	public void deshacer() {
		if (!pilaDeshacer.isEmpty()) {
			String accion=pilaDeshacer.headAction();
			Cancion cancion=pilaDeshacer.pop();
			pilaRehacer.push(cancion, accion);
			Usuario usuario=(Usuario)usuarioSesion;
			switch(accion) {
				case "ADDplaylist": usuario.getLstCancionesGuardadas().remove(cancion); break;
				case "RMplaylist": usuario.getLstCancionesGuardadas().add(cancion); break;
				case "ADDfavorita": usuario.getLstCancionesFavoritas().remove(cancion);  break;
				case "RMfavorita": usuario.getLstCancionesFavoritas().add(cancion); break;
				default: System.out.println("Error en deshacer");
			}
		}
	}
	
	/**
	 * 
	 */
	public void rehacer() {
		if (!pilaRehacer.isEmpty()) {
			String accion=pilaRehacer.headAction();
			Cancion cancion=pilaRehacer.pop();
			pilaDeshacer.push(cancion, accion);
			Usuario usuario=(Usuario)usuarioSesion;
			switch(accion) {
				case "ADDplaylist": usuario.getLstCancionesGuardadas().add(cancion); break;
				case "RMplaylist": usuario.getLstCancionesGuardadas().remove(cancion); break;
				case "ADDfavorita": usuario.getLstCancionesFavoritas().add(cancion);  break;
				case "RMfavorita": usuario.getLstCancionesFavoritas().remove(cancion); break;
				default: System.out.println("Error en rehacer");
			}
		}
	}
	
	/**
	 * 
	 */
	public void cargarDatos() {
		
	}
	
	/**
	 * 
	 */
	public void guardarDatos() {
		
	}
	
	
 }
