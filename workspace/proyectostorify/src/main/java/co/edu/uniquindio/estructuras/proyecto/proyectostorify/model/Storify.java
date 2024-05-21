package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import java.util.HashMap;
import java.util.Iterator;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.binarytree.BinaryTree;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils.InterfazFXUtil;
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
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

	}

	public Storify(String nombre) {
		this.nombre = nombre;
	}

	public Cuenta obtenerCuenta(String nombre) {
		return lstCuentas.get(nombre);
	}

	public Artista obtenerArtista(String nombre) {
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

	public void agregarCancion(Cancion cancion) {
		lstCanciones.add(cancion);
	}

	public void eliminarCancion(Cancion cancion) {
		CircularList<Artista> artistas = cancion.getLstArtistas();
		for (Artista a : artistas) {
			a.eliminarCancion(cancion);
		}
		lstCanciones.remove(cancion);
	}

	public void agregarArtista(Artista artista) {
		System.out.println(lstArtistas.toCircularList().getSize());
		lstArtistas.add(artista);
		System.out.println(lstArtistas.toCircularList().getSize());
	}

	public void eliminarArtista(Artista artista) {
		lstArtistas.remove(artista);
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

	public void registrarUsuario(String nombre, String email, String contrasenia) {
		// TODO Auto-generated method stub
		Usuario newUsuario = new Usuario(nombre, contrasenia, email);
		agregarUsuario(newUsuario);

	}

	public CircularList<Artista> obtenerArtistas() {
		return getLstArtistas().toCircularList();
	}

	public Artista obtenerArtistaNombre(String nombre) {
		CircularList<Artista> listTemp = getLstArtistas().toCircularList();
		for (Artista a : listTemp) {
			if (a.getNombre().equalsIgnoreCase(nombre.trim())) {
				return a;
			}
		}
		return null;
	}

	public ListaDoble<Cancion> establecerCancionesArtista(Artista newArtista) {
		ListaDoble<Cancion> cancionesArtista = new ListaDoble<Cancion>();
		for (Cancion c : getLstCanciones()) {

			if (c.getLstArtistas().contains(newArtista)) {

				cancionesArtista.agregar(c);
			}
		}

		newArtista.setLstCanciones(cancionesArtista);
		return cancionesArtista;
	}

	public boolean existeCodigoCancion(String codigo) {
		return lstCanciones.filter(cancion->cancion.getCodigo().equals(codigo)).size()!=0;
	}

	public void agregarCancionesArtistas(CircularList<Artista> lstArtistas, Cancion cancion) {
		// TODO Auto-generated method stub
		for (Artista a : lstArtistas) {
			a.getLstCanciones().agregar(cancion);
		}
	}

	public void guardarPlayListUsuario(Cancion c, Usuario u) {
		// TODO Auto-generated method stub
		CircularList<Cancion> playlist = u.getLstCancionesGuardadas();
		if(!playlist.contains(c)) {
			playlist.add(c);
			InterfazFXUtil.mostrarMensaje("Cancion guardada", "Cancion Guardada En PlayList");
		}else {
			InterfazFXUtil.mostrarMensaje("Cancion ya guardada", "La cancion ya se encuentra en su PlayList");
		}
	}
	
	public void guardarCancionFavoritaUsuario(Cancion c, Usuario u) {
		// TODO Auto-generated method stub
		CircularList<Cancion> favoritas = u.getLstCancionesFavoritas();
		if(!favoritas.contains(c)) {
			favoritas.add(c);
			InterfazFXUtil.mostrarMensaje("Cancion guardada", "Cancion Guardada En PlayList");
		}else {
			InterfazFXUtil.mostrarMensaje("Cancion ya guardada", "La cancion ya se encuentra en su PlayList");
		}
	}

	public void eliminarCancionGuardada(Cancion cancion, Usuario usuario) {
		// TODO Auto-generated method stub
		usuario.getLstCancionesGuardadas().remove(cancion);

	}

	public void eliminarCancionFavorita(Cancion cancion, Usuario usuario) {
		// TODO Auto-generated method stub
		usuario.getLstCancionesFavoritas().remove(cancion);
	}

	public CircularList<Cancion> obtenerCancionesArtistas() {
		CircularList<Artista> artistas=lstArtistas.toCircularList();
		CircularList<Cancion> canciones=new CircularList<Cancion>();
		for (Artista artista : artistas) {
			canciones.setUnion(artista.getLstCanciones().toCircularList());
		}
		return canciones;
	}
	
	public int contarGeneroCancionesUsuarios(Genero genero) {
		int cantidad=0;
		for (Cuenta cuenta : lstCuentas.values()) {
			if (cuenta instanceof Usuario) {
				cantidad+=contarCancionesGenero(genero,((Usuario)cuenta).getLstCancionesGuardadas());
				cantidad+=contarCancionesGenero(genero,((Usuario)cuenta).getLstCancionesFavoritas());
			}
		}
		return cantidad;
	}
	
	public int contarCancionesGenero(Genero genero,CircularList<Cancion> canciones) {
		int cantidad=0;
		for (Cancion cancion : canciones) {
			if (cancion.getGenero()==genero) {
				cantidad++;
			}
		}
		return cantidad;
	}
	
	public CircularList<Genero> obtenerGenerosPopulares() {
		CircularList<Genero> generosPopulares=new CircularList<Genero>();
		int mayor=0;
		int cantidad;
		for (Genero genero : Genero.values()) {
			cantidad=contarGeneroCancionesUsuarios(genero);
			if (mayor>cantidad) {
				mayor=cantidad;
				generosPopulares.clear();
				generosPopulares.add(genero);
			} else if (mayor==cantidad) {
				generosPopulares.add(genero);
			}
		}
		return generosPopulares;
	}

}
