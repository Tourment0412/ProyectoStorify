package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.io.IOException;
import java.util.List;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.model.*;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.stack.Stack;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class ModelFactoryController {
	

	Storify tiendaMusica;
	Stack<Cancion> pilaCanciones;
	Usuario usuarioSesion;
	
	// ------------------------------ Singleton
	// ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Método para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	public ModelFactoryController() {
		tiendaMusica=new Storify("Storify");
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
	
	public void agregarCambioCancion(Cancion cancion,String accion) {
		pilaCanciones.push(cancion, accion);
	}
	
	public void deshacerCambioCancion() {
		String accion=pilaCanciones.headAction();
		Cancion cancion=pilaCanciones.pop();
		switch(accion) {
		
		}
	}
	
}
