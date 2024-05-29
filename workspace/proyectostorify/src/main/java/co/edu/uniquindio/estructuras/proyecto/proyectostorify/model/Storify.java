package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import java.io.Serializable;
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
public class Storify implements Serializable {

	@NonNull
	private String nombre;

	private BinaryTree<Artista> lstArtistas;

	private CircularList<Cancion> lstCanciones;

	private HashMap<String, Cuenta> lstCuentas;

	/**
	 * Metodo constructor
	 */
	public Storify() {
		lstArtistas=new BinaryTree<Artista>();
		lstCanciones=new CircularList<Cancion>();
		lstCuentas=new HashMap<String, Cuenta>();
	}
	
	/**
	 * Metodo constructor
	 * @param nombre Nombre de la tienda
	 */
	public Storify(String nombre) {
		this.nombre = nombre;
		lstArtistas=new BinaryTree<Artista>();
		lstCanciones=new CircularList<Cancion>();
		lstCuentas=new HashMap<String, Cuenta>();
	}
	
	/**
	 * Obtiene una cuenta con el nombre indicado
	 * @param nombre Nombre de la cuenta
	 * @return Cuenta obtenida
	 */
	public Cuenta obtenerCuenta(String nombre) {
		return lstCuentas.get(nombre);
	}
	
	/**
	 * Obtiene una cancion indicando su codigo
	 * @param codigo Codigo de la cancion
	 * @return Cancion encontrada
	 */
	public Cancion obtenerCancion(String codigo) {
		for (Cancion cancion : lstCanciones) {
			if (cancion.getCodigo().equals(codigo)) {
				return cancion;
			}
		}
		return null;
	}
	
	/**
	 * Agrega una cancion a la tienda
	 * @param cancion Cancion a agregar
	 */
	public void agregarCancion(Cancion cancion) {
		lstCanciones.add(cancion);
	}
	
	/**
	 * Elimina una cancion de la tienda
	 * @param cancion Cancion a eliminar
	 */
	public void eliminarCancion(Cancion cancion) {
		CircularList<Artista> artistas = cancion.getLstArtistas();
		for (Artista a : artistas) {
			a.eliminarCancion(cancion);
		}
		lstCanciones.remove(cancion);
	}
	
	/**
	 * Agrega un artista a la tienda
	 * @param artista Artista a agregar
	 */
	public void agregarArtista(Artista artista) {
		lstArtistas.add(artista);
	}
	
	/**
	 * Elimina una cancion de la tienda
	 * @param artista Artista a eliminar
	 */
	public void eliminarArtista(Artista artista) {
		lstArtistas.remove(artista);
	}
	
	/**
	 * Agrega un usuario a la tienda
	 * @param usuario Usuario a agregar
	 */
	public void agregarUsuario(Usuario usuario) {
		lstCuentas.put(usuario.getUsername(), usuario);
	}
	
	/**
	 * Elimina un usuario de la tienda
	 * @param nombreUsuario Nombre del usuario a eliminar
	 */
	public void eliminarUsuario(String nombreUsuario) {
		lstCuentas.remove(nombreUsuario);
	}
	
	/**
	 * Verifica si un usuario existe
	 * @param nombre Nombre del usuario a verificar
	 * @param contrasenia Contrasenia del usuario a verificar
	 * @return Respuesta de que si existe el usuario o no
	 */
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
	
	/**
	 * Obtener el tipo de cuenta en forma de cadena
	 * @param nombre Nombre de la cuenta
	 * @param contrasenia Contrasenia de la cuenta
	 * @return Cadena del tipo de usuario
	 */
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
	
	/**
	 * Obtiene una cuenta indicando sus datos
	 * @param nombre Nombre de la cuenta
	 * @param contrasenia Contrasenia de la cuenta
	 * @return Cuenta obtenida
	 */
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
	
	/**
	 * Registra un usuario
	 * @param nombre Nombre del usuario a registrar
	 * @param email Email del usuario a registrar
	 * @param contrasenia Contrasenia del usuario a registrar
	 */
	public void registrarUsuario(String nombre, String email, String contrasenia) {
		// TODO Auto-generated method stub
		Usuario newUsuario = new Usuario(nombre, contrasenia, email);
		agregarUsuario(newUsuario);

	}
	
