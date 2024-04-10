package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import java.util.HashMap;


import co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree.BinaryTree;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
//@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Builder
@ToString
public class Storify {

	@NonNull
	private String nombre;

	private BinaryTree<Artista> lstArtistas;

	private CircularList<Cancion> lstCanciones;

	private HashMap<String, Cuenta> lstCuentas;

	public Storify() {
		lstArtistas = new BinaryTree<Artista>();
		lstCanciones = new CircularList<Cancion>();
		lstCuentas = new HashMap<String, Cuenta>();
		Administrador admin = new Administrador("pepe", "123", "code1");
		Usuario usuario = new Usuario("juan", "456", "@789");
		lstCuentas.put(usuario.getUsername(), usuario);
		lstCuentas.put(admin.getUsername(), admin);
	}

	public Storify(String nombre) {
		this.nombre = nombre;
		this.lstArtistas = new BinaryTree<Artista>();
		this.lstCanciones = new CircularList<Cancion>();
		this.lstCuentas = new HashMap<String, Cuenta>();
		Administrador admin = new Administrador("pepe", "123", "code1");
		Usuario usuario = new Usuario("juan", "456", "@789");
		lstCuentas.put(usuario.getUsername(), usuario);
		lstCuentas.put(admin.getUsername(), admin);
	}

	public Cuenta obtenerCuenta(String nombre) {
		return lstCuentas.get(nombre);
	}

	public Artista obtenerArtista(String nombre) {
		// Organizar arboles
		return null;
	}

	public Cancion obtenerCancion(String codigo) {
		for (Cancion cancion : lstCanciones) {
			if (cancion.getCodigo().equals(codigo)) {
				return cancion;
			}
		}
		return null;
	}

	public void agregarCancion(Cancion cancion, Artista artista) {
		artista.agregarCancion(cancion);
		cancion.getLstArtista().agregarfinal(artista);
		lstCanciones.add(cancion);
	}

	public void eliminarCancion(Cancion cancion) {
		ListaDoble<Artista> artistas = cancion.getLstArtista();
		for (Artista artista : artistas) {
			artista.eliminarCancion(cancion);
		}
		lstCanciones.remove(cancion);
	}

	public void agregarArtista(Artista artista) {
		lstArtistas.add(artista);
	}

	public void eliminarArtista(Artista artista) {
		// Organizar arboles
	}

	public void agregarUsuario(Usuario usuario) {
		lstCuentas.put(usuario.getUsername(), usuario);
	}

	public void eliminarUsuario(String nombreUsuario) {
		lstCuentas.remove(nombreUsuario);
	}

	public CircularList<Cancion> obtenerCancionesFiltradasUnion(CircularList<Cancion> lstCanciones, String[] datos) {
		CircularList<Cancion> obtainedList = new CircularList<Cancion>();
		if (!datos[0].equals("")) {
			obtainedList.setUnion(lstCanciones.filter(cancion -> cancion.getNombreCancion().equals(datos[0])));
		}
		if (!datos[1].equals("")) {
			obtainedList.setUnion(lstCanciones.filter(cancion -> cancion.getNombreAlbum().equals(datos[1])));
		}
		if (!datos[2].equals("")) {
			obtainedList.setUnion(lstCanciones.filter(cancion -> cancion.getAnio().equals(datos[2])));
		}
		if (!datos[3].equals("")) {
			obtainedList.setUnion(lstCanciones.filter(cancion -> cancion.getDuracion().equals(datos[3])));
		}
		if (!datos[4].equals("")) {
			obtainedList.setUnion(
					lstCanciones.filter(cancion -> cancion.getGenero().equals(Genero.getEstadoByString(datos[4]))));
		}
		return obtainedList;
	}

	public CircularList<Cancion> obtenerCancionesFiltradasInterseccion(CircularList<Cancion> lstCanciones,
			String[] datos) {
		CircularList<Cancion> obtainedList = lstCanciones.clone();
		if (!datos[0].equals("")) {
			obtainedList.setIntersection(lstCanciones.filter(cancion -> cancion.getNombreCancion().equals(datos[0])));
		}
		if (!datos[1].equals("")) {
			obtainedList.setIntersection(lstCanciones.filter(cancion -> cancion.getNombreAlbum().equals(datos[1])));
		}
		if (!datos[2].equals("")) {
			obtainedList.setIntersection(lstCanciones.filter(cancion -> cancion.getAnio().equals(datos[2])));
		}
		if (!datos[3].equals("")) {
			obtainedList.setIntersection(lstCanciones.filter(cancion -> cancion.getDuracion().equals(datos[3])));
		}
		if (!datos[4].equals("")) {
			obtainedList.setIntersection(
					lstCanciones.filter(cancion -> cancion.getGenero().equals(Genero.getEstadoByString(datos[4]))));
		}
		return obtainedList;
	}

	public CircularList<Cancion> obtenerCancionesFiltradasUnion(String lista, Usuario usuario, String[] datos) {
		CircularList<Cancion> canciones = null;
		switch (lista) {
		case "guardadas":
			canciones = obtenerCancionesFiltradasUnion(usuario.getLstCancionesGuardadas(), datos);
			break;
		case "favoritas":
			canciones = obtenerCancionesFiltradasUnion(usuario.getLstCancionesFavoritas(), datos);
			break;
		}
		return canciones;
	}

	public CircularList<Cancion> obtenerCancionesFiltradasInterseccion(String lista, Usuario usuario, String[] datos) {
		CircularList<Cancion> canciones = null;
		switch (lista) {
		case "guardadas":
			canciones = obtenerCancionesFiltradasInterseccion(usuario.getLstCancionesGuardadas(), datos);
			break;
		case "favoritas":
			canciones = obtenerCancionesFiltradasInterseccion(usuario.getLstCancionesFavoritas(), datos);
			break;
		}
		return canciones;
	}

	public boolean existeUsuario(String nombre, String contrasenia) {
		// TODO Auto-generated method stub
		for (HashMap.Entry<String, Cuenta> cuenta : lstCuentas.entrySet()) {
			String keyActual = cuenta.getKey();
			Cuenta cuentaActual = cuenta.getValue();
			if (keyActual.equals(nombre) && cuentaActual.verificarDatos(nombre, contrasenia)) {
				return true;
			}
		}
		return false;

	}

	public String obtenerTipoCuenta(String nombre, String contrasenia) {
		// TODO Auto-generated method stub
		String tipoCuenta = "";
		for (HashMap.Entry<String, Cuenta> cuenta : lstCuentas.entrySet()) {
			String keyActual = cuenta.getKey();
			Cuenta cuentaActual = cuenta.getValue();
			if (keyActual.equals(nombre) && cuentaActual.verificarDatos(nombre, contrasenia)) {
				if (cuentaActual instanceof Administrador) {
					tipoCuenta = "Administrador";
				} else {
					tipoCuenta = "Usuario";
				}
				break;
			}
		}
		return tipoCuenta;
	}

	public Cuenta obtenerCuentaDatos(String nombre, String contrasenia) {
		// TODO Auto-generated method stub
		Cuenta cuentaObtenida = null;
		for (HashMap.Entry<String, Cuenta> cuenta : lstCuentas.entrySet()) {
			String keyActual = cuenta.getKey();
			Cuenta cuentaActual = cuenta.getValue();
			if (keyActual.equals(nombre) && cuentaActual.verificarDatos(nombre, contrasenia)) {
				cuentaObtenida = cuentaActual;
				break;
			}
		}
		return cuentaObtenida;
	}

}
