package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.util.List;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.application.App;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.*;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.stack.Stack;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.TiendaUtil;
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
	@NonNull
	Usuario usuarioSesion;
	
	
	
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

	}

	public CircularList<Cancion> obtenerListaCaciones (){
		return tiendaMusica.getLstCanciones();
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
	
	public void agregarCancion(Cancion cancion,Artista artista) {
		tiendaMusica.agregarCancion(cancion, artista);
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


}
