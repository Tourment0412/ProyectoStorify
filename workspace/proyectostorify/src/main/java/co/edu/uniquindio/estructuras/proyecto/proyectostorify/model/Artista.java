package co.edu.uniquindio.estructuras.proyecto.proyectostorify.model;

import java.io.Serializable;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.doubleList.ListaDoble;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@ToString
public class Artista implements Comparable<Artista>,Serializable{
	
	private String codigo;
	private String nombre;
	private String nacionalidad;
	private boolean esGrupo;
	private ListaDoble<Cancion> lstCanciones;
	
	/**
	 * Metodo que se compara con otro artista
	 */
	@Override
	public int compareTo(Artista o) {
		return nombre.compareTo(o.getNombre());
		
	}
	
	/**
	 * Agrega una cancion al artista
	 * @param cancion Cancion a agregar
	 */
	public void agregarCancion(Cancion cancion) {
		cancion.getLstArtistas().add(this);
		lstCanciones.agregarfinal(cancion);
	}
	
	/**
	 * Elimina una cancion del artista 
	 * @param cancion Cancion a eliminar
	 */
	public void eliminarCancion(Cancion cancion) {
		lstCanciones.eliminar(cancion);
	}

	/**
	 * Metodod constructor
	 * @param codigo Codigo del artista
	 * @param nombre Nombre del artista
	 * @param nacionalidad Nacionalidad del artista
	 * @param esGrupo Si es un grupo o no del artista
	 * @param lstCanciones Lista de canciones del artista
	 */
	public Artista(String codigo, String nombre, String nacionalidad, boolean esGrupo,
			ListaDoble<Cancion> lstCanciones) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.esGrupo = esGrupo;
		this.lstCanciones = lstCanciones;
	}

	/**
	 * Metodo constructor
	 */
	public Artista() {
		super();
		lstCanciones=new ListaDoble<Cancion>();
	}
	
	


}