	/**
	 * Obtiene la lista de artistas
	 * @return Lista de artistas
	 */
	public CircularList<Artista> obtenerArtistas() {
		return getLstArtistas().toCircularList();
	}
	
	/**
	 * Obtiene un artista a a partir de un nombre
	 * @param nombre Nombre del artista
	 * @return Artista obtenida
	 */
	public Artista obtenerArtistaNombre(String nombre) {
		CircularList<Artista> listTemp = getLstArtistas().toCircularList();
		for (Artista a : listTemp) {
			if (a.getNombre().equalsIgnoreCase(nombre.trim())) {
				return a;
			}
		}
		return null;
	}
	
	/**
	 * Obtiene las canciones de un artista
	 * @param newArtista Artista del que se quiere obtener las canciones
	 * @return Canciones del artista
	 */
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
	
	/**
	 * Verifica si existe una cancion con el codigo indicado
	 * @param codigo Codigo que se va a buscar
	 * @return Si existe una cancion con el codigo indicado o no
	 */
	public boolean existeCodigoCancion(String codigo) {
		return lstCanciones.filter(cancion->cancion.getCodigo().equals(codigo)).size()!=0;
	}
	
	/**
	 * Agrega una cancion a artistas
	 * @param lstArtistas Lista de artistas
	 * @param cancion Cancion a agregar
	 */
	public void agregarCancionesArtistas(CircularList<Artista> lstArtistas, Cancion cancion) {
		// TODO Auto-generated method stub
		for (Artista a : lstArtistas) {
			a.getLstCanciones().agregar(cancion);
		}
	}
	
	/**
	 * Guarda una cancion en las canciones guardadas o playlist
	 * @param c Cancion a guardar
	 * @param u Usuario donde se le va agregar la cancion
	 */
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
	
	/**
	 * Guarda una cancion en las canciones favoritas
	 * @param c Cancion a guardar
	 * @param u Usuario donde se le va agregar la cancion
	 */
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
	
	/**
	 * Elimina una cancion en la lsita de canciones guardadas
	 * @param cancion Cancion a eliminar
	 * @param usuario Usuario donde se va a eliminar la cancion
	 */
	public void eliminarCancionGuardada(Cancion cancion, Usuario usuario) {
		// TODO Auto-generated method stub
		usuario.getLstCancionesGuardadas().remove(cancion);

	}
	
	/**
	 * Elimina una cancion en la lsita de canciones favoritas
	 * @param cancion Cancion a eliminar
	 * @param usuario Usuario donde se va a eliminar la cancion
	 */
	public void eliminarCancionFavorita(Cancion cancion, Usuario usuario) {
		// TODO Auto-generated method stub
		usuario.getLstCancionesFavoritas().remove(cancion);
	}
	
	/**
	 * Obteiene las canciones de todos los artistas
	 * @return Lista de las canciones de todos los artistas
	 */
	public CircularList<Cancion> obtenerCancionesArtistas() {
		CircularList<Artista> artistas=lstArtistas.toCircularList();
		CircularList<Cancion> canciones=new CircularList<Cancion>();
		for (Artista artista : artistas) {
			canciones.setUnion(artista.getLstCanciones().toCircularList());
		}
		return canciones;
	}
	
	/**
	 * Cuenta cuantas canciones han sido guardadas y seleccionadas como favoritas de cierto genero 
	 * @param genero Genero indicado
	 * @return Cantidad de canciones guardades del genero indicado
	 */
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
	
	/**
	 * Cuenta cuantas canciones tienen el genero indicado
	 * @param genero Genero indicado
	 * @param canciones Canciones donde se van a contar la cantidad que aparece el genero
	 * @return Cantidad que aparece el genero
	 */
	public int contarCancionesGenero(Genero genero,CircularList<Cancion> canciones) {
		int cantidad=0;
		for (Cancion cancion : canciones) {
			if (cancion.getGenero()==genero) {
				cantidad++;
			}
		}
		return cantidad;
	}
	
	/**
	 * Obtiene los generos mas populares
	 * @return Generos mas populares
	 */
	public CircularList<Genero> obtenerGenerosPopulares() {
		CircularList<Genero> generosPopulares=new CircularList<Genero>();
		int mayor=0;
		int cantidad;
		for (Genero genero : Genero.values()) {
			cantidad=contarGeneroCancionesUsuarios(genero);
			if (mayor<cantidad) {
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
