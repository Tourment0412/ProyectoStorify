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
	@NonNull
	Stack<Cancion> pilaCanciones;
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
		inicializarDatos();
	}
	
	public void inicializarDatos() {
	    // Inicialización de la tienda de música
	    tiendaMusica.setLstArtistas(new BinaryTree<Artista>());
	    tiendaMusica.setLstCanciones(new CircularList<Cancion>());
	    tiendaMusica.setLstCuentas(new HashMap<String, Cuenta>());

	    // Creación de cuentas de administrador y usuario
	    Administrador admin = new Administrador("pepe", "123", "code1");
	    Usuario usuario = new Usuario("juan", "456", "@789");
	    tiendaMusica.getLstCuentas().put(usuario.getUsername(), usuario);
	    tiendaMusica.getLstCuentas().put(admin.getUsername(), admin);

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


	public CircularList<Cancion> obtenerListaCaciones (){
		return tiendaMusica.getLstCanciones();
	}
	
	public BinaryTree<Artista> obtenerListaArtistas(){
		return tiendaMusica.getLstArtistas();
	}
	
	public Cuenta obtenerCuenta(String nombre) {
		return tiendaMusica.obtenerCuenta(null);
	}
	
	public Cancion obtenerCancion(String codigo) {
		return tiendaMusica.obtenerCancion(codigo);
	}
	
	public Artista obtenerArtista(String nombre) {
		return tiendaMusica.obtenerArtista(nombre);
	}
	
	public void agregarUsuario(Usuario usuario) {
		tiendaMusica.agregarUsuario(usuario);
	}
	
	public void eliminarUsuario(String nombreUsuario) {
		tiendaMusica.eliminarUsuario(nombreUsuario);
	}
	
	public void agregarCancion(Cancion cancion) {
		tiendaMusica.agregarCancion(cancion);
	}
	
	public void eliminarCancion(Cancion cancion) {
		tiendaMusica.eliminarCancion(cancion);
	}
	
	public void agregarArtista(Artista artista) {
		tiendaMusica.agregarArtista(artista);
	}
	
	public void eliminarArtista(Artista artista) {
		tiendaMusica.eliminarArtista(artista);
	}
	
	public CircularList<Cancion> obtenerCancionesFiltradasUnion(String lista,Usuario usuario,String[] datos) {
		return tiendaMusica.obtenerCancionesFiltradasUnion(lista, usuario, datos);
	}
	
	public CircularList<Cancion> obtenerCancionesFiltradasInterseccion(String lista,Usuario usuario,String[] datos) {
		return tiendaMusica.obtenerCancionesFiltradasInterseccion(lista, usuario, datos);
	}
	
	public Artista obtenerArtistaNombre(String nombre) {
		return tiendaMusica.obtenerArtistaNombre(nombre);
	}
	
	/**
	 * 
	 * @param cancion
	 * @param accion
	 */
	public void agregarCambioCancion(Cancion cancion,String accion) {
		pilaCanciones.push(cancion, accion);
	}
	
	/**
	 * 
	 */
	public void deshacerCambioCancion() {
		String accion=pilaCanciones.headAction();
		Cancion cancion=pilaCanciones.pop();
		switch(accion) {
		
		}
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

	public void agregarUsuario(String nombre, String email, String contrasenia) {
		// TODO Auto-generated method stub
		tiendaMusica.registrarUsuario(nombre, email, contrasenia);
	}
	
	public CircularList<Artista> obtenerArtistas() {
		return tiendaMusica.obtenerArtistas();
	}

	public void establecerCancionesArtista(Artista newArtista) {
		// TODO Auto-generated method stub
		tiendaMusica.establecerCancionesArtista(newArtista);
	}

	public boolean existeCodigoCancion(String codigo) {
		return tiendaMusica.existeCodigoCancion(codigo);
	}
	
	public void agregarCancionesArtistas (CircularList<Artista> lstArtistas, Cancion cancion) {
		tiendaMusica.agregarCancionesArtistas(lstArtistas, cancion);
	}

	public CircularList<File> obtenerAudiosCancionesUsuario(CircularList<Cancion> listaCanciones) {
		CircularList<File> audios=new CircularList<File>();
		String audio;
		for (Cancion cancion : listaCanciones) {
			audio=cancion.getUrl();
		}
		return audios;
	}
	
	public void mostrarReproductorAudio(CircularList<File> archivos) {
		this.getAplicacion().reproducirCancion(archivos);
	}

 }
