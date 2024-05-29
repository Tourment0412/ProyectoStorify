package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import java.io.Serializable;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

//@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Setter
@Getter
@ToString
public class Usuario extends Cuenta implements Serializable {

	@EqualsAndHashCode.Include
	@NonNull
	private String email;
	
	private CircularList<Cancion> lstCancionesGuardadas;
	
	private CircularList<Cancion> lstCancionesFavoritas;
	
	/**
	 * Metodo constructor
	 * @param username Nombre de usuario del usuario
	 * @param contrasenia Contrasenia del usuario
	 * @param email Email del usuario
	 */
	public Usuario(@NonNull String username, @NonNull String contrasenia, @NonNull String email) {
		super(username, contrasenia);
		this.email = email;
		lstCancionesGuardadas=new CircularList<Cancion>();
		lstCancionesFavoritas=new CircularList<Cancion>();
	}
	
	/**
	 * Metodo constructor
	 */
	public Usuario() {
		super();
		lstCancionesGuardadas=new CircularList<Cancion>();
		lstCancionesFavoritas=new CircularList<Cancion>();
	}
	
	/**
	 * Agrega una cancion en la lista de canciones guardadas
	 * @param cancion Cancion a agregar
	 */
	public void agregarCancionGuardada(Cancion cancion) {
		lstCancionesGuardadas.add(cancion);
	}
	
	/**
	 * Elimina una cancion de la lista de canciones guardadas
	 * @param cancion Cancion a eliminar
	 */
	public void eliminarCancionGuardada(Cancion cancion) {
		lstCancionesGuardadas.remove(cancion);
	}
	
	/**
	 * Agrega una cancion en la lista de canciones favoritas
	 * @param cancion Cancion a agregar
	 */
	public void agregarCancionFavorita(Cancion cancion) {
		lstCancionesFavoritas.add(cancion);
	}
	
	/**
	 * Elimina una cancion de la lista de canciones favoritas
	 * @param cancion Cancion a eliminar
	 */
	public void eliminarCancionFavorita(Cancion cancion) {
		lstCancionesFavoritas.remove(cancion);
	}

}
